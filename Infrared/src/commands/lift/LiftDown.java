package commands.lift;

import commands.CommandBase;
import framework.OI;

/**
 *
 * @author matthew
 */
public class LiftDown extends CommandBase {

    protected void initialize() {
        CommandBase.lift.setSpeed(0);
    }

    protected void execute() {
        CommandBase.lift.setSpeed(-0.7);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        CommandBase.lift.setSpeed(0);
    }

    protected void interrupted() {
        CommandBase.lift.setSpeed(0);
    }

}
