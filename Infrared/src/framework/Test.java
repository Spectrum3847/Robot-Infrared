package framework;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author matthew
 */
public class Test {
        public static void init()
        {
        }
        
        public static void periodic()
        {
            Scheduler.getInstance().run();
        }
}