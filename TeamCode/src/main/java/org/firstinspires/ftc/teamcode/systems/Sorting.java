package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotBase;

public class Sorting
{
    public Servo vindexer;
    public Transfer transfer;

    public static final double A = 0.052;
    public static final double B = 0.118;
    public static final double C = 0.187;

    public ElapsedTime timer;
    public static double vindexerWait = 500;

    public Telemetry telemetry;
    public Sorting(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Transfer transfer)
    {
        vindexer = hardwareMap.get(Servo.class, "vindexer");
        this.transfer = transfer;
        timer = new ElapsedTime();
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
    public void vindexerABC()
    {
        vindexerPositions(A, B, C);
    }

    /*
    public void vindexerACB() {
        vindexer.setPosition(A); transfer.flickOne();
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(B); transfer.flickOne(); }

     */
    public void vindexerBAC()
    {
        vindexerPositions(B, A, C);
    }

    /*
    public void vindexerBCA() {
        vindexer.setPosition(B); transfer.flickOne();
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(A); transfer.flickOne(); }

     */
    public void vindexerCAB()
    {
        vindexerPositions(C, A, B);
    }

    /*
    public void vindexerCBA() {
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(B); transfer.flickOne();
        vindexer.setPosition(A); transfer.flickOne(); }

     */

    private void vindexerPositions(double pos1, double pos2, double pos3)
    {
        vindexer.setPosition(pos1); vindexerWait(); transfer.flickOne();
        vindexer.setPosition(pos2); vindexerWait(); transfer.flickOne();
        vindexer.setPosition(pos3); vindexerWait(); transfer.flickOne();
    }

    public void vindexerWait()
    {
        timer.reset();
        while(timer.milliseconds()<vindexerWait)
        {
            int m = 4;
        }
        RobotBase.setTelemetry1("Vindexer position", vindexer.getPosition());
    }

}
