// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.RotationConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotationSubsystem;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.RotationCommand;
import frc.robot.commands.engageAuto;
import frc.robot.commands.mobilityAuto;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final RotationSubsystem rotationSubsystem = new RotationSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController =
      new Joystick(ControllerConstants.DRIVER_CONTROLLER_PORT);

  private final Joystick m_operatorController =
      new Joystick(ControllerConstants.OPERATOR_CONTROLLER_PORT);
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private final Command m_engageAuto = new engageAuto(driveSubsystem, elevatorSubsystem, rotationSubsystem, intakeSubsystem);
  private final Command m_mobilityAuto = new mobilityAuto(driveSubsystem, elevatorSubsystem, rotationSubsystem, intakeSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    elevatorSubsystem.setDefaultCommand(new ElevatorCommand(elevatorSubsystem, 0));
    intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem, 0, 0));
    rotationSubsystem.setDefaultCommand(new RotationCommand(rotationSubsystem, RotationConstants.ROTATION_STALL, RotationConstants.ROTATION_STALL));
    driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, m_driverController.getRawAxis(DriveConstants.DRIVE_AXIS), -m_driverController.getRawAxis(DriveConstants.TURN_AXIS) * DriveConstants.TURN_PROPORTION));
  
    m_chooser.setDefaultOption("Engage Auto", m_engageAuto);
    m_chooser.addOption("Mobility Auto", m_mobilityAuto);
    
    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  private void configureBindings() {

    new JoystickButton(m_operatorController, ElevatorConstants.ELEVATOR_UP_BUTTON).whileTrue(new ElevatorCommand(elevatorSubsystem, ElevatorConstants.ELEVATOR_UP_SPEED));
    new JoystickButton(m_operatorController, ElevatorConstants.ELEVATOR_DOWN_BUTTON).whileTrue(new ElevatorCommand(elevatorSubsystem, ElevatorConstants.ELEVATOR_DOWN_SPEED));
    new JoystickButton(m_operatorController, IntakeConstants.CUBE_IN_BUTTON).whileTrue(new IntakeCommand(intakeSubsystem, IntakeConstants.CUBE_IN_SPEED, IntakeConstants.CUBE_IN_STALL));
    new JoystickButton(m_operatorController, IntakeConstants.CONE_IN_BUTTON).whileTrue(new IntakeCommand(intakeSubsystem, IntakeConstants.CONE_IN_SPEED, IntakeConstants.CONE_IN_STALL));
    new JoystickButton(m_operatorController, IntakeConstants.INTAKE_OFF_BUTTON).whileTrue(new IntakeCommand(intakeSubsystem, 0, 0));
    new JoystickButton(m_operatorController, RotationConstants.ROTATION_DOWN_BUTTON).whileTrue(new RotationCommand(rotationSubsystem, RotationConstants.ROTATION_DOWN_SPEED, RotationConstants.ROTATION_STALL));
    new JoystickButton(m_operatorController, RotationConstants.ROTATION_UP_BUTTON).whileTrue(new RotationCommand(rotationSubsystem, RotationConstants.ROTATION_UP_SPEED, RotationConstants.ROTATION_STALL));
    new JoystickButton(m_driverController, DriveConstants.SLOW_BUTTON).onTrue(new ArcadeDriveCommand(driveSubsystem,  m_driverController.getRawAxis(DriveConstants.DRIVE_AXIS) * DriveConstants.DRIVE_SLOW, -m_driverController.getRawAxis(DriveConstants.TURN_AXIS) * DriveConstants.TURN_PROPORTION * DriveConstants.TURN_SLOW));
    new JoystickButton(m_driverController, DriveConstants.NORMAL_BUTTON).onTrue(new ArcadeDriveCommand(driveSubsystem, m_driverController.getRawAxis(DriveConstants.DRIVE_AXIS), -m_driverController.getRawAxis(DriveConstants.TURN_AXIS) * DriveConstants.TURN_PROPORTION));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_chooser.getSelected();
  }
}
