package org.firstinspires.ftc.teamcode.auto.lib;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

@Configurable
public class BluePoses
{
    public static Pose bcStart = RedPoses.rcStart.getPose().mirror();
    public static Pose bcShoot = RedPoses.rcShoot.getPose().mirror();
    public static Pose bcCollect = RedPoses.rcCollect.getPose().mirror();
    public static Pose bcCollectFinish = RedPoses.rcCollectFinish.getPose().mirror();
    public static Pose bcEnd = RedPoses.rcEnd.getPose().mirror();

    public static Pose bfStart = RedPoses.rfStart.getPose().mirror();

    public static Pose bfShoot = RedPoses.rfShoot.getPose().mirror();
    public static Pose bfCollect = RedPoses.rfCollect.getPose().mirror();
    public static Pose bfCollectFinish = RedPoses.rfCollectFinish.getPose().mirror();

    public static Pose bfEnd = RedPoses.rfEnd.getPose().mirror();


}
