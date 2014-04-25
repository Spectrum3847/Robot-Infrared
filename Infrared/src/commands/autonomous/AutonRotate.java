package commands.autonomous;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * Drive forward until canceled
 * @author matthew
 */
public class AutonRotate extends CommandBase {

    private double rot = 0.0;
    private String key = "";
    private double inverse = 1;
    
    public AutonRotate() {
        requires(CommandBase.drivebase);
        rot = 0.0;
        this.key = Dashboard.AUTON_AT_GOAL_ROTATION_ROT_R;
    }
    
    public AutonRotate(double rotate) {
        requires(CommandBase.drivebase);
        this.rot = rotate;
    }
    
    public AutonRotate(String key) {
        requires(CommandBase.drivebase);
        this.key = key;
    }
    
    public AutonRotate(double rotate, boolean invert) {
        requires(CommandBase.drivebase);
        this.rot = rotate;
        inverse = invert?-1:1;
    }
    
    public AutonRotate(String key, boolean invert) {
        requires(CommandBase.drivebase); 
        this.key = key;
        inverse = invert?-1:1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.engageOmni();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivebase.setCheesySensetivity(1.32);
        if(rot != 0.0)
            drivebase.setCheesyDrive(0, rot, true);
        else
            drivebase.setCheesyDrive(0, inverse*SmartDashboard.getNumber(key), true);
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