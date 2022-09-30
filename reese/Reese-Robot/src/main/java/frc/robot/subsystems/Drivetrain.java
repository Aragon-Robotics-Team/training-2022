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
    public static final int krightPrimaryID = 0;
    public static final int kleftPrimaryID = 1;
    public static final int krightSecondID = 4;
    public static final int kleftSecondID = 56;
  }
  
  // making motors IDs
  private WPI_TalonFX m_rightPrimaryMotor = new WPI_TalonFX(Config.krightPrimaryID);
  private WPI_TalonFX m_leftPrimaryMotor = new WPI_TalonFX(Config.krightPrimaryID);
  private WPI_TalonFX m_rightSecondMotor = new WPI_TalonFX(Config.krightSecondID);
  private WPI_TalonFX m_leftSecondMotor = new WPI_TalonFX(Config.krightSecondID);
  public Drivetrain() {
    m_leftSecondMotor.follow(m_leftPrimaryMotor);
    m_rightSecondMotor.follow(m_rightSecondMotor);

    m_leftSecondMotor.setInverted(true);
    m_rightPrimaryMotor.setInverted(true);
    
  }
  public void setrightPrimarySpeed(double speed){
    m_rightPrimaryMotor.set(speed);
  }

  public void setleftPrimarySpeed(double speed){
    m_rightPrimaryMotor.set(speed);
  }

public void setIdleeMode(NeutralMode idle){
m_leftSecondMotor.setNeutralMode(idle);
m_rightSecondMotor.setNeutralMode(idle);
m_rightPrimaryMotor.setNeutralMode(idle);
m_leftPrimaryMotor.setNeutralMode(idle);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
      
    }
  }

}
