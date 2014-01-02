package subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author matthew
 */
public class Lift extends Subsystem{

    private Jaguar motor;
   
    // Initialize your subsystem here
    public Lift() {
        super("Lift");
        motor = new Jaguar(HW.LIFT_MOTOR);
    }
    
    
    public void initDefaultCommand() {
    }
    
    //sets shooter motors to PWM value (-1.0->1.0)
    public void setSpeed(double speed){
        motor.set(speed); 
        
    }
}