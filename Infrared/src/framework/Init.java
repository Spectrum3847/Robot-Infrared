package framework;

import commands.CommandBase;
import commands.autonomous.AutonGroup1;
import commands.autonomous.AutonGroup2;
import commands.autonomous.AutonGroup3;
import commands.lift.LiftControl;
import commands.pneumatics.DeployBrakes;
import commands.pneumatics.DeployHooks;
import commands.pneumatics.ReleaseBrakes;
import commands.pneumatics.ReleaseHooks;
import commands.pneumatics.RunCompressor;
import commands.shoot.FireAll;
import commands.shoot.Flick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author matthew
 */
public class Init {

    
    public static Command cheesydrive = new commands.driving.CheesyDrive();
    public static Command dashboardShoot = new commands.shoot.DashboardShoot();
    public static Command dashboardCollect = new commands.shoot.DashboardCollect();
    public static Command dashboardShootCollect = new commands.shoot.ShootCollect();
    //public static PIDShoot PIDShootCommand = new commands.shoot.PIDShoot();
    public static Command fireall = new FireAll();
    public static Command test = new commands.test.TestClass();
    
    public static Command inc = new commands.shoot.FrontShooterInc();
    public static Command dec = new commands.shoot.FrontShooterDec();
    
    public static Command liftcontrol = new LiftControl();
    
    public static Flick flick = new Flick();
    
    public static Command auton1 = new AutonGroup1();
    public static Command auton2 = new AutonGroup2();
    public static Command auton3 = new AutonGroup3();
    public static Command auton;
    
    //Jacks Commands
    public static final Command runCompressor = new RunCompressor();
    public static final Command deployBrakes = new DeployBrakes();
    public static final Command deployHooks = new DeployHooks();
    public static final Command releaseBrakes = new ReleaseBrakes();
    public static final Command relaseHooks = new ReleaseHooks();
    
    public static DigitalInput auton_switch = new DigitalInput(HW.AUTON_KILL);
    public static DigitalInput auton_pick = new DigitalInput(HW.AUTON_PICK);

    public static void init() {
        CommandBase.init();
        
        Dashboard.intializeDashboard();
    }

    public static void periodic() {
    }
}
