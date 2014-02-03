package driver;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author David
 */
public class Potentiometer extends AnalogChannel {

    public final double MAX_ANGLE;

    public Potentiometer(int channel, double angle) {
        super(channel);
        MAX_ANGLE = angle;
    }
    public Potentiometer(int channel) {
        this(channel, 360);
    }

    //PLACEHOLDER: TO BE IMPLEMENTED LATER
    public double getAngle() {
        return getAverageVoltage() / 5.00 * MAX_ANGLE;
    }

}
