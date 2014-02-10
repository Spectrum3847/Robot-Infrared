package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;
import java.io.IOException;


/*
 * @author Matthew
 */
public class PIDLauncherDashboardFWD extends CommandBase {

    public PIDLauncherDashboardFWD() {
        requires(CommandBase.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("SHOOOT!");
        launcher.enableVelocityPID();
        launcher.stopLauncher();
        double kp = SmartDashboard.getNumber(Dashboard.SHOOTER_KP);
        double ki = SmartDashboard.getNumber(Dashboard.SHOOTER_KI);
        double kd = SmartDashboard.getNumber(Dashboard.SHOOTER_KD);
        launcher.disablePID();launcher.setVelocityPID(kp, ki, kd);
        try {
            Init.theFile.writeChars("\n\n#PID: " + kp + " " + ki + " " + kd + "\n\n");
        } catch (IOException ex) {
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //if(Init.launchready.isRunning() && shooter.getArmAngle() > SmartDashboard.getNumber(Dashboard.SHOOTER_ANGLE, 90.0))
        //    shooter.stopLauncher();
        //else
        launcher.PIDSetVelocity(SmartDashboard.getNumber(Dashboard.SHOOTER_PID_VELOCITY));
        try {
            Init.theFile.writeChars("" + launcher.getRate() + "\n");
        } catch (IOException ex) {
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //!Init.launchready.isRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.stopLauncher();
        launcher.disablePID();
        try {
            Init.theFile.writeChars("" + launcher.getRate() + "#\n\n");
            Init.theFile.flush();
        } catch (IOException ex) {
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
