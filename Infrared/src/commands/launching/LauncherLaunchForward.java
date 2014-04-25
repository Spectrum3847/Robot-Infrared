package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherLaunchForward extends CommandBase {
    private double wait;
    private double delay;
    private double time;
    private final String pulse;
    
    public LauncherLaunchForward() {
        super();
        this.pulse = Dashboard._______;
    }

    public LauncherLaunchForward(String pulse) {
        super();
        this.pulse = pulse;
    }
    
    protected void initialize() {
        compress.stopCompressor();
        time = Timer.getFPGATimestamp()*1000;
        delay = SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY);
        wait = Timer.getFPGATimestamp();
    }

    protected void execute() {
        if (Timer.getFPGATimestamp() - wait > delay && compress.getPressureRaw() >= 2.1) {
            compress.stopCompressor();
            sippingbird.collectorOUT();
            sippingbird.collectorDeploy();
            launcher.launcherUp();
        }
    }

    protected boolean isFinished() {
            return Timer.getFPGATimestamp()*1000 - time > SmartDashboard.getNumber(pulse, 50) + delay*1000 || compress.getPressureRaw() < 2.1;
    }

    protected void end() {
        launcher.launcherDown();
        sippingbird.collectorRetract();
        compress.runCompressor();
        sippingbird.collectorOFF();
    }

    protected void interrupted() {
        end();
    }

}
