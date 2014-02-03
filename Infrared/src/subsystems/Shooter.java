package subsystems;

import commands.launching.LauncherManual;
import driver.Potentiometer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author matthew
 */
public class Shooter extends Subsystem {

    private Victor v1, v2, v3, v4;
    private int invert1 = 1, invert2 = 1, invert3 = 1, invert4 = 1;
    private Potentiometer pot;

    public Shooter() {
        super();
        this.setInvert2(true);
        this.setInvert3(true);
        pot = new Potentiometer(HW.SHOOTER_POT);
    }

    protected void initDefaultCommand() {
        v1 = new Victor(HW.SHOOTER_MOTOR_1);
        v2 = new Victor(HW.SHOOTER_MOTOR_2);
        v3 = new Victor(HW.SHOOTER_MOTOR_3);
        v4 = new Victor(HW.SHOOTER_MOTOR_4);
        setDefaultCommand(new LauncherManual());
    }

    public void setLauncherSpeed(double speed) {
        v1.set(speed * invert1);
        v2.set(speed * invert2);
        v3.set(speed * invert3);
        v4.set(speed * invert4);
    }

    public void setInvert1(boolean isInverted) {
        invert1 = isInverted ? -1 : 1;
    }

    public void setInvert2(boolean isInverted) {
        invert2 = isInverted ? -1 : 1;
    }

    public void setInvert3(boolean isInverted) {
        invert3 = isInverted ? -1 : 1;
    }

    public void setInvert4(boolean isInverted) {
        invert4 = isInverted ? -1 : 1;
    }

    public void stopLauncher() {
        setLauncherSpeed(0);
    }

    public void fastLauncher() {
        setLauncherSpeed(1);
    }

    public void slowLauncher() {
        setLauncherSpeed(-0.2);
    }
    
    public double getAngle() {
        return (15.0/26.0)*(280.0 - pot.getAngle());
    }
}
