// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {
  /** Creates a new MoveForDistance. */
  private static final class Config{
    public static final double kMotorSpeed = 0.4;
    public static final double kEncoderTicksPerRevolution = 33500;
    public static final double kWheelCircumference = (6*Math.PI)/12;
  }
  private Drivetrain m_drivetrain;
  private double initialEndcoderPosition;
  private double error;

  public MoveForDistance(Drivetrain drivetrain, double distance) {
    addRequirements(drivetrain);
    error = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    initialEndcoderPosition = m_drivetrain.getLeftTicks();
    m_drivetrain.setLeftSpeed(Config.kMotorSpeed);
    m_drivetrain.setRightSpeed(Config.kMotorSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

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
    double distance = m_drivetrain.getLeftTicks()*(Config.kWheelCircumference/Config.kEncoderTicksPerRevolution);
    return ((error - distance) <= 0);
  }
}
