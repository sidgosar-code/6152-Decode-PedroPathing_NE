package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp(name = "stupidMotorTeleOp", group = "real OpModes")
public class stupidMotorTeleOp extends OpMode
{
    public RobotBase robot;
    double x, y, rotation;
    boolean shootingPosition;

    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.UNSELECTED);
    }
    public void loop()
    {
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;


        if(gamepad1.aWasPressed()) robot.movement.fl.setPower((.1));
        if(gamepad1.bWasPressed()) robot.movement.bl.setPower((.1));
        if(gamepad1.xWasPressed()) robot.movement.br.setPower((.1));
        if(gamepad1.yWasPressed()) robot.movement.fr.setPower((.1));

        if(gamepad2.dpadUpWasPressed()) robot.stopAll();
    }
}
