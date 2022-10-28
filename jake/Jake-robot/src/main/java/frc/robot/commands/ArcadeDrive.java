// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
  private static final class Config {
    public static final int k_leftStickY = 1;
    public static final int k_rightStickX = 2;
    public static final double kSpeedMutliplier = 0.7;
    public static final double kTurnMutliplier = 0.7;
  }

  private Drivetrain m_drivetrain;
  private Joystick m_joystick;

  public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {
    m_drivetrain = drivetrain;
    m_joystick = joystick;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turn = m_joystick.getRawAxis(Config.k_rightStickX) * Config.kTurnMutliplier; 

    // Turn made

    double speed = m_joystick.getRawAxis(Config.k_leftStickY) * Config.kSpeedMutliplier; 

    // Speed Made

    double left = speed + turn;

    //Left speed

    double right = speed - turn;

    //Right speed

    m_drivetrain.setRightSpeed(right); 

    //Setting right speed

    m_drivetrain.setLeftSpeed(left);

    //Setting left speed
  
    // Made Speed and Turn Multipliers
    SmartDashboard.putNumber("left speed", left);
    SmartDashboard.putNumber("right speed", right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    //set right speed to 0 when finished

    m_drivetrain.setRightSpeed(0);

    // set left speed to 0 when finished

    m_drivetrain.setLeftSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
