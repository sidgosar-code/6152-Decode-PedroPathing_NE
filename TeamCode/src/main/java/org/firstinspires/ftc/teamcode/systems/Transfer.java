package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Transfer
{
    public DcMotor feed;
    //public CRServo storage;
    public DcMotor storage;
    public Servo flicker;
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


}
