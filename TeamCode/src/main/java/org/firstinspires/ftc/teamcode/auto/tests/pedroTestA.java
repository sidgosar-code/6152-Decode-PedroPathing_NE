package org.firstinspires.ftc.teamcode.auto.tests;

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
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous
@Configurable
public class pedroTestA extends OpMode
//{
//    private Follower follower;
//    public static Pose start = new Pose(30, 30, Math.toRadians(90)), end = new Pose(30, 35, Math.toRadians(90));
//
//
//    public static PathChain pathChain;
//
//    public void buildPaths()
//    {
//        pathChain = follower.pathBuilder()
//                .addPath(new BezierLine(start, end))
//                .setLinearHeadingInterpolation(start.getHeading(), end.getHeading(), 1.5)
//                .build();
//    }
//
//    public Command auto()
//    {
//        return follow(follower, pathChain, true);
//    }
//
//
//    @Override
//    public void init()
//    {
//        Scheduler.reset();
//        follower = Constants.createFollower(hardwareMap);
//        buildPaths();
//        follower.setStartingPose(start);
//    }
//
//    @Override
//    public void start()
//    {
//        Scheduler.schedule(auto());
//    }
//
//    @Override
//    public void loop()
//    {
//        follower.update();
//        Scheduler.execute();
//        // Feedback to Driver Hub for debugging
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.update();
//    }
//
//    @Override
//    public void stop()
//    {
//        org.firstinspires.ftc.teamcode.teleOp.tests.pedroTest.startingPose = follower.getPose();
//    }
//
//
//
//}
{
    private RobotBase robot;
    @Override
    public void init()
    {
        //robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        //robot.initAuto(hardwareMap);
        //Scheduler.reset();
        //robot.follower.setStartingPose(RedPoses.rcStart);
        //CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
        //robot.setTelemetry("init complete");
        telemetry.addLine("init complete");
        telemetry.update();

    }
    //    @Override
//    public void init_loop()
//    {
//        CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
//        robot.setTelemetry("motif", CurrentMotif.motif);
//    }
    @Override
    public void start()
    {
        //Scheduler.schedule(Commands.autoRoutine(Commands.RCSort3()));
        //robot.setTelemetry("start complete");
    }
    @Override
    public void loop()
    {
        //robot.update();
        //Scheduler.execute();
    }
    @Override
    public void stop()
    {
        //robot.setTelemetry("auto complete");
    }
}
