package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.*;

import org.firstinspires.ftc.teamcode.systems.CommandLib;

public class Commands
{
    public static Command RCSort3()
    {
        return sequential(
                CommandLib.setShooter.with(CommandBuilder.moveBack),
                CommandLib.sort,
                CommandBuilder.shootToCollect.with(CommandLib.stopShooter),
                parallel(
                        CommandBuilder.collect,
                        parallel(
                                CommandLib.startIntake,
                                CommandLib.startIntakeTransferMode
                        )
                ),
                CommandBuilder.collectToShoot.with(
                        parallel(
                                CommandLib.stopIntake,
                                CommandLib.stopIntakeTransferMode
                        )
                ),
                CommandLib.fullTransfer,
                CommandLib.waitFeed,
                CommandLib.flick,
                CommandLib.waitFeed,
                CommandBuilder.end,
                CommandLib.stopAll
        );
    }



    public static Command autoRoutine(Command command)
    {
        return race(
                command,
                waitMs(28000)
        );
    }

}
