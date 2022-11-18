// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {
  public static final class Config{
    public static final double  k_ticksperRotation = 3350;
    public static final  double k_speed = 0.4;
    public static final double k_wheelCircumference  = (6 * Math.PI) / 12;
  
  } 
  private Drivetrain m_drivetrain;
   /** Creates a new MoveForDistance. */
  private double m_intialEncoderPostion;
private double m_error;

  public MoveForDistance(double distance, Drivetrain drivetrain) {
    m_error = distance;
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    m_intialEncoderPostion = m_drivetrain.getRightTicks();
    m_drivetrain.setleftPrimarySpeed(Config.k_speed);
    m_drivetrain.setrightPrimarySpeed(Config.k_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_drivetrain.setrightPrimarySpeed(0);
   m_drivetrain.setleftPrimarySpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_error - (m_drivetrain.getLeftTicks()*(Config.k_wheelCircumference / Config.k_ticksperRotation))) <= 0;
  }
}
