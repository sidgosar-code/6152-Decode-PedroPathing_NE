package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotBase;

@TeleOp(name = "blueTeleOp", group = "real OpModes")
public class blueTeleOp extends OpMode
{
    public RobotBase robot;
    double x, y, rotation;
    boolean shootingPosition;

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

        shootingPosition = gamepad2.right_trigger>0;

        if(gamepad1.right_trigger > 0) robot.movement.slowTeleOpDrive(x, y, rotation);
        else robot.movement.teleOpDrive(x, y, rotation);

        if(gamepad2.rightStickButtonWasPressed()) robot.transfer.switchMode();

        if(gamepad2.rightBumperWasPressed())
        {
            robot.shooter.setShooter();
            robot.setTelemetry("shooter velocity", robot.shooter.shooter.getVelocity());
        }

        if(gamepad2.leftBumperWasPressed()) robot.shooter.stopShooter();

        if(gamepad2.aWasPressed()) robot.sorting.vindexerA();
        if(!shootingPosition && gamepad2.bWasPressed()) robot.sorting.vindexerB();
        if(!shootingPosition && gamepad2.xWasPressed()) robot.sorting.vindexerC();

        if(gamepad2.left_trigger>0) robot.intake.startIntake();
        else robot.intake.stopIntake();

        if(gamepad2.yWasPressed()) robot.transfer.flickOne();
        if(gamepad2.dpad_down) robot.transfer.fullTransfer();
        else robot.transfer.stopAll();
        if(shootingPosition && gamepad2.bWasPressed()) robot.shoot3();
        if(shootingPosition && gamepad2.bWasPressed()) robot.farZoneShoot3();
        if(gamepad2.dpadUpWasPressed()) robot.stopALl();    }
}
