package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.groups.Groups.*;

import org.firstinspires.ftc.teamcode.systems.CommandLib;
import org.firstinspires.ftc.teamcode.systems.RobotBase;

public class Commands
{
    public static Command autoRoutine()
    {
        return sequential(
                CommandLib.setShooter,
                CommandBuilder.moveBack,
                CommandLib.sort,
                CommandBuilder.shootToCollect,
                CommandBuilder.collect,
                CommandBuilder.collectToShoot,
                CommandLib.sort
        );
    }
}
