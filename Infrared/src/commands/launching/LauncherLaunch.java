package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherLaunch extends CommandBase {

    private double wait;
    private double drop_delay;
    private double start_time;
    private double pulse_width;
    private String pulse;
    private boolean auton = false;
    private boolean to_cancel = false;
    private boolean override = false;

    public LauncherLaunch() {
        super();
        requires(launcher);
        this.pulse = Dashboard.LAUNCHER_PULSE_A;
    }

    public LauncherLaunch(String pulse) {
        this();
        this.pulse = pulse;
    }

    public LauncherLaunch(String pulse, boolean a) {
        this();
        this.pulse = pulse;
        this.auton = a;
    }

    public LauncherLaunch(String pulse, boolean a, boolean o) {
        this();
        this.pulse = pulse;
        this.auton = a;
        this.override = o;
    }

    protected void initialize() {
        compress.stopCompressor();
        start_time = Timer.getFPGATimestamp();
        drop_delay = SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY, 0.175);
        pulse_width = SmartDashboard.getNumber(pulse)/1000.0;
        sippingbird.collectorDeploy();
        if (compress.getPressureRaw() < 2.1 && !auton && !override) {
            this.cancel();
        }
    }

    protected void execute() {
        if (Timer.getFPGATimestamp() - start_time > drop_delay 
         && (compress.getPressureRaw() >= 2.1 || override)) {
                compress.stopCompressor();
                launcher.launcherUp();
        }
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - start_time > pulse_width + drop_delay
            || (compress.getPressureRaw() < 2.1 && !(override));
    }

    protected void end() {
        launcher.launcherDown();
        if (!auton) {
            sippingbird.collectorRetract();
        }
        compress.runCompressor();
    }

    protected void interrupted() {
        end();
    }

}
