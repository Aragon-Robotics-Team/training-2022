// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeArm extends SubsystemBase {
  /** Creates a new IntakeArm. */
  private static final class Config{
    public static final int kIntakeMotorID = 0;
    public static final double kIntakeSpeed = 0.7;
    public static final int kArmChannel = 0;
  }
  private CANSparkMax m_intakeMotor = new CANSparkMax(Config.kIntakeMotorID, MotorType.kBrushless);
  private Solenoid m_solenoid = new Solenoid(1, PneumaticsModuleType.CTREPCM, Config.kArmChannel);
  public IntakeArm(){
    m_intakeMotor.setInverted(true);
    m_intakeMotor.setIdleMode(IdleMode.kCoast);

      
  }
  public void armIn(){
    m_solenoid.set(false);
  }
  public void armOut(){
    m_solenoid.set(true);
  }

  public boolean getState(){
    return m_solenoid.get();
  }
  
  public InstantCommand IntakeForward(){
    return new InstantCommand(this::setForward, this);

  }
  public InstantCommand IntakeReverse(){
    return new InstantCommand(this::setReverse, this);
  }
  public InstantCommand IntakeOff(){
    return new InstantCommand(this::setOff, this);
  }
  public InstantCommand ArmOut(){
    return new InstantCommand(this::armOut, this);
  }
  public InstantCommand ArmIn(){
    return new InstantCommand(this::armIn, this);
  }

  public void setForward(){
    m_intakeMotor.set(Config.kIntakeSpeed);
  }
  public void setReverse(){
    m_intakeMotor.set(-(Config.kIntakeSpeed));
  }
  public void setOff(){
    m_intakeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
