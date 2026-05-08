package org.firstinspires.ftc.teamcode.auto.lib;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.Path;

@Configurable
public class BluePaths
{
    public static Path BCBack = new Path(new BezierLine(BluePoses.bcStart, BluePoses.bcShoot));
    public static Path BCShootToCollect = new Path(new BezierLine(BluePoses.bcShoot, BluePoses.bcCollect));
    public static Path BCCollect = new Path(new BezierLine(BluePoses.bcCollect, BluePoses.bcCollectFinish));
    public static Path BCCollectToShoot = new Path(new BezierLine(BluePoses.bcCollectFinish, BluePoses.bcShoot));
    public static Path BCEnd = new Path(new BezierLine(BluePoses.bcShoot, BluePoses.bcEnd));
    public static Path BFForward = new Path(new BezierLine(BluePoses.bfStart, BluePoses.bfShoot));
    public static Path BFShootToCollect = new Path(new BezierLine(BluePoses.bfShoot, BluePoses.bfCollect));
    public static Path BFCollect = new Path(new BezierLine(BluePoses.bfCollect, BluePoses.bfCollectFinish));
    public static Path BFCollectToShoot = new Path(new BezierLine(BluePoses.bfCollectFinish, BluePoses.bfShoot));
    public static Path BFEnd = new Path(new BezierLine(BluePoses.bfShoot, BluePoses.bfEnd));

}
