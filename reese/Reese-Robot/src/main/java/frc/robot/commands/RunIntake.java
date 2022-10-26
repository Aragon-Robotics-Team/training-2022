
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.intake.Hopper;
import frc.robot.subsystems.intake.IntakeArm;
public class RunIntake extends CommandBase {
  /** Creates a new RunIntake. */
  private IntakeArm m_intakeArm;
  private Hopper m_hopper; // name it
  private JoystickButton m_intakeIn;
  private JoystickButton m_intakeOut;
  public RunIntake(IntakeArm intakeArm, Hopper hopper, JoystickButton intakeIn, JoystickButton intakeOut) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeArm, hopper);
    m_intakeArm = intakeArm;
    m_hopper = hopper;
    m_intakeIn = intakeIn;
    m_intakeOut = intakeOut;
    m_intakeIn = intakeIn;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled. Check ()
  @Override
  public void execute() {
  if(m_intakeIn.get()) {
  m_intakeArm.setForward();
  m_hopper.setForward();
  }else if(m_intakeOut.get()){
    m_intakeArm.setReverse();
    m_hopper.setReverse();
  } else {
    m_intakeArm.setOff();
    m_hopper.setOff();
  }
  }
  
  
  
    
   
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hopper.setOff(); // add () at the end of method instance then set
    m_intakeArm.setOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
