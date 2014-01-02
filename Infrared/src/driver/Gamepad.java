package driver;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
1 - LeftX
2 - LeftY
3 - Triggers (Each trigger = 0 to 1, axis value = right - left)
4 - RightX
5 - RightY
6 - DPad Left/Right
 * @author David, Matthew, JAG
 */
public class Gamepad {
    
    public static final int LeftX = 1;
    public static final int LeftY = 2;
    public static final int Triggers = 3;
    public static final int RightX = 4;
    public static final int RightY = 5;
    public static final int Dpad = 6;
    
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int BACK_BUTTON = 7;
    public static final int START_BUTTON = 8;
    public static final int LEFT_CLICK = 9;
    public static final int RIGHT_CLICK = 10;
    
    
    private static final int DEFAULT_USB_PORT = 1;
    
    protected Joystick gamepad;
    
    public Gamepad(){
        this(DEFAULT_USB_PORT);
    }
    
    public Gamepad(int port){
        gamepad = new Joystick(port);
    }
    
    public double getLeftY(){
        return -gamepad.getRawAxis(LeftY);
    }
    
    public double getLeftX(){
        return gamepad.getRawAxis(LeftX);
    }
    
    public double getRightX(){
        return gamepad.getRawAxis(RightX);
    }
    
    public double getRightY(){
        return gamepad.getRawAxis(RightY);
    }
    
    public double getTriggers() {
        return gamepad.getRawAxis(Triggers);
    }
    
    public boolean getButton(int button){
        return gamepad.getRawButton(button);
    }
    
    public double getDPadLeftRight(){
        return gamepad.getRawAxis(Dpad);
    }
    
    public Joystick getGamepad(){
        return gamepad;
    }
    
}
