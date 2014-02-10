package subsystems;

import driver.IRSensor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.HW;

/**
 *
 * @author matthew
 */
public class SippingBird extends Subsystem {

    public SippingBird() {
        collector = new DoubleSolenoid(HW.SIPPINGBIRD, HW.SIPPINGBIRD + 1);
    }

    private Victor leftMotor, rightMotor;
    private IRSensor ballSensor;
    public double ballDetectDistance = 20.0; //This is the default ball detect distance
    final DoubleSolenoid collector;

    protected void initDefaultCommand() {
        leftMotor = new Victor(HW.COLLECTOR_LEFT);
        rightMotor = new Victor(HW.COLLECTOR_RIGHT);
        ballSensor = new IRSensor(HW.BALL_SENSOR);
    }

    public void collectorIN() {
        leftMotor.set(.7);
        rightMotor.set(.7);
    }

    public void collectorOFF() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    public void collectorOUT() {
        leftMotor.set(-1);
        rightMotor.set(-1);
    }
    
    public boolean isBall(){
        SmartDashboard.putNumber("Distance Ball", ballSensor.getDistance());
        ballDetectDistance = SmartDashboard.getNumber(Dashboard.BALL_DETECT_DISTANCE, ballDetectDistance);
        if (ballSensor.getDistance() <= ballDetectDistance){
            return true;
        } else {
            return false;
        }
    }

    public void collectorDeploy() {
        collector.set(DoubleSolenoid.Value.kForward);
    }

    public void collectorRetract() {
        collector.set(DoubleSolenoid.Value.kReverse);
    }
}
