// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants.DriveConstants;;



public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private final WPI_VictorSPX drivefrontleft = new WPI_VictorSPX(DriveConstants.kDriveFrontLeft);
  private final WPI_VictorSPX drivefrontright = new WPI_VictorSPX(DriveConstants.kDriveFrontRight);
  private final WPI_VictorSPX drivebackleft = new WPI_VictorSPX(DriveConstants.kDriveBackLeft);
  private final WPI_VictorSPX drivebackright = new WPI_VictorSPX(DriveConstants.kDriveBackRight);

  private final MotorControllerGroup driveleft = new MotorControllerGroup(drivefrontleft, drivebackleft);
  private final MotorControllerGroup driveright = new MotorControllerGroup(drivefrontright, drivebackright);
  private final DifferentialDrive driverobot = new DifferentialDrive(driveleft, driveright);

  private final AHRS gyro = new AHRS(SPI.Port.kMXP);
  private final Encoder encoderleftdrive = new Encoder(DriveConstants.kLeftEncoderA, DriveConstants.kLeftEncoderB);

  public double getEncoderDrivePosition() {
    return (encoderleftdrive.getDistance());
  }

  public double getGyroYaw() {
    return (gyro.getYaw());
  }

  public double getGyroPitch() {
    return (gyro.getPitch());
  }

  public double getGyroRoll() {
    return (gyro.getRoll());
  }

  public DriveSubsystem() {
    drivefrontleft.setNeutralMode(NeutralMode.Brake);
    drivefrontright.setNeutralMode(NeutralMode.Brake);
    drivebackleft.setNeutralMode(NeutralMode.Brake);
    drivebackright.setNeutralMode(NeutralMode.Brake);
    driveright.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Roll", getGyroRoll());
    SmartDashboard.putNumber("Pitch", getGyroPitch());
    SmartDashboard.putNumber("Yaw", getGyroYaw());
    SmartDashboard.putNumber("Encoder Left Distance", encoderleftdrive.getDistance());

  }

  public void setMotor(double forwardSpeed, double turnSpeed) {
      driverobot.arcadeDrive(forwardSpeed, turnSpeed);
  }
}
