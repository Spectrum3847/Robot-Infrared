package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;
import java.io.IOException;


/*
 * @author Matthew
 */
public class LauncherDashboardFWDPID extends CommandBase {

    public LauncherDashboardFWDPID() {
        requires(CommandBase.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("SHOOOT!");
        launcher.enableEncoder();
        double kp = SmartDashboard.getNumber(Dashboard.LAUNCHER_KP) / 100000.0;
        double ki = SmartDashboard.getNumber(Dashboard.LAUNCHER_KI) / 100000.0;
        double kd = SmartDashboard.getNumber(Dashboard.LAUNCHER_KD) / 100000.0;
        double kf = SmartDashboard.getNumber(Dashboard.LAUNCHER_KF) / 1000.0;
        launcher.enableVelocityPID();
        launcher.setVelocityPID(kp, ki, kd, kf);
        try {
            Init.theFile.writeChars("\n\n#PID: " + kp + " " + ki + " " + kd + "\n\n");
        } catch (IOException ex) {
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (launcher.getArmAngle() < SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0)) {
            launcher.PIDSetVelocity(SmartDashboard.getNumber(Dashboard.LAUNCHER_PID_VELOCITY));
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return launcher.getArmAngle() > SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0);
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.stopLauncher();
        launcher.disableEncoder();
        launcher.disablePID();
        try {
            Init.theFile.writeChars("" + launcher.getRate() + "#\n\n");
            Init.theFile.flush();
        } catch (IOException ex) {
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
