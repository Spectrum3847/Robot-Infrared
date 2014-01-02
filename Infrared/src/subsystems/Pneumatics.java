/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.HW;

/**
 *
 * @author David
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Compressor compressor;
    
    public static DoubleSolenoid brakes;
    public static DoubleSolenoid hooks;
    
    public Pneumatics(){
        compressor = new Compressor(HW.PRESSURE_SENSOR,HW.COMPRESSOR);
        brakes = new DoubleSolenoid(HW.BRAKES, HW.BRAKES+1);
        hooks = new DoubleSolenoid(HW.HOOKS, HW.HOOKS+1);
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

    protected void initDefaultCommand() {
    }
    
    public void engageBrakes(){
        brakes.set(DoubleSolenoid.Value.kForward);
        
        SmartDashboard.putBoolean("Brakes Engaged", true); //Brakes are engaged
    }
    
    public void releaseBrakes(){
        brakes.set(DoubleSolenoid.Value.kReverse);
        SmartDashboard.putBoolean("Brakes Engaged", false); //Brakes are released
    }
    
    public void engageHooks(){
        hooks.set(DoubleSolenoid.Value.kForward);
        
        SmartDashboard.putBoolean("Hooks Engaged", true); //Hooks are extended
    }
    
    public void releaseHooks(){
        hooks.set(DoubleSolenoid.Value.kReverse);
        SmartDashboard.putBoolean("Hooks Engaged", false); //Hooks are retracted
    }
    
    /**
     * 
     * @return state of the brakes solenoid
     */
    public boolean isBrakes(){
        return brakes.get().value==DoubleSolenoid.Value.kReverse_val?false:true;
    }
    
}