package commands.launching;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class LauncherStall extends CommandBase {

    protected void initialize() {
        launcher.setLauncherSpeed(-0.1);
    }

    protected void execute() {
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
