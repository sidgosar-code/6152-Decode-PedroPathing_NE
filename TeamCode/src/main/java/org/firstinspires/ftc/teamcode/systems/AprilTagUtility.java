package org.firstinspires.ftc.teamcode.systems;


import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class AprilTagUtility
{
    public boolean blue;

    public int goalID;
    public AprilTagDetection goalTag;
    public static int width = 640;
    public static int height = 480;
    public AprilTagProcessor aprilTagProcessor;
    public VisionPortal visionPortal;
    public List<AprilTagDetection> detectedTags = new ArrayList<>();
    public Telemetry telemetry;

    public AprilTagUtility(HardwareMap hardwareMap, Telemetry telemetry, boolean isBlue)
    {
        init(hardwareMap, telemetry);
        setColor(isBlue);

    }



    public void init(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(false)
                .setDrawCubeProjection(false)
                .setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "webcam"));
        builder.setCameraResolution(new Size(width, height));
        builder.addProcessor(aprilTagProcessor);

        visionPortal = builder.build();
    }

    public void update()
    {
        detectedTags = aprilTagProcessor.getDetections();
        goalTag = getTagByID(goalID);
    }
    public void displayDetectionTelemetry(AprilTagDetection detectedId)
    {
        if(detectedId == null) return;
        if (detectedId.metadata != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", detectedId.id, detectedId.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detectedId.ftcPose.x, detectedId.ftcPose.y, detectedId.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detectedId.ftcPose.pitch, detectedId.ftcPose.roll, detectedId.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detectedId.ftcPose.range, detectedId.ftcPose.bearing, detectedId.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detectedId.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detectedId.center.x, detectedId.center.y));
        }
    }   // end for() loop


    public List<AprilTagDetection> getDetectedTags()
    {
        return detectedTags;
    }

    public AprilTagDetection getTagByID(int iD)
    {
        update();
        for(AprilTagDetection detection : detectedTags)
        {
            if(detection.id == iD)
            {
                return detection;
            }
        }
        return null;
    }
    public int getTag()
    {
        update();
        for(AprilTagDetection detection : detectedTags)
        {
            if(detection.metadata != null) return detection.id;

        }
        return 0;
    }

    public int getObeliskTag()
    {
        update();
        int id = 0;
        for(AprilTagDetection detection : detectedTags)
        {
            id = detection.id;
            if(id == 21 || id == 22 || id == 23)
            {
                return id;
            }
            else id = 0;
        }
        return id;
    }

    public void setColor(boolean isBlue)//always true if blue
    {
        if(isBlue){ blue = true; setGoalID(20);}
        else {blue = false; setGoalID(24);}
    }
    private void setGoalID(int id)
    {
        goalID = id;
    }

    public double getDistanceToGoal()
    {
        update();
        goalTag = getTagByID(goalID);
        if(goalTag != null)//check if tag is the goal ID
        {
            return goalTag.ftcPose.range;
        }
        else return 0;
    }

    public double getAngleToGoal()
    {
        update();
        goalTag = getTagByID(goalID);
        if(goalTag != null)//check if tag is the goal ID
        {
            return goalTag.ftcPose.bearing;
        }
        else return 0;
    }

    public void stop()
    {
        if(visionPortal != null)
        {
            visionPortal.close();
        }
    }

}
