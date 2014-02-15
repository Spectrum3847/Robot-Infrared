package commands.autonomous;

import commands.CommandBase;
import commands.launching.LauncherDashboardFWD;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;

/**
 *
 * @author matthew
 */
public class AutonSingleBallHigh extends CommandGroup{
    public AutonSingleBallHigh() {
        this.addSequential(new DriveForward(), 0);
        this.addParallel(Init.sippingbirdlaunchready, 3);
        this.addSequential(new LauncherDashboardFWD());
    }
    
    
    protected boolean isFinished() {
        return CommandBase.launcher.getArmAngle() > SmartDashboard.getNumber(Dashboard.SHOOTER_ANGLE, 90.0);
    }
}
