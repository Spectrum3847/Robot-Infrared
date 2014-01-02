/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author JAG
 */
public class PIDShooterFront extends PIDCommand {

    private double inputLow = 0;
    private double inputHigh = 12000;
    
    private double output = 10000;
    
    private double tolerance = 0;
    
    private double platypus;
    private double iguana;
    private double dipstick;
    
    private double oldOutput = 0;
    
    public PIDShooterFront(double p, double i, double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(p,i,d,0.25);
        requires(CommandBase.shooter);
        getPIDController().setInputRange(inputLow, inputHigh);
        getPIDController().setOutputRange(-output,output);
        getPIDController().setAbsoluteTolerance(tolerance);
        getPIDController().setSetpoint(0);
        platypus = p;
        iguana = i;
        dipstick = d;
    } 


    public void setTolerance(double t){
        tolerance = t;
        getPIDController().setAbsoluteTolerance(tolerance);
    }
    
    public void setPID(double p, double i, double d, double f){
        platypus = p;
        iguana = i;
        dipstick = d;
        getPIDController().setPID(platypus, iguana, dipstick);
    }
    
    public void setP(double p){
        platypus = p;
        getPIDController().setPID(platypus, iguana, dipstick);
    }
    
    public void setI(double i){
        iguana = i;
        getPIDController().setPID(platypus, iguana, dipstick);
    }
    
    public void setD(double d){
        dipstick = d;
        getPIDController().setPID(platypus, iguana, dipstick);
    }
    
    public double getP(){ 
        return getPIDController().getP();
    }
    
    public double getI(){
        return getPIDController().getI();
    }
    
    public double getD(){
        return getPIDController().getD();
    }
    
    public double getF(){
        return getPIDController().getF();
    }
    
    public double getTolerance(){
        return tolerance;
    }
    
    protected void usePIDOutput(double x){
        if(getPIDController().isEnable())
        {
            
            double newOutput = oldOutput + x/output;
            if(newOutput < 1 || newOutput > -1)
                oldOutput = newOutput;
            
            CommandBase.shooter.setFrontMotor(oldOutput);
            System.out.println("Using the Front Output ");
        }
    }
    
    protected double returnPIDInput(){
        return CommandBase.shooter.FrontMotorRate();
    }
    
    public PIDController getPIDController(){
        return super.getPIDController();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.shooter.startFrontEncoder();
        getPIDController().reset();
        getPIDController().enable();
        System.out.println("PID SHOOT FRONT STARTED");
        oldOutput = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Front RPM", CommandBase.shooter.getFrontEncoder().getRate());
        SmartDashboard.putNumber("Front P", getP());
        SmartDashboard.putNumber("Front I", getI());
        SmartDashboard.putNumber("Front D", getD());
        SmartDashboard.putNumber("Front Setpoint", this.getSetpoint());
        SmartDashboard.putNumber("Front Error", this.getPIDController().getError());
        SmartDashboard.putNumber("Front Motor Output", CommandBase.shooter.getFrontMotor());
        if(getPIDController().isEnable() == false)
        {
            CommandBase.shooter.setFrontMotor(0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.shooter.setFrontMotor(0);
        CommandBase.shooter.resetFrontEncoder();
        CommandBase.shooter.stopFrontEncoder();
        System.out.println("PID SHOOT FRONT ENDED");
        getPIDController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
    
    
    
    
    
    
    
    
    
}
