package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;


/*
 * @author Matthew
 */
public class LauncherFWD extends CommandBase {

    public LauncherFWD() {
        requires(CommandBase.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("SHOOOT!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Init.launcherready.isRunning())
            launcher.fastLauncher();
        else
            launcher.stopLauncher();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Init.launcherready.isRunning();
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
