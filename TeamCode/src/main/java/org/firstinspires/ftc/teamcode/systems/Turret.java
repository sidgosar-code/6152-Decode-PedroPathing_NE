package org.firstinspires.ftc.teamcode.systems;/*


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

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotBase;

@Configurable
public class Turret
{
    public Servo turret;
    public Servo hood1;

    public AprilTagUtility aprilTagUtility;
    public static double center = 0.385;
    public static double right = 0.18;
    public static double left = 0.58;

    public static double cameraShooterOffset = 7; //inches

    public double cameraAimAngle, aimAngle, aimPosition, distance, curPosition;

    public static double increment = 0.01;
    public static double crIncrement = 0.0005;
    public static double turretMultiplier = 1;
    public static double hoodMin = 0.67;
    public static double hoodMax = 0.60;
    public Turret(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, AprilTagUtility aprilTagUtility)
    {
        turret = hardwareMap.get(Servo.class, "turret");
        hood1 = hardwareMap.get(Servo.class, "hood1");
        this.aprilTagUtility = aprilTagUtility;
        aimPosition = center;
        curPosition = center;
    }

    public void hoodHigh(){hood1.setPosition(hoodMax);}
    public void hoodLow(){hood1.setPosition(hoodMin);}

    public void update()
    {

        cameraAimAngle = aprilTagUtility.getAngleToGoal();
        aimAngle = cameraAngleToAim(cameraAimAngle);
        RobotBase.setTelemetry1("aimAngle", aimAngle);
    }

    public void updateCur()
    {
        curPosition = turret.getPosition();
    }

     /**
     * this method is used to account for the fact that the shooter and the camera are not in the same place,
      * which could account for some discrepancies in the aimAngle
     * @param angle made by the camera and the goal
     * @return angle made by the shooter and the goal
     */
    private double cameraAngleToAim(double angle)
    {
        double cameraDistance = aprilTagUtility.getDistanceToGoal();//angle from camera to goal
        RobotBase.setTelemetry1("camera range",cameraDistance);
        double curRadAngle = 90 + Math.toRadians(angle);//helper angle
        distance = Math.sqrt( (cameraDistance * cameraDistance + cameraShooterOffset * cameraShooterOffset - 2*distance*cameraShooterOffset * Math.cos(curRadAngle) ) ); //use law of cosines to solve for distance to goal
        RobotBase.setTelemetry1("real distance", distance);
        return Math.toDegrees( Math.asin ( (cameraDistance * Math.sin(curRadAngle) ) / distance ) );
    }

    private double angleToPosition(double angle)
    {
        angle = Math.abs(angle);
        double position = angle / 1800.0 * turretMultiplier * (88.0/17.0);
        return position;
    }

    public double aimTurret()
    {
        update();
        if(aprilTagUtility.isBlue) aimPosition = center - angleToPosition(aimAngle);
        if(!aprilTagUtility.isBlue) aimPosition = center + angleToPosition(aimAngle);
        if(checkPosition(aimPosition)) turret.setPosition(aimPosition);
        return aimPosition;
    }

    public void center()
    {
        updateCur();
        turret.setPosition(center);
        updateCur();
    }

    private boolean checkPosition(double position)
    {
        if(position >= right && position <= left) return true;
        else return false;
    }

    public void incRight()
    {
        updateCur();
        curPosition -= increment;
        if(checkPosition(curPosition)) turret.setPosition(curPosition);
        else turret.setPosition(right);
        updateCur();
    }
    public  void incLeft()
    {
        updateCur();
        curPosition += increment;
        if(checkPosition(curPosition)) turret.setPosition(curPosition);
        else turret.setPosition(left);
        updateCur();
    }

    public void crIncRight()
    {
        updateCur();
        curPosition -= crIncrement;
        if(checkPosition(curPosition)) turret.setPosition(curPosition);
        else turret.setPosition(right);
        updateCur();
    }
    public void crIncLeft()
    {
        updateCur();
        curPosition += crIncrement;
        if(checkPosition(curPosition)) turret.setPosition(curPosition);
        else turret.setPosition(left);
        updateCur();
    }


}