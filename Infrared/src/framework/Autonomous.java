package framework;

import commands.CommandBase;
import commands.autonomous.AutonDoubleBallHigh;
import commands.autonomous.AutonMobility;
import commands.autonomous.AutonSingleBallHigh;
import commands.autonomous.AutonSingleBallLow;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew
 */
public class Autonomous {

    public static void init() {
        CommandBase.launcher.lightOff();
        CommandBase.drivebase.zeroGyro();
        switch((int)SmartDashboard.getNumber(Dashboard.AUTON_SELECT, 0)) {
            case 0:
                break;
            case 1:
                (new AutonMobility()).start();
                break;
            case 2:
                (new AutonSingleBallLow()).start();
                break;
            case 3:
                (new AutonSingleBallHigh()).start();
                break;
            case 4:
                (new AutonDoubleBallHigh()).start();
                break;
        }
    }

    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
