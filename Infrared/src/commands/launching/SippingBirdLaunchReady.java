package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
        shooter.enableEncoder();
        System.out.println("SippingBird, LAUNCH READY!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Current Shooter Angle", shooter.getArmAngle());
        SmartDashboard.putNumber("Current Shooter Rate", shooter.getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        pneumatics.collectorRetract();
        shooter.disableEncoder();
        sippingbird.collectorOFF();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
