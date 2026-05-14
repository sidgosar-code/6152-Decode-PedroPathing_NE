package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.*;

import org.firstinspires.ftc.teamcode.systems.CommandLib;

import java.nio.file.Path;

public class Commands
{
    public static Command RCSort3()
    {
        return sequential(
                CommandLib.setShooter,
                CommandBuilder.moveToShoot,
                waitMs(3000),
                CommandLib.fullTransfer,
                CommandLib.sort,
                waitMs(3000),
                CommandBuilder.shootToCollect.with(CommandLib.stopAll),
//                        parallel(
//                                CommandBuilder.collect,
//                                parallel(
//                                        CommandLib.startIntake,
//                                        CommandLib.startIntakeTransferMode
//                                )
//                        ),
//                        CommandBuilder.collectToShoot.with(
//                                parallel(
//                                        CommandLib.stopIntake,
//                                        CommandLib.stopIntakeTransferMode
//                                )
//                        ),
//                        CommandLib.fullTransfer,
//                        CommandLib.waitFeed,
//                        CommandLib.flick,
//                        CommandLib.waitFeed,
//                        CommandBuilder.end,
                CommandLib.stopAll
        );
    }

    public static Command intake()
    {
        return sequential(
                CommandLib.vindexerA.with(CommandLib.switchMode),
                waitMs(1000),
                parallel(
                    CommandBuilder.collect,
                    CommandLib.startIntake,
                    CommandLib.startIntakeTransferMode
                )
        );
    }


    public static Command RFSort3()
    {
        return sequential(
                //CommandLib.setShooter,
                CommandBuilder.moveToShoot,
                waitMs(3000),
                CommandLib.fullTransfer,
                CommandLib.sort,
                waitMs(3000),
                //CommandBuilder.shootToCollect.with(CommandLib.stopAll),
//                        parallel(
//                                CommandBuilder.collect,
//                                parallel(
//                                        CommandLib.startIntake,
//                                        CommandLib.startIntakeTransferMode
//                                )
//                        ),
//                        CommandBuilder.collectToShoot.with(
//                                parallel(
//                                        CommandLib.stopIntake,
//                                        CommandLib.stopIntakeTransferMode
//                                )
//                        ),
//                        CommandLib.fullTransfer,
//                        CommandLib.waitFeed,
//                        CommandLib.flick,
//                        CommandLib.waitFeed,
//                        CommandBuilder.end,
                CommandLib.stopAll
        );
    }


    public static Command shoot()
    {
        return null;
    }



    public static Command autoRoutine(Command command)
    {
        return race(
                command,
                waitMs(28000)
        );
    }

}
