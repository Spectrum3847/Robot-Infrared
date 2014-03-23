package framework;

import commands.collection.SippingBirdKiss;
//import commands.launching.LauncherParameter;
import commands.launching.LauncherPneumatic;
import commands.launching.LauncherPneumaticLine;
//import commands.launching.LauncherPotZero;
import driver.Gamepad;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    public static final Button autocollect = new JoystickButton(gamepad.getGamepad(), Gamepad.Y_BUTTON);
    public static final Button kisspass = new JoystickButton(gamepad.getGamepad(), Gamepad.B_BUTTON);
    
    public static final Button launch_manual = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.LEFT_CLICK);
    public static final Button launch_ready = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.LEFT_BUMPER);
    public static final Button launch_block = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.RIGHT_BUMPER);
    public static final Button launch_a = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.A_BUTTON);
    public static final Button launch_b = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.B_BUTTON);
    public static final Button launch_x = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.X_BUTTON);
    public static final Button launch_power = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.Y_BUTTON);

    //Use this constructor to setup up button schedulers for commands
    public OI() {
        drive_toggle.whenPressed(new LauncherPneumatic());
        collect.whileHeld(Init.sippingbirdcollect);
        eject.whileHeld(Init.sippingbirdeject);
        autocollect.whenPressed(Init.sippingbirdcatch);
        kisspass.whileHeld(new SippingBirdKiss());
        
        launch_a.whenPressed(new LauncherPneumatic(Dashboard.LAUNCHER_PULSE_A));
        launch_b.whenPressed(new LauncherPneumatic(Dashboard.LAUNCHER_PULSE_B));
        launch_x.whileHeld(new LauncherPneumaticLine());
        launch_ready.toggleWhenPressed(Init.launcherready);
    }
}
