package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Configurable
@TeleOp
public class PIDFtune extends OpMode
{
    public DcMotorEx shooter;
    public double highVelocity = 1500;
    public double lowVelocity = 900;

    //double curTargetVelocity = highVelocity;
    static double curTargetVelocity = 0;

    static double F = 0;
    static double P = 0;
    double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};

    int stepIndex = 1;
    @Override
    public void init()
    {
        shooter = hardwareMap.get(DcMotorEx.class,"shooter");
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidf = new PIDFCoefficients(P, 0, 0, F);
        //shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);
        telemetry.addLine("Init complete.");
    }
    public void loop()
    {
        //get gamepad commands
        //set target velocity
        shooter.setVelocity(curTargetVelocity);
        //update telemetry
        if(gamepad1.yWasPressed())
        {
            if(curTargetVelocity == highVelocity)
            {
                curTargetVelocity = lowVelocity;
            }
            else
            {
                curTargetVelocity = highVelocity;
            }
        }
        if(gamepad1.bWasPressed())
        {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if(gamepad1.dpadRightWasPressed())
        {
            F += stepSizes[stepIndex];
        }
        if(gamepad1.dpadLeftWasPressed())
        {
            F -= stepSizes[stepIndex];
        }
        if(gamepad1.dpadUpWasPressed())
        {
            P += stepSizes[stepIndex];
        }
        if(gamepad1.dpadDownWasPressed())
        {
            P-=stepSizes[stepIndex];
        }

        //set new PIDF coefficients
        PIDFCoefficients pidf = new PIDFCoefficients(P, 0, 0, F);
        shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);

        shooter.setVelocity(curTargetVelocity);

        double curVelocity = shooter.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("Target Velocity", curTargetVelocity);
        telemetry.addData("Current Velocity", "%.2f", curVelocity);
        telemetry.addData("Error", "%.2f", error);
        telemetry.addLine("=====Fine-tune:=====");
        telemetry.addData("Tuning P (D-Pad U/D)", "%.4f", P);
        telemetry.addData("Tuning F (D-Pad L/R)", "%.4f", F);
        telemetry.addData("Step Size (B Button)", "%.4f", stepSizes[stepIndex]);

        telemetry.update();
    }
}
