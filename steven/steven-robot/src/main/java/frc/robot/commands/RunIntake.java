// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.intake.Hopper;
import frc.robot.subsystems.intake.IntakeArm;

public class RunIntake extends CommandBase {
  /** Creates a new RunIntake. */
  private Hopper m_hopper;
  private IntakeArm m_IntakeArm;
  private JoystickButton m_IntakeIn;
  private JoystickButton m_IntakeOut;
  public RunIntake(Hopper hopper, IntakeArm intake, JoystickButton intakeIn, JoystickButton intakeOut) {
    m_hopper = hopper;
    m_IntakeArm = intake
    m_IntakeIn = intakeIn;
    m_IntakeOut = intakeOut;
    addRequirements(hopper, intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
  //   if(m_IntakeArm.getState() || m_IntakeIn.get()) {
  //   m_hopper.HopperForward();
  //    if(m_IntakeOut.get())
  //     m_hopper.HopperOff();
  //  }
  //  else if(!m_IntakeArm.getState() && m_IntakeIn.get()) {
  //    m_hopper.HopperForward();
  //  }
  //  else { m_hopper.HopperOff(); }
      if(m_IntakeIn.get()) m_hopper.HopperForward(); m_IntakeArm.setForwardIntakeArmSpeed();
      else if(m_IntakeOut.get()) m_hopper.HopperReverse(); m_IntakeArm.setForwardIntakeArmSpeed();  
      else { m_hopper.setZeroHopperSpeed(); m_IntakeArm.setZeroIntakeArmSpeed(); }
  }

  @Override
  public void end(boolean interrupted) {
    m_hopper.setZeroHopperSpeed();
    m_IntakeArm.setZeroIntakeArmSpeed();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
