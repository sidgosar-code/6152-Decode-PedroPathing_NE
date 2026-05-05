package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.paths.PathChain;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


public class PathBuilder extends RedPaths
{
    public Follower follower;

    public static PathChain moveBack, shootToCollect, collect, collectToShoot, end;

    public PathBuilder(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        follower = Constants.createFollower(hardwareMap);
        buildPathsRed();
    }

    public void buildPathsRed()
    {
        moveBack = follower.pathBuilder()
                .addPath(RCBack)
                //.setLinearHeadingInterpolation(rcStart.getHeading(), rcShoot.getHeading())
                .build();

        shootToCollect = follower.pathBuilder()
                .addPath(RCShootToCollect)
                //.setLinearHeadingInterpolation(rcShoot.getHeading(), rcCollect.getHeading())
                .build();

        collect = follower.pathBuilder()
                .addPath(RCCollect)
                .build();
        collectToShoot = follower.pathBuilder()
                .addPath(RCCollectToShoot)
                .build();
        end = follower.pathBuilder()
                .addPath(RCEnd)
                .build();
    }

    public void buildPathsBlue()
    {

    }







}
