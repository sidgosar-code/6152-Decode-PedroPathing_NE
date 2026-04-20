package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.RobotBase;
@Configurable
public class Shooter
{
    public DcMotorEx s;//shooter motor
    public static int minInd = 1;
    public boolean isOn;

    public static double shoot2 = 200; //time to shoot 2 balls before flicking, in ms
    public static double shootPrep = 300; // time to aim turret, fix hood, and shooter to get up to speed, in ms
    public static double toSpeed = 500; // time for shooter to reach desired velocity, in ms

    public static double error = 250;

    public static double maxVelocity = 1750, midVelocity = 1500, minVelocity = 1125;
    public double curTarget;
    public static double[] speeds = {minVelocity, midVelocity, maxVelocity};
    public int speedIndex = 1;
    public static double F = 14.25;//for pidf
    public static double P = 125;//for pidf

    public double x, y, heading;


    PIDFCoefficients shooterPIDF;

    public Shooter(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        s = hardwareMap.get(DcMotorEx.class, "shooter");
        curTarget = midVelocity;


        RobotBase.useEncoders(s);
        shooterPIDF = new PIDFCoefficients(P, 0, 0, F);
        s.setPIDFCoefficients(s.getMode(), shooterPIDF);
    }

    public void set()
    {
        s.setVelocity(curTarget);
    }

    public boolean aboveTarget()
    {
        return s.getVelocity()>=curTarget-error;
    }


    public void shooterMax()
    {
        curTarget = maxVelocity;
        set();
    }
    public void shooterMin()
    {
        curTarget = minVelocity;
        set();
    }
    public void setShooter()
    {
        curTarget = midVelocity;
        set();

        //if(curVelocity==minVelocity) curVelocity = midVelocity;
        //else if(curVelocity == midVelocity) curVelocity = farVelocity;
        //else curVelocity = minVelocity;
        
    }
    public void bangSet(double target)
    {
        if(s.getVelocity()<curTarget) s.setPower(1);
        else s.setPower(0);
    }
    public void stopShooter()
    {
        s.setVelocity(0);
    }
    public double calculateVelocity(double x, double y)
    {
        double distance = Math.sqrt(x*x + y *y);
        if(distance<=40)
        {
            return speeds[0];
        }
        if(distance>40 && distance <=115)
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
