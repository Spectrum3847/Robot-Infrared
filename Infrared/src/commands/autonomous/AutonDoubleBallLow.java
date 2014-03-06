package commands.autonomous;

import commands.collection.SippingBirdEject;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * Command Group for two ball high goal autonomous mode
 * @author matthew
 */
public class AutonDoubleBallLow extends CommandGroup {

    public AutonDoubleBallLow() {
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.1);
        this.addParallel(new AutonBallDriveHold(), drive_time);
        this.addSequential(new AutonDriveForward(), drive_time);
        this.addSequential(new SippingBirdEject(), 2);
    }
}