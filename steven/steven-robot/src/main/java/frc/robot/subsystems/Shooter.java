// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private static final class Config {
    public static final int k_motorId = 7;
    public static final int k_servoId = 9;
  }
  public final double k_runningSpeed = 0.5;
  private Servo m_servo = new Servo(Config.k_servoId);
  /** Creates a new Shooter. */
  private CANSparkMax m_motor = new CANSparkMax(Config.k_motorId, MotorType.kBrushless);

  public void setMotorSpeed(double speed){
    m_motor.set(speed);
  }

  public boolean getUpperLimit(){
    return m_motor.getReverseLimitSwitch(Type.kNormallyClosed).isPressed();
  }

  public boolean getLowerLimit(){
    return m_motor.getForwardLimitSwitch(Type.kNormallyClosed).isPressed();
  }

  public void openServo(){
    m_servo.set(0);
  }

  public void closeServo(){
    m_servo.set(1);
  }

  public Shooter() {
    m_motor.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
