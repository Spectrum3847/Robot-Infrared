package commands.autonomous;

//import commands.launching.LauncherParameter;
import commands.launching.CheesyWait;
import commands.launching.LauncherLaunch;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * Command Group for single high goal autonomous
 *
 * @author matthew
 */
public class AutonSingleBallHigh extends CommandGroup {

    public AutonSingleBallHigh() {
        boolean red = SmartDashboard.getBoolean(Dashboard.RED_PILL); 
        String launch = red?Dashboard.LAUNCHER_PULSE_A:Dashboard.LAUNCHER_PULSE_B;
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME);
        double delay_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_TIME );
        boolean left = SmartDashboard.getBoolean(Dashboard.AUTON_LEFT_GOAL);
        double rot_time = SmartDashboard.getNumber(Dashboard.AUTON_AT_GOAL_ROTATION_TIME);
        double launcher_delay = SmartDashboard.getNumber(Dashboard.AUTON_LAUNCHER_DELAY);
        
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, true), drive_time);
        this.addSequential(new AutonDrive(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED, true), delay_time);
        this.addSequential(new AutonRotate(Dashboard.AUTON_AT_GOAL_ROTATION_ROT_L, left), rot_time);
        this.addSequential(new WaitCommand(launcher_delay));
        this.addSequential(new CheesyWait(), 3);
        this.addSequential(new LauncherLaunch(launch, true));
    }
}
