package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Servo;

public class Sorting
{
    public Servo vindexer;
    public static final double A = 0.052;
    public static final double B = 0.118;
    public static final double C = 0.187;
    public Sorting(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        vindexer = hardwareMap.get(Servo.class, "vindexer");
    }
    public void vindexerA()
    {
        vindexer.setPosition(A);
    }

    public void vindexerB()
    {
        vindexer.setPosition(B);
    }
    public void vindexerC()
    {
        vindexer.setPosition(C);
    }

}
