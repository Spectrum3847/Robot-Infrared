package subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author Matthew
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private final Compressor compressor;

    private final DoubleSolenoid mecanum;
    private final DoubleSolenoid collector;
    private final DoubleSolenoid wings;

    public Pneumatics() {
        compressor = new Compressor(HW.PRESSURE_SENSOR, HW.COMPRESSOR);
        
        mecanum = new DoubleSolenoid(HW.OCTOCANUM, HW.OCTOCANUM + 1);
        collector = new DoubleSolenoid(HW.SIPPINGBIRD, HW.SIPPINGBIRD + 1);
        wings = new DoubleSolenoid(HW.WINGS, HW.WINGS + 1);
    }

    protected void initDefaultCommand() {
    }
    
    public boolean isMaxPSI() {
        return compressor.getPressureSwitchValue();
    }

    public void runCompressor() {
        compressor.start();
    }

    public void stopCompressor() {
        compressor.stop();
    }

    public boolean isCompressor() {
        return compressor.enabled();
    }

    public void engageCheesy() {
        mecanum.set(DoubleSolenoid.Value.kForward);
    }

    public void engageAlt() {
        mecanum.set(DoubleSolenoid.Value.kReverse);
    }

    public void collectorDeploy() {
        collector.set(DoubleSolenoid.Value.kForward);
    }

    public void collectorRetract() {
        collector.set(DoubleSolenoid.Value.kReverse);
    }

    public void wingsOpen() {
        wings.set(DoubleSolenoid.Value.kReverse);
    }

    public void wingsClose() {
        wings.set(DoubleSolenoid.Value.kForward);
    }

    public boolean isCheesy() {
        return mecanum.get().value != DoubleSolenoid.Value.kReverse_val;
    }

}
