package driver;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author matthew
 */
public class AnalogButton extends AnalogChannel {
    private double threshold;
    private Direction direction;
    
    public static class Direction {
        public boolean value;
        public static Direction RISING = new Direction(true);
        public static Direction FALLING = new Direction(false);

        public Direction(boolean value) {
            this.value = value;
        }
    }

    public AnalogButton(int channel) {
        super(channel);
        getModule().setSampleRate(70000.0);
        threshold = 0;
        direction = Direction.RISING;
    }
    
    public AnalogButton(int channel, double threshold) {
        super(channel);
        this.threshold = threshold;
        direction = Direction.RISING;
    }
    
    public AnalogButton(int channel, double threshold, Direction dir) {
        super(channel);
        this.threshold = threshold;
        direction = dir;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public boolean get() {
        return !(this.getVoltage() > threshold) ^ direction.value;
    }
}
