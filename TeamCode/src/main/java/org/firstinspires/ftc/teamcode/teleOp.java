package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class teleOp extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry);

    }
    public void loop()
    {
        if(gamepad1.rightBumperWasPressed())
        {
            robot.shooter.setShooter();
            robot.setTelemetry("speedIndex", robot.shooter.speedIndex);
        }
        if(gamepad1.leftBumperWasPressed())
        {
            robot.shooter.stopShooter();
        }

    }
}
