package subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;
import framework.Init;

/**
 *
 * @author matthew
 */
public class Pole extends Subsystem {
    
    Victor drop_motor;
    
    public Pole() {
        drop_motor = new Victor(HW.POLE_DROP_MOTOR);
    }

    protected void initDefaultCommand() {
        Init.manualpole.start();
    }
    
    public void setMotor(double s) {
        drop_motor.set(s);
    }

}
