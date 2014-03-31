package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew
 */
public class Dashboard {

    static final boolean ENABLE_SPECTRUM_DASHBOARD = true;
    static final double SHORT_DELAY = .015;
    static double shortOldTime = 0.0;
    static final double LONG_DELAY = 2;
    static double longOldTime = 0.0;
    
    public static String LAUNCHER_DROP_DELAY = "Launching Collector Drop Delay";
    
    public static String LAUNCHER_PULSE_A = "Pnuematic Pulse Time A (millis)";
    public static String LAUNCHER_PULSE_B = "Pnuematic Pulse Time B (millis)";
    public static String LAUNCHER_PULSE_TOSS = "Pnuematic Pulse Time Toss (millis)";
    public static String _______ = "_______ (millis)";

    public static String FLASH_LIGHT_STATUS = "Flash Light On";
    
    public static String COLLECTOR_IN_SPEED = "Collector In Speed";
    public static String COLLECTOR_OUT_SPEED = "Collector Out Speed";
    
    public static String CHEESY_SENSITIVITY = "Cheesy Sensitivity";
    public static String BUTTERY_SENSITIVITY = "Buttery Sensitivity";
    
    public static String AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME = "Autonomous Single Ball Low Goal Drive Time";
    public static String AUTON_SINGLE_LOW_DELAY_FORWARD_TIME = "Autonomous Single Ball Low Goal Delay Time";
    public static String AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED = "Autonomous Single Ball Low Goal Drive SPEED";
    public static String AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED = "Autonomous Single Ball Low Goal Delay SPEED";
    
    public static String AUTON_LAUNCHER_DELAY = "Autonomous Launcher Delay";
    
    public static String AUTON_SELECT = "Auton Mode";
    
    public static String LEFT_LIGHT_DELTA = "Left Light Delta";
    public static String RIGHT_LIGHT_DELTA = "Right Light Delta";
    public static String LIGHT_SAMPLE_TIME = "Line Sample Time";


    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            SmartDashboard.putNumber(Dashboard.LAUNCHER_DROP_DELAY, 0.175);
            
            SmartDashboard.putNumber(Dashboard.LAUNCHER_PULSE_A, 300);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_PULSE_B, 170);
            SmartDashboard.putNumber(Dashboard.LAUNCHER_PULSE_TOSS, 100);
            SmartDashboard.putNumber(Dashboard._______, 300);
            
            SmartDashboard.putBoolean(Dashboard.FLASH_LIGHT_STATUS, true);

            SmartDashboard.putNumber(Dashboard.COLLECTOR_IN_SPEED, 0.6);
            SmartDashboard.putNumber(Dashboard.COLLECTOR_OUT_SPEED, -1.0);

            SmartDashboard.putNumber(Dashboard.COLLECTOR_IN_SPEED, 0.6);
            SmartDashboard.putNumber(Dashboard.COLLECTOR_OUT_SPEED, -1.0);

            SmartDashboard.putNumber(Dashboard.CHEESY_SENSITIVITY, 1.32);
            SmartDashboard.putNumber(Dashboard.BUTTERY_SENSITIVITY, 1.00);
            
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_TIME, 3.75);
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DRIVE_FORWARD_SPEED, 0.5);
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_TIME, 0.5);
            SmartDashboard.putNumber(Dashboard.AUTON_SINGLE_LOW_DELAY_FORWARD_SPEED, 0.2);
            SmartDashboard.putNumber(Dashboard.AUTON_LAUNCHER_DELAY, 0.5);
            
            SmartDashboard.putNumber(Dashboard.AUTON_SELECT, 0);
            
            SmartDashboard.putNumber(Dashboard.LEFT_LIGHT_DELTA, 0.1);
            SmartDashboard.putNumber(Dashboard.RIGHT_LIGHT_DELTA, 0.1);
            SmartDashboard.putNumber(Dashboard.LIGHT_SAMPLE_TIME, 0.030);
        }
    }

    private static void updatePut() {
        SmartDashboard.putNumber("Left Line Sensor Voltage", CommandBase.drivebase.getLeftLine().getVoltage());
        SmartDashboard.putNumber("Right Line Sensor Voltage", CommandBase.drivebase.getRightLine().getVoltage());
        SmartDashboard.putNumber("Pressure Voltage", CommandBase.compress.getPressureRaw());
        SmartDashboard.putNumber("Pressure PSI", CommandBase.compress.getPressure());
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
