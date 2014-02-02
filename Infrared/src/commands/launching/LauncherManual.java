package commands.launching;

import commands.CommandBase;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class LauncherManual extends CommandBase {

    public LauncherManual() {
        requires(CommandBase.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Joystick Shooter Control");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speed = OI.gamepad_aux.getLeftY();
        speed = Utilities.deadBand(speed, 0.2);
        if (speed < 0) {
            speed = speed * .5;
        }
        shooter.setLauncherSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.stopLauncher();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
