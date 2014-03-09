package commands.autonomous;

import commands.collection.SippingBirdEject;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class AutonTripleBallLow extends CommandGroup {

    public AutonTripleBallLow() {
        double drive_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.1);
        double delay_time = SmartDashboard.getNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_TIME, 2.0);
        this.addSequential(new AutonDriveForward(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED), drive_time);
        this.addSequential(new AutonDriveForward(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED), delay_time);
        this.addSequential(new SippingBirdEject(), 2);
    }

}
