package subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author matthew
 */
public class SippingBird extends Subsystem{
    
    private Victor leftMotor, rightMotor;
    private DoubleSolenoid deploy;
    

    protected void initDefaultCommand() {
        leftMotor = new Victor(HW.COLLECTOR_LEFT);
        rightMotor = new Victor(HW.COLLECTOR_RIGHT);
        deploy = new DoubleSolenoid(HW.SIPPINGBIRD_DOWN, HW.SIPPINGBIRD_UP);
    }
    
    public void collectorFWD(){
        leftMotor.set(.7);
        rightMotor.set(.7);
    }
    
    public void collectorOFF(){       
        leftMotor.set(0);
        rightMotor.set(0);
    }
    
    public void collectorREV(){
        leftMotor.set(-1);
        rightMotor.set(-1);
    }

    public void collectorDeploy(){
        deploy.set(DoubleSolenoid.Value.kForward);
    }
    
    public void collectorRetract(){
        deploy.set(DoubleSolenoid.Value.kReverse);
    }
}
