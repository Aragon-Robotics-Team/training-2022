// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends SubsystemBase {

  /** Creates a new Drivetrain. */
  private static final class Config {
    //creating primary motor constant IDs.
    public static final int kRightPrimaryMotorID = 0;
    public static final int kLeftPrimaryMotorID = 1;
    //creating secondary motor constant IDs.
    public static final int kRightSecondaryMotorID = 2;
    public static final int kLeftSecondaryMotorID = 3;
  }

  //creating primary motors
  private WPI_TalonFX m_rightPrimaryMotor = new WPI_TalonFX(Config.kRightPrimaryMotorID);
  private WPI_TalonFX m_leftPrimaryMotor = new WPI_TalonFX(Config.kLeftPrimaryMotorID);
  //creating secondary motors
  private WPI_TalonFX m_rightSecondaryMotor = new WPI_TalonFX(Config.kRightSecondaryMotorID);
  private WPI_TalonFX m_leftSecondaryMotor = new WPI_TalonFX(Config.kLeftSecondaryMotorID);

  public Drivetrain() {
    m_rightSecondaryMotor.follow(m_rightPrimaryMotor);
    m_leftSecondaryMotor.follow(m_leftPrimaryMotor);
    m_rightPrimaryMotor.setInverted(true);
    m_rightSecondaryMotor.setInverted(true);
  }

  public void setRightSpeed(double speed){
    m_rightPrimaryMotor.set(speed);
  }
  public void setLeftSpeed(double speed){
    m_leftPrimaryMotor.set(speed);
  }
  public void setIdleMode(NeutralMode d){
    m_rightPrimaryMotor.setNeutralMode(d);
    m_rightSecondaryMotor.setNeutralMode(d);
    m_leftSecondaryMotor.setNeutralMode(d);
    m_leftPrimaryMotor.setNeutralMode(d);
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
