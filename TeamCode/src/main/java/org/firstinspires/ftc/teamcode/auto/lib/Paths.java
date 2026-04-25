package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.Path;

public class Paths extends Poses
{
    public static Path RCBack = new Path(new BezierLine(rcStart, rcShoot));

    public static Path RCShootToCollect = new Path(new BezierLine(rcShoot, rcCollect));

    public static Path RCCollect = new Path(new BezierLine(rcCollect, rcCollectFinish));

    public static Path RCCollectToShoot = new Path(new BezierLine(rcCollectFinish, rcShoot));

    public static Path RCEnd = new Path(new BezierLine(rcShoot, rcEnd));



}
