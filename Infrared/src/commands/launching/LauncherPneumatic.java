package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 *
 * @author matthew
 */
public class LauncherPneumatic extends CommandBase {
    private double wait;
    private double delay;
    private double time;
    private String pulse;
    
    public LauncherPneumatic() {
        super();
        this.pulse = Dashboard.LAUNCHER_PULSE_A;
    }

    public LauncherPneumatic(String pulse) {
        super();
        this.pulse = pulse;
    }
    
    protected void initialize() {
        pneumatics.stopCompressor();
        time = Timer.getFPGATimestamp()*1000;
        delay = SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY);
        wait = Timer.getFPGATimestamp();
        sippingbird.collectorDeploy();
    }

    protected void execute() {
        if (Timer.getFPGATimestamp() - wait > delay && pneumatics.getPressureRaw() > 2.1) {
            pneumatics.stopCompressor();
            drivebase.engageCheesy();
        }
    }

    protected boolean isFinished() {
            return Timer.getFPGATimestamp()*1000 - time > SmartDashboard.getNumber(pulse, 50) + delay*1000 || pneumatics.getPressureRaw() < 2.1;
    }

    protected void end() {
        drivebase.engageAlt();
        sippingbird.collectorRetract();
        pneumatics.runCompressor();
    }

    protected void interrupted() {
        end();
    }

}
