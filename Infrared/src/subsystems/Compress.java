package subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;
import framework.Init;

/**
 *
 * @author Matthew
 */
public class Compress extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private final Compressor compressor;

    public Compress() {
        compressor = new Compressor(HW.PRESSURE_SENSOR, HW.COMPRESSOR);
    }

    protected void initDefaultCommand() {
        Init.runcompressor.start();
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

}
