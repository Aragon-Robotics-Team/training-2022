// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForTime extends CommandBase {
  private Drivetrain m_drivetrain;
  private double m_Speed;
  private double m_time;
  private Timer m_timer;
  public MoveForTime(Drivetrain drivetrain, double Speed, double Time) {
    m_drivetrain = drivetrain;
    m_Speed = Speed;
    m_time = Time;
    m_timer = new Timer();
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
    m_drivetrain.setLeftSpeed(m_Speed);
    m_drivetrain.setRightSpeed(m_Speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setLeftSpeed(0);
    m_drivetrain.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(m_time);
  }
}
