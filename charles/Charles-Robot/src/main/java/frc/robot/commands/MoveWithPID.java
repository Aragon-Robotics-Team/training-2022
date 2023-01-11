// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import pabeles.concurrency.ConcurrencyOps.Reset;




public class MoveWithPID extends CommandBase {
  private static final class Config{
    private static final double kP = 1;
    private static final double kI = 2;
    private static final double kD = 3;
    private static final double kErrorTolerance = 5;
    private static final double kDerivativeTolerance = 10;

  }
  double m_setPoint;
  Drivetrain m_Drivetrain;
  /** Creates a new MoveWithPID. */
  PIDController m_pidController = new PIDController(Config.kP, Config.kI, Config.kD);
  public MoveWithPID(double setPoint, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drivetrain = drivetrain;
    m_setPoint = setPoint;
    addRequirements(m_Drivetrain);
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_Drivetrain.resetEncoders();
      m_pidController.setTolerance(Config.kErrorTolerance, Config.kDerivativeTolerance);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    m_Drivetrain.setLeftSpeed(m_pidController.calculate(m_Drivetrain.getLeftTicks(), m_setPoint));
    m_Drivetrain.setRightSpeed(m_pidController.calculate(m_Drivetrain.getRightTicks(), m_setPoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.setLeftSpeed(0);
    m_Drivetrain.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_pidController.atSetpoint();
  }
}
