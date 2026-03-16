/*


package org.firstinspires.ftc.teamcode.systems;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.qualcomm.robotcore.hardware.Servo;

public class Turret
{
    public Servo turret;
    public static final double rotLeft = LEFT (~0?);
    public static final double rotRight = RIGHT (~0.4?);
    public static final double turretInc = 0.01;
    public Servo hood1;
    public static final double hoodMin = 0.67;
    public static final double hoodMax = 0.60;
    public static final double hoodInc = 0.01;
    public static final double[] rots = new double[] {rotLeft, (3*rotLeft+rotRight)/4, (rotLeft+rotRight)/2,
            (rotLeft+3*rotRight)/4, rotRight};
    public static int currentRot = 0;
    public Turret(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        turret = hardwareMap.get(Servo.class, "turret");
        hood1 = hardwareMap.get(Servo.class, "hood1");
    }
    public void turretLeft()
    {
        if(currentRot>0)
        {
            turret.setPosition(rots[currentRot-1]);
            currentRot--;
        }
        else {turret.setPosition(rots[0]);};
    }
    public void turretRight()
    {
        if(currentRot<4)
        {
            turret.setPosition(rots[currentRot+1]);
            currentRot++;
        }
        else {turret.setPosition(rots[4]);};
    }
    public void turretIncRight()
    {
        if(turret.getPosition()<rotRight-turretInc)
        {
            turret.setPosition(turret.getPosition() + turretInc);
            if (turret.getPosition() > rots[currentRot + 1]) {currentRot++;}
        }
    }
    public void turretIncLeft()
    {
        if(turret.getPosition()>rotLeft+turretInc)
        {
            turret.setPosition(turret.getPosition() - turretInc);
            if (turret.getPosition() < rots[currentRot - 1]) {currentRot--;}
        }
    }
    public double calcRealX()
    {
        return odometry.getX-3*cos(odometry.getHeading)-4*sin(odometry.getHeading));
    }
    public void calcRealY(double realX)
    {
        if(heading > 36.87 && heading < 216.87) {return -(odometry.getY-Math.sqrt(25-(3*cos(odometry.getHeading)+4*sin(odometry.getHeading))^2));}
        else {return odometry.getY-Math.sqrt(25-(3*cos(odometry.getHeading)+4*sin(odometry.getHeading))^2);}
    }
    public void getDistanceBlue(double realX, double realY)
    {
        return Math.atan2(8.108640-realX, 133.196421-realY) + heading_offset;
    }
    public void getDistanceRed(double realX, double realY)
    {
        return Math.atan2(144-8.108640-realX, 133.196421-realY) + heading_offset;
    }

    public void hoodHigh(){hood1.setPosition(hoodMax);}
    public void hoodLow(){hood1.setPosition(hoodMin);}
    public void hoodIncUp(){if(hood1.getPosition()<hoodMax)hood1.setPosition(hood1.getPosition()-hoodInc);}
    public void hoodIncDown(){if(hood1.getPosition()>hoodMin)hood1.setPosition(hood1.getPosition()+hoodInc);}
}

 */