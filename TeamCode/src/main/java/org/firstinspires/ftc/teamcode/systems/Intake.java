package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;

public class Intake
{
    public DcMotor intake;
    public static double reg = 0.7;
    public static double max = 0.95;
    public static double out = -0.8;
    public Intake(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        intake = hardwareMap.get(DcMotor.class, "intake");
        RobotBase.useEncoders(intake);
    }
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
}
