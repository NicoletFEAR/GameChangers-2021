// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// String(Files.readAllBytes(Paths.get(file)))
// String file = "src/test/resources/myFile.json";

package frc.robot.autonomous;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.kicker.KickToShooter;
import frc.robot.shooter.AutoAim;
import frc.robot.shooter.AutoFlywheel;
import frc.robot.shooter.AutoShoot;
import frc.robot.spindexer.SpinSmart;

public class Player extends CommandBase {

  double thisLine[] = new double[14];
  // double thisLine[] = new double[28]; // two controllers

  int currentLine = 0;
  public boolean isPlaying = false;

  //public Scanner scanner = null;
  long startTime;

  SpinSmart spiny; // command for SmartSpin
  KickToShooter kick; // command for KickToShoot
  AutoAim aim;
  AutoShoot autoshoot;
  AutoFlywheel flywheel;

  boolean onTime = true;
  double nextDouble;
  String autoToPlay;
  Gson gson;

  boolean isFinished;
  FileReader fileReader;

  //OLDAutoShoot shooty;

  ArrayList<double[]> allLines;
  /** Creates a new Player. */
  public Player() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveBase);
    //addRequirements(Robot.shifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gson = new Gson();
    currentLine = 0;
    isFinished = false;
    autoToPlay = "defaultEmpty";

    isPlaying = true;
    
    SmartDashboard.putBoolean("isPlaying", isPlaying);
    autoToPlay = SmartDashboard.getString("autoToPlay", "defaultEmpty");
    System.out.println("playing auto " + autoToPlay);
    //String file = "src/test/resources/myFile.json";
    // writer = new FileWriter("/c/recordings" + newPlayName + ".json");
    try {fileReader = new FileReader("/c/" + autoToPlay + ".json");}
    catch (Exception e) {
      isFinished = true;
      isPlaying = false;
      System.out.println("could not create FileReader");
      System.out.println(e);
    }
    //String stringJson = String(Files.readAllBytes(Paths.get(file)));
    if (allLines != null) {
      allLines.clear();
    }
    
    try {
      allLines = gson.fromJson(fileReader, new TypeToken<List<double[]>>(){}.getType());
      System.out.println("opened read file");
    } catch (Exception e) {
      System.out.println("failed to open read file");
      System.out.println(e);

      //Robot.player.endPlaying();
    }
		
		//let scanner know that the numbers are separated by a comma or a newline, as it is a .csv file
    
    isPlaying = true;
    SmartDashboard.putBoolean("isPlaying", isPlaying);
		
		//lets set start time to the current time you begin autonomous
    //startTime = System.currentTimeMillis();
    //Robot.shifter.isPlayingShift = true;

    

  }

  // Called every time the scheduler runs while the command is scheduled.
  /* current layout:
    XBOX0 (0-19), XBOX1(20-39) 
      joysticks 0-3
        0 = left x, 1 = left y
        2 = right x, 3 = right y
      4 = left trigger
      5 = right trigger
      6 = left bumper
      7 = right bumper
      coloured buttons 8-11
        8 = x
        9 = y
        10 = a
        11 = b
      12 = start
      13 = back
      14 = DPAD Up
      15 = DPad Right
      16 = DPad Down
      17 = DPad Left
      18 = Left Joy Btn
      19 = Right Joy Btn

    */
  @Override
  public void execute() {

    
    double[] thisLine = allLines.get(currentLine);
    
    // RUN LINE

     // DRIVE // triggers and joystick xbox0
     Robot.driveBase.RacingDrive(thisLine[5]-thisLine[4], thisLine[0]);// * DriveBaseMAP.TURN_SCALING);

     // SWITCH FRONT // 8 // X xbox0
     if (thisLine[8] == 1.0) {Robot.driveBase.switchFront();} 
    
     // SHIFTER (IGNORE)

     // SPIN INTAKE // 10 and 30 // A xbox0 & xbox1
     if (thisLine[10] == 1.0) {Robot.intake.intake();}
     if (thisLine[10] == 3.0) {Robot.intake.stop();} // when released
     if (thisLine[30] == 1.0) {Robot.intake.intake();}
     if (thisLine[30] == 3.0) {Robot.intake.stop();} // when released

     // DEPLOY INTAKE // 14 and 34 // D Pad Up xbox0 & xbox1
     if (thisLine[14] == 1.0) {Robot.intake.deploy();}
     if (thisLine[34] == 1.0) {Robot.intake.deploy();}

     // RETRACT INTAKE // 16 and 36 // D Pad Down xbox0 & xbox1
     if (thisLine[16] == 1.0) {Robot.intake.retract();}
     if (thisLine[36] == 1.0) {Robot.intake.retract();}

     // SPINDEXER SPIN SMART // 9 and 10 and 28 // A and Y xbox0 & X xbox1
     // reference:
     //if (thisLine[9] == 1.0) {shooty = new AutoShoot(); shooty.schedule();} else if (thisLine[9] == 3.0) {shooty.cancel();}
      if (thisLine[9] == 1.0) {spiny = new SpinSmart(); spiny.schedule();}
      else if (thisLine[9] == 3.0) {spiny.cancel();}
      else if (thisLine[10] == 1.0) {spiny = new SpinSmart(); spiny.schedule();}
      else if (thisLine[10] == 3.0) {spiny.cancel();}
      else if (thisLine[28] == 1.0) {spiny = new SpinSmart(); spiny.schedule();}
      else if (thisLine[28] == 3.0) {spiny.cancel();}

     // SPINDEXER SPIN COUNTERCLOCK // 17 and 37 // D Pad left xbox0 & xbox1
     if (thisLine[17] == 1.0) {Robot.spindexer.spinCounterClockwise();}
     if (thisLine[17] == 3.0) {Robot.spindexer.stop();}
     if (thisLine[37] == 1.0) {Robot.spindexer.spinCounterClockwise();}
     if (thisLine[37] == 3.0) {Robot.spindexer.stop();}

     // SPINDEXER SPIN CLOCK // 15 and 35 // D Pad right xbox0 & xbox1
     if (thisLine[15] == 1.0) {Robot.spindexer.spinClockwise();}
     if (thisLine[15] == 3.0) {Robot.spindexer.stop();}
     if (thisLine[35] == 1.0) {Robot.spindexer.spinClockwise();}
     if (thisLine[35] == 3.0) {Robot.spindexer.stop();}

     // AUTO SHOOT // 9 // Y xbox0
     if (thisLine[9] == 1.0) {autoshoot = new AutoShoot(); autoshoot.schedule();}
     else if (thisLine[9] == 3.0) {autoshoot.cancel();}

     // AUTO FLYWHEEL // 29 // Y xbox1
     if (thisLine[29] == 1.0) {flywheel = new AutoFlywheel(); flywheel.schedule();}
     else if (thisLine[29] == 3.0) {flywheel.cancel();}

     // AIM // 11 // B xbox0
     if (thisLine[11] == 1.0) {aim = new AutoAim(); aim.schedule();}
     else if (thisLine[11] == 3.0) {aim.cancel();}

     // KICKER // 9 and 31 // B and Y xbox1
     if (thisLine[9] == 1.0) {kick = new KickToShooter(); kick.schedule();}
     else if (thisLine[9] == 3.0) {kick.cancel();}
     else if (thisLine[31] == 1.0) {kick = new KickToShooter(); kick.schedule();}
     else if (thisLine[31] == 3.0) {kick.cancel();}

    // END RUN LINE

    currentLine ++;

    if (currentLine >= allLines.size() - 1) {
      isFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isPlaying = false;

    Robot.spindexer.stop();
    Robot.intake.intake();
    Robot.driveBase.stop();
    Robot.kicker.stop();
    Robot.driveBase.stop();

    SmartDashboard.putBoolean("isPlaying", isPlaying);
    try {fileReader.close();}
    catch(Exception e) {}
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (isFinished){
      return true;
    } else { 
      return false;
    }
  }
}
