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
  /** Creates a new RunIntake. */
  private IntakeArm m_intakeArm;
  private Hopper m_hopper;
  private JoystickButton m_intakeIn;
  private JoystickButton m_intakeOut;

  public RunIntake(IntakeArm intakeArm, Hopper hopper, JoystickButton intakeIn, JoystickButton intakeOut) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intakeArm = intakeArm;
    m_hopper = hopper;
    m_intakeIn = intakeIn;
    m_intakeOut = intakeOut;
    addRequirements(m_intakeArm);
    addRequirements(m_hopper);
    
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_intakeIn.get()){
      m_hopper.HopperForward();
      m_intakeArm.IntakeForward();
    }
    else if(m_intakeOut.get()){
      m_hopper.HopperReverse();
      m_intakeArm.IntakeReverse();
    }
    else{
      m_hopper.HopperOff();
      m_intakeArm.IntakeOff();
    }

  }

  // Called once the command ends or is interrupted.  
  @Override
  public void end(boolean interrupted) {
    m_intakeArm.setOff();
    m_hopper.setOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
