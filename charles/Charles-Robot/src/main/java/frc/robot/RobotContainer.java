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
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static final class Config{
     public static final int kJoystickID = 0;
     public static final int kInButtonID = 8;
     public static final int kOutButtonID = 9;
     public static final int kIntakeInID = 10;
     public static final int kIntakeOutID = 11;
     public static final int kRunIntakeButtonID = 12;


  

  }
  // The robot's subsystems and commands are defined here...
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private Drivetrain m_drivetrain = new Drivetrain();
  private Joystick m_stick = new Joystick(Config.kJoystickID);
  private ArcadeDrive m_ArcadeDrive= new ArcadeDrive(m_drivetrain, m_stick);
  private Hopper m_hopper = new Hopper();
  private IntakeArm m_intakeArm = new IntakeArm();
  private JoystickButton m_intakeIn = new JoystickButton(m_stick, Config.kInButtonID);
  private JoystickButton m_intakeOut = new JoystickButton(m_stick, Config.kOutButtonID);
  private JoystickButton m_armIn = new JoystickButton(m_stick, Config.kIntakeInID);
  private JoystickButton m_armOut = new JoystickButton(m_stick, Config.kIntakeOutID);
  private RunIntake m_runIntake = new RunIntake(m_intakeArm, m_hopper, m_intakeIn, m_intakeOut);
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
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(){
    return null;
  }
  public Command getTeleopCommand(){
    m_drivetrain.setDefaultCommand(m_ArcadeDrive);
    m_runIntake.schedule();
    return null;
   
  }
  
}
