package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase{
    private static final class Config{
    public static final int k_hopperMotorID=96;
    public static final double k_hopperSpeed=0.7;
  }

    private WPI_TalonFX hopperMotor = new WPI_TalonFX(Config.k_hopperMotorID);

    public Hopper(){
      hopperMotor.setInverted(true);
    }

    public void setFowardHopperSpeed(){
      hopperMotor.set(Config.k_hopperSpeed);
    }

    public void setReverseHopperSpeed(){
      hopperMotor.set(-Config.k_hopperSpeed);
    }

    public void setZeroHopperSpeed(){
      hopperMotor.set(0.0);
    }

    public InstantCommand HopperForward(){
      return new InstantCommand(this::setFowardHopperSpeed, this);
    }

    public InstantCommand HopperReverse(){
      return new InstantCommand(this::HopperReverse, this);
    }

    public InstantCommand HopperOff(){
      return new InstantCommand(this::HopperOff, this);
    }

    @Override
    public void periodic(){

    }
}