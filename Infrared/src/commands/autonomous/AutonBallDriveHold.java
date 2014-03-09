package commands.autonomous;

import commands.CommandBase;

/**
 * Drag second ball around using collectors
 * 
 * @author matthew
 */
public class AutonBallDriveHold extends CommandBase {

    public AutonBallDriveHold() {
        requires(CommandBase.sippingbird);
    }
    
    public AutonBallDriveHold(double speed) {
        requires(CommandBase.sippingbird);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        sippingbird.setCollector(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        sippingbird.setCollector(0.5);
        sippingbird.collectorDeploy();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        sippingbird.collectorOFF();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}