package commands.autonomous;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author matthew
 */
public class AutonCheesyVisionBlockDrive extends CommandBase {

    private final String speed;
    private final String angling;
    private double invert = 1;
    
    public AutonCheesyVisionBlockDrive(String key, String angling) {
        requires(CommandBase.drivebase);
        this.speed = key;
        this.angling = angling;
    }
    
    public AutonCheesyVisionBlockDrive(String key, String angling, boolean invert) {
        requires(CommandBase.drivebase);
        this.speed = key;
        this.angling = angling;
        this.invert = invert?-1:1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.engageOmni();
        drivebase.setCheesySensetivity(1.32);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!cheesyvision.getLeftStatus() && cheesyvision.getRightStatus())
            drivebase.setCheesyDrive(invert*SmartDashboard.getNumber(speed), SmartDashboard.getNumber(angling), false);
        else if(!cheesyvision.getRightStatus() && cheesyvision.getLeftStatus())
            drivebase.setCheesyDrive(invert*-SmartDashboard.getNumber(speed), -SmartDashboard.getNumber(angling), false);
        else
            drivebase.setCheesyDrive(0, 0, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setArcade(0, 0);
        drivebase.disableTurnController();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
