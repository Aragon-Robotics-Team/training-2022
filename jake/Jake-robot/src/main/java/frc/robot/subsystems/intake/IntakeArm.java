// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeArm extends SubsystemBase {
  private static final class Config {
    public static final int kIntakeMotorID = 0;
    public static final double kIntakeMotorSpeed = 0.7;
    public static final int karmchannel = 0;
  }

  public static boolean getState;

  private CANSparkMax m_intakeMotor = new CANSparkMax(Config.kIntakeMotorID, MotorType.kBrushless);
  private Solenoid m_arm = new Solenoid(1, PneumaticsModuleType.CTREPCM , Config.karmchannel);
  public IntakeArm() {
    m_intakeMotor.setInverted(true);
    m_intakeMotor.setIdleMode(IdleMode.kCoast);
  }

  public void setForwardIntakeArmSpeed(){
    m_intakeMotor.set(Config.kIntakeMotorSpeed);
  }

  public void setReserveIntakeArmSpeed(){
    m_intakeMotor.set(-Config.kIntakeMotorSpeed);
  }

  public void setZeroIntakeArmSpeed(){
    m_intakeMotor.set(0.0);
  }

  public InstantCommand IntakeArmForward(){
    return new InstantCommand(this::setForwardIntakeArmSpeed, this);
  }

  public InstantCommand IntakeArmReverse(){
    return new InstantCommand(this::setReserveIntakeArmSpeed, this);
  }

  public InstantCommand IntakeArmOff(){
    return new InstantCommand(this::setZeroIntakeArmSpeed, this);
  }

  public void armOut(){
    m_arm.set(true);
  }

  public InstantCommand armIn1(){
    return new InstantCommand(this::armIn, this);
  }

  public InstantCommand armOut1(){
    return new InstantCommand(this::armOut, this);
  }

  public void armIn(){
    m_arm.set(false);
  }

  public boolean getState(){
    return m_arm.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
