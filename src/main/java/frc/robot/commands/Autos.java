// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotationSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  
  public static CommandBase engageAuto(DriveSubsystem driveSubsystem, ElevatorSubsystem elevatorSubsystem, RotationSubsystem rotationSubsystem, IntakeSubsystem intakeSubsystem) {
    return Commands.sequence(
    new RotationCommand(rotationSubsystem, -0.5, 0.0).withTimeout(0.3),
    new ElevatorCommand(elevatorSubsystem, -0.5),
    new ElevatorCommand(elevatorSubsystem, 0.15).withTimeout(0.1),
    new RotationCommand(rotationSubsystem, 0.9, 0).withTimeout(0.7),
    new IntakeCommand(intakeSubsystem, -0.9, 0).withTimeout(0.5),
    new RotationCommand(rotationSubsystem, -0.6, 0).withTimeout(0.7),
    new ElevatorCommand(elevatorSubsystem, 0.4).withTimeout(1.5),
    new ArcadeDriveCommand(driveSubsystem, 0.65, 0).withTimeout(2),
    new DriveEncoderCommand(driveSubsystem, 0.55, -1240)
    );
  }

  public static CommandBase mobilityAuto(DriveSubsystem driveSubsystem, ElevatorSubsystem elevatorSubsystem, RotationSubsystem rotationSubsystem, IntakeSubsystem intakeSubsystem) {
    return Commands.sequence(
    new RotationCommand(rotationSubsystem, -0.5, 0.0).withTimeout(0.3),
    new ElevatorCommand(elevatorSubsystem, -0.5),
    new ElevatorCommand(elevatorSubsystem, 0.15).withTimeout(0.1),
    new RotationCommand(rotationSubsystem, 0.9, 0).withTimeout(0.7),
    new IntakeCommand(intakeSubsystem, -0.9, 0).withTimeout(0.5),
    new RotationCommand(rotationSubsystem, -0.6, 0).withTimeout(0.7),
    new ElevatorCommand(elevatorSubsystem, 0.4).withTimeout(1.5),
    new ArcadeDriveCommand(driveSubsystem, 0.65, 0).withTimeout(3.5)
    );
  } 

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
