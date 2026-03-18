package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotBase;
import org.firstinspires.ftc.teamcode.unused.ModeManager;

@Configurable
public class Transfer
{
    public DcMotor feed;
    //public CRServo storage;
    public DcMotor storage;
    public Servo flicker;

    public boolean sortMode, spamMode;

    public static double feedReg = 0.7;
    public static double storageReg = 0.7;
    public static double feedMax  = 0.9;
    public  static double storageMax = 0.9;
    public static double feedIntake = 0.4;

    public static final double flickerRest = 0.625;
    public static final double flickerPush = flickerRest + 0.125;
    public static double flickWait = 300; //time to wait for flickerPush, in ms
    public static double feedWait = 100; //time for one ball to be fed to shooter, in ms
    public ElapsedTime timer;
    Telemetry telemetry;

    public Transfer(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap)
    {
        feed = hardwareMap.get(DcMotor.class, "feed");
        flicker = hardwareMap.get(Servo.class, "flicker");
        //storage = hardwareMap.get(CRServo.class, "storage");
        storage = hardwareMap.get(DcMotor.class, "storage");
        storage.setDirection(DcMotor.Direction.REVERSE);

        RobotBase.useEncoders(feed);
        RobotBase.useEncoders(storage);

        timer = new ElapsedTime();

        sortMode = true;
        spamMode = false;

    }
    public void flickOne()
    {
        timer.reset();
        flicker.setPosition(flickerPush);
        while(timer.milliseconds()<flickWait)
        {
            RobotBase.setTelemetry1("flicking");
        }
        flicker.setPosition(flickerRest);
    }


    public void fullTransfer()
    {
        startFeed();
        startStorage();
    }
    public void startIntakeMode()
    {
        storage.setPower(storageReg);
        feed.setPower(feedIntake);
    }

    public void feedOne()
    {
        timer.reset();
        feed.setPower(feedReg);
        while(timer.milliseconds()<feedWait)
        {
            int m = 4;
        }
        feed.setPower(0);
    }
    public void startFeed()
    {
        feed.setPower(feedReg);
    }
    public void startStorage()
    {
        storage.setPower(storageReg);
    }
    public void stopFeed()
    {
        feed.setPower(0);
    }
    public void stopStorage()
    {
        storage.setPower(0);
    }
    public void stopAll()
    {
        stopFeed();
        stopStorage();
    }
    public void out()
    {
        feed.setPower(-feedMax);
        storage.setPower(-storageMax);
    }

    public void intake()
    {
        if(sortMode)storage.setPower(-storageReg);
        else if(spamMode)
        {
            startIntakeMode();
        }
    }

    public void switchMode()
    {
        sortMode = !sortMode;
        spamMode = !spamMode;
        RobotBase.setTelemetry1("sortMode" + sortMode);
    }



}
