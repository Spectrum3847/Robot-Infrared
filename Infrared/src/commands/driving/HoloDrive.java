package commands.driving;

import commands.CommandBase;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class HoloDrive extends CommandBase {

    public HoloDrive() {
        requires(CommandBase.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatics.engageAlt();
        drivebase.setHoloInversion(true);
        drivebase.getIMU().start();
        drivebase.enableTurnController();
        //drivebase.disableTurnController();
        System.out.println("Holodrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double y = Utilities.deadBand(OI.gamepad.getLeftY(), .25);
        double x = Utilities.deadBand(OI.gamepad.getLeftX(), .25);

        double rotation = -OI.gamepad.getTriggers();

        if (rotation != 0) {
            drivebase.setSetpoint(drivebase.getGyroAngle() + (rotation / -rotation));
            //drivebase.setHoloPolar(0, 0, rotation);
        } else {
            drivebase.setHoloCartesian(x, y, 0);
        }

        //double gy = drivebase.getGyro().getAngle()==0?0:((Utilities.sign(drivebase.getGyro().getAngle()))*drivebase.getGyro().getAngle()%360.0);
        //System.out.println("IMU = " + drivebase.getIMU().getAngle() + " Gyro = " + gy + " Gyro Raw = " + drivebase.getGyro().getAngle() + " Gyro Rate = " + drivebase.getGyro().getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.disableTurnController();
        drivebase.getIMU().end();
        drivebase.setArcade(0, 0);
        drivebase.setStandardInversion(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
