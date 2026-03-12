package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.unused.Values;

@TeleOp
@Configurable
public class testShooter extends OpMode
{
    DcMotorEx shooter;
    DcMotor feed;
    Servo hood1; public static double hoodMin = 0.67; public static double hoodMax = 0.60;
    //CRServo storage;
    DcMotor storage;
    TelemetryManager telemetryM;
    public static double feedP = 0.7;
    public double velocity;
    @Override
    public void init()
    { 
        shooter = hardwareMap.get(DcMotorEx.class,"shooter");
        feed = hardwareMap.get(DcMotor.class, "feed");
        //storage = hardwareMap.get(CRServo.class, "storage");
        storage = hardwareMap.get(DcMotor.class, "storage");
        hood1 = hardwareMap.get(Servo.class, "hood1");
        hood1.setDirection(Servo.Direction.REVERSE);
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
            feed.setPower(feedP);
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
        if(gamepad1.dpadLeftWasPressed())
        {
            hood1.setPosition(hoodMin);
        }
        if(gamepad1.dpadRightWasPressed())
        {
            hood1.setPosition(hoodMax);
        }
        telemetry.addData("Velocity", velocity);
        telemetry.addData("realVelocity", shooter.getVelocity());
    }


}
