package commands.vision;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetDistanceToGoal extends CommandBase {

    double wait = 0;
    double DELAY = 3;
    
    public GetDistanceToGoal() {
        requires(vision);
    }

    protected void initialize() {
        wait = Timer.getFPGATimestamp();
    }

    protected void execute() {
        if(Timer.getFPGATimestamp() - wait >= DELAY) {
            wait = Timer.getFPGATimestamp();
            SmartDashboard.putNumber("Distance", vision.getDistanceToGoalCrio());
            SmartDashboard.putBoolean("Is Hot", vision.isHotGoal());
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
}
