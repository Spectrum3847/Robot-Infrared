package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.HW;

/**
 *
 * @author matthew
 */
public class LauncherZero extends CommandBase {

    public LauncherZero() {
        requires(launcher);
    }

    protected void initialize() {
        launcher.enablePositionPID();
        launcher.PIDSetPosition(0);
        launcher.setPositionPID(HW.LAUNCHER_POS_KP, HW.LAUNCHER_POS_KI, HW.LAUNCHER_POS_KD);
        if(launcher.isDown())
            launcher.zeroPot();
    }

    protected void execute() {
        launcher.setPositionPID(SmartDashboard.getNumber(Dashboard.LAUNCHER_POS_KP), SmartDashboard.getNumber(Dashboard.LAUNCHER_POS_KI)/1000.0, SmartDashboard.getNumber(Dashboard.LAUNCHER_POS_KD));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        launcher.disablePID();
    }

    protected void interrupted() {
        end();
    }

}
