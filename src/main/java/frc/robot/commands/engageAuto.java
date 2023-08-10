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
public class engageAuto extends SequentialCommandGroup {
  /** Creates a new engageAuto. */
  public engageAuto(DriveSubsystem driveSubsystem, ElevatorSubsystem elevatorSubsystem, RotationSubsystem rotationSubsystem, IntakeSubsystem intakeSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new RotationCommand(rotationSubsystem, AutoConstants.ROTATION_OUT_SPEED, 0).withTimeout(AutoConstants.ROTATION_OUT_TIME),
      new ElevatorCommand(elevatorSubsystem, AutoConstants.ELEVATOR_UP_SPEED),
      new ElevatorCommand(elevatorSubsystem, AutoConstants.ELEVATOR_ALIGN_SPEED).withTimeout(AutoConstants.ELEVATOR_ALIGN_TIME),
      new RotationCommand(rotationSubsystem, AutoConstants.ROTATION_UP_SPEED, 0).withTimeout(AutoConstants.ROTATION_UP_TIME),
      new IntakeCommand(intakeSubsystem, AutoConstants.INTAKE_SHOOT_SPEED, 0).withTimeout(AutoConstants.INTAKE_SHOOT_TIME),
      new RotationCommand(rotationSubsystem, AutoConstants.ROTATION_DOWN_SPEED, 0).withTimeout(AutoConstants.ROTATION_DOWN_TIME),
      new ElevatorCommand(elevatorSubsystem, AutoConstants.ELEVATOR_DOWN_SPEED).withTimeout(AutoConstants.ELEVATOR_DOWN_TIME),
      new ArcadeDriveCommand(driveSubsystem, AutoConstants.AUTO_DRIVE_SPEED, 0).withTimeout(AutoConstants.ENGAGE_DRIVE_TIME),
      new DriveEncoderCommand(driveSubsystem, AutoConstants.ENGAGE_DRIVE_SPEED, AutoConstants.ENGAGE_ENCODER_VALUE));
  }
}
