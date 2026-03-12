package org.firstinspires.ftc.teamcode.tests.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Configurable
public class vindexerTest extends OpMode
{
    public Servo vindexer;
    public static double A, B, C;
    @Override
    public void init()
    {
        vindexer = hardwareMap.get(Servo.class, "vindexer");
    }
    public void loop()
    {
        vindexer.setPosition(A);
        if(gamepad1.rightBumperWasPressed())
        {
            vindexer.setPosition(A);
        }
        if(gamepad1.dpadUpWasPressed())
        {
            vindexer.setPosition(B);
        }
        if(gamepad1.leftBumperWasPressed())
        {
            vindexer.setPosition(C);
        }
    }

}
