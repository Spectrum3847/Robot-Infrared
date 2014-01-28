package framework;

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
    
    public static String FRONT_SHOOTER_RPM_KEY = "Front Shooter RPM Input";
    public static String MIDDLE_SHOOTER_RPM_KEY = "Mid Shooter RPM Input";
    public static String REAR_SHOOTER_RPM_KEY = "Rear Shooter RPM Input";
    
    public static String FRONT_COLLECT_RPM_KEY = "Front Collect RPM Input";
    public static String MIDDLE_COLLECT_RPM_KEY = "Mid Collect RPM Input";
    public static String REAR_COLLECT_RPM_KEY = "Rear Collect RPM Input";
    
    public static String FRONT_SHOOTER_OFFSET = "Front RPM Offset";
    
    public static String FRONT_MOTOR_ANALOG_SCALE_KEY = "Front Scale";
    public static String MIDDLE_MOTOR_ANALOG_SCALE_KEY = "Mid Scale";
    public static String REAR_MOTOR_ANALOG_SCALE_KEY = "Rear Scale";
    
    public static String CHEESY_SENSITIVITY_KEY = "Cheesy Sensitivity";
    
    public static String AUTON_SWITCH_KEY = "Auton On";
    public static String AUTON_TYPE_KEY = "Auton Type 2";
    public static String AUTON_POOL_NOODLE = "PoolNoodle 5";
    
    public static String FRONT_MOTOR_ANALOG_COLLECT_SCALE_KEY = "Front Collect Scale";
    public static String MIDDLE_MOTOR_ANALOG_COLLECT_SCALE_KEY = "Mid Collect Scale";
    public static String REAR_MOTOR_ANALOG_COLLECT_SCALE_KEY = "Rear Collect Scale";
    
    public static String FRONT_MOTOR_TIMEDROP = "Front Timedrop";
    public static String MIDDLE_MOTOR_TIMEDROP = "Mid Timedrop";
    public static String REAR_MOTOR_TIMEDROP = "Rear Timedrop";
    
    public static String FRONT_MOTOR_BANGLOW = "Front Bang-Low";
    public static String MIDDLE_MOTOR_BANGLOW = "Mid Bang-Low";
    public static String REAR_MOTOR_BANGLOW = "Rear Bang-Low";
    
    public static String AUTO_FLICK_TIME = "Auto Flick Delay";
    
    public static String BACKUP_TIME = "Backup Time";
    public static String POOL_NOODLE_FORWARD_TIME = "Noodle Forward Time";
    public static String POOL_NOODLE_BACK_TIME = "Noodle Back Time";

    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            SmartDashboard.putNumber(BACKUP_TIME, .5);
            SmartDashboard.putNumber(POOL_NOODLE_BACK_TIME, .75);
            SmartDashboard.putNumber(POOL_NOODLE_FORWARD_TIME, .75);
            
            //SmartDashboard.putData("MANUAL SHOOT", Init.dashboardShootCollect);
            //SmartDashboard.putData("PIDShoot", Init.PIDShootCommand);
            SmartDashboard.putNumber(FRONT_SHOOTER_RPM_KEY, 9000);
            SmartDashboard.putNumber(MIDDLE_SHOOTER_RPM_KEY, 5500);
            SmartDashboard.putNumber(REAR_SHOOTER_RPM_KEY, 3900);
            
            SmartDashboard.putNumber(FRONT_COLLECT_RPM_KEY, 2000);
            SmartDashboard.putNumber(MIDDLE_COLLECT_RPM_KEY, 6000);
            SmartDashboard.putNumber(REAR_COLLECT_RPM_KEY, 4600);
            
            SmartDashboard.putNumber(FRONT_SHOOTER_OFFSET, 0);
            
            SmartDashboard.putNumber(Dashboard.FRONT_MOTOR_ANALOG_SCALE_KEY, 1);
            SmartDashboard.putNumber(Dashboard.MIDDLE_MOTOR_ANALOG_SCALE_KEY, 1);
            SmartDashboard.putNumber(Dashboard.REAR_MOTOR_ANALOG_SCALE_KEY, 1);
            
            SmartDashboard.putNumber(Dashboard.FRONT_MOTOR_ANALOG_COLLECT_SCALE_KEY, .4);
            SmartDashboard.putNumber(Dashboard.MIDDLE_MOTOR_ANALOG_COLLECT_SCALE_KEY, 0.7);
            SmartDashboard.putNumber(Dashboard.REAR_MOTOR_ANALOG_COLLECT_SCALE_KEY, 0.85);
            
            SmartDashboard.putNumber(Dashboard.CHEESY_SENSITIVITY_KEY, 1.32);
            
            SmartDashboard.putBoolean(AUTON_SWITCH_KEY, true);
            SmartDashboard.putBoolean(AUTON_TYPE_KEY, false);
            SmartDashboard.putBoolean(AUTON_POOL_NOODLE,false);
            
            SmartDashboard.putNumber(REAR_MOTOR_TIMEDROP, 0);
            SmartDashboard.putNumber(MIDDLE_MOTOR_TIMEDROP, .012);
            SmartDashboard.putNumber(FRONT_MOTOR_TIMEDROP, .017);
            
            SmartDashboard.putNumber(REAR_MOTOR_BANGLOW, .8);
            SmartDashboard.putNumber(MIDDLE_MOTOR_BANGLOW, .4);
            SmartDashboard.putNumber(FRONT_MOTOR_BANGLOW, .6);
            
            SmartDashboard.putNumber(AUTO_FLICK_TIME, .2);
        }
    }

    public static void updateDashboard() {

        if (ENABLE_SPECTRUM_DASHBOARD) {

            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
            }

            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                //SmartDashboard.putData(Scheduler.getInstance());
                longOldTime = Timer.getFPGATimestamp();
            }
        }
    }
}
