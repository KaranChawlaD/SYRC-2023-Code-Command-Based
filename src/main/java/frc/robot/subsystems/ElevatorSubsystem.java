// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  private final CANSparkMax elevatorLeft = new CANSparkMax(ElevatorConstants.ELEVATOR_LEFT, MotorType.kBrushless);
  private final CANSparkMax elevatorRight = new CANSparkMax(ElevatorConstants.ELEVATOR_RIGHT, MotorType.kBrushless);
  private final MotorControllerGroup elevator = new MotorControllerGroup(elevatorLeft, elevatorRight);
  private final RelativeEncoder encoderRightElevator = elevatorRight.getEncoder();
  private final DigitalInput limitSwitchElevator = new DigitalInput(ElevatorConstants.LIMIT_SWITCH_PORT);

  public double getEncoderElevatorPosition() {
    return (encoderRightElevator.getPosition());
  }

  public boolean getLimitSwitchElevatorState() {
    return (limitSwitchElevator.get());
  }

  public ElevatorSubsystem() {
    elevatorRight.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Right Encoder", getEncoderElevatorPosition());
    SmartDashboard.putBoolean("Limit Switch State", getLimitSwitchElevatorState());
    // This method will be called once per scheduler run
  }

  public void setMotor(double speed) {
    elevator.set(speed);
  }

}
