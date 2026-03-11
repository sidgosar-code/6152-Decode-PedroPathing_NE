package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.unused.Values;

@TeleOp
public class testShooter extends OpMode
{
    DcMotorEx shooter;
    DcMotor feed;
    //CRServo storage;
    DcMotor storage;
    TelemetryManager telemetryM;
    public double velocity;
    @Override
    public void init()
    { 
        shooter = hardwareMap.get(DcMotorEx.class,"shooter");
        feed = hardwareMap.get(DcMotor.class, "feed");
        //storage = hardwareMap.get(CRServo.class, "storage");
        storage = hardwareMap.get(DcMotor.class, "storage");
        storage.setDirection(DcMotor.Direction.REVERSE);
        feed.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidf = new PIDFCoefficients(Values.P, 0, 0, Values.F);
        shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);
        velocity = 1500;


        telemetry.addLine("Init complete.");

    }
    public void loop()
    {
        if(gamepad1.rightBumperWasPressed())
        {
            shooter.setVelocity(velocity);
        }
        if(gamepad1.leftBumperWasPressed())
        {
            shooter.setVelocity(0);
        }

        //shooter.setVelocity(velocity);
        if(gamepad1.dpadUpWasPressed())
        {
            velocity += 50;
        }
        if(gamepad1.dpadDownWasPressed())
        {
            velocity -= 50;
        }
        if(gamepad1.b)
        {
            feed.setPower(0.7);
        }
        else {
            feed.setPower(0);
        }
        if(gamepad1.x)
        {
            storage.setPower(1);
        }
        else {
            storage.setPower(0);
        }
        telemetry.addData("Velocity", velocity);
        telemetry.addData("realVelocity", shooter.getVelocity());
    }


}
