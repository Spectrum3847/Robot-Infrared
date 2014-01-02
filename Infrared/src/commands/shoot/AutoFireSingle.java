package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;

/**
 *
 * @author matthew
 */
public class AutoFireSingle extends CommandBase {
    double front, middle, rear;
    boolean hasFinished = false, hasGone = false;

    public AutoFireSingle() {
    }
    
    protected void initialize() {
        front = SmartDashboard.getNumber(Dashboard.FRONT_SHOOTER_RPM_KEY);
        middle = SmartDashboard.getNumber(Dashboard.MIDDLE_SHOOTER_RPM_KEY);
        rear = SmartDashboard.getNumber(Dashboard.REAR_SHOOTER_RPM_KEY);
        Init.flick.start();
    }

    protected void execute() {
//        if(!hasGone)//shooter.atSpeeds(front, middle, rear, 100) && !hasGone)
//        {
//            Init.flick.start();
//            hasGone = true;
//            hasFinished = Init.flick.isCanceled();
//        }
        
            SmartDashboard.putNumber("Front RPM", shooter.getFrontEncoder().getRate());
            SmartDashboard.putNumber("Middle RPM", shooter.getMiddleEncoder().getRate());
            SmartDashboard.putNumber("Rear RPM", shooter.getRearEncoder().getRate());
    }

    protected boolean isFinished() {
        return hasGone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
