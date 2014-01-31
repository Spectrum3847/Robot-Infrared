package commands.driving;

import com.sun.squawk.util.MathUtils;
import commands.CommandBase;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class HoloDrive extends CommandBase {
    public HoloDrive()
    {
        requires(CommandBase.drivebase);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatics.engageHolo();
        drivebase.disableTurnController();
        drivebase.setHoloInversion(true);
        drivebase.getIMU().init();
        System.out.println("Holodrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double y = Utilities.haloDeadBand(OI.gamepad.getLeftY(), OI.gamepad.getLeftX(), .2, .25);
        double x = Utilities.haloDeadBand(OI.gamepad.getLeftX(), OI.gamepad.getLeftY(), .2, .25);
        
        double rotation = OI.gamepad.getTriggers();
        
        if (rotation != 0){
            drivebase.setHoloPolar(0, 0, rotation);
        } else{
            drivebase.setHoloCartesian(x, y, rotation);
        }
        System.out.println("x = " + x + " y = " + y + " triggers = " + rotation + " angle = " + drivebase.getIMUAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
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