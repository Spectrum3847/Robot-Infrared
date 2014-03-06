package commands.blocking;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class LauncherBlock extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        launcher.setCIMSpeed(0.7);
    }

    protected boolean isFinished() {
        return CommandBase.launcher.isUp();
    }

    protected void end() {
        CommandBase.launcher.setLauncherSpeed(0);
    }

    protected void interrupted() {
        end();
    }

}
