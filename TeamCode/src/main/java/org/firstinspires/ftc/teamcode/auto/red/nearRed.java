package org.firstinspires.ftc.teamcode.auto.red;


import static com.pedropathing.ivy.Scheduler.schedule;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.auto.lib.Commands;
import org.firstinspires.ftc.teamcode.auto.lib.RedPoses;
import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.CurrentMotif;

@Autonomous(name = "nearRed", group = "new")
@Configurable
public class nearRed extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        robot.initAuto(hardwareMap);
        Scheduler.reset();
        robot.follower.setStartingPose(RedPoses.rcStart);
        CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
        robot.setTelemetry("motif", CurrentMotif.motif);
    }
//    @Override
//    public void init_loop()
//    {
//        CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
//        robot.setTelemetry("motif", CurrentMotif.motif);
//    }
    @Override
    public void start()
    {
        Scheduler.schedule(Commands.autoRoutine(Commands.RCSort3()));
    }
    @Override
    public void loop()
    {
        robot.update();
        Scheduler.execute();

    }
    @Override
    public void stop()
    {
        robot.setTelemetry("auto complete");
    }
}
