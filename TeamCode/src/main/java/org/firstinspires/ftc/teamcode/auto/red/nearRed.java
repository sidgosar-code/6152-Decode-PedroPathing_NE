package org.firstinspires.ftc.teamcode.auto.red;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RobotBase;
import org.firstinspires.ftc.teamcode.systems.Alliance;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.*;

@Autonomous
public class nearRed extends LinearOpMode
{
    public void runOpMode()
    {
        //Scheduler.reset();
        RobotBase robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        boolean done = robot.timer.seconds() > 30;
        /*
        Command startShooter = Command.build()
                .setStart(() -> robot.timer.reset())
                .setExecute(() -> robot.shooter.setShooter())
                .setDone(() -> done);
        Command transfer = Command.build()
                .setStart(() -> robot.timer.reset())
                .setExecute(() -> robot.transfer.fullTransfer())
                .setDone(() ->done);

         */

        while(opModeIsActive())
        {
            robot.shooter.setShooter();
            robot.transfer.fullTransfer();
            robot.sorting.vindexerABC();
            break;
        }
    }
}
