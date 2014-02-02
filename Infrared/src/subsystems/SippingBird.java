package subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author matthew
 */
public class SippingBird extends Subsystem {

    private Victor leftMotor, rightMotor;

    protected void initDefaultCommand() {
        leftMotor = new Victor(HW.COLLECTOR_LEFT);
        rightMotor = new Victor(HW.COLLECTOR_RIGHT);
    }

    public void collectorFWD() {
        leftMotor.set(.7);
        rightMotor.set(.7);
    }

    public void collectorOFF() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    public void collectorREV() {
        leftMotor.set(-1);
        rightMotor.set(-1);
    }
}
