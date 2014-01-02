package framework;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew
 */
public class Autonomous {
    public static void init()
    {
        /*
        //Init.auton = Init.auton1; 
        SmartDashboard.putBoolean("Auton Status", Init.auton_pick.get());
        SmartDashboard.putBoolean("Auton Switch", Init.auton_switch.get());
        Init.auton = (Init.auton_pick.get())?Init.auton2:Init.auton1;
        if(Init.auton_switch.get())
        {
            Init.auton.start();
        }
        */
        
        //Init.auton = (SmartDashboard.getBoolean(Dashboard.AUTON_TYPE_KEY))?Init.auton2:Init.auton1;
        //if(SmartDashboard.getBoolean(Dashboard.AUTON_SWITCH_KEY))
        
            if(SmartDashboard.getBoolean(Dashboard.AUTON_POOL_NOODLE)==true)
                Init.auton=Init.auton3;
            if(SmartDashboard.getBoolean(Dashboard.AUTON_TYPE_KEY)==true)
                Init.auton = Init.auton2;
            else
                Init.auton = Init.auton1;
        
        
        
        Init.auton.start();
        
    }

    public static void periodic()
    {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }
    
    public static void cancel()
    {
        Init.auton = Init.auton1;
        if(Init.auton_switch.get())
        {
            Init.auton.cancel();
        }
    }
}
