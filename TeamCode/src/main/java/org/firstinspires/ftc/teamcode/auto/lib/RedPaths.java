package org.firstinspires.ftc.teamcode.auto.lib;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.Path;

@Configurable
public class RedPaths
{
    public static Path RCBack = new Path(new BezierLine(RedPoses.rcStart, RedPoses.rcShoot));

    public static Path RCShootToCollect = new Path(new BezierLine(RedPoses.rcShoot, RedPoses.rcCollect));

    public static Path RCCollect = new Path(new BezierLine(RedPoses.rcCollect, RedPoses.rcCollectFinish));

    public static Path RCCollectToShoot = new Path(new BezierLine(RedPoses.rcCollectFinish, RedPoses.rcShoot));

    public static Path RCEnd = new Path(new BezierLine(RedPoses.rcShoot, RedPoses.rcEnd));

    public static Path RFForward = new Path(new BezierLine(RedPoses.rfStart, RedPoses.rfShoot));

    public static Path RFShootToCollect = new Path(new BezierLine(RedPoses.rfShoot, RedPoses.rfCollect));

    public static Path RFCollect = new Path(new BezierLine(RedPoses.rfCollect, RedPoses.rfCollectFinish));

    public static Path RFCollectToShoot = new Path(new BezierLine(RedPoses.rfCollectFinish, RedPoses.rfShoot));

    public static Path RFEnd = new Path(new BezierLine(RedPoses.rfShoot, RedPoses.rfEnd));









}
