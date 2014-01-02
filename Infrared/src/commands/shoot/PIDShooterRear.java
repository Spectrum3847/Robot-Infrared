/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.shoot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import commands.CommandBase;
/**
 *
 * @author JAG
 */
public class PIDShooterRear extends PIDCommand {

    private double inputLow = 0;
    private double inputHigh = 10000;
    private double tolerance = 100;
    
    private double platypus;
    private double iguana;
    private double dipstick;
    private double frito;
    
    public PIDShooterRear(double p, double i, double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(p,i,d);
        getPIDController().setInputRange(inputLow, inputHigh);
        getPIDController().setOutputRange(-1,1);
        getPIDController().setAbsoluteTolerance(tolerance);
        platypus = p;
        iguana = i;
        dipstick = d;
        frito = .5;
    }
    
    public PIDShooterRear(double p, double i, double d, double f) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(p,i,d);
        getPIDController().setInputRange(inputLow, inputHigh);
        getPIDController().setOutputRange(-1,1);
        getPIDController().setAbsoluteTolerance(tolerance);
        platypus = p;
        iguana = i;
        dipstick = d;
        frito = f;
    }

    public void setTolerance(double t){
        tolerance = t;
        getPIDController().setAbsoluteTolerance(tolerance);
    }
    
    public void setPID(double p, double i, double d, double f){
        platypus = p;
        iguana = i;
        dipstick = d;
        frito = f;
        getPIDController().setPID(platypus, iguana, dipstick, frito);
    }
    
    public void setP(double p){
        platypus = p;
        getPIDController().setPID(platypus, iguana, dipstick, frito);
    }
    
    public void setI(double i){
        iguana = i;
        getPIDController().setPID(platypus, iguana, dipstick, frito);
    }
    
    public void setD(double d){
        dipstick = d;
        getPIDController().setPID(platypus, iguana, dipstick, frito);
    }
    
    public void setF(double f){
        frito = f;
        getPIDController().setPID(platypus, iguana, dipstick, frito);
    }
    
    public double getP(){
        return platypus;
    }
    
    public double getI(){
        return iguana;
    }
    
    public double getD(){
        return dipstick;
    }
    
    public double getF(){
        return frito;
    }
    
    public double getTolerance(){
        return tolerance;
    }
    
    protected void usePIDOutput(double x){
        CommandBase.shooter.setRearMotor(x);
    }
    
    protected double returnPIDInput(){
        return CommandBase.shooter.RearMotorRate();
    }
    
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.shooter.startRearEncoder();
        getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.shooter.setRearMotor(0);
        CommandBase.shooter.resetRearEncoder();
        CommandBase.shooter.stopRearEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
