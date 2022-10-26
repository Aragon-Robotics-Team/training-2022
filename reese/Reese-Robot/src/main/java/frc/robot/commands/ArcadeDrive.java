// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  private static final class Config {
    public static final int kLeftStickYAxis = 1;
    public static final int kRightStickYAxis = 2;
    public static final double kSpeedMultiplier = 0.7;
    public static final double kTurnMultiplier= 0.7;
  }

  // create joystick
  private Joystick m_joyStick;
  private Drivetrain m_drivetrain;

  public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    m_joyStick = joystick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_joyStick.getRawAxis(Config.kLeftStickYAxis) * Config.kSpeedMultiplier;
    double turn = m_joyStick.getRawAxis(Config.kRightStickYAxis) * Config.kTurnMultiplier;
    double left = speed + turn;
    double right = speed - turn;
    m_drivetrain.setrightPrimarySpeed(right);
    m_drivetrain.setleftPrimarySpeed(left);
  SmartDashboard.putNumber("left speed", left);
  SmartDashboard.putNumber("right speed", right);
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
    return false;
  }
}
