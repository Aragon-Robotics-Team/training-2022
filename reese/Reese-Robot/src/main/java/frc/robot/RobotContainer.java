// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.MoveForDistance;
import frc.robot.commands.MoveForTime;
import frc.robot.commands.MoveWithPID;
import frc.robot.commands.RunIntake;
import frc.robot.commands.shooting.Launch;
import frc.robot.commands.shooting.Reload;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.intake.Hopper;
import frc.robot.subsystems.intake.IntakeArm;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private static final class Config{
    public static final int kjoystickID = 0;
    public static final int kJoystickButtonIDIntakeOut = 90;
    public static final int kJoystickButtonIDIntakeIn = 99;
    public static final int kJoystickButtonIDIntakeArmIn = 92;
    public static final int kJoystickButtonIDIntakeArmOut = 78;
    public static int kJoystickButtonIDReload = 7;
    public static int kJoystickButtonIDLaunch = 8;
    public static double kAutoSpeed = 0.4;
    public static double kAutoTime = 3;
    public static double kDistance = 4;
    public static double kSetPoint = 10;

  }
  

  // The robot's subsystems and commands are defined here..
  private Joystick m_joyStick =  new Joystick(Config.kjoystickID);
  private Drivetrain m_drivetrain = new Drivetrain();
  private ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drivetrain, m_joyStick);
  private IntakeArm m_intakeArm = new IntakeArm();
  private Hopper m_hopper = new Hopper(); // makes buttons
  private JoystickButton m_IntakeIn = new JoystickButton(m_joyStick, Config.kJoystickButtonIDIntakeIn);
  private JoystickButton m_IntakeOut = new JoystickButton(m_joyStick,Config.kJoystickButtonIDIntakeOut);
  private JoystickButton m_intakeArmOut = new JoystickButton(m_joyStick,Config.kJoystickButtonIDIntakeArmOut);
  private JoystickButton m_intakeArmIn = new JoystickButton(m_joyStick,Config.kJoystickButtonIDIntakeArmIn);
  private RunIntake m_runintake = new RunIntake(m_intakeArm, m_hopper,m_IntakeIn, m_IntakeOut);
  private Shooter m_shooter = new Shooter(); // subsystems
  private Launch m_launch = new Launch(m_shooter);
  private Reload m_reload = new Reload(m_shooter);
  private JoystickButton m_reloadButton = new JoystickButton(m_joyStick,Config.kJoystickButtonIDReload);
  private JoystickButton m_launchButton = new JoystickButton(m_joyStick,Config. kJoystickButtonIDLaunch );
  private Climb m_climb = new Climb(); 
  private JoystickButton m_climbExtendButton = new JoystickButton(m_joyStick, 3);
  private JoystickButton m_climbRetractButton = new JoystickButton(m_joyStick,4);
  //private MoveForTime m_MoveForTime = new MoveForTime(m_drivetrain, Config.kAutoTime, Config.kAutoSpeed);
  private MoveWithPID m_MoveWithPID = new MoveWithPID(m_drivetrain, Config.kSetPoint);
  private MoveForDistance m_MoveForTime = new MoveForDistance(Config.kDistance, m_drivetrain);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }
  
  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  // runs commands instantiates
  private void configureButtonBindings() {
    m_intakeArmIn.whenPressed(m_intakeArm.SolenoidArmIn());
    m_intakeArmOut.whenPressed(m_intakeArm.SolenoidArmOut());
    m_reloadButton.whenPressed(m_reload);
    m_launchButton.whenPressed(m_launch);
    m_climbExtendButton.whenPressed(m_climb.climbExtend());
    m_climbRetractButton.whenPressed(m_climb.climbRetact());
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_MoveWithPID;
  }
  public Command getTeleopCommand() {
    m_drivetrain.setDefaultCommand(m_ArcadeDrive);
    m_runintake.schedule();
    return null;
  }
}

