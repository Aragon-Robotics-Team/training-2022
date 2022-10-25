// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import java.time.Instant;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {

  private static final class Config {
    //creating primary motor constant IDs.
    public static final int kHopperMotorID = 10;
    public static final double kHopperSpeed = 0.7;
       
  }
  private WPI_TalonFX m_hopperMotor = new WPI_TalonFX(Config.kHopperMotorID);

  /** Creates a new Hopper. */
  public Hopper() {
    m_hopperMotor.setInverted(true);
    
  }
  public InstantCommand HopperForward(){
    return new InstantCommand(this::setForward, this);

  }
  public InstantCommand HopperReverse(){
    return new InstantCommand(this::setReverse, this);
  }
  public InstantCommand HopperOff(){
    return new InstantCommand(this::setOff, this);
  }
  

  public void setForward(){
    m_hopperMotor.set(Config.kHopperSpeed);
  }
  public void setReverse(){
    m_hopperMotor.set(-(Config.kHopperSpeed));
    
  }
  public void setOff(){
    m_hopperMotor.set(0);
  }
    
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
