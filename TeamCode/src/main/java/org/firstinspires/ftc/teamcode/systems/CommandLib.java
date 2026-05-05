package org.firstinspires.ftc.teamcode.systems;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;

import com.pedropathing.ivy.Command;


public class CommandLib {
    // Subsystem Commands
    public static Command setShooter, stopShooter,
            startIntake, stopIntake, startIntakeTransferMode, startIntakeTransferMode1, stopIntakeTransferMode,
            sort, waitFeed, flick,stopAll,
            fullTransfer, stopTransfer;


    /**
     * Call this in your Auto OpMode after initializing RobotBase
     */
    public static void create(RobotBase robot) {
        // Wrapping existing methods into Ivy Commands
        setShooter = instant(() -> robot.shooter.setShooter()).requiring(robot.shooter.s);
        stopShooter = instant( () -> robot.shooter.stopShooter()).requiring(robot.shooter.s);
        startIntake = instant(() -> robot.intake.startIntake()).requiring(robot.intake.i);
        startIntakeTransferMode = instant(() -> robot.transfer.startIntakeMode());

        stopIntakeTransferMode = instant(() -> robot.transfer.stopAll());
        stopIntake = instant(() -> robot.intake.stopIntake()).requiring(robot.intake.i);
        sort = instant(() -> robot.sorting.sort());

        flick = instant(() -> robot.transfer.flickOne());
        waitFeed = waitMs(Transfer.feedWait);
        fullTransfer = instant(() -> robot.transfer.fullTransfer());
        stopTransfer = instant(() -> robot.transfer.stopAll());

        stopAll = instant(() -> robot.stopAll());


    }
}