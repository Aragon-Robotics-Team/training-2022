// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */
  private static final class Config {
    public static final int k_hopperID = 10;
  }
  private WPI_TalonFX m_hopperMontor = new WPI_TalonFX(Config.k_hopperID);
  public Hopper() {

  
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
