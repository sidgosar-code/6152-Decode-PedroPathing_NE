package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RobotBase;

public class turretTest extends OpMode
{
    public RobotBase robot;
    public static boolean isBlue = true;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, isBlue);
    }
    public void loop()
    {
        robot.aprilTagUtility.displayDetectionTelemetry(robot.aprilTagUtility.goalTag);
        if(gamepad1.yWasPressed()) robot.turret.aimTurret();
        if(gamepad1.aWasPressed()) robot.turret.turretCenter();

    }



}
