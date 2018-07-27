package lib;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import com.diozero.api.*;

import studentcode.robot.RobotMap;

/**
 * Represents a motor controller to simulate the TalonSRX.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class TalonSRX extends PIDController
{
    private FeedbackSensor[] selectedSensors;
    private Map<FeedbackDevice, FeedbackSensor> sensors;
    
    public enum ControlMode
    {
        PercentOutput, Position, Velocity, Disabled;
    }
    
    public enum DemandType
    {
        FeedForward;
    }
    
    public enum FeedbackDevice
    {
        MagneticEncoder;
    }
    
    private OutputDeviceInterface motor;
    
    /**
     * Constructs a new Talon using a digital H-Bridge motor.
     * 
     * @param enablePort the GPIO port used to enable the channel (the magnitude of the speed)
     * @param forwardPort the GPIO port which is used to change the sign of the forward direction
     * @param backwardPort the GPIO port which is used to change the sign of the backward direction
     */
    public TalonSRX(int enablePort, int forwardPort, int backwardPort)
    {
        super (-1, 1);
        motor = new DigitalMotor(forwardPort, backwardPort, enablePort);
        initializeVariables();
    }
    
    /**
     * Constructs a new Talon using a Servo port.
     * 
     * @param port the GPIO port into which the Servo signal line is connected
     */
    public TalonSRX(int port)
    {
        super (-1, 1);
        motor = new Servo(port);
        initializeVariables();
    }
    
    /**
     * Initializes variables which are independent of motor type.
     */
    private void initializeVariables()
    {
        selectedSensors = new FeedbackSensor[2];
        sensors = new HashMap<FeedbackDevice, FeedbackSensor>();
    }
    /**
     * Sets the Talon to a given output.
     * @param mode the type of control to be performed
     * on the talon (including percent output, velocity, and position)
     * @param magnitude the magnitude of the value to be set
     */
    public void set(ControlMode mode, double magnitude)
    {
        double output = 0;
        if (mode == ControlMode.PercentOutput)
        {
            output = magnitude;
        }
        else if (mode == ControlMode.Position)
        {
            if (motor instanceof DigitalMotor)
                output = getOutput(getSelectedSensorPosition(RobotMap.PID_PRIMARY, RobotMap.TIMEOUT), magnitude);
            else if (motor instanceof Servo)
                output = MathUtil.map(magnitude, 0, 1023, 0, 1);
        }
        else if (mode == ControlMode.Velocity)
        {
            if (motor instanceof DigitalMotor)
                output = getOutput(getSelectedSensorVelocity(RobotMap.PID_PRIMARY, RobotMap.TIMEOUT), magnitude);
            else
                throw new RuntimeException("Velocity control mode only supported for digital motors.");
            
        }

        motor.setValue((float) output);
    }
    
    /**
     * Sets the Talon to a given output.
     * @param mode the mode of control to be performed on the Talon (including % output, velocity, and position)
     * @param magnitude the magnitude of the value to be set
     * @param dt the demand type to be added to the output (like a feed forward or an auxiliary sensor value)
     * @param demandValue the magnitude of the demand type
     */
    public void set(ControlMode mode, double magnitude, DemandType dt, double demandValue)
    {
        if (!(motor instanceof Servo))
        {
            double output = 0;
            if (mode == ControlMode.PercentOutput)
            {
                output = magnitude;
            }
            else if (mode == ControlMode.Position)
            {
                output = getOutput(getSelectedSensorPosition(RobotMap.PID_PRIMARY, RobotMap.TIMEOUT), magnitude);
            }
            else if (mode == ControlMode.Velocity)
            {
                output = getOutput(getSelectedSensorVelocity(RobotMap.PID_PRIMARY, RobotMap.TIMEOUT), magnitude);
                
            }
            motor.setValue((float) ((dt == DemandType.FeedForward) ? (output + demandValue) : output));
        }
        else
            throw new RuntimeException("Demand types not supported for Servo motors.");
    }
    
    public void setupEncoder (int orangePin, int brownPin)
    {
        sensors.put(FeedbackDevice.MagneticEncoder, new Encoder(orangePin, brownPin));
    }
    
    /**
     * Configures the feedback sensor to be selected for reading at a given PID loop, 
     * provided it has been set up.
     * @param fd the type of sensor to be selected (like MagneticEncoder)
     * @param loopIndex the PID loop index (primary/auxiliary) [0,1]
     * @param timeout the time after which the CAN command stops being attempted to send
     */
    public void configSelectedFeedbackSensor(FeedbackDevice fd, int loopIndex, int timeout)
    {
        if (!sensors.containsKey(fd))
            throw new InvalidParameterException ("Sensor has not been set up.");
        selectedSensors[loopIndex] = sensors.get(fd);
    }
    
    /**
     * Gets the sensor position at the given loop index.
     * @param loopIndex the PID loop index (primary/auxiliary) [0, 1]
     * @return the current sensor position
     * @throws InterruptedException 
     */
    public double getSelectedSensorPosition (int loopIndex, int timeout)
    {
        if (selectedSensors[loopIndex] == null)
        {
                throw new InvalidParameterException("A selected sensor has not been configured. Use configSelectedFeedbackSensor.");
        }
        return selectedSensors[loopIndex].getPosition();
    }
    
    /**
     * Gets the sensor velocity at the given loop index.
     * @param loopIndex the PID loop index (primary/auxiliary) [0,1]
     * @return the sensor velocity
     */
    public double getSelectedSensorVelocity (int loopIndex, int timeout)
    {
        if (selectedSensors[loopIndex] == null)
        {
                throw new InvalidParameterException("A selected sensor has not been configured. Use configSelectedFeedbackSensor.");
        }
        return selectedSensors[loopIndex].getVelocity();
    }
    
    public void setSelectedSensorPosition (double sensorValue, int loopIndex, int timeout)
    {
        selectedSensors[loopIndex].setPosition(sensorValue);
    }
}
