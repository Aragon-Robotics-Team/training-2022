package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase{
    private static final class Config{
    public static final int k_hopperMotorID=96;
  }

    private WPI_TalonFX hopperMotor = new WPI_TalonFX(Config.k_hopperMotorID);

    public Hopper(){

    }

    @Override
    public void periodic(){

    }
}