package driver;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import framework.Utilities;

/**
 *
 * @author JAG
 */
public class SpectrumDrive extends RobotDrive {

    public static double tSens = 1.5;
    public static final double DEADBAND_VALUE = .10;

    public SpectrumDrive(Victor vic1, Victor vic2, Victor vic3, Victor vic4) {
        super(vic1, vic2, vic3, vic4);
    }

    /**
     * Provide tank steering using the stored robot configuration. This function
     * lets you directly provide joystick values from any source.
     *
     * @param leftValue The value of the left stick.
     * @param rightValue The value of the right stick.
     */
    public void tankDrive(double leftValue, double rightValue) {
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);

        leftValue = Utilities.deadBand(leftValue, DEADBAND_VALUE);
        rightValue = Utilities.deadBand(rightValue, DEADBAND_VALUE);
        setLeftRightMotorOutputs(leftValue, rightValue);
    }

    // Cheesy Drive - Thanks to Austin Schuh and Teams 254/971. Yeah Buddy!
    // It would not be possible to control a fast drive without this
    public void Cheesydrive(double throttle, double wheel, boolean quickTurn) {

        double angular_power = 0.0;
        double overPower = 0.0;
        double sensitivity = tSens;
        double rPower = 0.0;
        double lPower = 0.0;

        if (quickTurn) {
            overPower = 1.0;
            sensitivity = 1.0;
            angular_power = wheel;
        } else {
            overPower = 0.0;
            angular_power = Math.abs(throttle) * wheel * sensitivity;
        }

        rPower = lPower = throttle;
        lPower += angular_power;
        rPower -= angular_power;

        if (lPower > 1.0) {
            rPower -= overPower * (lPower - 1.0);
            lPower = 1.0;
        } else if (rPower > 1.0) {
            lPower -= overPower * (rPower - 1.0);
            rPower = 1.0;
        } else if (lPower < -1.0) {
            rPower += overPower * (-1.0 - lPower);
            lPower = -1.0;
        } else if (rPower < -1.0) {
            lPower += overPower * (-1.0 - rPower);
            rPower = -1.0;
        }

        lPower = Utilities.deadBand(lPower, DEADBAND_VALUE);
        rPower = Utilities.deadBand(rPower, DEADBAND_VALUE);
        setLeftRightMotorOutputs(lPower, rPower);
    }

    public void setSensitivity(double sensitivity) {
        tSens = sensitivity;
    }

    /**
     * Arcade drive implements single stick driving. This function lets you
     * directly provide joystick values from any source.
     *
     * @param moveValue The value to use for forwards/backwards
     * @param rotateValue The value to use for the rotate right/left
     * @param squaredInputs If set, increases the sensitivity at low speeds
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);

        if (squaredInputs) {
            // cube the inputs (while preserving the sign) to increase fine control while permitting full power
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue * moveValue);
            } else {
                moveValue = (moveValue * moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue * rotateValue);
            } else {
                rotateValue = (rotateValue * rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        leftMotorSpeed = Utilities.deadBand(leftMotorSpeed, DEADBAND_VALUE);
        rightMotorSpeed = Utilities.deadBand(rightMotorSpeed, DEADBAND_VALUE);
        setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }

    /**
     * Holonomic Drive method for omni wheel robots
     *
     * This is a modified version of the WPILIB Mecannum code. The formula works
     * but there is no need to invert the rotations because you rotate but
     * increasing the speed of every wheel
     *
     * @param magnitude The speed that the robot should drive in a given
     * direction. [-1.0..1.0]
     * @param direction The direction the robot should drive. The direction and
     * maginitute are independent of the rotation rate.
     * @param rotation The rate of rotation for the robot that is completely
     * independent of the magnitude or direction. [-1.0..1.0]
     */
    public void holonomicDrive(double magnitude, double direction, double rotation) {
        // Normalized for full power along the Cartesian axes.
        magnitude = limit(magnitude) * Math.sqrt(2.0);
        // The rollers are at 45 degree angles.
        double dirInRad = (direction + 45.0) * 3.14159 / 180.0;
        double cosD = Math.cos(dirInRad);
        double sinD = Math.cos(dirInRad);

        double wheelSpeeds[] = new double[kMaxNumberOfMotors];
        wheelSpeeds[MotorType.kFrontLeft.value] = (sinD * magnitude + rotation);
        wheelSpeeds[MotorType.kRearRight.value] = (sinD * magnitude - rotation);
        wheelSpeeds[MotorType.kFrontRight.value] = (cosD * magnitude + rotation);
        wheelSpeeds[MotorType.kRearLeft.value] = (cosD * magnitude - rotation);

        normalize(wheelSpeeds);

        byte syncGroup = (byte) 0x80;

        m_frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * m_invertedMotors[MotorType.kFrontLeft.value] * m_maxOutput, syncGroup);
        m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * m_invertedMotors[MotorType.kFrontRight.value] * m_maxOutput, syncGroup);
        m_rearLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * m_invertedMotors[MotorType.kRearLeft.value] * m_maxOutput, syncGroup);
        m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * m_invertedMotors[MotorType.kRearRight.value] * m_maxOutput, syncGroup);

        if (m_safetyHelper != null) {
            m_safetyHelper.feed();
        }
    }

    /**
     * This class should be used for field centric control of the robot. It
     * adds the gyro angle to the direction of the robot. Ensure that the gyro
     * angle is at zero when the robot is driving away from the driver.
     *
     * @param magnitude
     * @param direction
     * @param rotation
     * @param gyro
     */
    public void holonomicDrive(double magnitude, double direction, double rotation, Gyro gyro) {
        holonomicDrive(magnitude, direction + gyro.getAngle(), rotation);
    }
}
