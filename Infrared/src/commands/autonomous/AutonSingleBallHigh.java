package commands.autonomous;

import commands.CommandBase;
import commands.launching.LauncherDashboardFWD;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;

/**
 * Command Group for single high goal autonomous
 *
 * @author matthew
 */
public class AutonSingleBallHigh extends CommandGroup {

    public AutonSingleBallHigh() {
        this.addSequential(new AutonDriveForward(), 0);
        this.addParallel(Init.launcherready, 3);
        this.addSequential(new LauncherDashboardFWD());
    }

    protected boolean isFinished() {
        return CommandBase.launcher.getArmAngle() > SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0);
    }
}
