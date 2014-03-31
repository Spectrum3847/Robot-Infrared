package commands.light;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class FlashLightOn extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        if(SmartDashboard.getBoolean(Dashboard.FLASH_LIGHT_STATUS))
            launcher.lightOn();
        else
            launcher.lightOff();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        launcher.lightOff();
    }

    protected void interrupted() {
        end();
    }

}
