package commands.collection;

import commands.CommandBase;


/*
 * @author Matthew
 */
public class SippingBirdEject extends CommandBase {

    public SippingBirdEject() {
        requires(CommandBase.sippingbird);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatics.collectorRetract();
        pneumatics.wingsOpen();
        System.out.println("SippingBird, Eject!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        sippingbird.collectorREV();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        sippingbird.collectorOFF();
        pneumatics.collectorRetract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
