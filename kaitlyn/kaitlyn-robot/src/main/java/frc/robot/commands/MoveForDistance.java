// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {
  /** Creates a new MoveForDistance. */

  private static final class Config{
    public static final double kSpeed = 0.4;
    public static final double kEncoderTicks = 3350;
  }

  private double m_error;
  private double m_initialEncoderPosition;
  private Drivetrain m_drivetrain;

  public MoveForDistance(double distance, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_error = distance;
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    m_initialEncoderPosition = m_drivetrain.getLeftEncoderTicks();
    m_drivetrain.setRightSpeed(Config.kSpeed);
    m_drivetrain.setLeftSpeed(Config.kSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setRightSpeed(0);
    m_drivetrain.setLeftSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
