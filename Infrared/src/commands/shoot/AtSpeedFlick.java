package commands.shoot;

import commands.CommandBase;
import framework.Init;

/**
 * @author matthew
 */
public class AtSpeedFlick extends CommandBase{

    protected void initialize() {
        Init.flick.initialize();
    }

    protected void execute() {
        if(shooter.motor_f.get() > 0.1)
        {
            if(CommandBase.shooter.atSpeeds())
            {
                Init.flick.getTimer().start();
                Init.flick.execute();
            }
            else
            {
                Init.flick.getTimer().start();
            }
        }
    }

    protected boolean isFinished() {
        return Init.flick.isFinished();
    }

    protected void end() {
        Init.flick.end();
    }

    protected void interrupted() {
        end();
    }

}