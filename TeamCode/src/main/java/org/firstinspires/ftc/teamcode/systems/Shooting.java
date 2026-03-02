package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotBase;

public class Shooting
{
    public DcMotorEx shooter;
    public Servo turret;
    public Servo hood1;
    public boolean isOn;
    public static final double maxVelocity = 1750;
    public static final double minVelocity = 1450;
    public double curVelocity;

    public static final double F = 13.507;//for pidf
    public static final double P = 65.0;//for pidf


    PIDFCoefficients shooterPIDF;

    public Shooting(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        turret = hardwareMap.get(Servo.class, "turret");
        hood1 = hardwareMap.get(Servo.class, "hood1");
        isOn = false;

        RobotBase.useEncoders(shooter);
        shooterPIDF = new PIDFCoefficients(P, 0, 0, F);
        shooter.setPIDFCoefficients(shooter.getMode(), shooterPIDF);
    }
    public void set(double velocity)
    {
        shooter.setVelocity(velocity);
    }
    public void stopShooter()
    {
        shooter.setVelocity(0);
    }
    public double calculateVelocity(double distance)
    {
        return 0;
    }
}
