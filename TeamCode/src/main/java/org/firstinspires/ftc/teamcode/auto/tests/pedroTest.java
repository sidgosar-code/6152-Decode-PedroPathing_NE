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

@Autonomous
@Configurable
public class pedroTest extends OpMode
{
    private Follower follower;
    public static Pose start = new Pose(30, 30, 90), end = new Pose(30, 35, 90);

    public static Path path = new Path(new BezierLine(start, end));

    public static PathChain pathChain;

    public void buildPaths()
    {
        pathChain = follower.pathBuilder()
                .addPath(path)
                .setLinearHeadingInterpolation(start.getHeading(), end.getHeading())
                .build();
    }

    public Command auto()
    {
        return follow(follower, pathChain, true);
    }


    @Override
    public void init()
    {
        Scheduler.reset();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(start);
    }

    @Override
    public void start()
    {
        Scheduler.schedule(auto());
    }

    @Override
    public void loop()
    {
        follower.update();
        Scheduler.execute();
        // Feedback to Driver Hub for debugging
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }



}
