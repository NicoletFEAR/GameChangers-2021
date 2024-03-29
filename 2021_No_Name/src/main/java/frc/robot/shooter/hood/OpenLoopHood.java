package frc.robot.shooter.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


// Command for manual control of the hood via joystick
// used mostly for tuning & testing purposes
public class OpenLoopHood extends CommandBase {
    double movementVal;
    private boolean usePID;
    
    public OpenLoopHood() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Robot.hood);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        usePID = false; //we set to determine
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        movementVal = Robot.oi.getHoodAxis(); //Get Y input from left joystick on mech driver xbox1
        if (Math.abs(movementVal) < 0.05) {   
            movementVal = 0; 
        }
        System.out.println(movementVal);
        // if (movementVal > 0.3) {
        //     Robot.hood.setPoint += 1.0;
        // } else if (movementVal < -0.3) {
        //     Robot.hood.setPoint -= 1.0;
        // }
        //Might need to tune multiplier value
        //System.out.println("********* INSIDE OPENLOOPHOOD");
      //if (usePID) {
          if (false) {
            if (movementVal > 0.5) {
                //Hood PID += 1
                Robot.hood.changePID(0.01);
            } else if (movementVal < -0.5) {
                //Hood PID--;
                Robot.hood.changePID(-0.01);
            } else {
                //Else
            }
        } else {
            movementVal *= HoodMAP.HOOD_MULTIPLIER;
            
            if (Math.abs(movementVal) > HoodMAP.MAX_SPEED) {
                if (movementVal > 0) {
                    Robot.hood.setHoodSpeed(HoodMAP.MAX_SPEED);
                } else {
                    Robot.hood.setHoodSpeed(-HoodMAP.MAX_SPEED);
                }
                Robot.hood.setHoodSpeed(HoodMAP.MAX_SPEED);
            } else {
                Robot.hood.setHoodSpeed(movementVal); //Pass adjusted joystick input to move method
            }
        }
        // leave this:
        // long term we really want this joystick input to
        // be changing the setpoint for the Sparkmax built in PID
        // so it would be a change pid thing, but for now 
        // its just manually setting speed
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.hood.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
