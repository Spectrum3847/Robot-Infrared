package subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
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
    private final AnalogChannel pressure;

    public Compress() {
        compressor = new Compressor(HW.PRESSURE_SENSOR, HW.COMPRESSOR);
        pressure = new AnalogChannel(HW.PRESSURE_TRANSDUCER);
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
    
    public double getPressureRaw() {
        return pressure.getVoltage();
    }
    
    public double getPressure() {
        return (120/3.1)*(pressure.getVoltage()-0.5);
    }

}
