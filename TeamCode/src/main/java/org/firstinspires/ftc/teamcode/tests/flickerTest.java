package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
 @TeleOp
 @Configurable
public class flickerTest extends OpMode
{
    public Servo flicker;
    public static double zero = 0;
    public static double push = 0.1;
    @Override
    public void init()
    {
        flicker = hardwareMap.get(Servo.class, "flicker");
    }
    public void loop()
    {
        flicker.setPosition(zero);
        if(gamepad1.rightBumperWasPressed())
        {
            flicker.setPosition(push);
        }

    }
}
