package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.pedro.PedroCommands.*;

public class CommandBuilder
{
    public Follower follower;
    public static Command moveToShoot, shootToCollect, collect, collectToShoot, end;

    public CommandBuilder(PathBuilder pathBuilder)
    {
        this.follower = pathBuilder.follower;
        pathBuilder.buildPathsRC();
        buildCommandsRed();
    }

    public void buildCommandsRed()
    {
        moveToShoot = follow(follower, PathBuilder.moveToShoot);
        shootToCollect = follow(follower, PathBuilder.shootToCollect);
        collect = follow(follower, PathBuilder.collect, 0.3);
        collectToShoot = follow(follower, PathBuilder.collectToShoot);
        end = follow(follower, PathBuilder.end);
    }
}
