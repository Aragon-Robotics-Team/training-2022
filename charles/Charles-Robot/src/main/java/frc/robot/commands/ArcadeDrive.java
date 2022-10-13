// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
  private static final class Config {
    //declaring joystick constants
    public static final int kleftStickY = 0;
    public static final int kRightStickX = 0;
    public static final double kSpeedMultiplier = 0.7;
    public static final double kTurnMultiplier = 0.7;

  }

  /** Creates a new ArcadeDrive. */
  private Drivetrain m_drivetrain;
  private Joystick m_stick;

  public ArcadeDrive(Drivetrain drivetrain, Joystick stick) {
    // Use addRequirements() here to declare subsystem dependencies.

    //making drivetrain and joystick
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
    m_drivetrain = drivetrain;
    m_stick = stick;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //getting turn
    double turn = m_stick.getRawAxis(Config.kRightStickX)*Config.kTurnMultiplier;
    double speed = m_stick.getRawAxis(Config.kleftStickY)*Config.kSpeedMultiplier;
    //setting left and right speed values
    double left = speed+turn;
    double right = speed-turn;
    m_drivetrain.setLeftSpeed(left);
    m_drivetrain.setRightSpeed(right);


    
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    //Setting speed to 0 once drivetrain is finished

    m_drivetrain.setLeftSpeed(0);
    m_drivetrain.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
