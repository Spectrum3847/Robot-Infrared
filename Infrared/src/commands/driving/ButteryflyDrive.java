package commands.driving;

import commands.CommandBase;
import driver.Gamepad;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class ButteryflyDrive extends CommandBase {

    public ButteryflyDrive() {
        requires(CommandBase.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.engageAlt();
        drivebase.enableTurnController();
        drivebase.setSetpoint(0.0);
        System.out.println("Butteryflydrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double throttle = Utilities.haloDeadBand(OI.gamepad.getLeftY(), OI.gamepad.getRightX(), .1, .13);
        double wheel = Utilities.haloDeadBand(OI.gamepad.getRightX(), OI.gamepad.getLeftY(), .1, .13);
        boolean quickturn = OI.gamepad.getButton(Gamepad.RIGHT_CLICK);

        drivebase.setCheesySensetivity(1.32);
        
        drivebase.setPID(SmartDashboard.getNumber(Dashboard.DRIVE_KP), SmartDashboard.getNumber(Dashboard.DRIVE_KI), SmartDashboard.getNumber(Dashboard.DRIVE_KD));

        double quickTurnTriggers = -OI.gamepad.getTriggers();

        if (quickTurnTriggers != 0) {
            drivebase.setCheesyDrive(0, quickTurnTriggers, true);
        }
        else {
            drivebase.setCheesyDrive(throttle, wheel != 0 ? wheel : drivebase.getPIDTurnOutput(), quickturn);
            //drivebase.setCheesyDrive(throttle, wheel, quickturn);
        }
        
        SmartDashboard.putNumber("Current Shooter Angle", launcher.getArmAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setArcade(0, 0);
        drivebase.disableTurnController();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
