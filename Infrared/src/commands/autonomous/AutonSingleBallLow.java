package commands.autonomous;

import commands.collection.SippingBirdEject;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author matthew
 */
public class AutonSingleBallLow extends CommandGroup{
    public AutonSingleBallLow() {
        this.addSequential(new AutonDriveForward(), 2);
        this.addSequential(new SippingBirdEject(), 2);
    }
}
