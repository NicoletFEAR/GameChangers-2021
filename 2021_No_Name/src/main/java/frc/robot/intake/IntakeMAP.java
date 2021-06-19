package frc.robot.intake; // the package where this file lives

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeMAP {

    // Speed Controllers/Motors
    public static CANSparkMax intakeMotor;

    // Intake Constants
    public static double DEFAULT_SPEED = 0.3; //  if we want a default speed for commands
    public static double MAX_SPEED = 1.0; // if we want to set a maximum intake speed

    // Piston
    public static DoubleSolenoid intakePiston;

    public static void init() {

        intakeMotor = new CANSparkMax(21, MotorType.kBrushless);

        intakeMotor.setSmartCurrentLimit(40, 40);

        intakeMotor.setIdleMode(IdleMode.kCoast);
        intakeMotor.setOpenLoopRampRate(0.05);

        intakePiston = new DoubleSolenoid(2, 3);


    }

}
