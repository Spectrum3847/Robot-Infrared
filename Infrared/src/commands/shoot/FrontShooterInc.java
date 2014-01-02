package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * @author matthew
 */
public class FrontShooterInc extends CommandBase {
    double tmp;

    protected void initialize() {
        tmp = SmartDashboard.getNumber(Dashboard.FRONT_SHOOTER_OFFSET) + 50;
        SmartDashboard.putNumber(Dashboard.FRONT_SHOOTER_OFFSET, tmp);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}