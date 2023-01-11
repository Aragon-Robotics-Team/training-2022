// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveWithPID extends CommandBase {
  /** Creates a new MoveWithPID. */
  private static final class Config{

    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final int kErrorTolerance = 5;
    public static final int kErrorDerivativeTolerance = 10;
    
  }

  private PIDController m_pidController = new PIDController(Config.kP, Config.kI, Config.kD);

  private Drivetrain m_drivetrain;
  private double setpoint;

  public MoveWithPID(Drivetrain drivetrain, double Setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    setpoint = Setpoint;
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_drivetrain.resetEncoders();
    m_pidController.setTolerance(Config.kErrorTolerance, Config.kErrorDerivativeTolerance);;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_drivetrain.setRightSpeed(m_pidController.calculate(m_drivetrain.getRightEncoderTicks(), setpoint));
    m_drivetrain.setLeftSpeed(m_pidController.calculate(m_drivetrain.getLeftEncoderTicks(), setpoint));

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
    return m_pidController.atSetpoint();
  }
}
