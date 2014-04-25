package commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import framework.Dashboard;
import framework.Teleop;

/**
 *
 * @author matthew
 */
public class AutonBlocking extends CommandGroup {
    public AutonBlocking() {
        Teleop.isPoleUp = true;
        this.addSequential(new AutonCheesyVisionBlockDrive(Dashboard.AUTON_BLOCKING_SPEED, Dashboard.AUTON_BLOCKING_ANGLING), 10);
    }
}
