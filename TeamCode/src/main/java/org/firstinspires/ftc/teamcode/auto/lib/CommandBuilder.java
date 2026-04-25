package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.pedro.PedroCommands.*;

public class CommandBuilder
{
    public Follower follower;
    public static Command moveBack, shootToCollect, collect, collectToShoot, end;

    public CommandBuilder(PathBuilder pathBuilder)
    {
        this.follower = pathBuilder.follower;
        pathBuilder.buildPathsRed();
    }

    public void buildCommandsRed()
    {
        moveBack = follow(follower, PathBuilder.moveBack);
        shootToCollect = follow(follower, PathBuilder.shootToCollect);
        collect = follow(follower, PathBuilder.collect);
        collectToShoot = follow(follower, PathBuilder.collectToShoot);
        end = follow(follower, PathBuilder.end);
    }
}
