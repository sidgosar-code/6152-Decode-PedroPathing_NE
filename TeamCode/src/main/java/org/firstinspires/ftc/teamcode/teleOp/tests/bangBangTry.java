package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.systems.Shooter;

@TeleOp
public class bangBangTry extends OpMode
{
    public RobotBase robot;
    double x, y, rotation;

    boolean transfer;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        transfer = false;
    }
    public void loop()
    {
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;

        //this next section is a test to see if the transfer thing will work
        if(gamepad2.dpadUpWasPressed())
        {
            transfer = !transfer;
        }

        if(transfer) robot.transfer.fullTransfer(robot.shooter.curTarget);


        //turret
        if(gamepad1.aWasPressed()) robot.turret.center();
        if(gamepad1.rightBumperWasPressed()) robot.turret.incRight();
        if(gamepad1.leftBumperWasPressed()) robot.turret.incLeft();
        if(gamepad1.right_trigger>0)robot.turret.crIncRight();
        if(gamepad1.left_trigger>0) robot.turret.crIncLeft();


        //movement
        if(gamepad1.y) robot.movement.slowTeleOpDrive(x, y, rotation);
        else robot.movement.teleOpDrive(x, y, rotation);

        //switchMode
        if(gamepad2.rightStickButtonWasPressed()) robot.transfer.switchMode();

        //shooter
        if(gamepad2.right_stick_y>0)
        {
            if(gamepad2.rightBumperWasPressed())
            {
                robot.shooter.bangSet(Shooter.maxVelocity);
                robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
            }
        }
        else if(gamepad2.right_stick_y<0)
        {
            if(gamepad2.rightBumperWasPressed())
            {
                robot.shooter.bangSet(Shooter.minVelocity);
                robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
            }
        }
        else if(gamepad2.rightBumperWasPressed())
        {
            robot.shooter.bangSet(Shooter.midVelocity);
            robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
        }
        if(gamepad2.leftBumperWasPressed()) robot.shooter.stopShooter();

        //sorting
        if(gamepad2.aWasPressed()) robot.sorting.vindexerA();
        if(gamepad2.bWasPressed()) robot.sorting.vindexerB();
        if(gamepad2.xWasPressed()) robot.sorting.vindexerC();

        //intake
        if(gamepad2.left_trigger>0) {
            if(gamepad2.left_stick_y<-.5) {robot.intake.outTake();} //might want to change control
            else robot.intake.startIntake();
        }
        else robot.intake.stopIntake();

        //transfer
        if(gamepad2.yWasPressed()) robot.transfer.flickOne();
        if(gamepad2.dpad_left) robot.transfer.fullTransfer();
        else if(gamepad2.left_trigger==0) robot.transfer.stopAll();
        //if(robot.transfer.spamMode && gamepad2.bWasPressed()) robot.shoot3();
        //if(robot.transfer.spamMode && gamepad2.xWasPressed()) robot.farZoneShoot3();
        if(gamepad2.dpadUpWasPressed()) robot.stopAll(); //gonna have to change this

        //hood
        if(gamepad2.dpadDownWasPressed()) robot.turret.hoodLow();
        if(gamepad2.dpadUpWasPressed()) robot.turret.hoodHigh();
    }
}
