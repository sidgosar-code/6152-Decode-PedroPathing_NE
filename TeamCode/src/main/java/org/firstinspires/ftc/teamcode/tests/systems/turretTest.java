package org.firstinspires.ftc.teamcode.tests.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp
public class turretTest extends OpMode
{
    public RobotBase robot;
    public static boolean isBlue = true;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.UNSELECTED);
    }
    public void loop()
    {
        robot.aprilTagUtility.displayDetectionTelemetry(robot.aprilTagUtility.goalTag);
        //if(gamepad1.yWasPressed()) robot.turret.aimTurret();
        if(gamepad1.aWasPressed()) robot.turret.center();
        if(gamepad1.rightBumperWasPressed()) robot.turret.incRight();
        if(gamepad1.leftBumperWasPressed()) robot.turret.incLeft();

        if(gamepad1.right_trigger>0)robot.turret.crIncRight();
        if(gamepad1.left_trigger>0) robot.turret.crIncLeft();
    }



}
