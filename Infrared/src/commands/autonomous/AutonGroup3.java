package commands.autonomous;

import commands.CommandBase;
import commands.shoot.DashboardShoot;
import commands.driving.PoolNoodleDrive;
import commands.lift.AutonLift;
import commands.shoot.Flick;
import commands.shoot.DashboardCollect;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Zachary
 */
public class AutonGroup3 extends CommandGroup {
    
    public AutonGroup3() {
        super();
        requires(CommandBase.shooter);
        requires(CommandBase.drivebase);
        requires(CommandBase.lift);
        requires(CommandBase.flicker);
        
        this.addParallel(new DashboardShoot(),6);
        this.addSequential(new WaitCommand(2));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addParallel(new AutonLift(),.25);
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        
        this.addParallel(new DashboardCollect(), 3);
        this.addSequential(new PoolNoodleDrive(1));
        this.addSequential(new WaitCommand(1.5));
        this.addSequential(new PoolNoodleDrive(-1));
        
        this.addParallel(new DashboardShoot(),5.5);
        this.addSequential(new WaitCommand(2));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        this.addSequential(new Flick());
        this.addSequential(new WaitCommand(.6));
        
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called at end of Command
    public void end() {
        this.cancel();
    }

    // Call end() if interrupted
    public void interrupted() {
        end();
    }
}
