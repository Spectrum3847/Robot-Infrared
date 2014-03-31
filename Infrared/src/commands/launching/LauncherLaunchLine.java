package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;
import framework.OI;
import framework.Utilities;

/**
 *
 * @author matthew
 */
public class LauncherLaunchLine extends CommandBase {
    private boolean done = false;
    private double prevl = 0;
    private double prevr = 0;
    private double s_timer = 0;
    private double tmp_t = 0;
    private double l_delta = 0;
    private double r_delta = 0;

    protected void initialize() {
        s_timer = SmartDashboard.getNumber(Dashboard.LIGHT_SAMPLE_TIME, 0.1);
        l_delta = SmartDashboard.getNumber(Dashboard.LEFT_LIGHT_DELTA);
        r_delta = SmartDashboard.getNumber(Dashboard.RIGHT_LIGHT_DELTA);
        prevl = drivebase.getLeftLine().getVoltage();
        prevr = drivebase.getRightLine().getVoltage();
        tmp_t = Timer.getFPGATimestamp();
    }

    protected void execute() {
        
        double dl = 0;
        double dr = 0;
        
        if(Timer.getFPGATimestamp() - tmp_t >= s_timer) {
            dl = prevl - drivebase.getLeftLine().getVoltage();
            dr = prevr - drivebase.getRightLine().getVoltage();
            
            prevl = drivebase.getLeftLine().getVoltage();
            prevr = drivebase.getRightLine().getVoltage();
            
            tmp_t = Timer.getFPGATimestamp();
        }
        
        if(Utilities.abs(dl) >= l_delta || Utilities.abs(dr) >= r_delta){
            Init.lauch.start();
            done = true;
        }
    }
 
    protected boolean isFinished() {
        return (!Init.lauch.isFinished() && done)|| !OI.launch_line.get();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }

}
