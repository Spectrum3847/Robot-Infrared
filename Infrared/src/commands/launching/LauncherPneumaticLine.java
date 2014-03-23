package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.OI;

/**
 *
 * @author matthew
 */
public class LauncherPneumaticLine extends CommandBase {
    private boolean done = false;
    private double prevl = 0;
    private double prevr = 0;
    private double s_timer = SmartDashboard.getNumber(Dashboard.LIGHT_SAMPLE_TIME, 30)/10000;;
    private double tmp_t = 0;

    protected void initialize() {
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
        }
        
        SmartDashboard.putNumber("Right Delta", dr*100);
        SmartDashboard.putNumber("Left Delta", dl*100);
        
        if((((dl > SmartDashboard.getNumber(Dashboard.LEFT_LIGHT_DELTA) || dr > SmartDashboard.getNumber(Dashboard.RIGHT_LIGHT_DELTA)) || drivebase.isOnLine()) && (drivebase.getLeftLine().getVoltage() > 3.1 && drivebase.getRightLine().getVoltage() > 3.1)) && OI.launch_x.get()) {
            (new LauncherPneumatic()).start();
            done = true;
        }
    }
 
    protected boolean isFinished() {
        return done || !OI.launch_x.get();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }

}
