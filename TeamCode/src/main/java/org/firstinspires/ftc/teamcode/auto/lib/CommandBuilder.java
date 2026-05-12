package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.pedro.PedroCommands.*;

import org.firstinspires.ftc.teamcode.systems.Intake;

public class CommandBuilder
{
    public Follower follower;
    public static Command moveToShoot, shootToCollect, collect, collectToShoot, end;

    public CommandBuilder(PathBuilder pathBuilder)
    {
        this.follower = pathBuilder.follower;
        //pathBuilder.buildPathsRC();
        buildCommands();
    }

    public void buildCommands()
    {
        moveToShoot = follow(follower, PathBuilder.moveToShoot);
        shootToCollect = follow(follower, PathBuilder.shootToCollect);
        collect = follow(follower, PathBuilder.collect, Intake.collectPower);
        collectToShoot = follow(follower, PathBuilder.collectToShoot);
        end = follow(follower, PathBuilder.end);
    }
}
