package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author matthew
 */
public class Flick extends CommandBase {
    Timer time;
    double start = 0.1;
    double end = start + 0.1;
    
    public Flick(){
        super();
        requires(flicker);
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
    }

    protected void execute() {
        if(shooter.motor_f.get() > 0.1)
        {
            flicker.setFlick(time.get()<start?0.8:-0.8);
            if(time.get()>=start)
                lights.disableLights();
        }
    }

    protected boolean isFinished() {
        return time.get()>end?true:false;
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
    }
}
