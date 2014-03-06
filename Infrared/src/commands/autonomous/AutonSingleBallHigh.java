package commands.autonomous;

import commands.CommandBase;
import commands.launching.LauncherDashboardFWD;
import commands.launching.LauncherParameter;
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
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.1);
        this.addSequential(new AutonDriveForward(-0.5), drive_time);
        this.addSequential(new LauncherParameter(Dashboard.LAUNCHER_LOW_ANG, Dashboard.LAUNCHER_LOW_POW));
    }
}
