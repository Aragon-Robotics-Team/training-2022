// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {

  private static final class Config {
    public static final double k_tickspRevolution= 3350;
    public static final double k_speed = 0;
    public static final double k_encoderTF = ((Math.PI * 6)/12) / k_tickspRevolution; 
  }

  public Drivetrain m_driveTrain;

  private double m_error;
  private double m_initialEncoderPosition;
  private double m_distance;
  /** Creates a new MoveForDistance. */
  public MoveForDistance(double distance, Drivetrain drivetrain) {
    m_driveTrain = drivetrain;
    m_error = distance;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.resetEncoders();
    m_initialEncoderPosition = m_driveTrain.getLeftEncoderTick();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_distance = m_driveTrain.getLeftEncoderTick() * Config.k_encoderTF;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_error - m_distance) <= 0;
  }
}
