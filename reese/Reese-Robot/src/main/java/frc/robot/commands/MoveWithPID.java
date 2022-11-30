// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class MoveWithPID extends CommandBase {
  private static final class Config {
    public static final double kI = 0;
    public static final double kP = 4;
    public static final double kD = 6;
    public static final double kerrortolerance = 5;
    public static final double kerrorDerivativeTolerance = 10;
  }
  private PIDController pidController = new PIDController(Config.kP, Config.kI, Config.kD);
  
  private Drivetrain m_drivetrain;
  private double Setpoint;
  /** Creates a new MoveWithPID. */
  public MoveWithPID(Drivetrain drivetrain, double setpoint) {
    m_drivetrain = drivetrain; 
    // Use addRequirements() here to declare subsystem dependencies.
    
addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   m_drivetrain.resetEncoders();
    pidController.setTolerance(Config.kerrortolerance, Config.kerrorDerivativeTolerance);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.setrightPrimarySpeed(pidController.calculate(m_drivetrain.getRightTicks(), Setpoint));
    m_drivetrain.setleftPrimarySpeed(pidController.calculate(m_drivetrain.getLeftTicks(), Setpoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setrightPrimarySpeed(0);
    m_drivetrain.setleftPrimarySpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
