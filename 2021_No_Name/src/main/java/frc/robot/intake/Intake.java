package frc.robot.intake;

import frc.robot.intake.*; // imports everything in the drivebase folder - "*" means all in folder

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends SubsystemBase {
    /**
     * Operates Intake.
     */

    // Our Methods HERE
    public void intake() { // suck balls in at a constant speed
        IntakeMAP.intakeMotor.set(IntakeMAP.DEFAULT_SPEED);
    }

    public void exhaust() { // exhaust balls out at a constant speed
        IntakeMAP.intakeMotor.set(-IntakeMAP.DEFAULT_SPEED);
    }

    public void variableIntake(double speed) {
        if (speed < IntakeMAP.MAX_SPEED) {
            IntakeMAP.intakeMotor.set(speed);
        } else {
            IntakeMAP.intakeMotor.set(IntakeMAP.MAX_SPEED);
        }
        
    }

    public void up() { // arm up
        IntakeMAP.intakePiston.set(Value.kForward); // may need to flip forward and reverse
    }

    public void down() { // arm down
        IntakeMAP.intakePiston.set(Value.kReverse);
    }

    public void stop() {
        IntakeMAP.intakeMotor.set(0.0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
    

