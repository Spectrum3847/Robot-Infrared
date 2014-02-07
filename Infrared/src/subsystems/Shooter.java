package subsystems;

import commands.launching.LauncherManual;
import driver.Potentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.HW;

/**
 *
 * @author matthew
 */
public final class Shooter extends PIDSubsystem {

    private final Victor v1, v2, v3, v4;
    private int invert1 = 1, invert2 = 1, invert3 = 1, invert4 = 1;
    private final Potentiometer pot;
    private final Encoder enc;
    
    private double velocityControllerOut;
    private final double tolerance = 1; //Percentage of error that the turn controller can be off and still be onTarget()

    public Shooter() {
        super(HW.SHOOTER_KP, HW.SHOOTER_KI, HW.SHOOTER_KD);
        v1 = new Victor(HW.SHOOTER_MOTOR_1);
        v2 = new Victor(HW.SHOOTER_MOTOR_2);
        v3 = new Victor(HW.SHOOTER_MOTOR_3);
        v4 = new Victor(HW.SHOOTER_MOTOR_4);
        setInvert2(true);
        setInvert3(true);
        pot = new Potentiometer(HW.SHOOTER_POT);
        pot.setInvertAngle(true);
        enc = new Encoder(HW.SHOOTER_ENCODER, HW.SHOOTER_ENCODER+1);
        this.getPIDController().setOutputRange(0, 1);
        this.getPIDController().setAbsoluteTolerance(tolerance);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new LauncherManual());
    }

    public void setLauncherSpeed(double speed) {
        v1.set(speed * invert1);
        v2.set(speed * invert2);
        v3.set(speed * invert3);
        v4.set(speed * invert4);
    }

    public final void setInvert1(boolean isInverted) {
        invert1 = isInverted ? -1 : 1;
    }

    public final void setInvert2(boolean isInverted) {
        invert2 = isInverted ? -1 : 1;
    }

    public final void setInvert3(boolean isInverted) {
        invert3 = isInverted ? -1 : 1;
    }

    public final void setInvert4(boolean isInverted) {
        invert4 = isInverted ? -1 : 1;
    }

    public void stopLauncher() {
        setLauncherSpeed(0);
    }

    public void fastLauncher() {
        setLauncherSpeed(1);
    }

    public void slowLauncher() {
        setLauncherSpeed(-0.2);
    }
    
    public void PIDLauncher() {
        setLauncherSpeed(velocityControllerOut);
    }
    
    public double getArmAngle() {
        
        return (15.0/22.0)* pot.getAngle() - (SmartDashboard.getNumber(Dashboard.SHOOTER_OFFSET, 100));
    }
    
    public Potentiometer getPot(){
        return pot;
    }
    
    public double getRate() {
        return enc.getRate();
    }

    protected double returnPIDInput() {
        return getRate();
    }

    protected void usePIDOutput(double d) {
        velocityControllerOut = d;
    }
    
    public void enableEncoder() {
        enc.start();
    }
    
    public void disableEncoder() {
        enc.stop();
    }
    
    public double getVelocityControllerOut() {
        return velocityControllerOut;
    }
    
    public void setVelocity(double v) {
        this.getPIDController().setSetpoint(v);
    }
    
    public void enablePID() {
        this.getPIDController().enable();
    }
    
    public void disablePID() {
        this.getPIDController().disable();
    }
    
    public void setPID(double p, double i, double d) {
        this.getPIDController().setPID(p, i, d);
    }
}