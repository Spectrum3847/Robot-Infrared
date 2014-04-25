package commands.launching;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class CheesyWait extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return (cheesyvision.getLeftStatus() || cheesyvision.getRightStatus());
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
