package driver;

import com.sun.squawk.util.Arrays;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author matthew
 */
public class MultiMotor implements SpeedController {

    private final SpeedController[] controllers;
    private final int length;
    private final double[] inversions;
    private final double[] scales;
    private double value = 0;

    public MultiMotor(int[] ports) {
        length = ports.length;
        Arrays.sort(ports, 0, length);
        controllers = new SpeedController[length];
        inversions = new double[length];
        scales = new double[length];
        for (int i = 0; i < length; i++) {
            controllers[i] = new Victor(ports[i]);
            inversions[i] = 1;
            scales[i] = 1;
        }
    }

    public MultiMotor(SpeedController[] controllers) {
        length = controllers.length;
        inversions = new double[length];
        scales = new double[length];
        this.controllers = new SpeedController[length];
        System.arraycopy(controllers, 0, this.controllers, 0, length);
        for (int i = 0; i < length; i++) {
            inversions[i] = 1;
            scales[i] = 1;
        }
    }

    public double get() {
        return value;
    }

    public double get(int j) {
        int i = 0;
        for (; i < length; i++) {
            if (((Victor) controllers[i]).getChannel() == j) {
                break;
            }
        }
        return value * inversions[i] * scales[i];
    }

    public void set(double d, byte b) {
        value = d;
        for (int i = 0; i < length; i++) {
            controllers[i].set(d * inversions[i] * scales[i], b);
        }
    }

    public void set(double d) {
        set(d, (byte) 0);
    }

    public void set(double d, int j) {
        int i = 0;
        for (; i < length; i++) {
            if (((Victor) controllers[i]).getChannel() == j) {
                break;
            }
        }
        controllers[i].set(d);
    }

    public void setInversion(int port, boolean inverted) {
        int i = 0;
        for (; i < length; i++) {
            if (((Victor) controllers[i]).getChannel() == port) {
                break;
            }
        }
        inversions[i] = inverted ? -1 : 1;
    }

    public void setScale(int port, double scale) {
        int i = 0;
        for (; i < length; i++) {
            if (((Victor) controllers[i]).getChannel() == port) {
                break;
            }
        }
        scales[i] = scale;
    }

    public boolean getInversion(int port, boolean inverted) {
        int i = 0;
        for (; i < length; i++) {
            if (((Victor) controllers[i]).getChannel() == port) {
                break;
            }
        }
        return inversions[i] < 0;
    }

    public double getScale(int port, double scale) {
        int i = 0;
        for (; i < length; i++) {
            if (((Victor) controllers[i]).getChannel() == port) {
                break;
            }
        }
        return scales[i];
    }

    public void disable() {
        set(0);
    }

    public void pidWrite(double d) {
    }

}
