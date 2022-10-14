package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  private Drivetrain m_Drivetrain;

  private Joystick m_joystick;

  private final static class Config {
    public static final double speedMultiplier = 0.7;
    public static final double turnMultiplier = 0.7;
    public static final int leftStickY = 1;
    public static final int rightStickX = 2;
  }

  public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {
    m_Drivetrain = drivetrain;
    m_joystick = joystick;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled. (stupid wpi confusing
  // comment)
  // a function that is called once at the start that sets up stuff.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled. (another
  // stupid wpi comment)
  // Loop that is executed every 0.2 ms.
  @Override
  public void execute() {
    // WPI has some function that makes you set raw axis so here we go========>
    double turn = m_joystick.getRawAxis(Config.rightStickX) * Config.turnMultiplier;
    double speed = m_joystick.getRawAxis(Config.leftStickY) * Config.speedMultiplier;
    double left = speed + turn;
    double right = speed - turn;
    m_Drivetrain.setRightSpeed(right);
    m_Drivetrain.setLeftSpeed(left);
  }

  // Called once the command ends or is interrupted.
  // ^^^ stupid wpi comment
  // when end() is called, it stops the robot, setting speed to zero, stopping it
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.setRightSpeed(0);
    m_Drivetrain.setLeftSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}