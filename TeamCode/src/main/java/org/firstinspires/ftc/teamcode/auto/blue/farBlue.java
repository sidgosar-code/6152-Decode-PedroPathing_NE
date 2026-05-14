package org.firstinspires.ftc.teamcode.auto.blue;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.auto.lib.BluePoses;
import org.firstinspires.ftc.teamcode.auto.lib.Commands;
import org.firstinspires.ftc.teamcode.systems.RobotBase;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.CurrentMotif;

@Autonomous
public class farBlue extends OpMode
{
    public RobotBase robot;
    @Override
    public void init()
    {
        robot = new RobotBase(hardwareMap, telemetry, Alliance.RED);
        robot.initAuto(hardwareMap);
        robot.pathBuilder.buildPathsBF();
        Scheduler.reset();
        robot.follower.setStartingPose(BluePoses.bfStart);

    }
    @Override
    public void init_loop()
    {
        //CurrentMotif.update(robot.aprilTagUtility.getObeliskTag());
        robot.setTelemetry("motif", CurrentMotif.motif);
    }
    @Override
    public void start()
    {
        schedule(Commands.autoRoutine(Commands.fSort3()));
        robot.aprilTagUtility.stop();
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
