package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;

/**
 * @author matthew
 */
public class FireAll extends CommandGroup {
    public FireAll() {
        super();
        requires(CommandBase.shooter);
        requires(CommandBase.flicker);
        
        this.addParallel(new DashboardShoot(), 4);
        
        /*
        this.addSequential(new AtSpeedFlick(), 0.5);
        this.addSequential(new AtSpeedFlick(), 0.5);
        this.addSequential(new AtSpeedFlick(), 0.5);
        this.addSequential(new AtSpeedFlick(), 0.5);
        */
        
        this.addSequential(new AutoFlick(), 1);
        this.addSequential(new AutoFlick(), 1);
        this.addSequential(new AutoFlick(), 1);
        this.addSequential(new AutoFlick(), 1);
        this.addSequential(new AutoFlick(), 1);
        this.addSequential(new AutoFlick(), 1);
    }
}
