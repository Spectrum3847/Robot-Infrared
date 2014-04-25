package commands.collection;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;


/*
 * @author Matthew
 */
public class SippingBirdAutoCatch extends CommandBase {
    private boolean ball_detected = false;
    private double delay;
    private double distance;
    private double start_time;

    public SippingBirdAutoCatch() {
        requires(CommandBase.sippingbird);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        sippingbird.collectorDeploy();
        sippingbird.collectorOFF();
        launcher.wingsOpen();
        System.out.println("SippingBird, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(sippingbird.ballDetectDistance < distance && !ball_detected) {
            ball_detected = true;
            start_time = Timer.getFPGATimestamp()*1000;
        }
        if(Timer.getFPGATimestamp() - start_time > delay && ball_detected) {
            end();
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        sippingbird.collectorRetract();
        launcher.wingsClose();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
