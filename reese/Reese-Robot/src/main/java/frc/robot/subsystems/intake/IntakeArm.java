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
  public static final class Config {
    public static final int k_intakeMotarID = 5; 
    public static final double k_intakeSpeed = 0.7;
    public static final int k_armChannel = 0;
  }
  // controls pneumatics
  private CANSparkMax m_intakeMotor = new CANSparkMax(Config.k_intakeMotarID, MotorType.kBrushless);
  private Solenoid m_arm = new Solenoid(1, PneumaticsModuleType.CTREPCM, Config.k_armChannel);
  /** Creates a new IntakeArm. */
  public IntakeArm() {
    m_intakeMotor.setInverted(true);
    m_intakeMotor.setIdleMode(IdleMode.kCoast);
    
  }
  public void setArmIn(){
    m_arm.set(false);
  }
  public void setArmOut(){
    m_arm.set(true);
  } 
  public boolean getState(){
    return m_arm.get();
  }
  public InstantCommand SolenoidArmIn(){
    return new InstantCommand(this :: setArmIn, this);
  }
  public InstantCommand SolenoidArmOut(){
    return new InstantCommand(this :: setArmOut, this);
  }
    public void setForward(){
    m_intakeMotor.set(Config.k_intakeSpeed);
      }
  public void setOff(){
   m_intakeMotor.set(0.0);
   }
   public void setReverse(){
     m_intakeMotor.set(-Config.k_intakeSpeed);
   }
   public InstantCommand IntakeForward(){
    return new InstantCommand(this::setForward, this);
   }
   public InstantCommand IntakeOff(){
    return new InstantCommand(this::setOff, this);
   }
   public InstantCommand IntakeReverse(){
    return new InstantCommand(this::setReverse, this);
   }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
