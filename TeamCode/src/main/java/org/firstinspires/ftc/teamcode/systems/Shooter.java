package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotBase;

public class Shooter
{
    public DcMotorEx shooter;

    public boolean isOn;
    public static final double maxVelocity = 1750;
    public static final double minVelocity = 1450;
    public double curVelocity;

    public static final double F = 13.507;//for pidf
    public static final double P = 200;//for pidf


    PIDFCoefficients shooterPIDF;

    public Shooter(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        isOn = false;

        RobotBase.useEncoders(shooter);
        shooterPIDF = new PIDFCoefficients(P, 0, 0, F);
        shooter.setPIDFCoefficients(shooter.getMode(), shooterPIDF);
    }
    public void set(double velocity)
    {
        shooter.setVelocity(velocity);
        isOn = true;

    }
    public void stopShooter()
    {
        shooter.setVelocity(0);
        isOn = false;
    }
    public double calculateVelocity(double distance)
    {
        return 0;
    }
}
