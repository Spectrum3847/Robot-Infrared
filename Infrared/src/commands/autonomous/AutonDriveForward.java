package commands.autonomous;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * Drive forward until canceled
 * @author matthew
 */
public class AutonDriveForward extends CommandBase {

    public AutonDriveForward() {
        requires(CommandBase.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.engageAlt();
        //drivebase.enableTurnController();
        System.out.println("Cheesydrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivebase.setCheesySensetivity(1.32);

        drivebase.setCheesyDrive(SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, 0.5), 0, false);
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