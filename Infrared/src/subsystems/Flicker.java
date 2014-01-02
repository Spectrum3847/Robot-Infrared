package subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;
import edu.wpi.first.wpilibj.Timer;


/**
 * @author matthew
 */
public class Flicker extends Subsystem {
    private Jaguar flickmotor;
    private Timer timeRunning;
    
    public Flicker() {
        super("Flicker");
        
        flickmotor = new Jaguar(HW.FLICK_SHOOTER_MOTOR);
        timeRunning = new Timer();
    }
    
    public void setFlick(double speed) {
        flickmotor.set(speed);
    }
    
    public double getFlick(){
        return flickmotor.get();
    }

    protected void initDefaultCommand() {
    }

    public void startTimer(){
        timeRunning.start();
    }
    
    public void stopTimer(){
        timeRunning.stop();
        timeRunning.reset();
    }
    
    public double getTime(){
        return timeRunning.get();
    }
}
