/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


// NOT CURRENTLY USED
// WE USE A LAMBDA FUNCTION IN OI INSTEAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 

package frc.robot.drivebase;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class SwitchFront extends InstantCommand {
  public SwitchFront() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveBase);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.driveBase.switchFront();
  }
}
