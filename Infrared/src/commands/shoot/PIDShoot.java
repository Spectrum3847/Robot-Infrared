/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.shoot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;

/**
 *
 * @author JAG
 */
public class PIDShoot extends CommandGroup {
    
    private PIDShooterFront frontCommand;
    private PIDShooterMiddle middleCommand;
    private PIDShooterRear rearCommand;
    
    private double fp = 0;
    private double fi = 0;
    private double fd = 0;
    private double mp = 0;
    private double mi = 0;
    private double md = 0;
    private double rp = 0;
    private double ri = 0;
    private double rd = 0;
    private double ff = 0;
    private double mf = 0;
    private double rf = 0;
    
    public PIDShoot(){
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        requires(CommandBase.shooter);
        fp = SmartDashboard.getNumber("FRONT_PID/p", 0.1);
        fi = SmartDashboard.getNumber("FRONT_PID/i", 0);
        fd = SmartDashboard.getNumber("FRONT_PID/d", 0);
        
        mp = SmartDashboard.getNumber("MIDDLE_PID/p", 0.1);
        mi = SmartDashboard.getNumber("MIDDLE_PID/i", 0);
        md = SmartDashboard.getNumber("MIDDLE_PID/d", 0);
        
        rp = SmartDashboard.getNumber("REAR_PID/p", 0.1);
        ri = SmartDashboard.getNumber("REAR_PID/i", 0);
        rd = SmartDashboard.getNumber("REAR_PID/d", 0);
        
        System.out.println();
        frontCommand = new PIDShooterFront(fp,fi,fd);
        middleCommand = new PIDShooterMiddle(mp,mi,md);
        rearCommand = new PIDShooterRear(rp,ri,rd);
        addParallel(frontCommand);
        addParallel(middleCommand);
        addParallel(rearCommand);
        SmartDashboard.putData("FRONT_PID", frontCommand.getPIDController());
        //SmartDashboard.putData("MIDDLE_PID", middleCommand);
        //SmartDashboard.putData("REAR_PID", rearCommand);
    }
        
    protected void initialize() {
        super.initialize();
        System.out.println("PIDSHOOT RUNNING");
    }

    protected void execute() {
        super.execute();
    }

    protected void end() {
        super.end();
        System.out.println("PIDSHOOT END");
    }
    
    protected void interrupted(){
        super.interrupted();
        end();
    }
    
    public PIDCommand getFrontCommand(){
        return frontCommand;
    }
    
    public PIDCommand getMiddleCommand(){
        return middleCommand;
    }
    public PIDCommand getRearCommand(){
        return rearCommand;
    }    
    
    public double getFrontD() {
        return fd;
    }

    public double getFrontF() {
        return ff;
    }

    public double getFrontI() {
        return fi;
    }

    public double getFrontP() {
        return fp;
    }

    public double getMiddleD() {
        return md;
    }

    public double getMiddleF() {
        return mf;
    }

    public double getMiddleI() {
        return mi;
    }

    public double getMiddleP() {
        return mp;
    }

    public double getRearD() {
        return rd;
    }

    public double getRearF() {
        return rf;
    }

    public double getRearI() {
        return ri;
    }

    public double getRearP() {
        return rp;
    }

    public void setFrontD(double fd) {
        this.fd = fd;
    }

    public void setFrontF(double ff) {
        this.ff = ff;
    }

    public void setMiddleD(double md) {
        this.md = md;
    }

    public void setFrontI(double fi) {
        this.fi = fi;
    }

    public void setFrontP(double fp) {
        this.fp = fp;
    }

    public void setMiddleF(double mf) {
        this.mf = mf;
    }

    public void setMiddleI(double mi) {
        this.mi = mi;
    }

    public void setMiddleP(double mp) {
        this.mp = mp;
    }

    public void setRearD(double rd) {
        this.rd = rd;
    }

    public void setRearF(double rf) {
        this.rf = rf;
    }

    public void setRearI(double ri) {
        this.ri = ri;
    }

    public void setRearP(double rp) {
        this.rp = rp;
    }
    
    
}
