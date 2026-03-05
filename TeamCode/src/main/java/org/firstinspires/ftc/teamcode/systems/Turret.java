package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Servo;

public class Turret
{
    public Servo turret;
    public Servo hood1;
    public Turret(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        turret = hardwareMap.get(Servo.class, "turret");
        hood1 = hardwareMap.get(Servo.class, "hood1");
    }
}
