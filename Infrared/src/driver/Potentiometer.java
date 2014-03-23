package driver;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author David, Matthew
 */
public class Potentiometer extends AnalogChannel implements PIDSource {

    public final double MAX_ANGLE;
    private boolean inverted = false;
    private double OFFSET;
    private double gear_ratio = 1.0;
    
    public Potentiometer(int channel, double angle, double offset) {
        super(channel);
        MAX_ANGLE = angle;
        OFFSET = offset;
    }

    public Potentiometer(int channel, double angle) {
        this(channel, angle, 0);
    }
    public Potentiometer(int channel) {
        this(channel, 360, 0);
    }
    
    public double getRawAngle() {
        return getVoltage() / 5.00 * MAX_ANGLE;
    }
    public double getAngle() {
        double raw_angle = getRawAngle();
        double angle = raw_angle;
        
        if (inverted)
            angle = MAX_ANGLE - raw_angle;
        
        return gear_ratio*angle + OFFSET;
    }

    public void setInvertAngle(boolean invert){
       inverted = invert;
    }
    
    public void setOffset(double offset) {
        OFFSET = offset;
    }
    
    public void setGearRatio(double ratio) {
        gear_ratio = ratio;
    }
    
    public double getGearRatio() {
        return gear_ratio;
    }
    
    public double PIDGet() {
        return getAngle();
    }
}
