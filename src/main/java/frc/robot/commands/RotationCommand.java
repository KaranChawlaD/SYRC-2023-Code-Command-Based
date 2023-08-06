// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RotationSubsystem;

public class RotationCommand extends CommandBase {
  private final RotationSubsystem rotationSubsystem;
  private final double speed;
  private final double stall;
  /** Creates a new RotationCommand. */
  public RotationCommand(RotationSubsystem rotationSubsystem, double speed, double stall) {
    this.rotationSubsystem = rotationSubsystem;
    this.speed = speed;
    this.stall = stall;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rotationSubsystem.setMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rotationSubsystem.setMotor(stall);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
