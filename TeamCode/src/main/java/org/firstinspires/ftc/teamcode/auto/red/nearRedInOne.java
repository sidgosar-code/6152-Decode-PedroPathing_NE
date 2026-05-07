package org.firstinspires.ftc.teamcode.auto.red;

import static com.pedropathing.ivy.commands.Commands.instant;
//import static com.pedropathing.ivy.commands.Commands.race;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.systems.Transfer;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AprilTagUtility;
import org.firstinspires.ftc.teamcode.util.CurrentMotif;

@Autonomous(name = "nearRedInOne", group = "new")
@Configurable
public class nearRedInOne extends OpMode {
    public RobotBase robot;
    public Follower follower;

    // Poses (from RedPoses)
    public static Pose rcStart = new Pose(123, 123, Math.toRadians(45));
    public static Pose rcShoot = new Pose(83, 83, Math.toRadians(45));
    public static Pose rcCollect = new Pose(103, 83, Math.toRadians(0));
    public static Pose rcCollectFinish = new Pose(112, 83, Math.toRadians(0));
    public static Pose rcEnd = new Pose(85, 115, Math.toRadians(45));

    // Paths (from RedPaths)
    public Path RCBack, RCShootToCollect, RCCollect, RCCollectToShoot, RCEnd;

    // PathChains (from PathBuilder)
    public PathChain moveToShoot, shootToCollect, collect, collectToShoot, end;

    // Path Commands (from CommandBuilder)
    public static Command moveToShootC, shootToCollectC, collectC, collectToShootC, endC;

    // Subsystem Commands (from CommandLib)
    public Command setShooter, stopShooter, startIntake, stopIntake, 
                   startIntakeTransferMode, stopIntakeTransferMode, 
                   sort, flick, waitFeed, fullTransfer, stopAll;

    @Override
    public void init() {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        
        // Ensure AprilTagUtility is initialized before subsystems to avoid NPEs
        robot.aprilTagUtility = new AprilTagUtility(hardwareMap, telemetry);
        robot.initHardwareAuto(hardwareMap);
        
        follower = Constants.createFollower(hardwareMap);
        robot.follower = follower; // Link for robot.update()
        
        follower.setStartingPose(rcStart);
        Scheduler.reset();

        buildPaths();
        buildCommands();
        
        // Motif detection (requires initialized AprilTagUtility)
        CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
        robot.setTelemetry("motif", CurrentMotif.motif);
    }

    public void buildPaths() {
        RCBack = new Path(new BezierLine(rcStart, rcShoot));
        RCShootToCollect = new Path(new BezierLine(rcShoot, rcCollect));
        RCCollect = new Path(new BezierLine(rcCollect, rcCollectFinish));
        RCCollectToShoot = new Path(new BezierLine(rcCollectFinish, rcShoot));
        RCEnd = new Path(new BezierLine(rcShoot, rcEnd));

        moveToShoot = follower.pathBuilder()
                .addPath(RCBack)
                .setLinearHeadingInterpolation(rcStart.getHeading(), rcShoot.getHeading())
                .build();

        shootToCollect = follower.pathBuilder()
                .addPath(RCShootToCollect)
                .setLinearHeadingInterpolation(rcShoot.getHeading(), rcCollect.getHeading())
                .build();

        collect = follower.pathBuilder()
                .addPath(RCCollect)
                .setLinearHeadingInterpolation(rcCollect.getHeading(), rcCollectFinish.getHeading())
                .build();

        collectToShoot = follower.pathBuilder()
                .addPath(RCCollectToShoot)
                .setLinearHeadingInterpolation(rcCollectFinish.getHeading(), rcShoot.getHeading())
                .build();

        end = follower.pathBuilder()
                .addPath(RCEnd)
                .setLinearHeadingInterpolation(rcShoot.getHeading(), rcEnd.getHeading())
                .build();
    }

    public void buildCommands() {
        // Path following commands ( PedroCommands.follow )
        moveToShootC = follow(follower, moveToShoot);
        shootToCollectC = follow(follower, shootToCollect);
        collectC = follow(follower, collect, 0.3);
        collectToShootC = follow(follower, collectToShoot);
        endC = follow(follower, end);

        // Subsystem commands ( wrapping robot methods )
        setShooter = instant(() -> robot.shooter.setShooter()).requiring(robot.shooter.s);
        stopShooter = instant(() -> robot.shooter.stopShooter()).requiring(robot.shooter.s);
        startIntake = instant(() -> robot.intake.startIntake()).requiring(robot.intake.i);
        stopIntake = instant(() -> robot.intake.stopIntake()).requiring(robot.intake.i);
        startIntakeTransferMode = instant(() -> robot.transfer.startIntakeMode());
        stopIntakeTransferMode = instant(() -> robot.transfer.stopAll());
        sort = instant(() -> robot.sorting.sort());
        flick = instant(() -> robot.transfer.flickOne());
        waitFeed = waitMs(Transfer.feedWait);
        fullTransfer = instant(() -> robot.transfer.fullTransfer());
        stopAll = instant(() -> robot.stopAll());
    }

    public Command RCSort3() {
        return sequential(
                setShooter.with(moveToShootC).with(fullTransfer),
                sort,
                shootToCollectC.with(stopAll),
                parallel(
                        collectC,
                        parallel(
                                startIntake,
                                startIntakeTransferMode
                        )
                ),
                collectToShootC.with(
                        parallel(
                                stopIntake,
                                stopIntakeTransferMode
                        )
                ),
                fullTransfer,
                waitFeed,
                flick,
                waitFeed,
                endC,
                stopAll
        );
    }

    @Override
    public void start() {
        Scheduler.schedule(RCSort3());
    }

    @Override
    public void loop() {
        robot.update();
        Scheduler.execute();
    }

    @Override
    public void stop() {
        robot.setTelemetry("auto complete");
    }
}
