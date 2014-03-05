package commands.pneumatics;

import commands.CommandBase;
import framework.OI;
import framework.Utilities;

/**
 *
 * @author JAG
 */
public class RunCompressor extends CommandBase {

    public RunCompressor() {
    }

    /**
     * Start Compressor once it's started
     */
    protected void initialize() {
        pneumatics.runCompressor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double raw_throttle = Utilities.haloDeadBand(OI.gamepad.getLeftY(), OI.gamepad.getRightX(), .15, .17);
        if(raw_throttle > 0.92)
            pneumatics.stopCompressor();
        else
            pneumatics.runCompressor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        pneumatics.stopCompressor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
