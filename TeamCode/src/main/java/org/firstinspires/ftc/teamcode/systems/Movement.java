package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;

@Configurable
public class Movement
{
    public DcMotor br, bl, fr, fl;

    public Follower follower;

    public static double DRIVE_VALUE = 0.5;

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

    public void strafe(double distanceInches, double power)
    {
        RobotBase.useEncoders(fl);
        RobotBase.useEncoders(bl);
        RobotBase.useEncoders(fr);
        RobotBase.useEncoders(br);

        int ticks = distanceToTicks(distanceInches);

        br.setTargetPosition(-ticks);//just for now, so we can strafe
        bl.setTargetPosition(-ticks);
        fr.setTargetPosition(ticks);
        fl.setTargetPosition(ticks);

        //driveTrainEncoderSetup(DcMotor.RunMode.RUN_TO_POSITION);
        //power=Values.DRIVE_VALUE;
        drive(power);
    }

    public void drive(double power)
    {

        RobotBase.rtpMode(fl);
        RobotBase.rtpMode(bl);
        RobotBase.rtpMode(fr);
        RobotBase.rtpMode(br);

        fl.setPower(power);
        bl.setPower(power);
        fr.setPower(power);
        br.setPower(power);

        while(fl.isBusy() || bl.isBusy() || fr.isBusy() || br.isBusy())
        {
            int m = 4;
        }
        stopAll();
    }
    public void stopAll()
    {
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
    }

    public int distanceToTicks(double distanceInches)
    {
        double wheelDiameter = 4 * 0.0393701;       // in inches
        double ticksPerRev = 28;         // motor-specific
        double gearRatio = 1.0;           // motor:wheel
        double wheelCircumference = Math.PI * wheelDiameter;
        double rotations = distanceInches / wheelCircumference;
        return (int)(rotations * ticksPerRev * gearRatio * DRIVE_VALUE);
    }




}
