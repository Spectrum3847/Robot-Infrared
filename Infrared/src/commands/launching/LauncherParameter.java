package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;
import java.io.IOException;


/*
 * @author Matthew
 */
public class LauncherParameter extends CommandBase {

    private double wait;
    private double delay;
    private final String delays;
    private final String angle;
    private final String power;
    private boolean auton = false;
    private boolean PID;

    public LauncherParameter(String angle, String power) {
        requires(launcher);
        this.angle = angle;
        this.power = power;
        this.delays = Dashboard.LAUNCHER_DROP_DELAY;
    }
    
    
    public LauncherParameter(String angle, String power, String delay) {
        requires(launcher);
        this.angle = angle;
        this.power = power;
        this.delays = delay;
    }
    
    public LauncherParameter(String angle, String power, String delay, boolean auto) {
        requires(launcher);
        this.angle = angle;
        this.power = power;
        this.delays = delay;
        this.auton = auto;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Init.runcompressor.cancel();
        CommandBase.pneumatics.stopCompressor();
        PID = SmartDashboard.getBoolean(Dashboard.LAUNCHER_PID_TOGGLE, false);
        delay = SmartDashboard.getNumber(delays);
        wait = Timer.getFPGATimestamp();
        sippingbird.collectorDeploy();
        System.out.println("SHOOOT!");
        launcher.enableEncoder();
        if (PID) {
            double kp = SmartDashboard.getNumber(Dashboard.LAUNCHER_KP) / 100000.0;
            double ki = SmartDashboard.getNumber(Dashboard.LAUNCHER_KI) / 100000.0;
            double kd = SmartDashboard.getNumber(Dashboard.LAUNCHER_KD) / 100000.0;
            double kf = SmartDashboard.getNumber(Dashboard.LAUNCHER_KF) / 1000.0;
            launcher.enableVelocityPID();
            launcher.setVelocityPID(kp, ki, kd, kf);
            try {
                Init.theFile.writeChars("\n\n#PID: " + kp + " " + ki + " " + kd + "\n\n");
            } catch (IOException ex) {
            }
        }
        if(auton) {
            sippingbird.setCollector(0.2);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (launcher.getArmAngle() < SmartDashboard.getNumber(angle) && Timer.getFPGATimestamp() - wait > delay) {
            if (PID) {
                launcher.PIDSetVelocity(SmartDashboard.getNumber(Dashboard.LAUNCHER_PID_VELOCITY));
            } else {
                launcher.setLauncherSpeed(SmartDashboard.getNumber(power));
            }
        }
        SmartDashboard.putNumber("Current Launcher Rate", launcher.getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return launcher.getArmAngle() > SmartDashboard.getNumber(angle);
    }

    // Called once after isFinished returns true
    protected void end() {
        Init.runcompressor.start();
        if (PID) {
            launcher.stopLauncher();
            launcher.disablePID();
            try {
                Init.theFile.writeChars("" + launcher.getRate() + "#\n\n");
                Init.theFile.flush();
            } catch (IOException ex) {
            }
        }
        launcher.disableEncoder();
        launcher.stopLauncher();
        if(!auton)
            sippingbird.collectorRetract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
