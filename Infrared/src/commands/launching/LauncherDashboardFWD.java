package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;


/*
 * @author Matthew
 */
public class LauncherDashboardFWD extends CommandBase {

    public LauncherDashboardFWD() {
        requires(launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("SHOOOT!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Init.launchready.isRunning()){
            if(launcher.getArmAngle() < SmartDashboard.getNumber(Dashboard.SHOOTER_ANGLE, 90.0))
                launcher.fastLauncher(); 
            else
                launcher.setLauncherSpeed(-.2);
        } else {
            launcher.stopLauncher();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Init.launchready.isRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.stopLauncher();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
