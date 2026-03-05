package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.RobotBase;

public class teleOp extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase();
        robot.initHardware(hardwareMap, telemetry);
    }
    public void loop()
    {
        if(gamepad1.rightBumperWasPressed() && !robot.shooter.isOn)
        {
            robot.shooter.set(robot.shooter.curVelocity);
        }
        if(gamepad1.rightBumperWasPressed() && robot.shooter.isOn)
        {
            robot.shooter.set(robot.shooter.curVelocity);
        }

    }
}
