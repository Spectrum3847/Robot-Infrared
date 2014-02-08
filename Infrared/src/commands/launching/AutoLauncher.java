package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class AutoLauncher extends CommandBase {
    private final double DELAY = 0.1;
    private double wait;
    private boolean launched = false;
    public AutoLauncher() {
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
        if(Timer.getFPGATimestamp()-wait > DELAY)
            if(!launched )
                if(launcher.getArmAngle() <= SmartDashboard.getNumber(Dashboard.SHOOTER_ANGLE, 90.0))
                    launcher.fastLauncher();
                else
                    launched = true;
            else
                if(launcher.getArmAngle() >= 10.0)
                    launcher.setLauncherSpeed(-0.3);
                else
                    launcher.stopLauncher();
        else
            launcher.stopLauncher();
            
    }

    protected boolean isFinished() {
        return launched && launcher.getArmAngle() < 10.0;
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
