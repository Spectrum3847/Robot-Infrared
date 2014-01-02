package commands.test;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author matthew
 */
public class TestClass extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        SmartDashboard.putNumber("Alive", Timer.getFPGATimestamp());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        SmartDashboard.putNumber("Alive", 0);
    }

    protected void interrupted() {
        end();
    }
}