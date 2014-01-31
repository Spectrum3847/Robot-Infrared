package subsystems;

import driver.IMU;
import driver.SpectrumDrive;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.visa.VisaException;
import framework.HW;
import framework.Init;

/**
 *
 * @author David, Matthew
 */
public class DriveBase extends PIDSubsystem {
    
     //Drive Motor Controller
     private Victor vic_1,vic_2,vic_3,vic_4;
     private Victor[] vic_arr;
     private final SpectrumDrive spectrumDrive;
     
     //Drive Encoders
     private Encoder left_encoder;
     private Encoder right_encoder;
     private double leftOldTime = 0;
     private double rightOldTime = 0;
     private double leftOldDistance = 0;
     private double rightOldDistance = 0;
     
     //Drive X Gyro
     private final AnalogChannel x_gyro_raw;
     private final Gyro x_gyro;
     private IMU imu;
     private double turnControllerOut = 0;
     private final double tolerance = 1; //Percentage of error that the turn controller can be off and still be onTarget()
     
     

    
     public DriveBase(){
         super(HW.STRAIGHT_KP,HW.STRAIGHT_KI,HW.STRAIGHT_KD);
         setVictors();
         spectrumDrive = new SpectrumDrive(vic_1, vic_2, vic_3, vic_4);
         spectrumDrive.setMaxOutput(1.0);
         x_gyro_raw = new AnalogChannel(HW.GYRO);
         x_gyro_raw.setAverageBits(2); //Get 4 samples of gyro data and average them for the raw output
         x_gyro = new Gyro(x_gyro_raw);
         try {
             imu = new IMU();
         } catch (VisaException ex) {}
         this.getPIDController().setOutputRange(-1, 1);
         this.getPIDController().setInputRange(-360, 360);
         this.getPIDController().setAbsoluteTolerance(tolerance);
    }
    
    
     /**
      * Set the Default Command
      * Sets it to driveSelector so that we always have the correct drive mode 
      * re-enabled after breaking or turning.
      */
    public void initDefaultCommand(){
        setDefaultCommand(Init.driveselect);   // set default command
    }
    
    /**
     * This defines the input to the PID controller, in this case the gyro
     * angle for the turn controller PID loop
     * @return 
     */
    public double returnPIDInput(){
        return getAngle();
    }
    
    /**
     * Code that takes the output from the PID controller when it's running
     * @param output
     */
    public void usePIDOutput(double output){
        turnControllerOut = output;
    }
    
    /*
     * Get PID Output
     */
    public double getPIDTurnOutput(){
        return turnControllerOut;
    }
    
    //Return the PID Controller
    public PIDController getController(){
        return this.getPIDController();
    }
    
    public void setPID(double p, double i, double d){
        getController().setPID(p, i, d);
    }
    
    //Is the turn controller enabled?
    public boolean isControllerEnanbled(){
        return getController().isEnable();
    }
       /*
     * Turn on the controller
     */
    public void enableTurnController(){
        if (!isControllerEnanbled()) {
            x_gyro.reset();
            getController().setSetpoint(0.0);
            getController().enable();
        }
    }
    
    /**
     * Disable Turn Controller
     */
    public void disableTurnController(){
        if (isControllerEnanbled()){
            getController().disable();
        }
    }
    
    /**
     * Get the setpoint for the turn controller
     * @return the setpoint from the turn controller
     */
    public double getSetpoint(){
        return getController().getSetpoint();
    }
    
    //Reset the gyro angle and it's PID controller for the drivebase
    public void reset(){
        x_gyro.reset();
        getController().reset();
    }
    
    //Return the Gyro object
    public Gyro getGyro(){
        return x_gyro;
    }
    
    public IMU getIMU(){
        return imu;
    }
    //Return the rawGyro AnalogChannel
    public AnalogChannel getRawGyro(){
        return x_gyro_raw;
    }
    
    //Output gyro average voltage based on 4 samples
    public double getGyroRate(){
        return x_gyro_raw.getAverageVoltage();
    }
    
    //Get the current gyro angle
    public double getAngle(){
        return x_gyro.getAngle();
    }
    
