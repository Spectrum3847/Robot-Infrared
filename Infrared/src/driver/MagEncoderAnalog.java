package driver;

import edu.wpi.first.wpilibj.AnalogChannel;
import framework.HW;

/**
 *
 * @author matthew
 */
public class MagEncoderAnalog {
    AnalogChannel encoder;
    
    public MagEncoderAnalog(int in)
    {
        encoder = new AnalogChannel(in);
    }
    
    public MagEncoderAnalog(AnalogChannel in)
    {
        encoder = in;
    }
    
    public double getRaw()
    {
        return encoder.getVoltage();
    }
    
    public double getAngle()
    {
        return getRaw()/HW.AS5415_MAX_VOLTAGE*360.0;
    }
}