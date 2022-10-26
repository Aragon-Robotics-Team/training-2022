package frc.robot.subsystems.intake;

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
        public static final int kChannel = 0;
    }

    private CANSparkMax m_intakeMotor = new CANSparkMax(Config.kIntakeMotorID, MotorType.kBrushless);
    private Solenoid m_arm = new Solenoid(1, PneumaticsModuleType.CTREPCM, Config.kChannel);
    public IntakeArm() {
        m_intakeMotor.setInverted(true);
        m_intakeMotor.setIdleMode(IdleMode.kCoast);
    }

    public void setForwardIntakeArmSpeed(){
        m_intakeMotor.set(Config.kIntakeMotorSpeed);
    }

    public void setReverseIntakeArmSpeed(){
        m_intakeMotor.set(-Config.kIntakeMotorSpeed);
    }

    public void setZeroIntakeArmSpeed(){
        m_intakeMotor.set(0.0);
    }

    public boolean getState(){
        return m_arm.get();
    }

    public InstantCommand IntakeArmForward(){
        return new InstantCommand(this::setForwardIntakeArmSpeed, this);
    }

    public InstantCommand reverseIntakeArmForward(){
        return new InstantCommand(this::setReverseIntakeArmSpeed, this);
    }

    public InstantCommand IntakeArmOff(){
        return new InstantCommand(this::setZeroIntakeArmSpeed, this);
    }

    public InstantCommand IntakeSetForward(){
        return new InstantCommand(this::setForward, this);
    }

    public InstantCommand IntakeSetBack(){
        return new InstantCommand(this::setBack, this);
    }

    public void setForward(){
        m_arm.set(true);
    }

    public void setBack(){
        m_arm.set(false);
    }

    @Override
    public void periodic(){
            
    }
}