// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  /** Creates a new Climb. */
  private Solenoid m_climb = new Solenoid(1, PneumaticsModuleType.CTREPCM,5);
  public Climb() {
  }
public void setClimbExtend(){
  m_climb.set(true);}
  public void setClimbRetact(){
    m_climb.set(false);
  }
public InstantCommand climbExtend(){
  return new InstantCommand(this :: setClimbExtend, this );
}
public InstantCommand climbRetact(){
  return new InstantCommand(this :: setClimbRetact, this);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
