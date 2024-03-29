package frc.robot.hold; // the package where this file lives

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class HoldMAP {

    // Speed Controllers/Motors
    public static CANSparkMax holdMotor;

    // Hold Constants
    public static double DEFAULT_SPEED = -0.4; //  if we want a default speed for commands
    public static double MAX_SPEED = -1; // if we want to set a maximum intake speed


    public static void init() {

        holdMotor = new CANSparkMax(33, MotorType.kBrushless); 

    }

    

}