package commands.autonomous;

import commands.collection.SippingBirdEject;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author matthew
 */
public class AutonSingleBallLow extends CommandGroup{
    public AutonSingleBallLow() {
        this.addSequential(new AutonDriveForward(), 0);
        this.addSequential(new SippingBirdEject());
    }
}
