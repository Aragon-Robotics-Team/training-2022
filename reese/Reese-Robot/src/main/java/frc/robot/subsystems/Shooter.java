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
  /** Creates a new Shooter. */
  private static final class Config {
    public static final int k_shooterMotorID = 7;
    public static final int k_servoID = 9;
  }
  public static final double k_runningSpeed = 0.5;
  private CANSparkMax m_shooterMotor = new CANSparkMax(Config.k_shooterMotorID, MotorType.kBrushless);
  private Servo m_servo = new Servo(Config.k_servoID);
  public Shooter() {
    m_shooterMotor.setInverted(false);
  }
public void setMotor(double speed){
m_shooterMotor.set(speed);
}
public void closeServo(){
  m_servo.set(0);
}
public void openServo(){
m_servo.set(1);
}
public Boolean getUpperLimt(){
return m_shooterMotor.getReverseLimitSwitch(Type.kNormallyClosed).isPressed();
}
public Boolean getLowerLimt(){
return m_shooterMotor.getForwardLimitSwitch(Type.kNormallyClosed).isPressed();
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
