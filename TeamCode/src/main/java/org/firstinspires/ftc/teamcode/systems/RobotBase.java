package org.firstinspires.ftc.teamcode.systems;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.auto.lib.CommandBuilder;
import org.firstinspires.ftc.teamcode.auto.lib.PathBuilder;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AprilTagUtility;
import org.firstinspires.ftc.teamcode.util.CurrentAlliance;

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

    public Follower follower;

    public RobotBase(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry, Alliance alliance)
    {
        this.telemetry = telemetry;
        telemetry1 = telemetry;
        CurrentAlliance.alliance = alliance;
        initHardware(hardwareMap);
    }

    public RobotBase(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;
        telemetry1 = telemetry;
        CurrentAlliance.alliance = Alliance.UNSELECTED;
        initHardware(hardwareMap);
    }

    public void initHardware(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        //aprilTagUtility = new AprilTagUtility(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap);
        transfer = new Transfer(hardwareMap, shooter);
        turret = new Turret(hardwareMap, aprilTagUtility);
        sorting = new Sorting(hardwareMap, transfer, aprilTagUtility);
        intake = new Intake(hardwareMap, transfer);
        movement = new Movement(hardwareMap);
        timer = new ElapsedTime();
    }

    public void initAuto(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        // Clear previous state if any
        //aprilTagUtility = new AprilTagUtility(hardwareMap, telemetry);
        
        shooter = new Shooter(hardwareMap);
        transfer = new Transfer(hardwareMap, shooter);
        turret = new Turret(hardwareMap, aprilTagUtility);
        sorting = new Sorting(hardwareMap, transfer, aprilTagUtility);
        intake = new Intake(hardwareMap, transfer);
        
        pathBuilder = new PathBuilder(hardwareMap);
        this.follower = pathBuilder.follower; // Crucial link
        
        commandBuilder = new CommandBuilder(pathBuilder);
        CommandLib.create(this);
        
        timer = new ElapsedTime();
    }

    public void setTelemetry(String caption, double data)
    {
        telemetry.addData(caption, data);
        telemetry.update();
    }
    public void setTelemetry(String caption, Object cur)
    {
        telemetry.addData(caption, cur);
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

    public static void useEncoders(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);}
    public static void useEncoders(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);}
    public static void noEncoders(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);}
    public static void noEncoders(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);}

    public static void rtpMode(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);}
    public static void rtpMode(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}

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
        turret.hoodLow();
        waitTime(Shooter.shootPrep);
        transfer.fullTransfer();
        waitTime(Shooter.shoot2);
        transfer.fullTransfer();
        intake.startIntake();
        transfer.flickOne();
    }

    public void waitTime(double time)
    {
        timer.reset();
        while(timer.milliseconds() < time) { }
    }

    public void stopAll()
    {
        shooter.stopShooter();
        transfer.stopAll();
        intake.stopIntake();
    }

    public void update()
    {
        if (follower != null) {
            follower.update();
            telemetry.addData("x", follower.getPose().getX());
            telemetry.addData("y", follower.getPose().getY());
            telemetry.addData("heading", follower.getPose().getHeading());
        }
        telemetry.update();
    }

    public void zeroHeading()
    {
        follower.setHeading(Math.PI/2);
    }
}
