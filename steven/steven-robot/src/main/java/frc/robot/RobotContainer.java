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
import frc.robot.commands.MoveForDistance;
import frc.robot.commands.MoveForTime;
import frc.robot.commands.RunIntake;
import frc.robot.commands.shooting.Launch;
import frc.robot.commands.shooting.Reload;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.climb.Climb;
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
  //things that will never change
  private static final class Config {
    public static final int k_joystickId = 0;
    public static final int k_intakeArmIn = 1;
    public static final int k_intakeArmOut = 2;
    public static final int k_reloadButtonId = 7;
    public static final int k_launchButtonId = 8;
    public static final int k_activateClimbId = 3;
    public static final int k_deactivateClimbId = 4;
    public static final double k_speed = 0.4;
    public static final double k_time = 9;
    public static final double k_distance = 4.999999;
  }

  // subsystems
  private Drivetrain m_drivetrain = new Drivetrain();
  private IntakeArm m_IntakeArm = new IntakeArm();
  private Hopper m_hopper = new Hopper();
  private Shooter m_shooter = new Shooter();
  private Climb m_climb = new Climb();

  //a joystick
  private Joystick m_joystick = new Joystick(Config.k_joystickId);

  //buttons
  private JoystickButton m_IntakeArmIn = new JoystickButton(m_joystick, Config.k_intakeArmIn);
  private JoystickButton m_IntakeArmOut = new JoystickButton(m_joystick, Config.k_intakeArmOut);
  private JoystickButton m_reloadButton = new JoystickButton(m_joystick, Config.k_reloadButtonId);
  private JoystickButton m_launchButton = new JoystickButton(m_joystick, Config.k_launchButtonId);
  private JoystickButton m_activateClimbButton = new JoystickButton(m_joystick, Config.k_activateClimbId);
  private JoystickButton m_deactivateClimbButton = new JoystickButton(m_joystick, Config.k_deactivateClimbId);
  
  //commands
  private Reload m_reload = new Reload(m_shooter);
  private Launch m_launch = new Launch(m_shooter);
  private ArcadeDrive m_arcadedrive = new ArcadeDrive(m_drivetrain, m_joystick);
  private RunIntake m_RunIntake = new RunIntake(m_hopper, m_IntakeArm, m_IntakeArmIn, m_IntakeArmOut);
  private MoveForTime m_moveforetime = new MoveForTime(m_drivetrain, Config.k_speed, Config.k_time);
  private MoveForDistance m_movefordistance = new MoveForDistance(Config.k_distance, m_drivetrain);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  //binds commands to the buttons
  private void configureButtonBindings() {
    m_reloadButton.whenPressed(m_reload);
    m_launchButton.whenPressed(m_launch);

    m_IntakeArmIn.whenPressed(m_IntakeArm.IntakeSetForward());
    m_IntakeArmOut.whenPressed(m_IntakeArm.IntakeSetBack());
    
    m_activateClimbButton.whenPressed(m_climb.extendArmUp());
    m_deactivateClimbButton.whenPressed(m_climb.retractArmDown());
  }

  public Command getAutonomousCommand() {
    return m_movefordistance;
  }

  public Command getTeleopCommand() {
    m_drivetrain.setDefaultCommand(m_arcadedrive);
    m_RunIntake.schedule();
    return null;
  }
}
