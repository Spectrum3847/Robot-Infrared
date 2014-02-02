package commands.launching;

import commands.CommandBase;


/*
 * @author Matthew
 */
public class SippingBirdLaunchReady extends CommandBase {

    public SippingBirdLaunchReady() {
        requires(CommandBase.sippingbird);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatics.collectorDeploy();
        sippingbird.collectorOFF();
        System.out.println("SippingBird, LAUNCH READY!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        pneumatics.collectorRetract();
        sippingbird.collectorOFF();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
