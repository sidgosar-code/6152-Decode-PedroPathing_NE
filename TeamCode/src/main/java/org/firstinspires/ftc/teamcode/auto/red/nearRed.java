package org.firstinspires.ftc.teamcode.auto.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.systems.Shooter;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import static com.pedropathing.ivy.Scheduler.schedule;

import java.util.List;

@Autonomous
public class nearRed extends LinearOpMode
{
    public RobotBase robot;
    public static double wait = 4000;
    public static double strafe = 20;
    @Override
    public void runOpMode()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);

        //Scheduler.reset();
        //boolean done = robot.timer.seconds() > 30;
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

        while(!isStarted() && !isStopRequested())
        {
            robot.aprilTagUtility.getObeliskTag();
        }


        while(opModeIsActive())
        {
            robot.shooter.setShooter();
            robot.transfer.fullTransfer();
            robot.waitTime(Shooter.toSpeed);
            robot.sorting.vindexerABC();
            robot.waitTime(wait);
            robot.movement.strafe(strafe, 0.5);
            break;
        }
    }
}
