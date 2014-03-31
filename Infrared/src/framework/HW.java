package framework;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {
    /**DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int REAR_RDRIVE_MOTOR = 1;
    public static final int FRONT_RDRIVE_MOTOR = 2;
    public static final int FRONT_LDRIVE_MOTOR = 9;
    public static final int REAR_LDRIVE_MOTOR = 10;
    
    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int LAUNCHER_MOTOR_1 = 4;
    public static final int LAUNCHER_MOTOR_2 = 5;
    public static final int LAUNCHER_MOTOR_3 = 6;
    public static final int LAUNCHER_MOTOR_4 = 7;
    
    public static final int COLLECTOR_RIGHT = 3;
    public static final int COLLECTOR_LEFT = 8;
    
    /**DIGITAL SENSOR ALLOCATIONS**/
    /**Pneumatics**/
    public static final int PRESSURE_SENSOR = 5;
    
    
    /**ANALOG SENSOR ALLOCATIONS**/ 
    /**Gyroscope**/
    public static final int GYRO = 1;
    /**Line Sensors**/
    public static final int LINE_SENSOR_LEFT = 4;
    public static final int LINE_SENSOR_RIGHT = 5;
    /**Pressure Sensor**/
    public static final int PRESSURE_TRANSDUCER = 6;
    
    /**RELAY ALLOCATIONS**/
    public static final int COMPRESSOR = 1;
    public static final int LIGHT = 2;
   
    /**SOLENOID ALLOCATIONS**/
    public static final int DRIVESHIFT = 1; // and 2
    public static final int SIPPINGBIRD = 3; // and 4
    public static final int WINGS = 5; // and 6
    public static final int LAUNCHER_LEFT_PISTON = 7;
    public static final int LAUNCHER_RIGHT_PISTON = 8;
    
    
    /**PID CONSTANTS**/
    
    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int usbPort_one = 1;
    public static final int usbPort_two = 2;
}
