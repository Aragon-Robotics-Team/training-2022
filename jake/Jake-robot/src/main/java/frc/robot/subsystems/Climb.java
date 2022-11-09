// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  private static final class Config{
    public static final int kClimbID = 0;

  }

  private Solenoid m_climb = new Solenoid(1, PneumaticsModuleType.CTREPCM, 5);
  
  public void climbOut(){
    m_climb.set(true);
  }

  public void climbIn(){
    m_climb.set(false);
  }

  public InstantCommand climbIn1(){
    return new InstantCommand(this::climbIn, this);
  }

  public InstantCommand climbOut1(){
    return new InstantCommand(this::climbOut, this);
  }

  public boolean climbState(){
    return m_climb.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
