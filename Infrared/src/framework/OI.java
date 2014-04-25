package framework;

import driver.Gamepad;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static final Gamepad gamepad = new Gamepad(HW.usbPort_one);
    public static final Gamepad gamepad_aux = new Gamepad(HW.usbPort_two);

    public static final Button drive_toggle = new JoystickButton(gamepad.getGamepad(), Gamepad.A_BUTTON);
    public static final Button collect = new JoystickButton(gamepad.getGamepad(), Gamepad.LEFT_BUMPER);
    public static final Button eject = new JoystickButton(gamepad.getGamepad(), Gamepad.RIGHT_BUMPER);
    public static final Button kisspass = new JoystickButton(gamepad.getGamepad(), Gamepad.B_BUTTON);
    
    public static final Button launch_catch = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.LEFT_BUMPER);
    public static final Button launch_toss = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.RIGHT_BUMPER);
    public static final Button launch = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.A_BUTTON);
    public static final Button launch_alt = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.B_BUTTON);
    public static final Button launch_line = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.X_BUTTON);
    public static final Button launch_forward = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.Y_BUTTON);

    //Use this constructor to setup up button schedulers for commands
    public OI() {
        drive_toggle.toggleWhenPressed(Init.cheesydrive);
        collect.whileHeld(Init.sippingbirdcollect);
        eject.whileHeld(Init.sippingbirdeject);
        kisspass.whileHeld(Init.sippingbirdkiss);
        
        launch_catch.whileHeld(Init.launchercatch);
        launch_toss.whileHeld(Init.lauchertoss);
        launch.whenPressed(Init.lauch);
        launch_alt.whenPressed(Init.lauch_override);
        launch_line.whileHeld(Init.lauch_line);
        launch_forward.whenPressed(Init.lauch_forward);
    }
}
