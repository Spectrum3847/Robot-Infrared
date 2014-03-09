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
public class AutonDoubleBallHigh extends CommandGroup {
    public AutonDoubleBallHigh() {
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.1);
        double delay_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_TIME, 2.0);
        this.addParallel(new AutonBallDriveHold(), drive_time+delay_time-0.1);
        this.addSequential(new WaitCommand(0.75));
        this.addSequential(new AutonDriveForward(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, true), drive_time);
        this.addSequential(new AutonDriveForward(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED, true), delay_time);
        this.addSequential(new LauncherParameter(Dashboard.LAUNCHER_LOW_ANG, Dashboard.LAUNCHER_LOW_POW));
        this.addSequential(new LauncherZero(), 1);
        this.addSequential(new WaitCommand(1));
        this.addSequential(new AutonBallDriveHold(0.6), 1.2);
        this.addSequential(new LauncherParameter(Dashboard.LAUNCHER_LOW_ANG, Dashboard.LAUNCHER_LOW_POW));
    }
}
