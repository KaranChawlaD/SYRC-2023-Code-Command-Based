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
      new Joystick(ControllerConstants.kDriverControllerPort);

  private final Joystick m_operatorController =
      new Joystick(ControllerConstants.kOperatorControllerPort);
  
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
    rotationSubsystem.setDefaultCommand(new RotationCommand(rotationSubsystem, RotationConstants.kRotationStall, RotationConstants.kRotationStall));
    driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, m_driverController.getRawAxis(DriveConstants.kDriveAxis), -m_driverController.getRawAxis(DriveConstants.kTurnAxis) * DriveConstants.kTurnProportion));
  
    m_chooser.setDefaultOption("Engage Auto", m_engageAuto);
    m_chooser.addOption("Mobility Auto", m_mobilityAuto);
    
    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  private void configureBindings() {

    new JoystickButton(m_operatorController, ElevatorConstants.kElevatorUpButton).whileTrue(new ElevatorCommand(elevatorSubsystem, ElevatorConstants.kElevatorUpSpeed));
    new JoystickButton(m_operatorController, ElevatorConstants.kElevatorDownButton).whileTrue(new ElevatorCommand(elevatorSubsystem, ElevatorConstants.kElevatorDownSpeed));
    new JoystickButton(m_operatorController, IntakeConstants.kCubeInButton).whileTrue(new IntakeCommand(intakeSubsystem, IntakeConstants.kCubeInSpeed, IntakeConstants.kCubeInStall));
    new JoystickButton(m_operatorController, IntakeConstants.kConeInButton).whileTrue(new IntakeCommand(intakeSubsystem, IntakeConstants.kConeInSpeed, IntakeConstants.kConeInStall));
    new JoystickButton(m_operatorController, IntakeConstants.kIntakeOffButton).whileTrue(new IntakeCommand(intakeSubsystem, 0, 0));
    new JoystickButton(m_operatorController, RotationConstants.kRotationDownButton).whileTrue(new RotationCommand(rotationSubsystem, RotationConstants.kRotationDownSpeed, RotationConstants.kRotationStall));
    new JoystickButton(m_operatorController, RotationConstants.kRotationUpButton).whileTrue(new RotationCommand(rotationSubsystem, RotationConstants.kRotationUpSpeed, RotationConstants.kRotationStall));
    new JoystickButton(m_driverController, DriveConstants.kSlowButton).onTrue(new ArcadeDriveCommand(driveSubsystem,  m_driverController.getRawAxis(DriveConstants.kDriveAxis) * DriveConstants.kDriveSlow, -m_driverController.getRawAxis(DriveConstants.kTurnAxis) * DriveConstants.kTurnProportion * DriveConstants.kTurnSlow));
    new JoystickButton(m_driverController, DriveConstants.kNormalButton).onTrue(new ArcadeDriveCommand(driveSubsystem, m_driverController.getRawAxis(DriveConstants.kDriveAxis), -m_driverController.getRawAxis(DriveConstants.kTurnAxis) * DriveConstants.kTurnProportion));

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
