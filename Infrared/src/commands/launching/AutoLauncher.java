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
        requires(shooter);
    }

    protected void initialize() {
        System.out.println("SHOOOT!");
        pneumatics.collectorDeploy();
        pneumatics.wingsOpen();
        sippingbird.collectorOFF();
        shooter.enableEncoder();
        wait = Timer.getFPGATimestamp();
    }

    protected void execute() {
        if(Timer.getFPGATimestamp()-wait > DELAY)
            if(!launched )
                if(shooter.getAngle() <= SmartDashboard.getNumber(Dashboard.SHOOTER_ANGLE, 90.0))
                    shooter.fastLauncher();
                else
                    launched = true;
            else
                if(shooter.getAngle() >= 10.0)
                    shooter.setLauncherSpeed(-0.3);
                else
                    shooter.stopLauncher();
        else
            shooter.stopLauncher();
            
    }

    protected boolean isFinished() {
        return launched && shooter.getAngle() < 10.0;
    }

    protected void end() {
        pneumatics.collectorRetract();
        pneumatics.wingsClose();
        shooter.disableEncoder();
        sippingbird.collectorOFF();
    }

    protected void interrupted() {
        end();
    }
}
