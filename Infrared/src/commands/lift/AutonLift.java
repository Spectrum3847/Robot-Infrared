package commands.lift;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class AutonLift extends CommandBase {
    
    protected void initialize() {
    }

    protected void execute() {
        lift.setSpeed(-1);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        lift.setSpeed(0);
    }

    protected void interrupted() {
        end();
    }
    
}