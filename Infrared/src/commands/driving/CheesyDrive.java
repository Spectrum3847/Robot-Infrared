package commands.driving;

import commands.CommandBase;
import driver.Gamepad;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class CheesyDrive extends CommandBase {

    public CheesyDrive() {
        requires(CommandBase.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatics.engageCheesy();
        drivebase.disableTurnController();
        System.out.println("Cheesydrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double throttle = Utilities.haloDeadBand(OI.gamepad.getLeftY(), OI.gamepad.getRightX(), .1, .13);
        double wheel = Utilities.haloDeadBand(OI.gamepad.getRightX(), OI.gamepad.getLeftY(), .1, .13);
        boolean quickturn = OI.gamepad.getButton(Gamepad.RIGHT_CLICK);

        drivebase.setCheesySensetivity(1.32);

        double quickTurnTriggers = -OI.gamepad.getTriggers();

        if (quickTurnTriggers != 0) {
            drivebase.setCheesyDrive(0, quickTurnTriggers, true);
        } else {
            drivebase.setCheesyDrive(throttle, wheel, quickturn);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setArcade(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
