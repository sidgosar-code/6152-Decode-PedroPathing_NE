package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.RobotBase;
@Configurable
public class Shooter
{
    public DcMotorEx shooter;

    public static int minInd = 1;

    public static final double farVelocity = 1750;
    public static final double midVelocity = 1500;
    public static final double minVelocity = 1400;
    public double curVelocity;
    public static double[] speeds = {minVelocity, midVelocity, farVelocity};
    public int speedIndex = 1;
    public static final double F = 15.9086;//for pidf
    public static final double P = 175;//for pidf

    public double x, y, heading;


    PIDFCoefficients shooterPIDF;

    public Shooter(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        curVelocity = midVelocity;


        RobotBase.useEncoders(shooter);
        shooterPIDF = new PIDFCoefficients(P, 0, 0, F);
        shooter.setPIDFCoefficients(shooter.getMode(), shooterPIDF);
    }
    public void setShooter()
    {
        shooter.setVelocity(curVelocity);
        if(curVelocity==minVelocity) curVelocity = midVelocity;
        else if(curVelocity == midVelocity) curVelocity = farVelocity;
        else curVelocity = minVelocity;
        
    }
    public void stopShooter()
    {
        shooter.setVelocity(0);
    }
    public double calculateVelocity(double x, double y)
    {
        double distance = Math.sqrt(x*x + y *y);
        if(distance<=40)
        {
            return speeds[0];
        }
        if(distance>40 && distance <=100)
        {
            return speeds[1];
        }
        if(distance>100)
        {
            return speeds[2];
        }
        return 0;
    }
}
