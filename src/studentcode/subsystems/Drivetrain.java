
package studentcode.subsystems;

import lib.commandbased.Subsystem;
import lib.devices.TalonSRX;
import lib.devices.TalonSRX.ControlMode;
import lib.devices.TalonSRX.DemandType;
import lib.devices.TalonSRX.FeedbackDevice;
import studentcode.commands.*;
import studentcode.robot.RobotMap;

import java.security.InvalidParameterException;
import java.util.function.Consumer;

/**
 * Represents the drivetrain on the robot.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Drivetrain extends Subsystem {
    private static Drivetrain dt;
    private TalonSRX leftTalon;
    private TalonSRX rightTalon;
    private boolean closedLoopErrorWithin;

    /**
     * Constructs a new Drivetrain.
     */
    public Drivetrain () {
        leftTalon = new TalonSRX(RobotMap.LEFT_MOTOR_ENABLE,
                RobotMap.LEFT_MOTOR_FORWARD,
                RobotMap.LEFT_MOTOR_BACKWARD);
        rightTalon = new TalonSRX(RobotMap.RIGHT_MOTOR_ENABLE,
                RobotMap.RIGHT_MOTOR_FORWARD,
                RobotMap.RIGHT_MOTOR_BACKWARD);
    }

    public void talonInit () {
        leftTalon.setupEncoder(RobotMap.LEFT_ENCODER_ORANGE, RobotMap.LEFT_ENCODER_BROWN);
        rightTalon.setupEncoder(RobotMap.RIGHT_ENCODER_ORANGE, RobotMap.RIGHT_ENCODER_BROWN);

        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.MagneticEncoder, RobotMap.PID_PRIMARY, RobotMap.TIMEOUT);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.MagneticEncoder, RobotMap.PID_PRIMARY,
                RobotMap.TIMEOUT);
        
        configPositionClosedLoop();
    }

    /**
     * Sets up the default command for this subsystem (the one which the subsystem will default to when no other
     * commands are being sent).
     */
    public void initDefaultCommand () {
        //setDefaultCommand(new DriveToPosition(-50));
        setDefaultCommand (new DriveWithVelocityKeyboard());
    }

    /**
     * Drives the robot with a given speed and turn.
     * @param speed the speed at which the robot is to be driven
     * @param turn the amount by which the robot should turn
     */
    public void arcadeDriveVelocity (double speed, double turn) {
        leftTalon.set(ControlMode.PercentOutput, speed, DemandType.FeedForward, +turn);
        rightTalon.set(ControlMode.PercentOutput, speed, DemandType.FeedForward, -turn);
    }

    /**
     * Gets this singleton instance of Drivetrain.
     * @return the instance of Drivetrain
     */
    public static Drivetrain getInstance () {
        if (dt == null)
            dt = new Drivetrain();
        return dt;
    }

    /**
     * Gets the left Talon on the Drivetrain.
     * @return the left Talon
     */
    public TalonSRX getLeftTalon () {
        return leftTalon;
    }

    /**
     * Gets the right Talon on the Drivetrain.
     * @return the right Talon
     */
    public TalonSRX getRightTalon () {
        return rightTalon;
    }

    /**
     * Applies a given method to both Talons on the Drivetrain.
     * 
     * @param talonConsumer the consumer which represents the method to apply to both talons; correct syntax would be:
     *            set ((talon) -> {talon.[apply method here];})
     */
    public void applyToBoth (Consumer<TalonSRX> talonConsumer) {
        talonConsumer.accept(getLeftTalon());
        talonConsumer.accept(getRightTalon());
    }

    /**
     * Prints the sensor positions of both Talon sensors in a given PID loop.
     * 
     * @param pidLoop the PID loop index to be checked [0, 1]
     */
    public void printBothSensorPositions (int pidLoop) {
        applyToBoth( (talon) -> {
            if (talon.getSelectedSensorVelocity(pidLoop, RobotMap.TIMEOUT) > 0)
                System.out.println("Encoder Position " + pidLoop + ":" + talon.getSelectedSensorPosition(pidLoop, RobotMap.TIMEOUT));
        });
        applyToBoth( (talon) -> {
            if (talon.getSelectedSensorVelocity(pidLoop, RobotMap.TIMEOUT) > 0)
                System.out.println();
        });
    }

    /**
     * Determines whether the closed loop error of both Talons are within a given tolerance.
     * 
     * @param pidLoop the PID loop index to be checked [0, 1]
     * @param tolerance the tolerance
     */
    public boolean getClosedLoopErrorWithin (int pidLoop, int tolerance) {
        closedLoopErrorWithin = true;
        applyToBoth( (talon) -> {
            try {
                closedLoopErrorWithin &= Math.abs(talon.getClosedLoopError(pidLoop, RobotMap.TIMEOUT)) < tolerance;
            } catch (InvalidParameterException e) {
                closedLoopErrorWithin = false;
            }
        });
        return closedLoopErrorWithin;
    }

    /**
     * Configures the position closed loop.
     */
    public void configPositionClosedLoop () {
        getLeftTalon().config_kF(RobotMap.POS_KF_L, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        getLeftTalon().config_kP(RobotMap.POS_KP_L, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        getLeftTalon().config_kI(RobotMap.POS_KI_L, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        getLeftTalon().config_kD(RobotMap.POS_KD_L, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        
        getRightTalon().config_kF(RobotMap.POS_KF_R, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        getRightTalon().config_kP(RobotMap.POS_KP_R, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        getRightTalon().config_kI(RobotMap.POS_KI_R, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
        getRightTalon().config_kD(RobotMap.POS_KD_R, RobotMap.POS_PID_SLOT, RobotMap.TIMEOUT);
    }
    
    /**
     * Selects the profile slots for both Talons on the Drivetrain.
     * @param pidSlot the PID slot index to be used [0, 3]
     * @param pidLoop the PID loop index to which the pidSlot will be assigned [0, 1]
     */
    public void selectProfileSlots (int pidSlot, int pidLoop)
    {
        applyToBoth ( (talon) -> {talon.selectProfileSlot(pidSlot, pidLoop);});
    }
}
