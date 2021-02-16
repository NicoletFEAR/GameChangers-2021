package frc.robot.drivebase;

// Constants mapping for the Drive Base

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


/**
 * The DriveBaseMAP is a mapping from the ports sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
 */
public class DriveBaseMAP {

    // Speed Controllers/Motors
    public static CANSparkMax driveMotorL1;
    public static CANSparkMax driveMotorL2;
    public static CANSparkMax driveMotorL3;
    public static CANSparkMax driveMotorR1;
    public static CANSparkMax driveMotorR2;
    public static CANSparkMax driveMotorR3;
    
    // Shifter
    public static DoubleSolenoid shifty = new DoubleSolenoid(0, 1);
    public static double SHIFT_DOWN_THRESHOLD = 1500; // NEEDS CALCULATING
    public static double SHIFT_UP_THRESHOLD = 3000; // NEEDS CALCULATING

    public static final Value LOW_GEAR = Value.kForward;
    public static final Value HIGH_GEAR = Value.kReverse;

    // OpenLoopDriveCMD
    public static final double TURN_SCALING = 1.0; // dampens sensitivity of controls to turning
    public static final double SPEED_SCALING = 1.0; // sets a max speed for driver control
    public static final double LEFT_RIGHT_ADJUST = 0.0; // basic adjust to keep robot applying roughly equal power

    // SparkMax Configurations
    public static final int DRIVE_CURRENT_STALL_LIMIT = 80; // default is 80A
    public static final int DRIVE_CURRENT_FREE_LIMIT = 80; // default is 80A

    public static final double DRIVE_RAMP_RATE = 0.0; // bigger number = less acceleration

    // Dimensions
    // public static double WHEEL_RADIUS = 3.0; // wheel radius in inches
    // public static double WHEEL_WIDTH_DST = 26.0; // ESTIMATE CHANGE

    public static void init() {

        // Speed Controller/Motors
        driveMotorL1 = new CANSparkMax(11, MotorType.kBrushless);
        driveMotorL2 = new CANSparkMax(13, MotorType.kBrushless);
        driveMotorL3 = new CANSparkMax(15, MotorType.kBrushless);

        driveMotorR1 = new CANSparkMax(12, MotorType.kBrushless);
        driveMotorR2 = new CANSparkMax(14, MotorType.kBrushless);
        driveMotorR3 = new CANSparkMax(16, MotorType.kBrushless);

        driveMotorL2.follow(driveMotorL1);
        driveMotorL3.follow(driveMotorL1);

        driveMotorR2.follow(driveMotorR1);
        driveMotorR3.follow(driveMotorR1);


        driveMotorL1.setSmartCurrentLimit(DRIVE_CURRENT_STALL_LIMIT, DRIVE_CURRENT_FREE_LIMIT);
        driveMotorL2.setSmartCurrentLimit(DRIVE_CURRENT_STALL_LIMIT, DRIVE_CURRENT_FREE_LIMIT);
        driveMotorL3.setSmartCurrentLimit(DRIVE_CURRENT_STALL_LIMIT, DRIVE_CURRENT_FREE_LIMIT);
        driveMotorR1.setSmartCurrentLimit(DRIVE_CURRENT_STALL_LIMIT, DRIVE_CURRENT_FREE_LIMIT);
        driveMotorR2.setSmartCurrentLimit(DRIVE_CURRENT_STALL_LIMIT, DRIVE_CURRENT_FREE_LIMIT);
        driveMotorR3.setSmartCurrentLimit(DRIVE_CURRENT_STALL_LIMIT, DRIVE_CURRENT_FREE_LIMIT);

        driveMotorL1.setIdleMode(IdleMode.kBrake);
        driveMotorL2.setIdleMode(IdleMode.kBrake);
        driveMotorL3.setIdleMode(IdleMode.kBrake);
        driveMotorR1.setIdleMode(IdleMode.kBrake);
        driveMotorR2.setIdleMode(IdleMode.kBrake);
        driveMotorR3.setIdleMode(IdleMode.kBrake);
        // driveMotorL1.setOpenLoopRampRate(DRIVE_RAMP_RATE);

    }

    public static double getNEOEncoderRotations(CANSparkMax spark) {
        return spark.getEncoder().getPosition(); // in # of rotations
    }

    public static double getNEOEncoderVelocity(CANSparkMax spark) {
        return spark.getEncoder().getVelocity(); // in RPM
    }

    public static void resetEncoderNEO(CANSparkMax spark, double position) {
        spark.getEncoder().setPosition(position); // in # of rotations
    }
    
}