package commands.lift;

import commands.CommandBase;
import framework.OI;

/**
 *
 * @author matthew
 */
public class LiftControl  extends CommandBase {

    protected void initialize() {
        CommandBase.lift.setSpeed(0);
    }

    protected void execute() {
        CommandBase.lift.setSpeed(OI.gamepad_aux.getLeftY());
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
