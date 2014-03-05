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
        leftMotor = new Victor(HW.COLLECTOR_LEFT);
        rightMotor = new Victor(HW.COLLECTOR_RIGHT);
        ballSensor = new IRSensor(HW.BALL_SENSOR);
    }

    private final Victor leftMotor, rightMotor;
    private final IRSensor ballSensor;
    public double ballDetectDistance = 0.0; //This is the default ball detect distance
    final DoubleSolenoid collector;

    protected void initDefaultCommand() {
    }
    
    public void setCollector(double v) {
        leftMotor.set(-v); // Invert for robot
        rightMotor.set(v);
    }

    public void collectorIN() {
        setCollector(SmartDashboard.getNumber(Dashboard.COLLECTOR_IN_SPEED, 0.6));
    }

    public void collectorOFF() {
        setCollector(0.0);
    }

    public void collectorOUT() {
        setCollector(SmartDashboard.getNumber(Dashboard.COLLECTOR_OUT_SPEED, -1.0));
    }
    
    public double ballDistance() {
        return ballSensor.getDistance();
    }
    
    public boolean isBall() {
        ballDetectDistance = SmartDashboard.getNumber(Dashboard.BALL_DETECT_DISTANCE, ballDetectDistance);
        return ballDistance() <= ballDetectDistance;
    }

    public void collectorDeploy() {
        collector.set(DoubleSolenoid.Value.kForward);
    }

    public void collectorRetract() {
        collector.set(DoubleSolenoid.Value.kReverse);
    }
}
