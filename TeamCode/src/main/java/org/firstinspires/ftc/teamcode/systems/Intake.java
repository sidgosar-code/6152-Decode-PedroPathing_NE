package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.RobotBase;
@Configurable
public class Intake
{
    public DcMotorEx intake;

    /*
    public static double reg = 0.7;
    public static double max = 0.95;
    public static double out = -0.8;
     */
    public static double reg = 960;
    public static double max = 1150;
    public static double out = -1000;

    public static double P = 13;
    public static double F = 13;
    public  Transfer transfer;
    public Intake(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, Transfer transfer)
    {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        this.transfer = transfer;
        RobotBase.useEncoders(intake);
        intake.setDirection(DcMotor.Direction.REVERSE);
        PIDFCoefficients intakePIDF = new PIDFCoefficients(P, 0, 0, F);
        intake.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, intakePIDF);
    }
    /*
    public void startIntake()
    {
        intake.setPower(reg);
    }
    public void maxIntake()
    {
        intake.setPower(max);
    }
    public void outTake()
    {
        intake.setPower(out);
    }
    public void stopIntake()
    {
        intake.setPower(0);
    }

     */

    public void startIntake()
    {
        intake.setVelocity(reg);
        transfer.intake();
    }

    public void maxIntake()
    {
        intake.setVelocity(max);
    }
    public void outTake()
    {
        intake.setVelocity(out);
    }
    public void stopIntake()
    {
        intake.setVelocity(0);
    }
}
