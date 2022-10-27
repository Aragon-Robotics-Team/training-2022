// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */ 
  private static final class Config{
    // Making IDs for motors
    public static final int kRightPrimaryMotorID = 2;
    public static final int kLeftPrimaryMotorID = 4;
    public static final int kRightSecondaryMotorID = 1;
    public static final int kLeftSecondaryMotorID = 3;
  }

  //Instructions for each motor, assigning it their ID
 private WPI_TalonFX m_rightPrimary = new WPI_TalonFX(Config.kRightPrimaryMotorID);
 private WPI_TalonFX m_leftPrimary = new WPI_TalonFX(Config.kLeftPrimaryMotorID);
 private WPI_TalonFX m_rightSecondary = new WPI_TalonFX(Config.kRightSecondaryMotorID);
 private WPI_TalonFX m_leftSecondary = new WPI_TalonFX (Config.kLeftSecondaryMotorID);

 public Drivetrain() {

   //So only need to program once (for directions only)

  m_leftSecondary.follow(m_leftPrimary);
   m_rightSecondary.follow(m_rightPrimary);

   //Invert so backward --> forward, etc
   //So motor movements align and don't conflict

   m_rightPrimary.setInverted(true);
   m_rightSecondary.setInverted(true);
 }
//Speed from -1 to 1, percentage of speed (full power forwards to backwards)
public void setRightSpeed(double rightSpeed) {
     m_rightPrimary.set(rightSpeed);

   }

   public void setLeftSpeed(double leftSpeed) {
    m_leftPrimary.set(leftSpeed);
    
  }
  //So robot stays put when engine is on

  public void setIdleMode(NeutralMode idleMode){

  m_leftPrimary.setNeutralMode(idleMode);
  m_rightPrimary.setNeutralMode(idleMode);
  m_leftSecondary.setNeutralMode(idleMode);
  m_rightSecondary.setNeutralMode(idleMode);
  }
   
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
