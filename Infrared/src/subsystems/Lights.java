package subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import framework.HW;

/**
 * @author matthew
 */
public class Lights extends Subsystem {
    public Relay light;
    public static boolean lights_enabled = false;
    
    public Lights() {
        super("Lights");
        //light;
        //light.set(Relay.Value.kOff);
    }
    
    public void enableLights() {
        if(lights_enabled)
            light.set(Relay.Value.kOn);
    }
    
    public void disableLights() {
        if(lights_enabled)
            light.set(Relay.Value.kOff);
    }
    
    public void toggleLights() {
        if(lights_enabled)
            light.set(light.get()==Relay.Value.kOff?Relay.Value.kOn:Relay.Value.kOff);
    }

    protected void initDefaultCommand() {
    }

}
