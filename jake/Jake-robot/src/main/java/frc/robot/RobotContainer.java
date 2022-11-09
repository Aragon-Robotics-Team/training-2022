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
import frc.robot.commands.Shooting.Launch;
import frc.robot.commands.Shooting.Reload;
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
    public static final int kIntakeInID = 2;
    public static final int kIntakeOutID = 1;
    public static final int kArmInID = 4;
    public static final int kArmOutID = 3;
    public static final int kLaunchID = 8;
    public static final int kReloadID = 7;
    public static final int kClimbInID = 4;
    public static final int kClimbOutID = 3;
  }
  // The robot's subsystems and commands are defined here...

  //subsystem
  private Drivetrain m_drivetrain = new Drivetrain();
  private IntakeArm m_intakeArm = new IntakeArm();
  private Hopper m_hopper = new Hopper();
  private Shooter m_shooter = new Shooter();
  private Climb m_climb = new Climb();
  //Joystick
  private Joystick m_Joystick = new Joystick(Config.kjoystickID);
  //button
  private JoystickButton m_intakeIn = new JoystickButton(m_Joystick, Config.kIntakeInID);
  private JoystickButton m_intakeOut = new JoystickButton(m_Joystick, Config.kIntakeOutID);
  private JoystickButton m_armin = new JoystickButton(m_Joystick, Config.kArmInID);
  private JoystickButton m_armOut = new JoystickButton(m_Joystick, Config.kArmOutID);
  private JoystickButton m_launchButton = new JoystickButton(m_Joystick, Config.kLaunchID);
  private JoystickButton m_reloadButton = new JoystickButton(m_Joystick, Config.kReloadID);
  private JoystickButton m_climbOut = new JoystickButton(m_Joystick, Config.kClimbOutID);
  private JoystickButton m_climbIn = new JoystickButton(m_Joystick, Config.kClimbInID);
  //commands
  private ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drivetrain, m_Joystick);
  private RunIntake m_runintake = new RunIntake(m_hopper, m_intakeArm, m_intakeIn, m_intakeOut);
  private Launch m_launch = new Launch(m_shooter);
  private Reload m_reload = new Reload(m_shooter);

  


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
  private void configureButtonBindings() {

    //arm in and arm out commands when pressed

    m_armin.whenPressed(m_intakeArm.armIn1());
    m_armOut.whenPressed(m_intakeArm.armOut1());

    //launch and reload when pressed

    m_launchButton.whenPressed(m_launch);
    m_reloadButton.whenPressed(m_reload);

    // climb in and climb out when pressed
    
    m_climbOut.whenPressed(m_climb.climbOut1());
    m_climbIn.whenPressed(m_climb.climbIn1());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  public Command getTeleopCommand(){
    m_drivetrain.setDefaultCommand(m_ArcadeDrive);
    m_runintake.schedule();
    return null;
  }
}
