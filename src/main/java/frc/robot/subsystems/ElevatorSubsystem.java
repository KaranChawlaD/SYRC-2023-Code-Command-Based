// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  private final CANSparkMax elevatorleft = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax elevatorright = new CANSparkMax(2, MotorType.kBrushless);
  private final MotorControllerGroup elevator= new MotorControllerGroup(elevatorleft, elevatorright);
  private final RelativeEncoder encoderrightelevator = elevatorright.getEncoder();
  private final DigitalInput limitswitchelevator = new DigitalInput(0);

  public double getEncoderElevatorPosition() {
    return (encoderrightelevator.getPosition());
  }

  public boolean getLimitSwitchElevatorState() {
    return (limitswitchelevator.get());
  }

  public ElevatorSubsystem() {
    elevatorright.setInverted(true);
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
