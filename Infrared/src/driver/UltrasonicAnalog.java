package driver;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author matthew
 */
public class UltrasonicAnalog extends AnalogChannel {
    double volts_to_inches = 5.0/512.0;
    
    public UltrasonicAnalog(int channel) {
        super(channel);
    }
    
    public double getDistance() {
        return this.getVoltage()/volts_to_inches;
    }

}
