// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotationSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class engageAuto extends SequentialCommandGroup {
  /** Creates a new engageAuto. */
  public engageAuto(DriveSubsystem driveSubsystem, ElevatorSubsystem elevatorSubsystem, RotationSubsystem rotationSubsystem, IntakeSubsystem intakeSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new RotationCommand(rotationSubsystem, -0.5, 0.0).withTimeout(0.3),
    new ElevatorCommand(elevatorSubsystem, -0.5),
    new ElevatorCommand(elevatorSubsystem, 0.15).withTimeout(0.1),
    new RotationCommand(rotationSubsystem, 0.9, 0).withTimeout(0.7),
    new IntakeCommand(intakeSubsystem, -0.9, 0).withTimeout(0.5),
    new RotationCommand(rotationSubsystem, -0.6, 0).withTimeout(0.7),
    new ElevatorCommand(elevatorSubsystem, 0.4).withTimeout(1.5),
    new ArcadeDriveCommand(driveSubsystem, 0.65, 0).withTimeout(2),
    new DriveEncoderCommand(driveSubsystem, 0.55, -1240));
  }
}
