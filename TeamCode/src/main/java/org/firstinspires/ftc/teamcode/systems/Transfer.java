package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotBase;

public class Transfer
{
    public DcMotor feed;
    //public CRServo storage;
    public DcMotor storage;
    public Servo flicker;

    public  double feedReg, storageReg = 0.7;
    public double feedMax, storageMax = 0.9;
    public double feedIntake = 0.3;

    public static final double flickerRest = 0.1;
    public static final double flickerPush = 0.4;
    public static final double flickWait = 500; //time to wait for flickerPush, in ms
    public ElapsedTime timer;
    Telemetry telemetry;

    public Transfer(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Telemetry telemetry)
    {
        feed = hardwareMap.get(DcMotor.class, "feed");
        flicker = hardwareMap.get(Servo.class, "flicker");
        //storage = hardwareMap.get(CRServo.class, "storage");
        storage = hardwareMap.get(DcMotor.class, "storage");

        RobotBase.useEncoders(feed);
        RobotBase.useEncoders(storage);

        timer = new ElapsedTime();
        this.telemetry = telemetry;

    }
    public void flickOne()
    {
        timer.reset();
        flicker.setPosition(flickerPush);
        while(timer.milliseconds()<flickWait)
        {
            telemetry.addLine("flicking");
        }
        flicker.setPosition(flickerRest);
    }
    public void fullTransfer()
    {
        startFeed();
        startStorage();
    }
    public void startIntakeMode()
    {
        storage.setPower(storageReg);
        feed.setPower(feedIntake);
    }
    public void startFeed()
    {
        feed.setPower(feedReg);
    }
    public void startStorage()
    {
        storage.setPower(storageReg);
    }
    public void stopFeed()
    {
        feed.setPower(0);
    }
    public void stopStorage()
    {
        storage.setPower(0);
    }
    public void stopAll()
    {
        stopFeed();
        stopStorage();
    }



}
