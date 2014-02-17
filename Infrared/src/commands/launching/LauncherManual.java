package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class LauncherManual extends CommandBase {

    public LauncherManual() {
        requires(CommandBase.launcher);
        launcher.enableEncoder();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Joystick Launcher Control");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speed = OI.gamepad_aux.getLeftY();
        speed = Utilities.deadBand(speed, 0.2);
        if (speed < 0) {
            speed = speed * .5;
        }
        launcher.setLauncherSpeed(speed);
        SmartDashboard.putNumber("Current Launcher Rate", launcher.getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.stopLauncher();
        launcher.disableEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