    public double getIMUAngle(){
        return imu.getAngle();
    }
    
    public Encoder getRightEncoder(){
        return right_encoder;
    }
    
    public Encoder getLeftEncoder(){
        return left_encoder;
    }
    
    public double getLeftVelocity(){
        left_encoder.start();
        if (leftOldTime > 0){
            double newTime = Timer.getFPGATimestamp();
            double newDistance = getLeftEncoder().getDistance();
            double leftVelocity =  (newDistance - leftOldDistance)/(newTime - leftOldTime);
            leftOldDistance = newDistance;
            leftOldTime = newTime;
            return leftVelocity;
        } else{
            leftOldDistance = getLeftEncoder().getDistance();
            leftOldTime = Timer.getFPGATimestamp();
            return 0;
        }
    }
    
    public double getRightVelocity(){
        right_encoder.start();
        if (rightOldTime > 0){
            double newTime = Timer.getFPGATimestamp();
            double newDistance = getRightEncoder().getDistance();
            double leftVelocity =  (newDistance - rightOldDistance)/(newTime - rightOldTime);
            rightOldDistance = newDistance;
            rightOldTime = newTime;
            return leftVelocity;
        } else{
            rightOldDistance = getRightEncoder().getDistance();
            rightOldTime = Timer.getFPGATimestamp();
            return 0;
        }
    }
    
    
    public void setLeft(double left_speed){
        spectrumDrive.tankDrive(left_speed, vic_3.get());
    }
    
    public double getLeft(){
        return vic_1.get();
    }
    
    public void setRight(double right_speed){
        spectrumDrive.tankDrive(vic_1.get(), right_speed);
    }
    
    public double getRight(){
        return vic_3.get();
    }
    
    public void setArcade(double straight_speed, double turn_speed){
        spectrumDrive.arcadeDrive(straight_speed, turn_speed, true);
    }
    
    public void setHoloPolar(double mag, double dir, double rot){
        //spectrumDrive.mecanumDrive_Polar(mag, dir+x_gyro.getAngle(), rot);
        spectrumDrive.mecanumDrive_Polar(mag, dir, rot);
        System.out.println(x_gyro.getAngle());
        //spectrumDrive.holonomicDrive(mag, dir, rot, x_gyro);
    }
    
    public void setHoloCartesian(double x, double y, double rot){
        spectrumDrive.mecanumDrive_Cartesian(x, y, rot, 0);//x_gyro.getAngle());
        System.out.println(x_gyro.getAngle());
    }
    public void setCheesyDrive(double throttle, double wheel, boolean quickTurn){
        spectrumDrive.Cheesydrive(throttle,-1* wheel, quickTurn);
    }
    
    public void setCheesySensetivity(double sensetivity){
        spectrumDrive.setSensitivity(sensetivity);
    }
    
    public void turnLeft(double speed){
        setLeft(speed);
        setRight(-1*speed);
    }
    
    public void turnRight(double speed){
        setLeft(-1*speed);
        setRight(speed);
    }
    
    private void setVictors(){
        vic_arr = new Victor[4];
        vic_1 = new Victor(HW.FRONT_RDRIVE_MOTOR);
        vic_arr[0] = vic_1;
        vic_2 = new Victor(HW.REAR_RDRIVE_MOTOR);
        vic_arr[1] = vic_2;
        vic_3 = new Victor(HW.FRONT_LDRIVE_MOTOR);
        vic_arr[2] = vic_3;
        vic_4 = new Victor(HW.REAR_LDRIVE_MOTOR);
        vic_arr[3] = vic_4;
    }
    
    /*
     * END INIT COMMANDS
     */
    
    public Victor getVictor(int id){
        return vic_arr[id];
    }
    
    public Victor[] getVicArr(){
        return vic_arr;
    }
    
    public void setStandardInversion(boolean b) {
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false || !b);
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false || !b);
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false || !b);
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false || !b);
        
    }
    
    public void setHoloInversion(boolean b) {
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false || !b);
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false || !b);
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true && b);
        spectrumDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true && b);
    }
    
    public SpectrumDrive getDrive() {
        return spectrumDrive;
    }
}
