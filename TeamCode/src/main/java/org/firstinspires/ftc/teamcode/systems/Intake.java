package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake
{
    public DcMotor intake;
    public Intake(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        intake = hardwareMap.get(DcMotor.class, "intake");
    }
}
