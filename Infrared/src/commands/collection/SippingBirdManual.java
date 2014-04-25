package commands.collection;

import commands.CommandBase;
import framework.OI;

/**
 *
 * @author matthew
 */
public class SippingBirdManual extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        sippingbird.setCollector(OI.gamepad_aux.getTriggers());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        sippingbird.setCollector(0.0);
    }

    protected void interrupted() {
        end();
    }

}
