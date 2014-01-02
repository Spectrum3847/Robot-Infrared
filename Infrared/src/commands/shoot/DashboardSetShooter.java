package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * @author matthew
 */
class DashboardSetShooter extends Command {
    double front, middle, rear;
    
    protected void initialize() {
        CommandBase.shooter.startEncoders();
        front = SmartDashboard.getNumber(Dashboard.FRONT_SHOOTER_RPM_KEY);
        middle = SmartDashboard.getNumber(Dashboard.MIDDLE_SHOOTER_RPM_KEY);
        rear = SmartDashboard.getNumber(Dashboard.REAR_SHOOTER_RPM_KEY);
    }

    protected void execute() {
        CommandBase.shooter.setBangBang(front, middle, rear);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        CommandBase.shooter.setShooter(0,0,0);
        CommandBase.shooter.resetEncoders();
        CommandBase.shooter.stopEncoders();
    }

    protected void interrupted() {
        end();
    }
}
