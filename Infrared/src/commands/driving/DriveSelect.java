package commands.driving;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import framework.Init;

/**
 *
 * @author matthew
 */
public class DriveSelect extends CommandBase {

    private Command DEFAULT = Init.butteryflydrive;
    private Command defaultDriveMode = DEFAULT;

    public DriveSelect(Command com) {
        DEFAULT = com;
    }

    public DriveSelect() {
        requires(drivebase);
    }

    public void setDefaultDriveMode(Command command) {
        defaultDriveMode = command;
    }

    public Command getDefaultDriveMode() {
        return defaultDriveMode;
    }

    protected void initialize() {
    }

    /**
     * if we are here we need to leave to a useful drive mode Do this by
     * starting defaultDriveMode if it's not null and it requires drivebase
     */
    protected void execute() {
        if (defaultDriveMode != null && defaultDriveMode.doesRequire(drivebase)) {
            defaultDriveMode.start();
        } else {
            Init.butteryflydrive.start();        //This is the standard drive mode in case defaultDriveMode gets corrupt or unset
            defaultDriveMode = DEFAULT;
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
}
