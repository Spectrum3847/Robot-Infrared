package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class LauncherPotZero extends CommandBase {

    public LauncherPotZero() {
        requires(launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putNumber(Dashboard.LAUNCHER_OFFSET, -launcher.getPot().getRawAngle()*launcher.getPot().getGearRatio());
        launcher.getPot().setOffset(-launcher.getPot().getRawAngle()*launcher.getPot().getGearRatio());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
