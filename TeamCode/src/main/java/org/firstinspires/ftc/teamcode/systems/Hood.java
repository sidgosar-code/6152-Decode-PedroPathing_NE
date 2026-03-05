package org.firstinspires.ftc.teamcode.systems;


import com.qualcomm.robotcore.hardware.Servo;

public class Hood
{
    public Servo hood1;
    public Hood(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {

        hood1 = hardwareMap.get(Servo.class, "hood1");
    }
}
