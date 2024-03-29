// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
// importing stuff
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. **/
  private static final class Config {
    public static final int krightPrimaryID = 2;
    public static final int kleftPrimaryID = 4;
    public static final int krightSecondID = 1;
    public static final int kleftSecondID = 3;
  }
  
  // making motors IDs
  private WPI_TalonFX m_rightPrimaryMotor = new WPI_TalonFX(Config.krightPrimaryID);
  private WPI_TalonFX m_leftPrimaryMotor = new WPI_TalonFX(Config.kleftPrimaryID);
  private WPI_TalonFX m_rightSecondMotor = new WPI_TalonFX(Config.krightSecondID);
  private WPI_TalonFX m_leftSecondMotor = new WPI_TalonFX(Config.kleftSecondID);
  private DifferentialDrive m_DifferentialDrive = new DifferentialDrive(m_leftPrimaryMotor, m_rightPrimaryMotor);
  public Drivetrain() {
   //so motors follow eachother
    m_leftSecondMotor.follow(m_leftPrimaryMotor);
    m_rightSecondMotor.follow(m_rightPrimaryMotor);
    //Setting Inverted
    m_leftSecondMotor.setInverted(true);
    m_leftPrimaryMotor.setInverted(true);
    
  }
  //setting speed
  public void setrightPrimarySpeed(double speed){
    m_rightPrimaryMotor.set(speed);
  }

  public void setleftPrimarySpeed(double speed){
    m_leftPrimaryMotor.set(speed);
  }
//setting neutral
public void setIdleeMode(NeutralMode idle){
m_leftSecondMotor.setNeutralMode(idle);
m_rightSecondMotor.setNeutralMode(idle);
m_rightPrimaryMotor.setNeutralMode(idle);
m_leftPrimaryMotor.setNeutralMode(idle);
}
public double getLeftTicks(){
return  m_leftPrimaryMotor.getSelectedSensorPosition();
}
public double getRightTicks(){
  return m_rightPrimaryMotor.getSelectedSensorPosition();
}
public DifferentialDrive getDrive(){
  return m_DifferentialDrive;

}
public void resetEncoders() {
  m_leftPrimaryMotor.setSelectedSensorPosition(0);
  m_rightPrimaryMotor.setSelectedSensorPosition(0);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  SmartDashboard.putNumber("LeftEncoderTicks", getLeftTicks());
  SmartDashboard.putNumber("RightEncoderTicks", getRightTicks());
  SmartDashboard.putData(m_DifferentialDrive);
    }
  }




