package subsystems;

import driver.IMU;
import driver.SpectrumDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import framework.HW;
import framework.Init;
import framework.Utilities;

/**
 *
 * @author David, Matthew
 */
public class DriveBase extends PIDSubsystem {

    //Drive Motor Controller
    private Victor vic_1, vic_2, vic_3, vic_4;
    private Victor[] vic_arr;
    private final SpectrumDrive spectrumDrive;

    private final Gyro gyro;
    private final IMU imu;
    private double turnControllerOut = 0;
    private final double tolerance = 0.5;
    final DoubleSolenoid mecanum;

    public DriveBase() {
        super(HW.TURN_KP, HW.TURN_KI, HW.TURN_KD);
        setVictors();
        mecanum = new DoubleSolenoid(HW.OCTOCANUM, HW.OCTOCANUM + 1);
        spectrumDrive = new SpectrumDrive(vic_1, vic_2, vic_3, vic_4);
        spectrumDrive.setMaxOutput(1.0);
        imu = new IMU(HW.IMU_PORT);
        gyro = new Gyro(HW.GYRO);
        this.getPIDController().setOutputRange(-1, 1);
        this.getPIDController().setInputRange(-250.0, 250);
        this.getPIDController().setAbsoluteTolerance(tolerance);
    }

    /**
     * Set the Default Command Sets it to driveSelector so that we always have
     * the correct drive mode re-enabled after breaking or turning.
     */
    public void initDefaultCommand() {
        setDefaultCommand(Init.driveselect);   // set default command
    }

    /**
     * This defines the input to the PID controller, in this case the gyro angle
     * for the turn controller PID loop
     *
     * @return
     */
    public double returnPIDInput() {
        return gyro.getRate();
    }

    /**
     * Code that takes the output from the PID controller when it's running
     *
     * @param output
     */
    public void usePIDOutput(double output) {
        turnControllerOut = output;
    }

    /*
     * Get PID Output
     */
    public double getPIDTurnOutput() {
        return turnControllerOut;
    }

    //Return the PID Controller
    public PIDController getController() {
        return this.getPIDController();
    }

    public void setPID(double p, double i, double d) {
        getController().setPID(p, i, d);
    }

    //Is the turn controller enabled?
    public boolean isControllerEnanbled() {
        return getController().isEnable();
    }
    /*
     * Turn on the controller
     */

    public void enableTurnController() {
        if (!isControllerEnanbled()) {
            getController().setSetpoint(0.0);
            getController().enable();
        }
    }

    /**
     * Disable Turn Controller
     */
    public void disableTurnController() {
        if (isControllerEnanbled()) {
            getController().disable();
        }
    }

    /**
     * Get the setpoint for the turn controller
     *
     * @return the setpoint from the turn controller
     */
    public double getSetpoint() {
        return getController().getSetpoint();
    }

    //Reset the gyro angle and it's PID controller for the drivebase
    public void reset() {
        getController().reset();
    }

    public Gyro getGyro() {
        return gyro;
    }

    public double getGyroAngle() {
        return getGyro().getAngle() == 0 ? 0 : ((Utilities.sign(getGyro().getAngle())) * getGyro().getAngle() % 360.0);
    }

    public IMU getIMU() {
        return imu;
    }

    //Get the current IMU angle
    public double getIMUAngle() {
        return imu.getAngle();
    }

    public void zeroIMU() {
        imu.zero();
    }

    public void zeroGyro() {
        gyro.reset();
    }

    public void setLeft(double left_speed) {
        spectrumDrive.tankDrive(left_speed, vic_3.get());
    }

    public double getLeft() {
        return vic_1.get();
    }

    public void setRight(double right_speed) {
        spectrumDrive.tankDrive(vic_1.get(), right_speed);
    }

    public double getRight() {
        return vic_3.get();
    }

    public void setArcade(double straight_speed, double turn_speed) {
        spectrumDrive.arcadeDrive(straight_speed, turn_speed, true);
    }

    public void setHoloPolar(double mag, double dir, double rot) {
        spectrumDrive.mecanumDrive_Polar(mag, dir, rot);
    }

    public void setHoloCartesian(double x, double y, double rot) {
        spectrumDrive.mecanumDrive_Cartesian(x, y, rot, 0);
    }

    public void setHoloCartesian(double x, double y) {
        spectrumDrive.mecanumDrive_Cartesian(x, y, this.turnControllerOut, this.getGyroAngle());
    }

    public void setCheesyDrive(double throttle, double wheel, boolean quickTurn) {
        spectrumDrive.Cheesydrive(throttle, -1 * wheel, quickTurn);
    }

    public void setCheesySensetivity(double sensetivity) {
        spectrumDrive.setSensitivity(sensetivity);
    }

    public void turnLeft(double speed) {
        setLeft(speed);
        setRight(-1 * speed);
    }

    public void turnRight(double speed) {
        setLeft(-1 * speed);
        setRight(speed);
    }

    private void setVictors() {
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
    public Victor getVictor(int id) {
        return vic_arr[id];
    }

    public Victor[] getVicArr() {
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

    public void engageAlt() {
        mecanum.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isCheesy() {
        return mecanum.get().value != DoubleSolenoid.Value.kReverse_val;
    }

    public void engageCheesy() {
        mecanum.set(DoubleSolenoid.Value.kForward);
    }
}
