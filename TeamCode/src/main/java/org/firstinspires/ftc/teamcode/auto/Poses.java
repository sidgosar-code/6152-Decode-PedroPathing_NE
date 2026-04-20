package org.firstinspires.ftc.teamcode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

@Configurable

public class Poses
{
    public static Pose rcStart = new Pose(123, 123, rad(45));
    public static Pose rcShoot = new Pose (83, 83, rad(45));

    public static Pose rcCollect = new Pose(103, 83, rad(0));

    public static Pose rcCollectFinish = new Pose(112, 83, rad(0));

    private static double rad(double deg)
    {
        return Math.toRadians(deg);
    }



}
