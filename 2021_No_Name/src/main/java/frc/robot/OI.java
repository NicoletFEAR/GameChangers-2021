package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
// import frc.robot.drivebase.OpenLoopDrive;
// import frc.robot.climb.*;
// import frc.robot.constants.*;

/**
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public XboxController xbox0; // the drive controller // input 0 on driver station
    private Button xbox0LeftStick;
    private Button xbox0RightStick;
    private Button xbox0LBumper;
    private Button xbox0RBumper;
    private Button xbox0Start;
    private Button xbox0Back;
    private Button xbox0X;
    private Button xbox0Y;
    private Button xbox0B;
    private Button xbox0A;
    private POVButton xbox0DpadUp;
    private POVButton xbox0DpadRight;
    private POVButton xbox0DpadDown;
    private POVButton xbox0DpadLeft;

    private XboxController xbox1; // the game mech controller // input 1 on driver station
    private Button xbox1LeftStick;
    private Button xbox1RightStick;
    private Button xbox1LBumper;
    private Button xbox1RBumper;
    private Button xbox1Start;
    private Button xbox1Back;
    private Button xbox1X;
    private Button xbox1Y;
    private Button xbox1B;
    private Button xbox1A;
    private POVButton xbox1DpadUp0;
    private POVButton xbox1DpadUp45;
    private POVButton xbox1DpadUp315;
    private POVButton xbox1DpadDown180;
    private POVButton xbox1DpadDown135;
    private POVButton xbox1DpadDown225;
    private POVButton xbox1DpadRight;
    private POVButton xbox1DpadLeft;

    private Joystick customController; // the box button controller // input 2 on driver station
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private Button Button6;
    private Button Button7;
    private Button Button8;
    private Button Button9;
    private Button Button10;

    public OI() {

        // Init our driver controller and buttons
        xbox0 = new XboxController(0);

        xbox0A = new JoystickButton(xbox0, 1);
        xbox0B = new JoystickButton(xbox0, 2);
        xbox0X = new JoystickButton(xbox0, 3);
        xbox0Y = new JoystickButton(xbox0, 4);
        xbox0LBumper = new JoystickButton(xbox0, 5);
        xbox0RBumper = new JoystickButton(xbox0, 6);
        xbox0Back = new JoystickButton(xbox0, 7);
        xbox0Start = new JoystickButton(xbox0, 8);
        xbox0LeftStick = new JoystickButton(xbox0, 9);
        xbox0RightStick = new JoystickButton(xbox0, 10);
        xbox0DpadUp = new POVButton(xbox0, 0);
        xbox0DpadLeft = new POVButton(xbox0, 270);
        xbox0DpadRight = new POVButton(xbox0, 90);
        xbox0DpadDown = new POVButton(xbox0, 180);

        // Init our mech controller and buttons
        xbox1 = new XboxController(1);

        xbox1A = new JoystickButton(xbox1, 1);
        xbox1B = new JoystickButton(xbox1, 2);
        xbox1X = new JoystickButton(xbox1, 3);
        xbox1Y = new JoystickButton(xbox1, 4);
        xbox1LBumper = new JoystickButton(xbox1, 5);
        xbox1RBumper = new JoystickButton(xbox1, 6);
        xbox1Back = new JoystickButton(xbox1, 7);
        xbox1Start = new JoystickButton(xbox1, 8);
        xbox1LeftStick = new JoystickButton(xbox1, 9);
        xbox1RightStick = new JoystickButton(xbox1, 10);
        xbox1DpadUp0 = new POVButton(xbox1, 0);
        xbox1DpadUp45 = new POVButton(xbox1, 45);
        xbox1DpadUp315 = new POVButton(xbox1, 315);
        xbox1DpadRight = new POVButton(xbox1, 90);
        xbox1DpadDown180 = new POVButton(xbox1, 180);
        xbox1DpadDown135 = new POVButton(xbox1, 135);
        xbox1DpadDown225 = new POVButton(xbox1, 315);
        xbox1DpadLeft = new POVButton(xbox1, 270);

        // xbox0 for normal operation
        xbox0X.whenPressed(() -> Robot.driveBase.switchFront()); 

        // D pad up and down for intake piston
        xbox0DpadUp.whenPressed(() -> Robot.intake.up());
        xbox0DpadDown.whenPressed(() -> Robot.intake.down());
        
        // Y for automatic shooting (whenHeld we think)
        //xbox0Y.whenHeld(whatever out full auto shooting is)

        // A whileHeld lambda for constant intake
        xbox0A.whileHeld(() -> Robot.intake.intake());
        
        // left joy y axis for intake variable
        // i think this might just be inside of a default command im not sure tho

        // xbox1 for manual shooter stuff
        // right trigger for firing speed
        // left trigger for intake speed
        // left joystick x axis for turret manual
        // a button when held for hold noodles to actually fire balls
        xbox1A.whileHeld(() -> Robot.shooter.runHold());
        // 

        // xbox1Y.whileHeld(new ClimbUp(), true);
        // xbox1X.whileHeld(new ClimbReset(), true);
    }

    // Driver
    public XboxController getXbox0() {
        return xbox0;
    }

    // Mech
    public XboxController getXbox1() {
        return xbox1;
    }

    public double getDriveRightTrigger() {
        return getXbox0().getTriggerAxis(GenericHID.Hand.kRight);
    }

    public double getDriveLeftTrigger() {
        return getXbox0().getTriggerAxis(GenericHID.Hand.kLeft);
    }

    public double getDriveSteer() {
        return getXbox0().getX(GenericHID.Hand.kLeft);
    }

    public double getClimbSpeed() {
        return -getXbox1().getY(GenericHID.Hand.kLeft);
    }

    public double getWinchSpeed() {
        return getXbox0().getY(GenericHID.Hand.kRight);
    }
}