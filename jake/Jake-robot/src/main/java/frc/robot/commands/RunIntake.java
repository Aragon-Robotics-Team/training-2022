// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.intake.Hopper;
import frc.robot.subsystems.intake.IntakeArm;

public class RunIntake extends CommandBase {
  private Hopper m_Hopper;
  private IntakeArm m_IntakeArm;
  private JoystickButton m_intakeIn;
  private JoystickButton m_intakeOut;
  public RunIntake(Hopper hopper, IntakeArm intakeArm, JoystickButton intakeIn, JoystickButton intakeOut) {
    m_Hopper = hopper;
    m_IntakeArm = intakeArm;
    m_intakeIn = intakeIn;
    m_intakeOut = intakeOut;
    addRequirements(m_IntakeArm, m_Hopper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_intakeIn.get()){
      m_Hopper.HopperForward();
      m_IntakeArm.IntakeArmForward();
    }
    else if(m_intakeOut.get()){
      m_Hopper.HopperReverse();
      m_IntakeArm.IntakeArmReverse();
    }
    else{
      m_Hopper.setZeroHopperSpeed();
      m_IntakeArm.setZeroIntakeArmSpeed();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Hopper.setZeroHopperSpeed();
    m_IntakeArm.setZeroIntakeArmSpeed();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
