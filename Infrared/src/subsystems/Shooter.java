package subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author matthew
 */
public class Shooter extends Subsystem{
    
    private Victor v1, v2, v3, v4;

    protected void initDefaultCommand() {
        v1 = new Victor(HW.SHOOTER_MOTOR_1);
        v2 = new Victor(HW.SHOOTER_MOTOR_2);
        v3 = new Victor(HW.SHOOTER_MOTOR_3);
        v4 = new Victor(HW.SHOOTER_MOTOR_4);
    }

}
