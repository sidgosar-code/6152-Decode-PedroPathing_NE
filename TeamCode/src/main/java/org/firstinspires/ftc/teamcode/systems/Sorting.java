package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotBase;
import org.firstinspires.ftc.teamcode.unused.ModeManager;

@Configurable
public class Sorting
{
    public Servo vindexer;
    public Transfer transfer;

    public static double A = 0.204;
    public static double B = 0.271;
    public static double C = 0.343;

    public ElapsedTime timer;
    public static double vindexerWaitTime = 500;
    public static double flickOneWaitTime = 500;

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
        if(transfer.sortMode)vindexer.setPosition(B);
    }
    public void vindexerC()
    {
        if(transfer.sortMode)vindexer.setPosition(C);
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
        vindexer.setPosition(pos1); waitTime(vindexerWaitTime); transfer.flickOne(); waitTime(flickOneWaitTime);
        vindexer.setPosition(pos2); waitTime(vindexerWaitTime); transfer.flickOne(); waitTime(flickOneWaitTime);
        vindexer.setPosition(pos3); waitTime(vindexerWaitTime); transfer.flickOne(); waitTime(flickOneWaitTime);
    }

    public void waitTime(double time)
    {
        timer.reset();
        while(timer.milliseconds()<time)
        {
            int m = 4;
        }
        RobotBase.setTelemetry1("Vindexer position", vindexer.getPosition());
    }

}
