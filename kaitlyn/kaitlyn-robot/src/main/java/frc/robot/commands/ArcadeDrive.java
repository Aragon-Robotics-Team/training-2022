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
  private Joystick m_joystick;
  private Drivetrain m_drivetrain;

  private static final class Config {

    //Constants should be diff. no.
    public static final int kLeftStickY = 1;
    public static final int kRightStickX = 2;
  
    public static final double kSpeedMultiplier = 0.7;
    public static final double kTurnMultiplier = 0.7;
  }

  public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {

    // Use addRequirements() here to declare subsystem dependencies.

    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);

    m_joystick = joystick;

  }

  // Called when the command is initially scheduled.
  // Runs once at the start of the program
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turn = m_joystick.getRawAxis(Config.kRightStickX)*Config.kTurnMultiplier;
    double speed = m_joystick.getRawAxis(Config.kLeftStickY)*Config.kSpeedMultiplier;

    double left = speed + turn;
    double right = speed - turn;
    
    m_drivetrain.setRightSpeed(right);
    m_drivetrain.setLeftSpeed(left);

    SmartDashboard.putNumber("left speed", left);
    SmartDashboard.putNumber("right speed", right);

  }

  // Returns true when the command should end.
  // So bot doesn't stop running
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    m_drivetrain.setRightSpeed(0);
    m_drivetrain.setLeftSpeed(0);
  }

}
