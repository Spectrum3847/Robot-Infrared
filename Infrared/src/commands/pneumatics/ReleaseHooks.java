package commands.pneumatics;

import commands.CommandBase;
import framework.Init;

/**
 * @author matthew
 */
public class ReleaseHooks extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        Init.deployHooks.cancel();
    }

    protected boolean isFinished() {
        return Init.deployHooks.isCanceled();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }

}