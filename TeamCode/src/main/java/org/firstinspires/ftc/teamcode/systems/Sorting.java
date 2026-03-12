package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Servo;

public class Sorting
{
    public Servo vindexer;
    public Transfer transfer;

    public static final double A = 0.052;
    public static final double B = 0.118;
    public static final double C = 0.187;
    public Sorting(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Transfer transfer)
    {
        vindexer = hardwareMap.get(Servo.class, "vindexer");
        this.transfer = transfer;

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
    public void vindexerABC() {
        vindexer.setPosition(A); transfer.flickOne();
        vindexer.setPosition(B); transfer.flickOne();
        vindexer.setPosition(C); transfer.flickOne(); }
    public void vindexerACB() {
        vindexer.setPosition(A); transfer.flickOne();
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(B); transfer.flickOne(); }
    public void vindexerBAC() {
        vindexer.setPosition(B); transfer.flickOne();
        vindexer.setPosition(A); transfer.flickOne();
        vindexer.setPosition(C); transfer.flickOne(); }
    public void vindexerBCA() {
        vindexer.setPosition(B); transfer.flickOne();
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(A); transfer.flickOne(); }
    public void vindexerCAB() {
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(A); transfer.flickOne();
        vindexer.setPosition(B); transfer.flickOne(); }
    public void vindexerCBA() {
        vindexer.setPosition(C); transfer.flickOne();
        vindexer.setPosition(B); transfer.flickOne();
        vindexer.setPosition(A); transfer.flickOne(); }

}
