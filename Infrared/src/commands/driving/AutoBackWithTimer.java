/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zachary
 */
package commands.driving;
import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;


public class AutoBackWithTimer extends CommandBase {
    
    Timer time;
    double speed;
    double end;
    
    public AutoBackWithTimer() {
        super();
        requires(drivebase);
        speed = 1;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    public Timer getTimer(){
        return time;
    }
    
    public double getTime(){
        return time.get();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    time = new Timer();
    time.start();
    end = SmartDashboard.getNumber(Dashboard.BACKUP_TIME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivebase.setArcade(-1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(time.get() < end)
            return false;
        else
            return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
