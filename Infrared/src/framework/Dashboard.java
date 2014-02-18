package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew
 */
public class Dashboard {

    static final boolean ENABLE_SPECTRUM_DASHBOARD = true;
    static final double SHORT_DELAY = .075;
    static double shortOldTime = 0.0;
    static final double LONG_DELAY = 2;
    static double longOldTime = 0.0;

    public static String LAUNCHER_ANGLE = "Launching Angle";
    public static String LAUNCHER_PID_VELOCITY = "Launching PID Velocity";
    public static String LAUNCHER_SPEED = "Launching Speed";

    public static String LAUNCHER_KP = "Launch KP";
    public static String LAUNCHER_KI = "Launch KI";
    public static String LAUNCHER_KD = "Launch KD";
    public static String LAUNCHER_KF = "Launch KF";

    public static String LAUNCHER_POS_KP = "Launch Position KP";
    public static String LAUNCHER_POS_KI = "Launch Position KI";
    public static String LAUNCHER_POS_KD = "Launch Position KD";

    public static String LAUNCHER_OFFSET = "Launcher Offset";

    public static String COLLECTOR_IN_SPEED = "Collector In Speed";
    public static String COLLECTOR_OUT_SPEED = "Collector Out Speed";

    public static String VISION_H_H = "Vision High Hue";
    public static String VISION_L_H = "Vision Low Hue";
    public static String VISION_H_S = "Vision High Saturation";
    public static String VISION_L_S = "Vision Low Saturation";
    public static String VISION_H_V = "Vision High Value";
    public static String VISION_L_V = "Vision Low Value";

    public static String DRIVE_KP = "Drive KP";
    public static String DRIVE_KI = "Drive KI";
    public static String DRIVE_KD = "Drive KD";
    
    public static String CHEESY_SENSITIVITY = "Cheesy Sensitivity";
    public static String BUTTERY_SENSITIVITY = "Buttery Sensitivity";

    public static String COLLECT_DELAY = "Collect Delay";
    public static String BALL_DETECT_DISTANCE = "Ball Detect Distance";

    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            SmartDashboard.putNumber(LAUNCHER_PID_VELOCITY, 20.0);
            SmartDashboard.putNumber(LAUNCHER_SPEED, 1);
            SmartDashboard.putNumber(LAUNCHER_ANGLE, 90.0);
            SmartDashboard.putNumber(LAUNCHER_OFFSET, -28.5);

            SmartDashboard.putNumber(LAUNCHER_KP, HW.LAUNCHER_KP);
            SmartDashboard.putNumber(LAUNCHER_KI, HW.LAUNCHER_KP);
            SmartDashboard.putNumber(LAUNCHER_KD, HW.LAUNCHER_KP);
            SmartDashboard.putNumber(LAUNCHER_KF, HW.LAUNCHER_KF);

            SmartDashboard.putNumber(LAUNCHER_POS_KP, HW.LAUNCHER_POS_KP);
            SmartDashboard.putNumber(LAUNCHER_POS_KI, HW.LAUNCHER_POS_KI);
            SmartDashboard.putNumber(LAUNCHER_POS_KD, HW.LAUNCHER_POS_KD);

            SmartDashboard.putNumber(COLLECTOR_IN_SPEED, 0.6);
            SmartDashboard.putNumber(COLLECTOR_OUT_SPEED, -1.0);

            /*
             SmartDashboard.putNumber(VISION_H_H, 270);
             SmartDashboard.putNumber(VISION_L_H, 235);
             SmartDashboard.putNumber(VISION_H_S, 255);
             SmartDashboard.putNumber(VISION_L_S, 230);
             SmartDashboard.putNumber(VISION_H_V, 153);
             SmartDashboard.putNumber(VISION_L_V, 51);
             */
            
            SmartDashboard.putNumber(DRIVE_KP, HW.TURN_KP);
            SmartDashboard.putNumber(DRIVE_KI, HW.TURN_KI);
            SmartDashboard.putNumber(DRIVE_KD, HW.TURN_KD);

            SmartDashboard.putNumber(COLLECTOR_IN_SPEED, 0.6);
            SmartDashboard.putNumber(COLLECTOR_OUT_SPEED, -1.0);

            SmartDashboard.putNumber(CHEESY_SENSITIVITY, 1.32);
            SmartDashboard.putNumber(BUTTERY_SENSITIVITY, 1.32);
            
            /*
             SmartDashboard.putNumber(COLLECT_DELAY, 0.0);
             */
            SmartDashboard.putNumber(BALL_DETECT_DISTANCE, CommandBase.sippingbird.ballDetectDistance); //Set Default distance in SippingBird Subsystem
        }
    }

    private static void updatePut() {
        SmartDashboard.putNumber("Current Launcher Angle", CommandBase.launcher.getArmAngle());
        SmartDashboard.putBoolean("Launcher Position At Point", CommandBase.launcher.atPosition());
        SmartDashboard.putNumber("Ball Distance", CommandBase.sippingbird.ballDistance());
        SmartDashboard.putNumber("Drive Yaw Rate", CommandBase.drivebase.getGyro().getRate());
    }

    public static void updateDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
                updatePut();
            }
            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                longOldTime = Timer.getFPGATimestamp();
            }
        }
    }
}
