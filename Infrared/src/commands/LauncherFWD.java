package commands;


/*
 * @author Matthew
 */
public class LauncherFWD extends CommandBase {
    public LauncherFWD()
    {
        requires(CommandBase.shooter);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("SHOOOT!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.fastLauncher();
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