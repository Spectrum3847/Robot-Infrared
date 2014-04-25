package driver;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import framework.Utilities;

/**
 *
 * @author matthew
 */
public class AnalogButton extends Trigger {

    private double threshold;
    private Direction direction;
    private AnalogChannel input;

    public static class Direction {

        public int value;
        public static Direction RISING = new Direction(0);
        public static Direction FALLING = new Direction(1);
        public static Direction DELTA = new Direction(2);
        public static Direction DELTA_RISING = new Direction(3);
        public static Direction DELTA_FALLING = new Direction(4);

        public Direction(int value) {
            this.value = value;
        }

        public boolean equals(Direction d) {
            return this.value == d.value;
        }
    }

    public AnalogButton(int channel) {
        this(channel, 0, Direction.RISING);
    }

    public AnalogButton(int channel, double threshold) {
        this(channel, threshold, Direction.RISING);
    }

    public AnalogButton(int channel, double threshold, Direction dir) {
        super();
        input = new AnalogChannel(channel);
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
        return !(input.getVoltage() > threshold) ^ (direction.value < 2 && direction.value < 1);
    }

    public double getVoltage() {
        return input.getVoltage();
    }

    /**
     * Starts the given command whenever the trigger just becomes active.
     *
     * @param command the command to start
     */
    public void whenActive(final Command command) {
        new AnalogButtonScheduler() {
            boolean pressedLast = get();
            double valueLast = getVoltage();

            public void execute() {
                if (direction != Direction.DELTA) {
                    if (get()) {
                        if (!pressedLast) {
                            pressedLast = true;
                            command.start();
                        }
                    } else {
                        pressedLast = false;
                    }
                } else if (direction == Direction.DELTA) {
                    if (Utilities.abs(valueLast - getVoltage()) > threshold) {
                        command.start();
                    }
                } else if (direction == Direction.DELTA_RISING) {
                    double dlt = valueLast - getVoltage();
                    if (Utilities.abs(dlt) > threshold && dlt > 0) {
                        command.start();
                    }
                } else if (direction == Direction.DELTA_FALLING) {
                    double dlt = valueLast - getVoltage();
                    if (Utilities.abs(dlt) > threshold && dlt < 0) {
                        command.start();
                    }
                }
            }
        }.start();
    }

    /**
     * An internal class of {@link Trigger}. The user should ignore this, it is
     * only public to interface between packages.
     */
    public abstract class AnalogButtonScheduler extends ButtonScheduler {

        public abstract void execute();

        protected void start() {
            Scheduler.getInstance().addButton(this);
        }
    }
}
