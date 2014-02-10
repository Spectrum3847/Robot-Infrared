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
    public static String SHOOTER_PID_VELOCITY = "Shooting PID Velocity";
    public static String SHOOTER_SPEED = "Shooting Speed";
    public static String SHOOTER_REV_SPEED = "Shooting Reverse Speed";
    public static String SHOOTER_KP = "Shoot KP";
    public static String SHOOTER_KI = "Shoot KI";
    public static String SHOOTER_KD = "Shoot KD";
    public static String SHOOTER_POS_KP = "Shoot Position KP";
    public static String SHOOTER_POS_KI = "Shoot Position KI";
    public static String SHOOTER_POS_KD = "Shoot Position KD";
    public static String SHOOTER_OFFSET = "Shooter Offset";
    public static String LAUNCHER_STALL = "Launcher Stall Voltage";
    
    public static String VISION_H_H = "Vision High Hue";
    public static String VISION_L_H = "Vision Low Hue";
    public static String VISION_H_S = "Vision High Saturation";
    public static String VISION_L_S = "Vision Low Saturation";
    public static String VISION_H_V = "Vision High Value";
    public static String VISION_L_V = "Vision Low Value";
    
    public static String DRIVE_KP = "Drive KP";
    public static String DRIVE_KI = "Drive KI";
    public static String DRIVE_KD = "Drive KD";
    
    public static String COLLECT_DELAY = "Collect Delay";
    public static String BALL_DETECT_DISTANCE = "BALL DETECT DISTANCE";  
    
    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            //SmartDashboard.putData(CommandBase.drivebase);
            SmartDashboard.putData(CommandBase.launcher);
            SmartDashboard.putNumber(SHOOTER_PID_VELOCITY, 20.0);
            SmartDashboard.putNumber(SHOOTER_SPEED, 1);
            SmartDashboard.putNumber(SHOOTER_REV_SPEED, -0.2);
            SmartDashboard.putNumber(SHOOTER_ANGLE, 90.0);
            SmartDashboard.putNumber(SHOOTER_OFFSET, 20.0);
            SmartDashboard.putNumber(SHOOTER_KP, 0.0);
            SmartDashboard.putNumber(SHOOTER_KI, 0.0);
            SmartDashboard.putNumber(SHOOTER_KD, 0.0);
            SmartDashboard.putNumber(SHOOTER_POS_KP, HW.SHOOTER_POS_KP);
            SmartDashboard.putNumber(SHOOTER_POS_KI, HW.SHOOTER_POS_KI);
            SmartDashboard.putNumber(SHOOTER_POS_KD, HW.SHOOTER_POS_KD);
            
            SmartDashboard.putNumber(VISION_H_H, 270);
            SmartDashboard.putNumber(VISION_L_H, 235);
            SmartDashboard.putNumber(VISION_H_S, 255);
            SmartDashboard.putNumber(VISION_L_S, 230);
            SmartDashboard.putNumber(VISION_H_V, 153);
            SmartDashboard.putNumber(VISION_L_V, 51);
            
            SmartDashboard.putNumber(DRIVE_KP, HW.TURN_KP);
            SmartDashboard.putNumber(DRIVE_KI, HW.TURN_KI);
            SmartDashboard.putNumber(DRIVE_KD, HW.TURN_KD);
            
            SmartDashboard.putNumber(LAUNCHER_STALL, 0.0);
            SmartDashboard.putNumber(COLLECT_DELAY, 0.0);
            
            SmartDashboard.putNumber(BALL_DETECT_DISTANCE, CommandBase.sippingbird.ballDetectDistance); //Set Default distance in SippingBird Subsystem
        }
    }

    public static void updateDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
                SmartDashboard.putNumber("Pot", CommandBase.launcher.getPot().getAngle());
                SmartDashboard.putNumber("Pot Voltage", CommandBase.launcher.getPot().getAverageVoltage());                
                SmartDashboard.putNumber("Arm Angle", CommandBase.launcher.getArmAngle());
            }
            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                //SmartDashboard.putData(Scheduler.getInstance());
                longOldTime = Timer.getFPGATimestamp();
            }
        }
    }
}
