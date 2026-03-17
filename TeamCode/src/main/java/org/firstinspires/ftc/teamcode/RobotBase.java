package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.systems.AprilTagUtility;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Movement;
import org.firstinspires.ftc.teamcode.systems.Shooter;
import org.firstinspires.ftc.teamcode.systems.Sorting;
import org.firstinspires.ftc.teamcode.systems.Transfer;
import org.firstinspires.ftc.teamcode.systems.Turret;
//import org.firstinspires.ftc.teamcode.systems.Turret;

public class RobotBase
{
    public Shooter shooter;
    public Transfer transfer;
    public Sorting sorting;
    public Intake intake;
    public Turret turret;
    public Telemetry telemetry;
    public static Telemetry telemetry1;

    //public ModeManager modeManager;

    public AprilTagUtility aprilTagUtility;
    public Movement movement;
    public ElapsedTime timer;

    //constructor here
    public RobotBase(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry, boolean isBlue)
    {
        initHardware(hardwareMap, telemetry, isBlue);
    }
    public RobotBase(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)//for tests
    {
        initHardware(hardwareMap, telemetry);
    }
    public void initHardware(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry, boolean isBlue)
    {
        aprilTagUtility = new AprilTagUtility(hardwareMap, telemetry, isBlue);
        //modeManager = new ModeManager();
        transfer = new Transfer(hardwareMap);
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap, aprilTagUtility);
        sorting = new Sorting(hardwareMap, transfer);
        intake = new Intake(hardwareMap, transfer);
        movement = new Movement(hardwareMap);
        this.telemetry = telemetry;
        telemetry1 = telemetry;
        timer = new ElapsedTime();
    }
    public void initHardware(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)//for tests
    {
        transfer = new Transfer(hardwareMap);
        shooter = new Shooter(hardwareMap);
        //turret = new Turret(hardwareMap, aprilTagUtility);
        sorting = new Sorting(hardwareMap, transfer);
        intake = new Intake(hardwareMap, transfer);
        this.telemetry = telemetry;
        movement = new Movement(hardwareMap);
        telemetry1 = telemetry;
        timer = new ElapsedTime();
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
        shooter.setShooter();
        transfer.startStorage();
        transfer.feedOne();
        waitTime(Shooter.toSpeed);
        transfer.feedOne();
        transfer.flickOne();
        waitTime(Shooter.toSpeed - Transfer.flickWait);
        transfer.fullTransfer();
    }
    public void shoot3()
    {
        shooter.setShooter();
        turret.aimTurret();
        turret.hoodLow();
        waitTime(Shooter.shootPrep);
        transfer.fullTransfer();
        waitTime(Shooter.shoot2);
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

    public void stopALl()
    {
        shooter.stopShooter();
        transfer.stopAll();
        intake.stopIntake();
    }

}
