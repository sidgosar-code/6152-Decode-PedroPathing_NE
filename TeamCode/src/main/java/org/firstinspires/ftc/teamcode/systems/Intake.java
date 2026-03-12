package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
    public static double reg = 500;
    public static double max = 536;
    public static double out = -500;
    public Intake(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        RobotBase.useEncoders(intake);
        intake.setDirection(DcMotor.Direction.REVERSE);
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
