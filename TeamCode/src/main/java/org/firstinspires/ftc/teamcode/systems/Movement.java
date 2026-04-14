package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;

@Configurable
public class Movement
{
    public DcMotor br, bl, fr, fl;

    public static double xV, yV, rV; //for motor directions, can try in panels

    public static double slowValue = 0.3;

    public Movement(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        //initialize on hardwaremap
        br = hardwareMap.get(DcMotor.class, "backRightMotor");
        bl = hardwareMap.get(DcMotor.class, "backLeftMotor");
        fr = hardwareMap.get(DcMotor.class, "frontRightMotor");
        fl = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        //set directions
        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);
        xV = 1;
        yV = 1;
        rV = 1;
    }

    public void teleOpDrive(double x, double y, double rotation)
    {
        x*=xV;
        y*=yV;
        rotation*=rV;
        fl.setPower(y - x - rotation);
        bl.setPower(y + x - rotation);
        fr.setPower(y + x + rotation);
        br.setPower(y - x + rotation);
    }

    public void slowTeleOpDrive(double x, double y, double rotation)
    {
        fl.setPower((y - x - rotation) * slowValue);
        bl.setPower((y + x - rotation) * slowValue);
        fr.setPower((y + x + rotation) * slowValue);
        br.setPower((y - x + rotation) * slowValue);
    }



}
