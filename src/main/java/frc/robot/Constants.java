// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class ControllerConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }
  public static class DriveConstants {
    public static final int kDriveFrontLeft = 4;
    public static final int kDriveFrontRight = 6;
    public static final int kDriveBackLeft = 12;
    public static final int kDriveBackRight = 5;
    public static final int kLeftEncoderA = 1;
    public static final int kLeftEncoderB = 2;
    public static final int kDriveAxis = 1;
    public static final int kTurnAxis = 4;
    public static final double kTurnProportion = 0.7;
    public static final double kDriveSlow = 0.5;
    public static final double kTurnSlow = 0.8;
    public static final int kSlowButton = 1;
    public static final int kNormalButton = 2;
  }
  public static class ElevatorConstants {
    public static final int kElevatorLeft = 1;
    public static final int kElevatorRIght = 2;
    public static final int kLimitSwitchPort = 0;
    public static final int kElevatorUpButton = 4;
    public static final int kElevatorDownButton = 2;
    public static final double kElevatorUpSpeed = -0.6;
    public static final double kElevatorDownSpeed = 0.3;
  }
  public static class IntakeConstants {
    public static final int kIntake = 3;
    public static final int kCubeInButton = 6;
    public static final int kConeInButton = 8;
    public static final int kIntakeOffButton = 3;
    public static final double kCubeInSpeed = 0.4;
    public static final double kCubeInStall = 0.2;
    public static final double kConeInSpeed = -0.5;
    public static final double kConeInStall = -0.2;
  }
  public static class RotationConstants {
    public static final int kRotation = 13;
    public static final double kRotationStall = 0.15;
    public static final int kRotationUpButton = 5;
    public static final int kRotationDownButton = 7;
    public static final double kRotationUpSpeed = 0.95;
    public static final double kRotationDownSpeed = -0.55;
  }
  public static class AutoConstants {
    //Rotation
    public static final double kRotationOutTime = 0.3;
    public static final double kRotationUpTime = 0.7;
    public static final double kRotationDownTime = 0.7;
    public static final double kRotationOutSpeed = -0.5;
    public static final double kRotationUpSpeed = 0.9;
    public static final double kRotationDownSpeed = -0.6;
  
    //Intake
    public static final double kIntakeShootTime= 0.5;
    public static final double kIntakeShootSpeed= -0.9;

    //Elevator
    public static final double kElevatorAlignTime= 0.1;
    public static final double kElevatorDownTime= 1.5;
    public static final double kElevatorUpSpeed = -0.5;
    public static final double kElevatorAlignSpeed = 0.15;
    public static final double kElevatorDownSpeed = 0.4;

    //Drive
    public static final double kEngageDriveTime = 2.0;
    public static final double kAutoDriveSpeed = 0.65;
    public static final double kEngageDriveSpeed = 0.55;
    public static final double kEngageEncoderValue = -1240;
    public static final double kMobilityDriveTime = 3.5;
  }
}
