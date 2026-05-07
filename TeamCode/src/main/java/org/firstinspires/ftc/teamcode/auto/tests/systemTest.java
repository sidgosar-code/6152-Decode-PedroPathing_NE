package org.firstinspires.ftc.teamcode.auto.tests;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.CurrentAlliance;
import org.firstinspires.ftc.teamcode.util.CurrentMotif;

@Autonomous
public class systemTest extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        robot.initAuto(hardwareMap);

    }
    @Override
    public void init_loop()
    {
        CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
        robot.setTelemetry("motif", CurrentMotif.motif);
    }
    @Override
    public void start()
    {

    }
    public void loop()
    {

    }
}
