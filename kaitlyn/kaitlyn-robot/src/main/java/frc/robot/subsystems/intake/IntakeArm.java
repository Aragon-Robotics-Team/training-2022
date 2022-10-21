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
  /** Creates a new Intake. */

  private static final class Config{
    public static final int kIntakeArmMotorID = 0;
    public static final double kIntakeArmMotorSpeed = 0.7;
    public static final int kArmChannel = 0;
  }

  private CANSparkMax m_intakeArmMotor = new CANSparkMax(Config.kIntakeArmMotorID, MotorType.kBrushless);
  private Solenoid m_arm = new Solenoid(1, PneumaticsModuleType.CTREPCM, Config.kArmChannel);

  public IntakeArm() {
    m_intakeArmMotor.setInverted(true);

    //Set motor to coast mode
    m_intakeArmMotor.setIdleMode(IdleMode.kCoast);
  }

  public InstantCommand IntakeArmForward(){
    return new InstantCommand(this::setForward,this);
  }

  public void setForward(){
    m_intakeArmMotor.set(Config.kIntakeArmMotorSpeed);
  }

  public InstantCommand IntakeArmReverse(){
    return new InstantCommand(this::setReverse,this);
  }

  public void setReverse(){
    m_intakeArmMotor.set(-Config.kIntakeArmMotorSpeed);
  }

  public InstantCommand IntakeArmOff(){
    return new InstantCommand(this::setOff,this);
  }

  public void setOff(){
    m_intakeArmMotor.set(0);
  }

  public InstantCommand SolenoidArmOut(){
    return new InstantCommand(this::setArmOut,this);
  }

  public void setArmOut(){
    //True makes the arm extend
    m_arm.set(true);
  }

  public InstantCommand SolenoidArmIn(){
    return new InstantCommand(this::setArmIn,this);
  }

  public void setArmIn(){
    m_arm.set(false)
  }

  public boolean getState(){
    //Prevents arm from being in while it's on
    return m_arm.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
