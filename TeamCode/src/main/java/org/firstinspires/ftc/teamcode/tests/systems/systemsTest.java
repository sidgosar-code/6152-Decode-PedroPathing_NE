package org.firstinspires.ftc.teamcode.tests.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.RobotBase;

@TeleOp()
public class systemsTest extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry);
    }
    public void loop()
    {
        robot.setTelemetry("shooter velocity", robot.shooter.s.getVelocity());
        robot.setTelemetry("shooter velocity", robot.shooter.curTarget);
        if(gamepad1.a)
        {
            /*
            if(robot == null)
            {
                telemetry.addLine("robot is null");
            }
            if(robot.shooter == null)
            {
                telemetry.addLine("shooter is null");
            }


            else*/ robot.shooter.setShooter();
        }
        else robot.shooter.stopShooter();
        if(gamepad1.bWasPressed())
        {
            robot.shooter.stopShooter();
        }
        if(gamepad1.right_trigger>0)
        {
            robot.intake.startIntake();
        }
    }

}
