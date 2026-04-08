package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotBase;

@TeleOp(name = "redTeleOp", group = "real OpModes")
public class redTeleOp extends OpMode
{
    public RobotBase robot;
    double x, y, rotation;
    boolean shootingPosition;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, false);//very important to have correct color
    }

    /** CONTROLS:DRIVER:
    * drive: left stick = forward/back/strafe, right stick = rotation, hold left trigger for slow mode;
    * CONTROLS:GUNNER:
    * right bumper: start shooter, left bumper: stop shooter;
    *  a, b, x : vindexer a, b, c, respectively;
    * left trigger(hold): intake;
    * y: flicker;
     * dpad down: full transfer;
    */
    public void loop()
    {
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;


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
        if(gamepad2.right_trigger>0)
        {
            if(gamepad2.rightBumperWasPressed())
            {
                robot.shooter.setShooter();
                robot.setTelemetry("shooter velocity", robot.shooter.shooterMotor.getVelocity());
            }
        }
        else if(gamepad2.right_stick_y<0)
        {
            if(gamepad2.rightBumperWasPressed())
            {
                robot.shooter.shooterMin();
                robot.setTelemetry("shooter velocity", robot.shooter.shooterMotor.getVelocity());
            }
        }
        else if(gamepad2.rightBumperWasPressed())
        {
            robot.shooter.shooterMax();
            robot.setTelemetry("shooter velocity", robot.shooter.shooterMotor.getVelocity());
        }
        if(gamepad2.leftBumperWasPressed()) robot.shooter.stopShooter();

        //sorting
        if(gamepad2.aWasPressed()) robot.sorting.vindexerA();
        if(!robot.transfer.spamMode && gamepad2.bWasPressed()) robot.sorting.vindexerB();
        if(!robot.transfer.spamMode && gamepad2.xWasPressed()) robot.sorting.vindexerC();

        //intake
        if(gamepad2.left_trigger>0) robot.intake.startIntake();
        else robot.intake.stopIntake();

        //transfer
        if(gamepad2.yWasPressed()) robot.transfer.flickOne();
        if(gamepad2.dpad_left) robot.transfer.fullTransfer();
        else robot.transfer.stopAll();
        //if(robot.transfer.spamMode && gamepad2.bWasPressed()) robot.shoot3();
        //if(robot.transfer.spamMode && gamepad2.xWasPressed()) robot.farZoneShoot3();
        if(gamepad2.dpadUpWasPressed()) robot.stopAll();

        //hood
        if(gamepad2.dpadDownWasPressed()) robot.turret.hoodLow();
        if(gamepad2.dpadUpWasPressed()) robot.turret.hoodHigh();
    }

    /*
    what's left: macros, turn off sorting for spamMode
     */
}
