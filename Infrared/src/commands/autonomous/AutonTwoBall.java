package commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author matthew
 */
public class AutonTwoBall extends CommandGroup {

    public AutonTwoBall() {
        this.addParallel(new AutonBallDriveHold());
        this.addParallel(new AutonDriveForward());
        this.addSequential(new WaitCommand(5));
    }
}