package org.firstinspires.ftc.teamcode.teleOp;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.Movement;
import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp(name="pedroTeleOpRed", group = "real OpModes")
public class pedroTeleOpRed extends OpMode
{
    private Follower follower;
    public RobotBase robot;
    public Pose startingPose;

    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        robot.follower.setStartingPose(startingPose == null ? new Pose(72, 72, 0): startingPose);
        robot.follower.update();
    }
    @Override
    public void start()
    {
        robot.follower.startTeleopDrive();
    }
    @Override
    public void loop()
    {
        if(gamepad1.dpadUpWasPressed()) robot.zeroHeading();//zero heading


        if (!gamepad1.y) robot.follower.setTeleOpDrive(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                true // Robot Centric
        );

        else robot.follower.setTeleOpDrive(
                -gamepad1.left_stick_y * Movement.slowValue,
                -gamepad1.left_stick_x * Movement.slowValue,
                -gamepad1.right_stick_x * Movement.slowValue,
                true // Robot Centric
        );

        //turret
        if(gamepad1.aWasPressed()) robot.turret.center();
        if(gamepad1.rightBumperWasPressed()) robot.turret.incRight();
        if(gamepad1.leftBumperWasPressed()) robot.turret.incLeft();
        if(gamepad1.right_trigger>0)robot.turret.crIncRight();
        if(gamepad1.left_trigger>0) robot.turret.crIncLeft();

        if(gamepad1.xWasPressed()) robot.turret.aimNear(robot.follower.getPose());

        //switchMode
        if(gamepad2.rightStickButtonWasPressed()) robot.transfer.switchMode();

        //shooter
        if(gamepad2.right_stick_y>0)
        {
            if(gamepad2.rightBumperWasPressed())
            {
                robot.shooter.shooterMax();
                robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
            }
        }
        else if(gamepad2.right_stick_y<0)
        {
            if(gamepad2.rightBumperWasPressed())
            {
                robot.shooter.shooterMin();
                robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
            }
        }
        else if(gamepad2.rightBumperWasPressed())
        {
            robot.shooter.setShooter();
            robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
        }
        if(gamepad2.leftBumperWasPressed()) robot.shooter.stopShooter();

        //sorting
        if(gamepad2.aWasPressed()) robot.sorting.vindexerA();
        if(gamepad2.bWasPressed()) robot.sorting.vindexerB();
        if(gamepad2.xWasPressed()) robot.sorting.vindexerC();

        //intake
        if(gamepad2.left_trigger>0) {
            if(gamepad2.left_stick_y<0) robot.intake.outTake(); //might want to change control
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

        ///hood
        //if(gamepad2.dpadDownWasPressed()) robot.turret.hoodLow();
        //if(gamepad2.dpadUpWasPressed()) robot.turret.hoodHigh();
        //else robot.turret.hoodReg();
        robot.turret.hoodSet();
//        robot.setTelemetry("position", robot.follower.getPose());
//        robot.setTelemetry("velocity", robot.follower.getVelocity());
    }
}
