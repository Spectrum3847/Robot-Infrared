package commands.blocking;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class PoleDrop extends CommandBase {
    String timeout;
    
    public PoleDrop(String timeout) {
        this.timeout = timeout;
    }

    protected void initialize() {
        setTimeout(SmartDashboard.getNumber(timeout));
    }

    protected void execute() {
        pole.setMotor(SmartDashboard.getNumber(Dashboard.AUTON_POLE_DROP_SPEED));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        pole.setMotor(0);
    }

    protected void interrupted() {
        end();
    }

}
