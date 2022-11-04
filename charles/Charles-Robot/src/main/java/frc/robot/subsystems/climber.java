// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class climber extends SubsystemBase {

  private static final class Config{
    public static int kSolenoidID = 11;
  }


  /** Creates a new climber. */
  private Solenoid m_solenoid = new Solenoid(1, PneumaticsModuleType.CTREPCM, Config.kSolenoidID);
 
 public void armIn(){
  m_solenoid.set(false);
 } 
 public void armOut(){
  m_solenoid.set(true);
 }
 public InstantCommand climberOut(){
    return new InstantCommand(this::armOut, this);
    
    
 }
public InstantCommand climberIn(){
  return new InstantCommand(this::armIn, this);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
