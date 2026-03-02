package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Transfer
{
    public DcMotor feed;
    public CRServo storage;
    public Servo flicker;
    public ElapsedTime timer;

    public Transfer(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        feed = hardwareMap.get(DcMotor.class, "feed");
        flicker = hardwareMap.get(Servo.class, "flicker");
        storage = hardwareMap.get(CRServo.class, "storage");
        timer = new ElapsedTime();
    }
    public void flickOne()
    {
        timer.reset();

    }


}
