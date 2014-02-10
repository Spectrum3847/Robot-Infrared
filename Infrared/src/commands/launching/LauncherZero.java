package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.HW;

/**
 *
 * @author matthew
 */
public class LauncherZero extends CommandBase {

    private boolean disabled = false;
    private boolean reached = false;
    
    public LauncherZero() {
        requires(launcher);
    }

    protected void initialize() {
        launcher.enablePositionPID();
        launcher.PIDSetPosition(0);
        launcher.setPositionPID(HW.SHOOTER_POS_KP, HW.SHOOTER_POS_KI, HW.SHOOTER_POS_KD);
    }

    protected void execute() {
        //launcher.setPositionPID(SmartDashboard.getNumber(Dashboard.SHOOTER_POS_KP), SmartDashboard.getNumber(Dashboard.SHOOTER_POS_KI)/1000.0, SmartDashboard.getNumber(Dashboard.SHOOTER_POS_KD));
        //launcher.PIDStall(sippingbird.isBall());
        SmartDashboard.putBoolean("Shooter Position At Point", launcher.atPosition());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        launcher.disablePID();
    }

    protected void interrupted() {
        end();
    }

}
