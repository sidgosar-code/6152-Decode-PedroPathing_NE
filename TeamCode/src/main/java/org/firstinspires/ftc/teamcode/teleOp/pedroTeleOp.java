package org.firstinspires.ftc.teamcode.teleOp;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp(name="pedroTeleOp", group = "real OpModes")
public class pedroTeleOp extends OpMode
{
    public RobotBase robot;
    public Pose startingPose;

    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        robot.follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
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
        robot.setTelemetry("position", robot.follower.getPose());
        robot.setTelemetry("velocity", robot.follower.getVelocity());
    }
}
