package driver;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author matthew
 */
public class IMU {

    private final Counter pH;
    private final Counter pL;
    private static float offset = 0;

    public IMU(int channel) {
        DigitalInput source = new DigitalInput(channel);
        pH = new Counter();
        pL = new Counter();

        pH.setUpSource(source);
        pL.setUpSource(source);

        pH.setSemiPeriodMode(true);
        pL.setSemiPeriodMode(false);
    }

    public void start() {
        pH.start();
        pL.start();
    }

    public void end() {
        pH.stop();
        pL.stop();
    }

    public final void zero() {
        offset = getAngle();
    }

    public float getWidth() {
        return (float) (pH.getPeriod() / (pH.getPeriod() + pL.getPeriod()));
    }

    public float getAngle() {
        return 360 * getWidth() + offset;
    }
}
