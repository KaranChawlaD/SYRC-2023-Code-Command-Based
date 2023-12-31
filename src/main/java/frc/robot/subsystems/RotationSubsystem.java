// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RotationConstants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class RotationSubsystem extends SubsystemBase {
  /** Creates a new RotationSubsystem. */
  private final WPI_VictorSPX rotation = new WPI_VictorSPX(RotationConstants.ROTATION);

  public RotationSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotor(double speed) {
    rotation.set(speed);
  }
}
