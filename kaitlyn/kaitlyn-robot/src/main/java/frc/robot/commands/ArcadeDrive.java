// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  private Joystick m_joystick;
  private Drivetrain m_drivetrain;

  private static final class Config {

    public static final int kLeftStickY = 0;
    public static final int kRightStickX = 0;
  }

  public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);

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
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
