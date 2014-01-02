package commands.driving;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class AutonDrive extends CommandBase {
    double direction = 1;
    
    public AutonDrive() {
        super();
        requires(drivebase);
    }
    
    public AutonDrive(double speed) {
        requires(drivebase);
        direction = speed;
    }
    
    protected void initialize() {
        drivebase.setArcade(direction, 0);
    }

    protected void execute() {
        drivebase.setArcade(direction, 0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        drivebase.setArcade(0, 0);
    }

    protected void interrupted() {
        end();
    }
    
}