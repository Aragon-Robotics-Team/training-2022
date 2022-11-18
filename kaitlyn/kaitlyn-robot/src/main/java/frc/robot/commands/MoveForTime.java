// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForTime extends CommandBase {
  /** Creates a new MoveForTime. */
  private Drivetrain m_drivetrain;
  private double m_speed = 0;
  private double m_timeInSeconds;
  private Timer m_timer;

  public MoveForTime(Drivetrain drivetrain, double speed, double timeInSeconds) {
    m_timer = new Timer();
    m_drivetrain = drivetrain;
    m_speed = speed;
    m_timeInSeconds = timeInSeconds; 
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.setRightSpeed(m_speed);
    m_drivetrain.setLeftSpeed(m_speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setRightSpeed(0);
    m_drivetrain.setLeftSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(m_timeInSeconds);
  }
}
