package commands.autonomous;

//import commands.launching.LauncherParameter;
//import commands.launching.LauncherPotZero;
//import commands.launching.LauncherZero;
import commands.launching.LauncherLaunch;
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
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME);
        double delay_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_TIME);
        this.addParallel(new AutonBallDriveHold(), drive_time+delay_time+0.5);
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, false), 0.5);
        this.addSequential(new WaitCommand(SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY)));
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, true), drive_time);
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED, true), delay_time);
        this.addParallel(new AutonBallDriveHold(0.2), 1.6);
        this.addSequential(new WaitCommand(0.8));
        this.addSequential(new LauncherLaunch(Dashboard.LAUNCHER_PULSE_A));
        this.addSequential(new WaitCommand(0.8));
        this.addParallel(new AutonBallDriveHold(0.6), 3);
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, false), 0.6);
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, true), 0.6);
        this.addSequential(new WaitCommand(SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY)));
        this.addSequential(new LauncherLaunch(Dashboard.LAUNCHER_PULSE_A));
    }
}