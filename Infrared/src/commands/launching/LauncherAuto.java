package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherAuto extends CommandBase {

    private final double DELAY = 1.0;
    private double wait;
    private boolean launched = false;

    public LauncherAuto() {
        requires(launcher);
    }

    protected void initialize() {
        System.out.println("SHOOOT!");
        sippingbird.collectorDeploy();
        launcher.wingsOpen();
        sippingbird.collectorOFF();
        launcher.enableEncoder();
        wait = Timer.getFPGATimestamp();
    }

    protected void execute() {
        if (Timer.getFPGATimestamp() - wait > DELAY) {
            if (launcher.getArmAngle() <= SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0)) {
                launcher.fastLauncher();
            }
        } else {
            launcher.stopLauncher();
        }
    }

    protected boolean isFinished() {
        return launcher.getArmAngle() <= SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0);
    }

    protected void end() {
        sippingbird.collectorRetract();
        launcher.wingsClose();
        launcher.disableEncoder();
        sippingbird.collectorOFF();
    }

    protected void interrupted() {
        end();
    }
}
