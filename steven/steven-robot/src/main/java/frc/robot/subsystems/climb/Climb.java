// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  /** Creates a new Climb. */
  private Solenoid m_climb = new Solenoid(1, PneumaticsModuleType.CTREPCM, 5);
  public Climb() {
  }

  //heres for people who dont understand for some reason: this gets the state of the arm whether its in or out
  public void getArmState(){
    m_climb.get();
  }

  //extends arm
  public void extendArm(){
    m_climb.set(true);
  }

  //retracts arm
  public void retractArm(){
    m_climb.set(false);
  }

  public InstantCommand extendArmUp(){
    return new InstantCommand(this::extendArm, this);
  }

  public InstantCommand retractArmDown(){
    return new InstantCommand(this::retractArm, this);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
