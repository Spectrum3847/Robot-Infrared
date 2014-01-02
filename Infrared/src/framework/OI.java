package framework;

import commands.shoot.FrontShooterDec;
import commands.shoot.FrontShooterInc;
import driver.Gamepad;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    /*
     * Buttons Available:
     * Bumper L
     * Bumper R
     * Button X
     * Button Y
     * Button A
     * Button B
     * Up
     * Down
     * Left
     * Right
     */
    public static final Gamepad gamepad = new Gamepad(HW.usbPort_one);
    public static final Gamepad gamepad_aux = new Gamepad(HW.usbPort_two);
    
    public static final Button flick = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.RIGHT_BUMPER);
    
    public static final Button dashboard_shoot = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.LEFT_BUMPER);
    public static final Button dashboard_collect = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.A_BUTTON);

    public static final Button all_shoot = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.Y_BUTTON);

    public static final Button front_inc = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.B_BUTTON);
    public static final Button front_dec = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.X_BUTTON);
    
    public static final Button hooks = new JoystickButton(gamepad.getGamepad(), Gamepad.A_BUTTON);

    public static final Button brakes = new JoystickButton(gamepad.getGamepad(), Gamepad.LEFT_CLICK);

    
    //Use this constructor to setup up button schedulers for commands
    public OI() {
        flick.whenPressed(Init.flick);
        
        dashboard_shoot.toggleWhenPressed(Init.dashboardShoot);
        dashboard_collect.whileHeld(Init.dashboardCollect);
        all_shoot.toggleWhenPressed(Init.fireall);
        
        front_inc.whenPressed(Init.inc);
        front_dec.whenPressed(Init.dec);
        
        hooks.toggleWhenPressed(Init.deployHooks);
    }
}
