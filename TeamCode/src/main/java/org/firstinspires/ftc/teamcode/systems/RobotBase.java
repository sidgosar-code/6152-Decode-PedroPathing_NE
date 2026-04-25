package org.firstinspires.ftc.teamcode.systems;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.auto.lib.CommandBuilder;
import org.firstinspires.ftc.teamcode.auto.lib.PathBuilder;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AprilTagUtility;
import org.firstinspires.ftc.teamcode.util.CurrentAlliance;

import static com.pedropathing.ivy.Scheduler.schedule;

public class RobotBase
{
    public Shooter shooter;
    public Transfer transfer;
    public Sorting sorting;
    public Intake intake;
    public Turret turret;
    public Telemetry telemetry;
    public static Telemetry telemetry1;

    public AprilTagUtility aprilTagUtility;
    public Movement movement;
    public ElapsedTime timer;

    public CommandBuilder commandBuilder;
    public PathBuilder pathBuilder;

    //constructor here
    public RobotBase(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry, Alliance alliance)
    {
        CurrentAlliance.alliance = alliance;
        initHardware(hardwareMap, telemetry);
    }

    public RobotBase(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)//for tests
    {
        CurrentAlliance.alliance = Alliance.UNSELECTED;
        initHardware(hardwareMap, telemetry);
    }


    public void initHardware(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)
    {
        transfer = new Transfer(hardwareMap, shooter);
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap, aprilTagUtility);
        sorting = new Sorting(hardwareMap, transfer);
        intake = new Intake(hardwareMap, transfer);
        movement = new Movement(hardwareMap);
        this.telemetry = telemetry;
        telemetry1 = telemetry;
        timer = new ElapsedTime();
    }
    public void initAuto(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)
    {
        aprilTagUtility = new AprilTagUtility(hardwareMap, telemetry);
        pathBuilder = new PathBuilder(hardwareMap);
        commandBuilder = new CommandBuilder(pathBuilder);
    }

    public void setTelemetry(String caption, double data)
    {
        telemetry.addData(caption, data);
        telemetry.update();
    }
    public void setTelemetry(String line)
    {
        telemetry.addLine(line);
        telemetry.update();
    }
    public static void setTelemetry1(String caption, double data)
    {
        telemetry1.addData(caption, data);
        telemetry1.update();
    }
    public static void setTelemetry1(String line)
    {
        telemetry1.addLine(line);
        telemetry1.update();
    }

    //encoder switching methods, overloaded for DcMotorEx
    public static void useEncoders(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);}
    public static void useEncoders(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);}
    public static void noEncoders(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);}
    public static void noEncoders(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);}

    public void farZoneShoot3()
    {
        shooter.shooterMax();
        transfer.startStorage();
        turret.hoodHigh();
        transfer.feedOne();
        waitTime(Shooter.toSpeed);
        transfer.feedOne();
        transfer.flickOne();
        waitTime(Shooter.toSpeed - Transfer.flickWait);
        transfer.fullTransfer();
    }
    public void shoot3()
    {
        shooter.shooterMin();
        //turret.aimTurret();
        turret.hoodLow();
        waitTime(Shooter.shootPrep);
        transfer.fullTransfer();
        waitTime(Shooter.shoot2);
        transfer.fullTransfer();
        intake.startIntake();
        transfer.flickOne();
    }

    public void waitTime(double time)//time in ms
    {
        timer.reset();
        while(timer.milliseconds() < time)
        {
            int m = 4;
        }
        return;
    }

    public void stopAll()
    {
        shooter.stopShooter();
        transfer.stopAll();
        intake.stopIntake();
    }

    public static class CommandLib
    {
        public static Command setShooter; //=
                //Command.build()

        public static Command sort; //=
        //Command.build()
    }
}
