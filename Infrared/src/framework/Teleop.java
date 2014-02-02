package framework;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author matthew
 */
public class Teleop {

    public static void init() {
        Autonomous.cancel();
        Init.runcompressor.start();
        Init.driveselect.start();
    }

    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }
}
