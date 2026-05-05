package org.firstinspires.ftc.teamcode.auto.lib;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.paths.PathChain;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Configurable

public class PathBuilder
{
    public Follower follower;

    public static PathChain moveToShoot, shootToCollect, collect, collectToShoot, end;

    //public static PathChain

    public PathBuilder(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        follower = Constants.createFollower(hardwareMap);
        buildPathsRC();
    }

    public void buildPathsRC()
    {
        moveToShoot = follower.pathBuilder()
                .addPath(RedPaths.RCBack)
                .setLinearHeadingInterpolation(RedPoses.rcStart.getHeading(), RedPoses.rcShoot.getHeading())
                .build();

        shootToCollect = follower.pathBuilder()
                .addPath(RedPaths.RCShootToCollect)
                .setLinearHeadingInterpolation(RedPoses.rcShoot.getHeading(), RedPoses.rcCollect.getHeading())
                .build();

        collect = follower.pathBuilder()
                .addPath(RedPaths.RCCollect)
                .setLinearHeadingInterpolation(RedPoses.rcCollect.getHeading(), RedPoses.rcCollectFinish.getHeading())
                .build();
        collectToShoot = follower.pathBuilder()
                .addPath(RedPaths.RCCollectToShoot)
                .setLinearHeadingInterpolation(RedPoses.rcCollectFinish.getHeading(), RedPoses.rcShoot.getHeading())
                .build();
        end = follower.pathBuilder()
                .addPath(RedPaths.RCEnd)
                .setLinearHeadingInterpolation(RedPoses.rcShoot.getHeading(), RedPoses.rcEnd.getHeading())
                .build();
    }

    public void buildPathsBC()
    {

    }

    public void buildPathsRF()
    {

    }

    public void buildPathsBF()
    {

    }






}
