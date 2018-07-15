package lib;

import java.util.Map;

import com.diozero.api.*;
import com.diozero.sandpit.Servo;
import com.diozero.devices.sandpit.TB6612FNGMotor;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class TalonSRX extends PIDController
{
    
    private int initialPWMFrequency;
    private float initialPulseWidthMs;
    
    public enum ControlMode
    {
        PercentOutput, Position, Velocity;
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
        motor = new DigitalMotor(enablePort, forwardPort, backwardPort);
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
    
    public void set(ControlMode mode, double magnitude)
    {
        if (mode == ControlMode.PercentOutput)
        {
            motor.setValue((float) magnitude);
        }
        else if (mode == ControlMode.Position)
        {
            motor.setValue((float)getOutput(getSelectedSensorPosition(0), magnitude));
        }
    }
    
    public void set(ControlMode mode, double magnitude, DemandType dt, double demandValue)
    {
        set(mode, dt == DemandType.FeedForward ? magnitude + demandValue : magnitude);
    }
    
    
    
    public void configSelectedFeedbackSensor(FeedbackDevice fd, int loopIndex, int timeout)
    {
        
    }
    
    /**
     * Gets the sensor position at the given loop index.
     * @param loopIndexthe PID loop index (primary/auxiliary) [0, 1]
     * @return
     */
    public double getSelectedSensorPosition (int loopIndex)
    {
        return 0;
    }
    
    public void setSelectedSensorPosition (int loopIndex)
    {
        
    }
}
