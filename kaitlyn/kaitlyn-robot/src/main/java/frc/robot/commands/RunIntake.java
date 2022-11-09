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
  //Instantiate the subsystems

  private Hopper m_hopper;
  private IntakeArm m_intakeArm;
  private JoystickButton m_intakeIn;
  private JoystickButton m_intakeOut;
  

  public RunIntake(Hopper hopper, IntakeArm intakeArm, JoystickButton intakeIn, JoystickButton intakeOut) {
    // Use addRequirements() here to declare subsystem dependencies (for subsystems only!!!).
    m_hopper = hopper;
    m_intakeArm = intakeArm;
    //taking in ball vs. spitting out ball
    m_intakeIn = intakeIn;
    m_intakeOut = intakeOut;

    addRequirements(hopper, intakeArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_intakeIn.get()){
      //If m_intakeIn is true, then it'll move forward
      m_intakeArm.setForward();
      m_hopper.setForward();
    }
    else if(m_intakeOut.get()){
      //If m_intakeOut is true, then it'll move in reverse
      m_intakeArm.setReverse();
      m_hopper.setReverse();
    }
    else{
      //Otherwise, it'll turn off
      m_intakeArm.setOff();
      m_hopper.setOff();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_hopper.setOff();
    m_intakeArm.setOff();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
