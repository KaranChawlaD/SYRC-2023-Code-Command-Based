// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
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
      new Joystick(OperatorConstants.kDriverControllerPort);

  private final Joystick m_operatorController =
      new Joystick(OperatorConstants.kOperatorControllerPort);
  
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
    rotationSubsystem.setDefaultCommand(new RotationCommand(rotationSubsystem, 0.15, 0.15));
    driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, m_driverController.getRawAxis(1), -m_driverController.getRawAxis(4)));
  
    m_chooser.setDefaultOption("Engage Auto", m_engageAuto);
    m_chooser.addOption("Mobility Auto", m_mobilityAuto);
    
    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  private void configureBindings() {

    new JoystickButton(m_operatorController, 4).whileTrue((new ElevatorCommand(elevatorSubsystem, -0.6)));
    new JoystickButton(m_operatorController, 2).whileTrue(new ElevatorCommand(elevatorSubsystem, 0.3));
    new JoystickButton(m_operatorController, 6).whileTrue(new IntakeCommand(intakeSubsystem, 0.4, 0.2));
    new JoystickButton(m_operatorController, 8).whileTrue(new IntakeCommand(intakeSubsystem, -0.5, -0.2));
    new JoystickButton(m_operatorController, 3).whileTrue(new IntakeCommand(intakeSubsystem, 0, 0));
    new JoystickButton(m_operatorController, 7).whileTrue(new RotationCommand(rotationSubsystem, -0.55, 0.15));
    new JoystickButton(m_operatorController, 5).whileTrue(new RotationCommand(rotationSubsystem, 0.95, 0.15));

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
