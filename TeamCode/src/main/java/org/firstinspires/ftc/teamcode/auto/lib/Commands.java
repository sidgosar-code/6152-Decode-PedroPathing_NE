package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.groups.Groups.*;

import org.firstinspires.ftc.teamcode.systems.RobotBase;

public class Commands
{
    public static Command autoRoutine()
    {
        return sequential(
                RobotBase.CommandLib.setShooter,
                CommandBuilder.moveBack,
                RobotBase.CommandLib.sort,
                CommandBuilder.shootToCollect,
                CommandBuilder.collect,
                CommandBuilder.collectToShoot,
                RobotBase.CommandLib.sort
        );
    }
}
