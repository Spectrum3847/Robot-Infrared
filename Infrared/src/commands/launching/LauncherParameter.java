package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;


/*
 * @author Matthew
 */
public class LauncherParameter extends CommandBase {
    private double wait;
    private double delay;
    private final String angle;
    private final String power;

    public LauncherParameter(String angle, String power) {
        requires(launcher);
        this.angle = angle;
        this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Init.runcompressor.cancel();
        delay = SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY);
        wait = Timer.getFPGATimestamp();
        sippingbird.collectorDeploy();
        System.out.println("SHOOOT!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (launcher.getArmAngle() < SmartDashboard.getNumber(angle) && Timer.getFPGATimestamp()-wait > delay ) {
            launcher.setLauncherSpeed(SmartDashboard.getNumber(power));
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return launcher.getArmAngle() > SmartDashboard.getNumber(angle);
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.stopLauncher();
        sippingbird.collectorRetract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
