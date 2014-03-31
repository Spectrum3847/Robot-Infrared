package subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author matthew
 */
public final class Launcher extends Subsystem {
    final DoubleSolenoid wings;
    final Solenoid left;
    final Solenoid right;
    final Relay light;

    public Launcher() {
        wings = new DoubleSolenoid(HW.WINGS, HW.WINGS + 1);
        left = new Solenoid(HW.LAUNCHER_LEFT_PISTON);
        right = new Solenoid(HW.LAUNCHER_RIGHT_PISTON);
        light = new Relay(HW.LIGHT);
    }

    protected void initDefaultCommand() {
    }
    
    public void wingsClose() {
        wings.set(DoubleSolenoid.Value.kForward);
    }

    public void wingsOpen() {
        wings.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void launcherUp() {
        left.set(true);
        right.set(true);
    }
    
    public void launcherDown() {
        left.set(false);
        right.set(false);
    }
    
    public void lightOn() {
        light.set(Relay.Value.kForward);
    }
    
    public void lightOff() {
        light.set(Relay.Value.kOff);
    }
}
