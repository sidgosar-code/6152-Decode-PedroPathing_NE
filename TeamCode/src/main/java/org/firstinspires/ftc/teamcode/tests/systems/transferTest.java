package org.firstinspires.ftc.teamcode.tests.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotBase;

@TeleOp
public class transferTest extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry);
    }
    public void loop()
    {
        if(gamepad1.left_trigger>0) robot.transfer.startStorage();
        if(gamepad1.leftBumperWasPressed()) robot.transfer.stopStorage();
        if(gamepad1.b) robot.transfer.startStorage();
        if(gamepad1.x) robot.transfer.startFeed();
        if(gamepad1.a) robot.transfer.flickOne();
    }

}
