package driver;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author David
 */
public class Potentiometer extends AnalogChannel {

    public final double MAX_ANGLE;
    private boolean inverted = true;

    public Potentiometer(int channel, double angle) {
        super(channel);
        MAX_ANGLE = angle;
    }
    public Potentiometer(int channel) {
        this(channel, 360);
    }

    //PLACEHOLDER: TO BE IMPLEMENTED LATER
    public double getAngle() {
        if (inverted){
            return MAX_ANGLE - (getAverageVoltage() / 5.00 * MAX_ANGLE);
        } else {
            return getAverageVoltage() / 5.00 * MAX_ANGLE;
        }
    }

    public void setInvertAngle(boolean invert){
       inverted = invert;
    }
}
