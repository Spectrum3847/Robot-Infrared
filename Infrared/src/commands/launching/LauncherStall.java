package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherStall extends CommandBase {
    
    public LauncherStall() {
        requires(launcher);
    }

    protected void initialize() {
    }

    protected void execute() {
        launcher.stallLauncher();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        launcher.stopLauncher();
    }

    protected void interrupted() {
        end();
    }

}
