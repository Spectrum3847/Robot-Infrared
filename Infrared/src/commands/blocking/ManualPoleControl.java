package commands.blocking;

import commands.CommandBase;
import framework.OI;

/**
 *
 * @author matthew
 */
public class ManualPoleControl extends CommandBase {
    
    public ManualPoleControl() {
        requires(pole);
    }

    protected void initialize() {
    }

    protected void execute() {
        pole.setMotor(OI.gamepad_aux.getTriggers());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
