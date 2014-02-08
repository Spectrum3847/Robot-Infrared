package commands.collection;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class DelayCollect extends CommandBase {
    private double wait;
    private double delay;

    public DelayCollect() {
    }

    protected void initialize() {
        delay = SmartDashboard.getNumber(Dashboard.COLLECT_DELAY);
        wait = Timer.getFPGATimestamp();
        sippingbird.collectorDeploy();
    }

    protected void execute() {
        sippingbird.collectorIN();
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-wait > delay;
    }

    protected void end() {
        sippingbird.collectorOFF();
    }

    protected void interrupted() {
        end();
    }

}
