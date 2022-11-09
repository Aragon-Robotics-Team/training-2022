// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Climb extends SubsystemBase {
  /** Creates a new Climb. */
  private static final class Config{
    public static final int kArmChannel = 0;
  }

  //Instantiate solenoid
  private Solenoid m_climbArms = new Solenoid(1, PneumaticsModuleType.CTREPCM, Config.kArmChannel);
  public Climb() {
  }
  
  public InstantCommand SetArmOut(){
    return new InstantCommand(this::armOut,this);
  }

  public void armOut(){
    m_climbArms.set(true);
  }

  public InstantCommand SetArmIn(){
    return new InstantCommand(this::armIn,this);
  }

  public void armIn(){
    m_climbArms.set(false);
  }

  public boolean getState(){
    return m_climbArms.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
