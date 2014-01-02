package commands.driving;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * @author matthew
 */
public class AutoBack extends CommandGroup {
    public AutoBack()
    {
        super();
        requires(CommandBase.drivebase);
    }
    
    public void initialize() {
        this.addSequential(new AutonDrive(-1), SmartDashboard.getNumber(Dashboard.BACKUP_TIME));
    }

    // Called at end of Command
    public void end() {
        this.cancel();
    }
}
