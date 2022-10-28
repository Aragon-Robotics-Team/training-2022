package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private static final class Config{
    public static final int kRightPrimaryID=2;
    public static final int kLeftPrimaryID=4;
    public static final int kRightSecondaryID=1;
    public static final int kLeftSecondaryID=3;
  }

  private WPI_TalonFX m_rightPrimary = new WPI_TalonFX(Config.kRightPrimaryID);
  private WPI_TalonFX m_leftPrimary = new WPI_TalonFX(Config.kLeftPrimaryID);
  private WPI_TalonFX m_rightSecondary = new WPI_TalonFX(Config.kRightSecondaryID);
  private WPI_TalonFX m_leftSecondary = new WPI_TalonFX(Config.kLeftSecondaryID);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    m_leftSecondary.follow(m_leftPrimary);
    m_rightSecondary.follow(m_rightPrimary);
    m_rightPrimary.setInverted(true);
    m_rightSecondary.setInverted(true);
  }

  public void setRightSpeed(double speed){
    m_rightPrimary.set(speed);
  }

  public void setLeftSpeed(double speed){
    m_leftPrimary.set(speed);
  }

  public void setIdleMode(NeutralMode mode){
    m_rightPrimary.setNeutralMode(mode);
    m_leftPrimary.setNeutralMode(mode);
    m_rightSecondary.setNeutralMode(mode);
    m_leftSecondary.setNeutralMode(mode);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}