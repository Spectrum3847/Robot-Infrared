package commands.blocking;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class LauncherBlock extends CommandBase {
    
    public static double power = 0.6;
    public static double angle = 95;
    
    public LauncherBlock() {
        requires(launcher);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }

}
