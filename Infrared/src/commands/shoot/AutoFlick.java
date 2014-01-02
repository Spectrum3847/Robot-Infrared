package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class AutoFlick extends CommandBase {
    Timer time;
    double start = 0.1;
    double end = start + 0.1;
    double delay;
    
    public AutoFlick(){
        super();
        requires(flicker);
        delay = .1;
    }
    
    public Timer getTimer() {
        return time;
    }
    
    public double getTime(){
        return time.get();
    }

    protected void initialize() {
        time  = new Timer();
        time.start();
        lights.enableLights();
        flicker.startTimer();
        delay = SmartDashboard.getNumber(Dashboard.AUTO_FLICK_TIME);
    }

    protected void execute() {
        if(shooter.motor_f.get() > 0.1)
        {
            
            if(time.get()<start)
            {
                flicker.setFlick(.8);
                logging();
            }
            else if(time.get()<=end)
                flicker.setFlick(-.8);
            else
                flicker.setFlick(0);
            if(time.get()>=start)
                lights.disableLights();
        }
    }

    protected boolean isFinished() {
        return time.get()>(end + delay)?true:false;
    }

    protected void end() {
        time.stop();
        lights.disableLights();
        flicker.setFlick(0);
        flicker.stopTimer();
    }
    
    public synchronized boolean isInterruptable() {
        return false;
    }
    
    protected void interrupted() {
        time.stop();
        flicker.setFlick(0);
        lights.toggleLights();
        flicker.stopTimer();
    }
    
    private void logging() {
        System.out.println("FRONT RPM: " + shooter.FrontMotorRate());
        System.out.println("MIDDLE RPM: " + shooter.MiddleMotorRate());
        System.out.println("REAR RPM: " + shooter.RearMotorRate());
    }
}
