package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.geometry.Pose;

public class BluePoses
{
    public static Pose bcStart = RedPoses.rcStart.getPose().mirror();
    public static Pose bcShoot = RedPoses.rcShoot.getPose().mirror();
    public static Pose bcCollect = RedPoses.rcCollect.getPose().mirror();
    public static Pose bcCollectFinish = RedPoses.rcCollectFinish.getPose().mirror();
    public static Pose bcEnd = RedPoses.rcEnd.getPose().mirror();
}
