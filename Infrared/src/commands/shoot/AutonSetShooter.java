package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class AutonSetShooter extends CommandBase {
    double front, middle, rear;
    boolean collect = false;
    
    public AutonSetShooter() {
        super();
    }
    
    public AutonSetShooter(double values) {
        super();
        front = values;
        middle = values;
        rear = values;
        collect = true;
    }
    
    protected void initialize() {
        shooter.startEncoders();
        if(!collect)
        {
            front = SmartDashboard.getNumber(Dashboard.FRONT_SHOOTER_RPM_KEY);
            middle = SmartDashboard.getNumber(Dashboard.MIDDLE_SHOOTER_RPM_KEY);
            rear = SmartDashboard.getNumber(Dashboard.REAR_SHOOTER_RPM_KEY);
        }
    }

    protected void execute() {
        if(!collect)
            shooter.setBangBang(front, middle, rear);
        //change to shooter.doDaPid(front, middle, rear);
        else
            shooter.setShooter(front, middle, rear);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        shooter.setShooter(0,0,0);
        shooter.resetEncoders();
        shooter.stopEncoders();
    }

    protected void interrupted() {
        end();
    }
}
