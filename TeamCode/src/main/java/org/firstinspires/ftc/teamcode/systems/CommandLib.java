package org.firstinspires.ftc.teamcode.systems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;


public class CommandLib {
    // Subsystem Commands
    public static Command setShooter, stopShooter,
            startIntake, stopIntake,
            sort,
            fullTransfer, stopTransfer;


    /**
     * Call this in your Auto OpMode after initializing RobotBase
     */
    public static void create(RobotBase robot) {
        // Wrapping existing methods into Ivy Commands
        setShooter = instant(() -> robot.shooter.setShooter() );
        stopShooter = instant( () -> robot.shooter.stopShooter());
        startIntake = instant(() -> robot.intake.startIntake());
        stopIntake = instant(() -> robot.intake.stopIntake());
        sort = instant(() -> robot.sorting.sort());
        fullTransfer = instant(() -> robot.transfer.fullTransfer());
        stopTransfer = instant(() -> robot.transfer.stopAll());


    }
}