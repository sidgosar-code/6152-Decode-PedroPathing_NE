package org.firstinspires.ftc.teamcode.auto.lib;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

@Configurable
public class RedPoses
{
    public static Pose rcStart = new Pose(123, 123, rad(45));
    public static Pose rcShoot = new Pose (83, 83, rad(45));

    public static Pose rcCollect = new Pose(103, 83, rad(0));

    public static Pose rcCollectFinish = new Pose(112, 83, rad(0));
    public static Pose rcEnd = new Pose(85, 115, rad(45));

    public static Pose rfStart;




    private static double rad(double deg)
    {
        return Math.toRadians(deg);
    }

    public static Pose mirror(Pose pose)
    {
        return new Pose(144-pose.getX(), pose.getY(), Math.toRadians(180) - pose.getHeading());
    }



}
