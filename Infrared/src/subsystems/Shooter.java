package subsystems;

import driver.OpticalEncoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.HW;
import framework.Init;
import framework.Utilities;

public class Shooter extends Subsystem {

    
    public Jaguar motor_f, motor_m, motor_r;
    private OpticalEncoder encoder_f;
    private OpticalEncoder encoder_m;
    private OpticalEncoder encoder_r;
    private double last_f = 0;
    private double last_m = 0;
    private double last_r = 0;
    
    // Initialize your subsystem here
    public Shooter() {
        super("Shooter");
        
        encoder_f = new OpticalEncoder(HW.SHOOTER_FRONT_ENCODER);
        encoder_m = new OpticalEncoder(HW.SHOOTER_MIDDLE_ENCODER);
        encoder_r = new OpticalEncoder(HW.SHOOTER_REAR_ENCODER);
        
        motor_f = new Jaguar(HW.FRONT_SHOOTER_MOTOR);
        motor_m = new Jaguar(HW.MIDDLE_SHOOTER_MOTOR);
        motor_r = new Jaguar(HW.REAR_SHOOTER_MOTOR);
    }
    
    
    public void initDefaultCommand() {
        this.setDefaultCommand(Init.dashboardShootCollect);
    }
    
    public void setShooter(double front, double middle, double rear){
        setFrontMotor(front); 
        setMiddleMotor(middle);
        setRearMotor(rear);
    }
    
    public void setFrontMotor(double speed) {
        motor_f.set(speed);
    }
    
    public void setMiddleMotor(double speed) {
        motor_m.set(speed);
    }
    
    public void setRearMotor(double speed) {
        motor_r.set(speed);
    }
    
    //PID code here, change implementation in AutonSetShooter.java to PID instead of setBangBang
//    public void doDaPID(double setpoint_f, double setpoint_m, double setpoint_r){
//        double rate_f = encoder_f.getRate()>16000?0:encoder_f.getRate();
//        double rate_m = encoder_m.getRate()>16000?0:encoder_m.getRate();
//        double rate_r = encoder_r.getRate()>16000?0:encoder_r.getRate();
        
        //PID stuff here
//    }
    
    public void setBangBang(double setpoint_f, double setpoint_m, double setpoint_r){
        double rate_f = FrontMotorRate();
        double rate_m = MiddleMotorRate();
        double rate_r = RearMotorRate();
        
        double minR = SmartDashboard.getNumber(Dashboard.REAR_MOTOR_BANGLOW);
        double minM = SmartDashboard.getNumber(Dashboard.MIDDLE_MOTOR_BANGLOW);
        double minF = SmartDashboard.getNumber(Dashboard.FRONT_MOTOR_BANGLOW);
        
        if(rate_f > setpoint_f)
            setFrontMotor(minF); //minimum math code
        else setFrontMotor(1.0);
        
        if(rate_m > setpoint_m)
            setMiddleMotor(minM); //minimum math code
        else setMiddleMotor(1.0);
        
        if(rate_r > setpoint_r)
            setRearMotor(minR); //minimum math code
        else setRearMotor(1.0);
    }
    
    public void setCollectBangBang(double setpoint_f, double setpoint_m, double setpoint_r){
        double rate_f = FrontMotorRate();
        double rate_m = MiddleMotorRate();
        double rate_r = RearMotorRate();
        
        if(rate_m > setpoint_m)
            setMiddleMotor(-.4);
        else
            setMiddleMotor(-1);
        
        if(rate_r > setpoint_r)
            setRearMotor(-.4);
        else
            setRearMotor(-1);
        
        if(rate_f > setpoint_f)
            setFrontMotor(-.1);
        else
            setFrontMotor(-.7);
    }
    
    public double getFrontMotor() {
        return motor_f.get();
    }
    
    public double getMiddleMotor() {
        return motor_m.get();
    }
    
    public double getRearMotor (){
        return motor_r.get();
    }
    
    public double FrontMotorRate() {
        last_f = encoder_f.getRate()>14000?last_f:encoder_f.getRate();
        return last_f;
    }
    
    public double MiddleMotorRate() {
        last_m = encoder_m.getRate()>14000?last_m:encoder_m.getRate();
        return last_m;
    }
    
    public double RearMotorRate() {
        last_r =  encoder_r.getRate()>14000?last_r:encoder_r.getRate();
        return last_r;
    }
    
    public OpticalEncoder getFrontEncoder() {
        return encoder_f;
    }
        
    public OpticalEncoder getMiddleEncoder() {
        return encoder_m;
    }
    
    public OpticalEncoder getRearEncoder() {
        return encoder_r;
    }
    
    public void startEncoders() {
        encoder_f.start();
        encoder_m.start();
        encoder_r.start();
    }
    
    public void startFrontEncoder(){
        encoder_f.start();
    }
    
    public void startMiddleEncoder(){
        encoder_m.start();
    }
    
    public void startRearEncoder(){
        encoder_r.start();
    }
    
    public void stopEncoders() {
        encoder_f.stop();
        encoder_m.stop();
        encoder_r.stop();
    }
    
    public void stopFrontEncoder(){
        encoder_f.stop();
    }
    
    public void stopMiddleEncoder(){
        encoder_m.stop();
    }
    
    public void stopRearEncoder(){
        encoder_r.stop();
    }
    
    public void resetEncoders() {
        encoder_f.reset();
        encoder_m.reset();
        encoder_r.reset();
    }
    
    public void resetFrontEncoder(){
        encoder_f.reset();
    }
    
    public void resetMiddleEncoder(){
        encoder_m.reset();
    }
    
    public void resetRearEncoder(){
        encoder_r.reset();
    }
    
    public boolean atSpeeds(double front, double middle, double rear, double tolerance) {
        if(Utilities.abs(encoder_f.getRate() - front) < tolerance && Utilities.abs(encoder_m.getRate()- middle) < tolerance && Utilities.abs(encoder_r.getRate() - rear) < tolerance)
        {
            return true;
        }
        return false;
    }
    public boolean atSpeeds() {
        double front = SmartDashboard.getNumber(Dashboard.FRONT_SHOOTER_RPM_KEY ) + SmartDashboard.getNumber(Dashboard.FRONT_SHOOTER_OFFSET);
        double middle = SmartDashboard.getNumber(Dashboard.MIDDLE_SHOOTER_RPM_KEY);
        double rear = SmartDashboard.getNumber(Dashboard.REAR_SHOOTER_RPM_KEY);
        return atSpeeds(front, middle, rear, 200);
    }
}
