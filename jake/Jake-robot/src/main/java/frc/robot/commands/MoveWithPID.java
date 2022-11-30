// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveWithPID extends CommandBase {
  public static final class Config {
    private static final double kP = 7;
    private static final double kI = 7;
    private static final double kD = 7;
    private static final double kErrorTolerance = 5;
    private static final double kderivativeTolerance = 10;
  }
  PIDController m_pidController = new PIDController(Config.kP, Config.kI, Config.kD);
  double m_setpoint;
  Drivetrain m_drivetrain;
  public MoveWithPID(double setPoint, Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_setpoint = setPoint;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    m_pidController.setTolerance(Config.kErrorTolerance,Config.kderivativeTolerance);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.setLeftSpeed(m_pidController.calculate(m_drivetrain.getLeftTicks(), m_setpoint));
    m_drivetrain.setRightSpeed(m_pidController.calculate(m_drivetrain.getRightTicks(), m_setpoint));
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
    return m_pidController.atSetpoint();
  }
}
