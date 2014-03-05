package commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Command Group for two ball high goal autonomous mode
 * @author matthew
 */
public class AutonDoubleBallLow extends CommandGroup {

    public AutonDoubleBallLow() {
        this.addParallel(new AutonBallDriveHold());
        this.addParallel(new AutonDriveForward());
        this.addSequential(new WaitCommand(5));
    }
}