// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotationSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class mobilityAuto extends SequentialCommandGroup {
  /** Creates a new mobilityAuto. */
  public mobilityAuto(DriveSubsystem driveSubsystem, ElevatorSubsystem elevatorSubsystem, RotationSubsystem rotationSubsystem, IntakeSubsystem intakeSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new RotationCommand(rotationSubsystem, AutoConstants.kRotationOutSpeed, 0).withTimeout(AutoConstants.kRotationOutTime),
      new ElevatorCommand(elevatorSubsystem, AutoConstants.kElevatorUpSpeed),
      new ElevatorCommand(elevatorSubsystem, AutoConstants.kElevatorAlignSpeed).withTimeout(AutoConstants.kElevatorAlignTime),
      new RotationCommand(rotationSubsystem, AutoConstants.kRotationUpSpeed, 0).withTimeout(AutoConstants.kRotationUpTime),
      new IntakeCommand(intakeSubsystem, AutoConstants.kIntakeShootSpeed, 0).withTimeout(AutoConstants.kIntakeShootTime),
      new RotationCommand(rotationSubsystem, AutoConstants.kRotationDownSpeed, 0).withTimeout(AutoConstants.kRotationDownTime),
      new ElevatorCommand(elevatorSubsystem, AutoConstants.kElevatorDownSpeed).withTimeout(AutoConstants.kElevatorDownTime),
      new ArcadeDriveCommand(driveSubsystem, AutoConstants.kAutoDriveSpeed, 0).withTimeout(AutoConstants.kMobilityDriveTime)
    );
  }
}
