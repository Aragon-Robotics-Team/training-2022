// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.intake.Hopper;
import frc.robot.subsystems.intake.IntakeArm;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private static final class Config {
    public static final int k_joystickId = 0;
    public static final int k_intakeArmIn = 1;
    public static final int k_intakeArmOut = 2;
  }

  // The robot's subsystems and commands are defined here...
  private Drivetrain m_drivetrain = new Drivetrain();
  private Joystick m_joystick = new Joystick(Config.k_joystickId);
  private ArcadeDrive m_arcadedrive = new ArcadeDrive(m_drivetrain, m_joystick);
  private IntakeArm m_IntakeArm = new IntakeArm();
  private Hopper m_hopper = new Hopper();
  private JoystickButton m_IntakeArmIn = new JoystickButton(m_joystick, Config.k_intakeArmIn);
  private JoystickButton m_IntakeArmOut = new JoystickButton(m_joystick, Config.k_intakeArmOut);
  private RunIntake m_RunIntake = new RunIntake(m_hopper, m_IntakeArm, m_IntakeArmIn, m_IntakeArmOut);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_IntakeArmIn.whenPressed(m_IntakeArm.IntakeSetForward());
    m_IntakeArmOut.whenPressed(m_IntakeArm.IntakeSetBack());
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public Command getTeleopCommand() {
    m_drivetrain.setDefaultCommand(m_arcadedrive);
    m_RunIntake.schedule();
    return null;
  }
}
