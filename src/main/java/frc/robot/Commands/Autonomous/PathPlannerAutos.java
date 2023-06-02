package frc.robot.Commands.Autonomous;

import java.util.HashMap;
import java.util.Map;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Subsystems.Swerve.SwerveDrive;

public class PathPlannerAutos {
    // The starting and ending gyro angle in degrees (CW positive)
    public static double startingGyroAngle;
    public static double endingGyroAngle;

    private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
            Map.entry("LogTest", new InstantCommand(() -> SmartDashboard.putString("Log", "Test")))));

    public static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
            SwerveDrive.getInstance()::getPose2d,
            SwerveDrive.getInstance()::resetPose2d,
            new PIDConstants(5, 0, 0),
            new PIDConstants(1.5, 0, 0),
            SwerveDrive.getInstance()::drive,
            eventMap,
            true,
            SwerveDrive.getInstance());

    public static CommandBase TestPath() {
        startingGyroAngle = 90;
        endingGyroAngle = 0;
        
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("Test Path", new PathConstraints(4, 3)));
    }
}
