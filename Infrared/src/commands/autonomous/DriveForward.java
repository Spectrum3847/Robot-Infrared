package commands.autonomous;

import commands.CommandBase;
import driver.Gamepad;
import framework.OI;
import framework.Utilities;

/**
 *
 * @author matthew
 */
public class DriveForward extends CommandBase {

    public DriveForward() {
        requires(CommandBase.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.engageCheesy();
        drivebase.enableTurnController();
        System.out.println("Cheesydrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivebase.setCheesySensetivity(1.32);

        drivebase.setCheesyDrive(0.3, 0, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setArcade(0, 0);
        drivebase.disableTurnController();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}