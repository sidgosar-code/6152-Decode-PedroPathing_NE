package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Shooting;
import org.firstinspires.ftc.teamcode.systems.Sorting;
import org.firstinspires.ftc.teamcode.systems.Transfer;

public class RobotBase
{
    public Shooting shooter;
    public Transfer transfer;
    public Sorting sorting;
    public Intake intake;

    //constructor here
    public void initHardware(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        transfer = new Transfer(hardwareMap);
        shooter = new Shooting(hardwareMap);
        sorting = new Sorting(hardwareMap);
        intake = new Intake(hardwareMap);
    }

    //encoder switching methods, overloaded for DcMotorEx
    public static void useEncoders(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);}
    public static void useEncoders(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);}
    public static void noEncoders(DcMotorEx motor) {motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);}
    public static void noEncoders(DcMotor motor) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);}
}
