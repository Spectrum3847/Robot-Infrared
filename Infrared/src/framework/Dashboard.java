package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew
 */
public class Dashboard {

    static final boolean ENABLE_SPECTRUM_DASHBOARD = true;
    static final double SHORT_DELAY = .2;
    static double shortOldTime = 0.0;
    static final double LONG_DELAY = 2;
    static double longOldTime = 0.0;
    
    public static String SHOOTER_ANGLE = "Shooting Angle";
    public static String SHOOTER_VELOCITY = "Shooting Velocity";
    public static String SHOOTER_KP = "Shoot KP";
    public static String SHOOTER_KI = "Shoot KI";
    public static String SHOOTER_KD = "Shoot KD";
    public static String SHOOTER_OFFSET = "Shooter Offset";

    
    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            //SmartDashboard.putData(CommandBase.drivebase);
            SmartDashboard.putData(CommandBase.shooter);
            SmartDashboard.putNumber(SHOOTER_VELOCITY, 20.0);
            SmartDashboard.putNumber(SHOOTER_ANGLE, 90.0);
            SmartDashboard.putNumber(SHOOTER_OFFSET, 67);
            SmartDashboard.putNumber(SHOOTER_KP, 0.0);
            SmartDashboard.putNumber(SHOOTER_KI, 0.0);
            SmartDashboard.putNumber(SHOOTER_KD, 0.0);
        }
    }

    public static void updateDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
                SmartDashboard.putNumber("Pot", CommandBase.shooter.getPot().getAngle());
                SmartDashboard.putNumber("Pot Voltage", CommandBase.shooter.getPot().getAverageVoltage());                
                SmartDashboard.putNumber("Arm Angle", CommandBase.shooter.getArmAngle());
            }
            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                //SmartDashboard.putData(Scheduler.getInstance());
                longOldTime = Timer.getFPGATimestamp();
            }
        }
    }
}
