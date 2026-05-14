package org.firstinspires.ftc.teamcode.auto.lib;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.*;

import org.firstinspires.ftc.teamcode.systems.CommandLib;
import org.firstinspires.ftc.teamcode.systems.Transfer;

public class Commands
{
    public static Command cSort3()
    {
        return sequential(
                CommandLib.setShooter,
                CommandBuilder.moveToShoot,
                waitMs(Transfer.waitShoot),
                CommandLib.fullTransfer,
                CommandLib.sort,
                waitMs(Transfer.sortTime),
                CommandBuilder.end.with(CommandLib.stopAll),
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


    public static Command fSort3()
    {
        return sequential(
                //CommandLib.setShooter,
                CommandBuilder.moveToShoot,
                waitMs(Transfer.waitShoot),
                CommandLib.fullTransfer,
                CommandLib.sort,
                waitMs(Transfer.sortTime),
                CommandBuilder.end.with(CommandLib.stopAll),
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
