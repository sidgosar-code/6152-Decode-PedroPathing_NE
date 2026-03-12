package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
@Configurable
public class hoodTest2 extends OpMode{
    public Servo hood1;
    public static double min = 0.01;
    public static double max = 0.02;
    public void init()
    {
        hood1 = hardwareMap.get(Servo.class, "hood1");
    }
    public void loop()
    {
        hood1.setPosition(min);
        if(gamepad1.leftBumperWasPressed()){hood1.setPosition(max);}
        if(gamepad1.rightBumperWasPressed()){hood1.setPosition(min);}
    }
}