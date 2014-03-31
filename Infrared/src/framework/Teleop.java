package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author matthew
 */
public class Teleop {

    public static double max_angle = 0;
    public static double speed_at = 0;
    
    public static void init() {
        Autonomous.cancel();
        Init.flashlighton.start();
        Init.runcompressor.start();
        Init.driveselect.start();
        max_angle = 0;
        speed_at = 0;
    }

    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
