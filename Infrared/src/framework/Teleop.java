package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author matthew
 */
public class Teleop {

    public static double max_angle = 0;
    public static double speed_at = 0;
    
    public static void init() {
        Autonomous.cancel();
        Init.runcompressor.start();
        Init.driveselect.start();
        max_angle = 0;
        speed_at = 0;
    }

    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
        if(Init.launcherzero.isRunning() || Init.launcherdashboardfwdpid.isRunning() || Init.launcherdashboardfwd.isRunning()) {
            double angle = CommandBase.launcher.getArmAngle();
            double speed = CommandBase.launcher.getRate();
            if(max_angle < angle) {
                max_angle = angle;
                speed_at = speed;
            }
        }
        else if(max_angle != 0) {
            SmartDashboard.putNumber("Max Angle", max_angle);
            SmartDashboard.putNumber("Max Angle Speed", speed_at);
        }
        else {
            max_angle = 0;
            speed_at = 0;
        }
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
