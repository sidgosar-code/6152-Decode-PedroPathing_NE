package org.firstinspires.ftc.teamcode.tests.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotBase;
@TeleOp

public class intakeTest extends OpMode
{
    public RobotBase robot;
    boolean isOn;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry);
    }
    public void loop()
    {
        if(gamepad1.b) robot.intake.startIntake();
        else robot.intake.stopIntake();
        if(gamepad1.a) robot.intake.outTake();
        else robot.intake.stopIntake();
    }
}
