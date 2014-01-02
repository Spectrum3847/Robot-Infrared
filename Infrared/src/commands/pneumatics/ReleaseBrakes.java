package commands.pneumatics;

import commands.CommandBase;
import framework.Init;

/**
 * @author matthew
 */
public class ReleaseBrakes extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        Init.deployBrakes.cancel();
    }

    protected boolean isFinished() {
        return Init.deployBrakes.isCanceled();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }

}
