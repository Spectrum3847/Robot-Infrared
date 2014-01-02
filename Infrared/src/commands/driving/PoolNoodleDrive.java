/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.driving;
import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
/**
 *
 * @author Zachary
 */
public class PoolNoodleDrive extends CommandBase {
    
    Timer time;
    double speed;
    double end;
    boolean back;
    
    public PoolNoodleDrive() {
        super();
        requires(drivebase);
        speed = .8;
        back = false;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public PoolNoodleDrive(int input){
        super();
        requires(drivebase);
        speed = .8;
        if(input == 1)
            back = false;
        else
            back = true;
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
        if(back == false)
            end = SmartDashboard.getNumber(Dashboard.POOL_NOODLE_FORWARD_TIME);
        else
            end = SmartDashboard.getNumber(Dashboard.POOL_NOODLE_BACK_TIME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(back == false)
            drivebase.setArcade(1, 0);
        else
            drivebase.setArcade(-1,0);
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
