package commands.collection;

import commands.CommandBase;


/*
 * @author Matthew
 */
public class SippingBirdCatch extends CommandBase {

    public SippingBirdCatch() {
        requires(CommandBase.sippingbird);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        sippingbird.collectorDeploy();
        sippingbird.collectorOFF();
        System.out.println("SippingBird, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return sippingbird.isBall();
    }

    // Called once after isFinished returns true
    protected void end() {
        sippingbird.collectorRetract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
