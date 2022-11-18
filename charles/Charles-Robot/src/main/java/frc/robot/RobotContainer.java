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
import frc.robot.subsystems.climber;
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
     public static final int kJoystickID = 0;
     public static final int kInButtonID = 1;
     public static final int kOutButtonID = 2;
     public static final int kIntakeInID = 10;
     public static final int kIntakeOutID = 9;
     public static final int kRunIntakeButtonID = 12;
     public static final int klaunchButtonID = 8;
     public static final int kReloadButtonID = 7;
     public static final int kclimberOutID = 3;
     public static final int kclimberInID = 4;
     private static final double kspeed = 0.4;
     private static final double ktime = 10;
     public static final double kDistance = 1;

  

  }
  // The robot's subsystems and commands are defined here...
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */

  //Subsystems
  private Drivetrain m_drivetrain = new Drivetrain();
  private Joystick m_stick = new Joystick(Config.kJoystickID);
  private ArcadeDrive m_ArcadeDrive= new ArcadeDrive(m_drivetrain, m_stick);
  private Hopper m_hopper = new Hopper();
  private IntakeArm m_intakeArm = new IntakeArm();
  // private climber m_climber = new climber();

  //Joystick Buttons
  private JoystickButton m_intakeIn = new JoystickButton(m_stick, Config.kInButtonID);
  private JoystickButton m_intakeOut = new JoystickButton(m_stick, Config.kOutButtonID);
  private JoystickButton m_armIn = new JoystickButton(m_stick, Config.kIntakeInID);
  private JoystickButton m_armOut = new JoystickButton(m_stick, Config.kIntakeOutID);
  private JoystickButton m_launchButton = new JoystickButton(m_stick, Config.klaunchButtonID);
  private JoystickButton m_reloadButton = new JoystickButton(m_stick, Config.kReloadButtonID); 
  private JoystickButton m_climberIn = new JoystickButton(m_stick, Config.kclimberInID);
  private JoystickButton m_climberOut = new JoystickButton(m_stick, Config.kclimberOutID);
  //Instantiated Commands
  private RunIntake m_runIntake = new RunIntake(m_intakeArm, m_hopper, m_intakeIn, m_intakeOut);
  private Shooter m_shooter = new Shooter();
  private Launch m_launch = new Launch(m_shooter);
  private Reload m_reload = new Reload(m_shooter);
  private MoveForTime m_MoveForTime = new MoveForTime(m_drivetrain, Config.kspeed, Config.ktime);
  private MoveForDistance m_MoveForDistance = new MoveForDistance(m_drivetrain, Config.kDistance);
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
    m_armIn.whenPressed(m_intakeArm.ArmIn());
    m_armOut.whenPressed(m_intakeArm.ArmOut());
    m_launchButton.whenPressed(m_launch);
    m_reloadButton.whenPressed(m_reload); 
    // m_climberIn.whenPressed(m_climber.climberIn());
    // m_climberOut.whenPressed(m_climber.climberOut());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(){
    return m_MoveForDistance;
  }
  public Command getTeleopCommand(){
    m_drivetrain.setDefaultCommand(m_ArcadeDrive);
    m_runIntake.schedule();
    return null;
   
  }
  
}
