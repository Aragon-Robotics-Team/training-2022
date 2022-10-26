// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */
  private static final class Config {
    public static final int k_hopperID = 10;
    public static final double k_hopperSpeed = 0.7;
    }
  
  private WPI_TalonFX m_hopperMontor = new WPI_TalonFX(Config.k_hopperID);
  public Hopper() {
    m_hopperMontor.setInverted(true);
  }
  public void setForward(){
m_hopperMontor.set(Config.k_hopperSpeed);
  }
  public void setOff(){
    m_hopperMontor.set(0.0);
  }
  public void setReverse(){
    m_hopperMontor.set(-Config.k_hopperSpeed);
  }

  public InstantCommand HopperForward(){
    return new InstantCommand(this::setForward, this);
  }
  public InstantCommand HopperOff(){
    return new InstantCommand(this::setOff, this);
  }
  public InstantCommand HopperReverse(){
    return new InstantCommand(this::setReverse, this);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
