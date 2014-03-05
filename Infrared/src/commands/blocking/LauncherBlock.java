package commands.blocking;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import framework.HW;

/**
 *
 * @author matthew
 */
public class LauncherBlock extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        if(CommandBase.launcher.isUp()) {
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        CommandBase.launcher.setLauncherSpeed(0);
    }

    protected void interrupted() {
        end();
    }

}
