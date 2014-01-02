/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
/**
 *
 * @author Zachary
 */
public class DashboardCollect extends CommandBase {
    
    double front, middle, rear;
    
    public DashboardCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putBoolean("dashboardCollectCMD", true);
        shooter.startEncoders();
        lights.disableLights();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        front = SmartDashboard.getNumber(Dashboard.FRONT_COLLECT_RPM_KEY );
        middle = SmartDashboard.getNumber(Dashboard.MIDDLE_COLLECT_RPM_KEY);
        rear = SmartDashboard.getNumber(Dashboard.REAR_COLLECT_RPM_KEY);
        //shooter.setBangBang(front, middle, rear);
        
        //if (CommandBase.flicker.getFlick() == 0){
           shooter.setCollectBangBang(front, middle, rear); 
        //} else{
        //    shooter.setShooter(1, 1, 1);
        //}
        
        SmartDashboard.putNumber("Front RPM", shooter.getFrontEncoder().getRate());
        SmartDashboard.putNumber("Middle RPM", shooter.getMiddleEncoder().getRate());
        SmartDashboard.putNumber("Rear RPM", shooter.getRearEncoder().getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.setShooter(0,0,0);
        shooter.resetEncoders();
        shooter.stopEncoders();
        
        lights.enableLights();

        SmartDashboard.putBoolean("dashboardCollectCMD", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
