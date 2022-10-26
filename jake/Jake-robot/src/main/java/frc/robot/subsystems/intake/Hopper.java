// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */
  private static final class Config {
    public static final int kHopperMotorID = 10;
    public static final double kHopperSpeed = 0.7;
  }

  private WPI_TalonFX m_hopperMotor = new WPI_TalonFX(Config.kHopperMotorID);

  public Hopper() {
    m_hopperMotor.setInverted(true);
    
  }
  // setting forward speed
  public void setForwardHopperSpeed(){
    m_hopperMotor.set(Config.kHopperSpeed);
  }
  // setting reverse speed
  public void setReverseHopperSpeed(){
    m_hopperMotor.set(-Config.kHopperSpeed);
  }
  
  public void setZeroHopperSpeed(){
    m_hopperMotor.set(0.0);
  }

  public InstantCommand HopperForward(){
    return new InstantCommand(this::setForwardHopperSpeed, this);
  }


  public InstantCommand HopperReverse(){
    return new InstantCommand(this::HopperReverse, this);
  }

  public InstantCommand HopperOff(){
    return new InstantCommand(this::HopperOff, this);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
