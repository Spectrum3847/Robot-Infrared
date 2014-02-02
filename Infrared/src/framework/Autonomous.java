package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Scheduler;

/*
 * @author matthew
 */
public class Autonomous {

    public static void init() {
        CommandBase.drivebase.zeroIMU();
    }

    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
    }
}
