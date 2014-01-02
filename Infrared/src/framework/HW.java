package framework;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {
    /**DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int FRONT_RDRIVE_MOTOR = 10;
    public static final int REAR_RDRIVE_MOTOR = 9;
    public static final int FRONT_LDRIVE_MOTOR = 1;
    public static final int REAR_LDRIVE_MOTOR = 2;
    
    public static final int KLUDGE_MOTOR = 5;
    
    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int FRONT_SHOOTER_MOTOR = 8;
    public static final int REAR_SHOOTER_MOTOR = 7;
    public static final int MIDDLE_SHOOTER_MOTOR = 3;
    public static final int FLICK_SHOOTER_MOTOR = 6;
    
    public static final int LIFT_MOTOR = 4;
    
    public static final int LIGHT_PORT = 5;
    
    /**PID CONSTANTS**/
    public static final double DRIVEBASE_PULSE = (1.0 / 4096.0);
    
    /**Turn Controller PID - Straight**/
    public static final double SKEW_KP = 0.0169;
    public static final double SKEW_KI = 0.0;
    public static final double SKEW_KD = 0.0;
    
    /**DIGITAL SENSOR ALLOCATIONS**/
    public static final int RDRIVE_ENCODER_A = 1;
    public static final int RDRIVE_ENCODER_B = 2;
    public static final int LDRIVE_ENCODER_A = 3;
    public static final int LDRIVE_ENCODER_B = 4;
    
    /**DIGITAL SENSOR ALLOCATIONS - PNEUMATICS**/
    public static final int PRESSURE_SENSOR = 5;
    
    /**DIGITAL SENSOR ALLOCATIONS - SHOOTER SYSTEM**/
    public static final int SHOOTER_FRONT_ENCODER = 6;
    public static final int SHOOTER_MIDDLE_ENCODER = 7;
    public static final int SHOOTER_REAR_ENCODER = 10;
    
    /**SOLENOID ALLOCATIONS**/
    public static final int COMPRESSOR = 1;
    public static final int HOOKS = 3;
    public static final int BRAKES = 1;
    
    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int usbPort_one = 1;
    public static final int usbPort_two = 2;
    
    /**DIGITAL MODULE SLOTS**/
    public static final int DM_ONE = 1;
    
    /**INNER-CODE MISCELANEOUS**/
    public static final double AS5415_MAX_VOLTAGE = 3.3;
    public static final int GYRO_CHANNEL = 1;
    public static final int AUTON_PICK = 11;
    public static final int AUTON_KILL = 12;
}
