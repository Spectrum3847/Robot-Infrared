package commands.blocking;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class LauncherBlock extends CommandBase {
    
    public LauncherBlock() {
        requires(launcher);
    }

    protected void initialize() {
    }

    protected void execute() {
        launcher.setCIMSpeed(0.95);
    }

    protected boolean isFinished() {
        return CommandBase.launcher.getArmAngle() > 107;
    }

    protected void end() {
        CommandBase.launcher.setLauncherSpeed(0);
    }

    protected void interrupted() {
        end();
    }

}
