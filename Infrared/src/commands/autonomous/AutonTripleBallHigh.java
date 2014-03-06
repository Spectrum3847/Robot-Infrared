package commands.autonomous;

import commands.collection.SippingBirdCollect;
import commands.launching.LauncherParameter;
import commands.launching.LauncherZero;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class AutonTripleBallHigh extends CommandGroup {
    public AutonTripleBallHigh() {
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.1);
        this.addParallel(new AutonBallDriveHold());
        this.addSequential(new AutonDriveForward(-0.5), drive_time);
        this.addSequential(new LauncherParameter(Dashboard.LAUNCHER_LOW_ANG, Dashboard.LAUNCHER_LOW_POW));
        this.addSequential(new LauncherZero(), 1);
        this.addSequential(new SippingBirdCollect(), 1);
        this.addSequential(new LauncherParameter(Dashboard.LAUNCHER_LOW_ANG, Dashboard.LAUNCHER_LOW_POW));
    }
}
