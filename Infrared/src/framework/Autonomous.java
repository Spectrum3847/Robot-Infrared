package framework;

import commands.CommandBase;
import commands.autonomous.AutonSingleBallLow;
import edu.wpi.first.wpilibj.command.Scheduler;

/*
 * @author matthew
 */
public class Autonomous {

    public static void init() {
        CommandBase.drivebase.zeroIMU();
        CommandBase.drivebase.zeroGyro();
        Init.launcherzero.start();
        (new AutonSingleBallLow()).start();
    }

    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
