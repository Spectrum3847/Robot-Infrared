package framework;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Init.init();
    }

    public void disabledInit() {
        Disabled.init();
    }

    public void disabledPeriodic() {
        Disabled.periodic();
    }

    public void disabledContinuous() {
        Disabled.continuous();
    }

    public void autonomousInit() {
        Autonomous.init();
    }

    public void autonomousPeriodic() {
        Autonomous.periodic();
    }

    public void teleopInit() {
        Teleop.init();
    }

    public void teleopPeriodic() {
        Teleop.periodic();
    }
}
