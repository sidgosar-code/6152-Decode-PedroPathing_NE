package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "blueTeleOp", group = "real OpModes")
public class blueTeleOp extends OpMode
{
    public RobotBase robot;
    double x, y, rotation;

    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, true);
    }
    public void loop()
    {
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;

        if(gamepad1.right_trigger > 0) robot.movement.slowTeleOpDrive(x, y, rotation);
        else robot.movement.teleOpDrive(x, y, rotation);


        if(gamepad2.rightBumperWasPressed())
        {
            robot.shooter.setShooter();
            robot.setTelemetry("shooter velocity", robot.shooter.shooter.getVelocity());
        }

        if(gamepad2.leftBumperWasPressed()) robot.shooter.stopShooter();

        if(gamepad2.aWasPressed()) robot.sorting.vindexerA();
        if(gamepad2.bWasReleased()) robot.sorting.vindexerB();
        if(gamepad2.xWasPressed()) robot.sorting.vindexerC();

        if(gamepad2.left_trigger>0) robot.intake.startIntake();
        else robot.intake.stopIntake();

        if(gamepad2.yWasPressed()) robot.transfer.flickOne();
        if(gamepad2.dpad_down) robot.transfer.fullTransfer();
        else robot.transfer.stopAll();




    }
}
