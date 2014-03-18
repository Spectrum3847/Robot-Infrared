package commands.blocking;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherBlock extends CommandBase {
    
    public static double power = 0.6;
    public static double angle = 95;
    
    public LauncherBlock() {
        requires(launcher);
    }

    protected void initialize() {
    }

    protected void execute() {
        if(CommandBase.launcher.getArmAngle() < SmartDashboard.getNumber(Dashboard.BLOCK_ANGLE, angle))
            launcher.setCIMSpeed(SmartDashboard.getNumber(Dashboard.BLOCK_POWER, power));
        else
            launcher.setCIMSpeed(0.1);
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
