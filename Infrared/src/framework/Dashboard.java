package framework;

import commands.CommandBase;
import commands.blocking.LauncherBlock;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.Launcher;

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
    
    public static String LAUNCHER_DROP_DELAY = "Launching Collector Drop Delay";

    public static String LAUNCHER_OFFSET = "Launcher Offset";
    
    public static String LAUNCHER_POWER_ANG = "Power Angle";
    public static String LAUNCHER_POWER_POW = "Power Power";
    public static String LAUNCHER_TRUSS_ANG = "Truss Angle";
    public static String LAUNCHER_TRUSS_POW = "Truss Power";
    public static String LAUNCHER_MIDRANGE_ANG = "Midrange Angle";
    public static String LAUNCHER_MIDRANGE_POW = "Midrange Power";
    public static String LAUNCHER_LOW_ANG = "Low Angle";
    public static String LAUNCHER_LOW_POW = "Low Power";
    
    public static String LAUNCHER_PID_TOGGLE = "PID Toggle";
    
    public static String LAUNCHER_PULSE_A = "Pnuematic Pulse Time A (millis)";
    public static String LAUNCHER_PULSE_B = "Pnuematic Pulse Time B (millis)";
    
    public static String BLOCK_ANGLE = "Block Angle";
    public static String BLOCK_POWER = "Block Power";

    public static String COLLECTOR_IN_SPEED = "Collector In Speed";
    public static String COLLECTOR_OUT_SPEED = "Collector Out Speed";

    public static String DRIVE_KP = "Drive KP";
    public static String DRIVE_KI = "Drive KI";
    public static String DRIVE_KD = "Drive KD";
    
    public static String CHEESY_SENSITIVITY = "Cheesy Sensitivity";
    public static String BUTTERY_SENSITIVITY = "Buttery Sensitivity";

    public static String COLLECT_DELAY = "Collect Delay";
    public static String BALL_DETECT_DISTANCE = "Ball Detect Distance";
    
    public static String AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME = "Autonomous Single Ball Low Goal Drive Time";
    public static String AUTON_SINGLE_LOW_DELAY_FORWARD_TIME = "Autonomous Single Ball Low Goal Delay Time";
    public static String AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED = "Autonomous Single Ball Low Goal Drive SPEED";
    public static String AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED = "Autonomous Single Ball Low Goal Delay SPEED";
    
    public static String AUTON_LAUNCHER_DELAY = "Autonomous Launcher Delay";
    
    public static String AUTON_SELECT = "Auton Mode";
    
    public static String LEFT_LIGHT_THRESHOLD = "Left Light Sensitivty";
    public static String RIGHT_LIGHT_THRESHOLD = "Right Light Sensitivty";
    public static String LEFT_LIGHT_DELTA = "Left Light Delta";
    public static String RIGHT_LIGHT_DELTA = "Right Light Delta";
    public static String LIGHT_SAMPLE_TIME = "Line Sample Time";


    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            SmartDashboard.putNumber(Dashboard.LAUNCHER_PID_VELOCITY, 20.0); 
            
            SmartDashboard.putNumber(Dashboard.LAUNCHER_OFFSET, Launcher.pot_offset);

            SmartDashboard.putNumber(Dashboard.LAUNCHER_KP, HW.LAUNCHER_KP);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_KI, HW.LAUNCHER_KI);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_KD, HW.LAUNCHER_KD);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_KF, HW.LAUNCHER_KF);

            SmartDashboard.putNumber(Dashboard.LAUNCHER_POS_KP, HW.LAUNCHER_POS_KP);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_POS_KI, HW.LAUNCHER_POS_KI);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_POS_KD, HW.LAUNCHER_POS_KD);
            
            SmartDashboard.putNumber(Dashboard.LAUNCHER_DROP_DELAY, 0.3);
            
            SmartDashboard.putNumber(Dashboard.LAUNCHER_POWER_POW, 0.9);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_POWER_ANG, 105);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_TRUSS_POW, 0.8);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_TRUSS_ANG, 105);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_MIDRANGE_POW, 0.97);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_MIDRANGE_ANG, 55);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_LOW_POW, .68);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_LOW_ANG, 115);
            
            SmartDashboard.putBoolean(Dashboard.LAUNCHER_PID_TOGGLE, false);
            
            SmartDashboard.putNumber(Dashboard.LAUNCHER_PULSE_A, 300);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_PULSE_B, 170);
            
            SmartDashboard.putNumber(Dashboard.BLOCK_ANGLE, LauncherBlock.angle);
            SmartDashboard.putNumber(Dashboard.BLOCK_POWER, LauncherBlock.power);

            SmartDashboard.putNumber(Dashboard.COLLECTOR_IN_SPEED, 0.6);
            SmartDashboard.putNumber(Dashboard.COLLECTOR_OUT_SPEED, -1.0);
            
            SmartDashboard.putNumber(Dashboard.DRIVE_KP, HW.TURN_KP);
            SmartDashboard.putNumber(Dashboard.DRIVE_KI, HW.TURN_KI);
            SmartDashboard.putNumber(Dashboard.DRIVE_KD, HW.TURN_KD);

            SmartDashboard.putNumber(Dashboard.COLLECTOR_IN_SPEED, 0.6);
            SmartDashboard.putNumber(Dashboard.COLLECTOR_OUT_SPEED, -1.0);

            SmartDashboard.putNumber(Dashboard.CHEESY_SENSITIVITY, 1.32);
            SmartDashboard.putNumber(Dashboard.BUTTERY_SENSITIVITY, 1.00);
            
            SmartDashboard.putNumber(Dashboard.BALL_DETECT_DISTANCE, CommandBase.sippingbird.ballDetectDistance); //Set Default distance in SippingBird Subsystem
            
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.75);
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, 0.5);
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_TIME, 0.5);
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED, 0.2);
            SmartDashboard.putNumber(Dashboard.AUTON_LAUNCHER_DELAY, 0.5);
            
            SmartDashboard.putNumber(Dashboard.AUTON_SELECT, 0);
            
            SmartDashboard.putNumber(Dashboard.LEFT_LIGHT_THRESHOLD, 3.35);
            SmartDashboard.putNumber(Dashboard.RIGHT_LIGHT_THRESHOLD, 4.3);
            SmartDashboard.putNumber(Dashboard.LEFT_LIGHT_DELTA, 0.01);
            SmartDashboard.putNumber(Dashboard.RIGHT_LIGHT_DELTA, 0.01);
            SmartDashboard.putNumber(Dashboard.LIGHT_SAMPLE_TIME, 30);
        }
    }

    private static void updatePut() {
        SmartDashboard.putNumber("Current Launcher Angle", CommandBase.launcher.getArmAngle());
        SmartDashboard.putBoolean("Launcher Position At Point", CommandBase.launcher.atPosition());
        SmartDashboard.putNumber("Ball Distance", CommandBase.sippingbird.ballDistance());
        SmartDashboard.putNumber("Drive Yaw Rate", CommandBase.drivebase.getGyro().getRate());
        SmartDashboard.putNumber("Left Line Sensor Voltage", CommandBase.drivebase.getLeftLine().getVoltage());
        SmartDashboard.putNumber("Right Line Sensor Voltage", CommandBase.drivebase.getRightLine().getVoltage());
        SmartDashboard.putNumber("Pressure Voltage", CommandBase.pneumatics.getPressureRaw());
        SmartDashboard.putNumber("Pressure PSI", CommandBase.pneumatics.getPressure());
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
