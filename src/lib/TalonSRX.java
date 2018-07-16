package lib;

import java.util.Map;

import com.diozero.api.*;
import com.diozero.sandpit.Servo;
import com.diozero.devices.sandpit.TB6612FNGMotor;

/**
 * Represents a motor controller.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class TalonSRX extends PIDController
{
    private int initialPWMFrequency;
    private float initialPulseWidthMs;
    private double previousSensorPosition;
    private double previousTime;
    
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
    }
    
    /**
     * Constructs a new Talon using a Servo port.
     * 
     * @param port the GPIO port into which the Servo signal line is connected
     */
    public TalonSRX(int port)
    {
        super (-1, 1);
        initialPWMFrequency = 50;
        initialPulseWidthMs = 1f;
        motor = new Servo(port, initialPWMFrequency, initialPulseWidthMs);
    }
    
    /**
     * 
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
            output = getOutput(getSelectedSensorPosition(0), magnitude);
        }
        else if (mode == ControlMode.Velocity)
        {
            output = getOutput(getSelectedSensorVelocity(0), magnitude);
            
        }
        motor.setValue((float) output);
    }
    
    public void set(ControlMode mode, double magnitude, DemandType dt, double demandValue)
    {
        double output = 0;
        if (mode == ControlMode.PercentOutput)
        {
            output = magnitude;
        }
        else if (mode == ControlMode.Position)
        {
            output = getOutput(getSelectedSensorPosition(0), magnitude);
        }
        else if (mode == ControlMode.Velocity)
        {
            output = getOutput(getSelectedSensorVelocity(0), magnitude);
            
        }
        motor.setValue((float) ((dt == DemandType.FeedForward) ? (output + demandValue) : output));
    }
    
    
    
    public void configSelectedFeedbackSensor(FeedbackDevice fd, int loopIndex, int timeout)
    {
        
    }
    
    /**
     * Gets the sensor position at the given loop index.
     * @param loopIndexthe PID loop index (primary/auxiliary) [0, 1]
     * @return the current sensor position
     */
    public double getSelectedSensorPosition (int loopIndex)
    {
        return 0;
    }
    
    public double getSelectedSensorVelocity (int loopIndex)
    {
        double currentVelocity = (getSelectedSensorPosition(0) - previousSensorPosition) 
                / (System.currentTimeMillis() - previousTime);
        previousSensorPosition = getSelectedSensorPosition(0);
        previousTime = System.currentTimeMillis();
        return currentVelocity;
    }
    
    public void setSelectedSensorPosition (int loopIndex)
    {
        
    }
}
