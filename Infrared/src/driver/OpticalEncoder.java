package driver;

import edu.wpi.first.wpilibj.Counter;

/**
 *
 * @author David
 *
 * This class implements the Optical Encoder, which is defined as a Counter. It
 * counts the number of ticks, or color change in the case of our wheel, which
 * is used to get rate.
 */
public class OpticalEncoder extends Counter {

    public OpticalEncoder(int channel) {
        super(channel);
    }

    public int getTicks() {
        return get();
    }

    public double getRate() {
        return 1.0 / getPeriod() * 60.0;
    }
}
