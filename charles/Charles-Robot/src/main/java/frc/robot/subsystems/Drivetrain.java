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
    // creating primary motor constant IDs.
    public static final int kRightPrimaryMotorID = 2;
    public static final int kLeftPrimaryMotorID = 4;
    // creating secondary motor constant IDs.
    public static final int kRightSecondaryMotorID = 1;
    public static final int kLeftSecondaryMotorID = 3;
  }

  // creating primary motors
  private WPI_TalonFX m_rightPrimaryMotor = new WPI_TalonFX(Config.kRightPrimaryMotorID);
  private WPI_TalonFX m_leftPrimaryMotor = new WPI_TalonFX(Config.kLeftPrimaryMotorID);
  // creating secondary motors
  private WPI_TalonFX m_rightSecondaryMotor = new WPI_TalonFX(Config.kRightSecondaryMotorID);
  private WPI_TalonFX m_leftSecondaryMotor = new WPI_TalonFX(Config.kLeftSecondaryMotorID);

  public Drivetrain() {

    // making secondary motors follow primary motors
    m_rightSecondaryMotor.follow(m_rightPrimaryMotor);
    m_leftSecondaryMotor.follow(m_leftPrimaryMotor);
    m_rightPrimaryMotor.setInverted(true);
    m_rightSecondaryMotor.setInverted(true);
  }

  public void setRightSpeed(double speed) {
    // setting speed for right primary motor
    m_rightPrimaryMotor.set(speed);
  }

  public void setLeftSpeed(double speed) {
    // setting speed for left primary motor
    m_leftPrimaryMotor.set(speed);
  }

  public void setIdleMode(NeutralMode mode) {
    // setting neutralMode
    m_rightPrimaryMotor.setNeutralMode(mode);
    m_rightSecondaryMotor.setNeutralMode(mode);
    m_leftSecondaryMotor.setNeutralMode(mode);
    m_leftPrimaryMotor.setNeutralMode(mode);
  }

  public double getLeftTicks() {
    return m_leftPrimaryMotor.getSelectedSensorPosition();
  }

  public double getRightTicks() {
    return m_rightPrimaryMotor.getSelectedSensorPosition();
  }

  public void resetEncoders() {
    m_leftPrimaryMotor.setSelectedSensorPosition(0);
    m_rightPrimaryMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
