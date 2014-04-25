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
        double launcher_drop = SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY);
        boolean red = SmartDashboard.getBoolean(Dashboard.RED_PILL); 
        double rot_time = SmartDashboard.getNumber(Dashboard.AUTON_AT_GOAL_ROTATION_TIME);
        String launch = red?Dashboard.LAUNCHER_PULSE_A:Dashboard.LAUNCHER_PULSE_B;
        boolean left = SmartDashboard.getBoolean(Dashboard.AUTON_LEFT_GOAL);
        
        
        // While ball collect/hold: drive fwd 0.5s, delay launcher_drop, drive bkwd drive + delay time
        this.addParallel(new AutonBallDriveHold(), drive_time+delay_time+launcher_drop+0.5+rot_time);
        
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, false), 0.5);
        
        this.addSequential(new WaitCommand(launcher_drop));
        
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, true), drive_time);
        
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED, true), delay_time);
        this.addSequential(new AutonRotate(Dashboard.AUTON_AT_GOAL_ROTATION_ROT_L, left), rot_time);
        
        
        // While ball hold: wait 0.6s, launch, wait 1.6s
        
        this.addSequential(new LauncherLaunch(launch, true));
        
        this.addSequential(new WaitCommand(1.0));
        
        
        // While ball hold: drive bkwd 0.6, drive fwd 0.6, wait
        this.addParallel(new AutonBallDriveHold(0.6), 2.4);
        
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, false), 1.2);
        
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, true), 1.2);
        
        this.addSequential(new WaitCommand(launcher_drop));
        
        this.addSequential(new LauncherLaunch(launch));
    }
}