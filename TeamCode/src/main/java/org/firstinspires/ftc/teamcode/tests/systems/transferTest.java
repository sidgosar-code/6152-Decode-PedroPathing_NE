package org.firstinspires.ftc.teamcode.tests.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.RobotBase;

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

        //everything works, will just have to be start/stop, no hold
        if(gamepad1.left_trigger>0) robot.transfer.fullTransfer();
        else robot.transfer.stopAll();
        if(gamepad1.leftBumperWasPressed()) robot.transfer.stopAll();
        if(gamepad1.b) robot.transfer.startStorage();
        else robot.transfer.stopStorage();
        if(gamepad1.x) robot.transfer.startFeed();
        else robot.transfer.stopFeed();
        if(gamepad1.aWasPressed()) robot.transfer.flickOne();
        if(gamepad1.y) robot.transfer.out();
        else robot.transfer.stopAll();
    }

}
