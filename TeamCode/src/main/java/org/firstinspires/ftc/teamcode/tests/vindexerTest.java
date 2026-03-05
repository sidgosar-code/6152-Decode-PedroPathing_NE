package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class vindexerTest extends OpMode
{
    public Servo vindexer;
    @Override
    public void init()
    {
        vindexer = hardwareMap.get(Servo.class, "vindexer");
    }
    public void loop()
    {
        if(gamepad1.rightBumperWasPressed())
        {
            vindexer.setPosition(0);
        }
        if(gamepad1.dpadUpWasPressed())
        {
            vindexer.setPosition(0.5);
        }
    }

}
