package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Configurable
public class Movement
{
    public DcMotor backRightMotor, backLeftMotor, frontRightMotor, frontLeftMotor;

    public static double xV, yV, rV; //for motor directions, can try in panels

    public static double slowValue = 0.3;

    public Movement(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        //initialize on hardwaremap
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        //set directions
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        xV = 1;
        yV = 1;
        rV = 1;
    }

    public void teleOpDrive(double x, double y, double rotation)
    {
        x*=xV;
        y*=yV;
        rotation*=rV;
        frontLeftMotor.setPower(y - x - rotation);
        backLeftMotor.setPower(y + x - rotation);
        frontRightMotor.setPower(y + x + rotation);
        backRightMotor.setPower(y - x + rotation);
    }

    public void slowTeleOpDrive(double x, double y, double rotation)
    {
        frontLeftMotor.setPower((y - x - rotation) * slowValue);
        backLeftMotor.setPower((y + x - rotation) * slowValue);
        frontRightMotor.setPower((y + x + rotation) * slowValue);
        backRightMotor.setPower((y - x + rotation) * slowValue);
    }



}
