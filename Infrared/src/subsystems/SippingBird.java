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
    
    private final Victor leftMotor, rightMotor;
    public double ballDetectDistance = 0.0; //This is the default ball detect distance
    final DoubleSolenoid collector;

    public SippingBird() {
        collector = new DoubleSolenoid(HW.SIPPINGBIRD, HW.SIPPINGBIRD + 1);
        leftMotor = new Victor(HW.COLLECTOR_LEFT);
        rightMotor = new Victor(HW.COLLECTOR_RIGHT);
    }
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

    public void collectorDeploy() {
        collector.set(DoubleSolenoid.Value.kForward);
    }

    public void collectorRetract() {
        collector.set(DoubleSolenoid.Value.kReverse);
    }
}
