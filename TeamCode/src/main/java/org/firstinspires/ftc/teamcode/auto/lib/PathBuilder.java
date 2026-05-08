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
        moveToShoot = follower.pathBuilder()
                .addPath(BluePaths.BCBack)
                .setLinearHeadingInterpolation(BluePoses.bcStart.getHeading(), BluePoses.bcShoot.getHeading())
                .build();

        shootToCollect = follower.pathBuilder()
                .addPath(BluePaths.BCShootToCollect)
                .setLinearHeadingInterpolation(BluePoses.bcShoot.getHeading(), BluePoses.bcCollect.getHeading())
                .build();

        collect = follower.pathBuilder()
                .addPath(BluePaths.BCCollect)
                .setLinearHeadingInterpolation(BluePoses.bcCollect.getHeading(), BluePoses.bcCollectFinish.getHeading())
                .build();
        collectToShoot = follower.pathBuilder()
                .addPath(BluePaths.BCCollectToShoot)
                .setLinearHeadingInterpolation(BluePoses.bcCollectFinish.getHeading(), BluePoses.bcShoot.getHeading())
                .build();
        end = follower.pathBuilder()
                .addPath(BluePaths.BCEnd)
                .setLinearHeadingInterpolation(BluePoses.bcShoot.getHeading(), BluePoses.bcEnd.getHeading())
                .build();
    }

    public void buildPathsRF()
    {
        moveToShoot = follower.pathBuilder()
                .addPath(RedPaths.RFForward)
                .setLinearHeadingInterpolation(RedPoses.rfStart.getHeading(), RedPoses.rfShoot.getHeading())
                .build();

        shootToCollect = follower.pathBuilder()
                .addPath(RedPaths.RFShootToCollect)
                .setLinearHeadingInterpolation(RedPoses.rfShoot.getHeading(), RedPoses.rfCollect.getHeading())
                .build();

        collect = follower.pathBuilder()
                .addPath(RedPaths.RFCollect)
                .setLinearHeadingInterpolation(RedPoses.rfCollect.getHeading(), RedPoses.rfCollectFinish.getHeading())
                .build();
        collectToShoot = follower.pathBuilder()
                .addPath(RedPaths.RFCollectToShoot)
                .setLinearHeadingInterpolation(RedPoses.rfCollectFinish.getHeading(), RedPoses.rfShoot.getHeading())
                .build();
        end = follower.pathBuilder()
                .addPath(RedPaths.RFEnd)
                .setLinearHeadingInterpolation(RedPoses.rfShoot.getHeading(), RedPoses.rfEnd.getHeading())
                .build();
    }

    public void buildPathsBF()
    {
        moveToShoot = follower.pathBuilder()
                .addPath(BluePaths.BFForward)
                .setLinearHeadingInterpolation(BluePoses.bfStart.getHeading(), BluePoses.bfShoot.getHeading())
                .build();

        shootToCollect = follower.pathBuilder()
                .addPath(BluePaths.BFShootToCollect)
                .setLinearHeadingInterpolation(BluePoses.bfShoot.getHeading(), BluePoses.bfCollect.getHeading())
                .build();

        collect = follower.pathBuilder()
                .addPath(BluePaths.BFCollect)
                .setLinearHeadingInterpolation(BluePoses.bfCollect.getHeading(), BluePoses.bfCollectFinish.getHeading())
                .build();
        collectToShoot = follower.pathBuilder()
                .addPath(BluePaths.BFCollectToShoot)
                .setLinearHeadingInterpolation(BluePoses.bfCollectFinish.getHeading(), BluePoses.bfShoot.getHeading())
                .build();
        end = follower.pathBuilder()
                .addPath(BluePaths.BFEnd)
                .setLinearHeadingInterpolation(BluePoses.bfShoot.getHeading(), BluePoses.bfEnd.getHeading())
                .build();
    }









}
