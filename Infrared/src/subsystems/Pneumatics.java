/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author Matthew
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private final Compressor compressor;
    
    public static DoubleSolenoid mecanum;
    
    public Pneumatics(){
        compressor = new Compressor(HW.PRESSURE_SENSOR,HW.COMPRESSOR);
        mecanum = new DoubleSolenoid(HW.OCTOCANUM, HW.OCTOCANUM+1);
    }
    
    public boolean isMaxPSI(){
        return compressor.getPressureSwitchValue();
    }
    
    public void runCompressor(){
        compressor.start();
    }
    
    public void stopCompressor(){
        compressor.stop();
    }
    
    public boolean isCompressor(){
        return compressor.enabled();
    }
    
    public void engageCheesy() {
        mecanum.set(DoubleSolenoid.Value.kForward);
        
    }
    
    public void engageHolo() {
        mecanum.set(DoubleSolenoid.Value.kReverse);
        
    }

    protected void initDefaultCommand() {
    }
    
    /**
     * 
     * @return state of the module
     */
    public boolean isCheesy(){
        return mecanum.get().value != DoubleSolenoid.Value.kReverse_val;
    }
    
}