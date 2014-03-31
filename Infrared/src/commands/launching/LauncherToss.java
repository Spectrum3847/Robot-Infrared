package commands.launching;

import commands.CommandBase;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherToss extends CommandBase {

    protected void initialize() {
        sippingbird.collectorOUT();
        (new LauncherLaunch(Dashboard.LAUNCHER_PULSE_TOSS)).start();
    }

    protected void execute() {
        sippingbird.collectorOUT();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        sippingbird.collectorOFF();
    }

    protected void interrupted() {
        end();
    }

}
