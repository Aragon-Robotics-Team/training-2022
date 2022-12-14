// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  private static final class Config{
    public static final int kShooterMotorID = 7;
    public static final int kServo = 9;
  }

  public static final double kRunningSpeed = 0.5;

  private CANSparkMax m_shooterMotor = new CANSparkMax(Config.kShooterMotorID, MotorType.kBrushless);
  private Servo m_servo = new Servo(Config.kServo);

  public Shooter() {
    m_shooterMotor.setInverted(false);
  };

  public void setMotor(double speed){
    m_shooterMotor.set(speed);
  }

  public void openServo(){
    m_servo.set(0.0);
  }

  public void closeServo(){
    m_servo.set(1.0);
  }

  public boolean getUpperLimit(){
    //Limit switches are connected to the motor
    //Normally closed = returning true, press = returning false
    //Is pressed = is limit switch pressed
    return m_shooterMotor.getReverseLimitSwitch(Type.kNormallyClosed).isPressed();
  }

  public boolean getLowerLimit(){
    return m_shooterMotor.getForwardLimitSwitch(Type.kNormallyClosed).isPressed();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Shooter/ Upper Limit", getUpperLimit());
    SmartDashboard.putBoolean("Shooter/ Lower Limit", getLowerLimit());

  }
}
