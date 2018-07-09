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
public class TalonSRX
{
    private int primarySlot;
    private int auxiliarySlot;
    
    private Map<Integer, Double> kF;
    private Map<Integer, Double> kP;
    private Map<Integer, Double> kI;
    private Map<Integer, Double> kD;
    
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
        motor = new DigitalMotor(enablePort, forwardPort, backwardPort);
    }
    
    /**
     * Constructs a new Talon using a Servo port.
     * 
     * @param port the GPIO port into which the Servo signal line is connected
     */
    public TalonSRX(int port)
    {
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
    }
    
    public void set(ControlMode mode, double magnitude, DemandType dt, double demandValue)
    {
        if (mode == ControlMode.PercentOutput)
        {
            if (dt == DemandType.FeedForward)
                motor.setValue((float)(magnitude + demandValue));
        }
    }
    
    public void selectProfileSlot(int slotIndex, int loopIndex)
    {
        if (loopIndex == 0)
            primarySlot = slotIndex;
        else
            auxiliarySlot = slotIndex;
    }
    
    public void config_kF(double kF, int slot, int timeout)
    {
        this.kF.put(slot, kF);
    }
    
    public void config_kP(double kP, int slot, int timeout)
    {
        this.kP.put(slot, kP);
    }
    
    public void config_kI(double kI, int slot, int timeout)
    {
        this.kI.put(slot, kI);
    }
    
    public void config_kD(double kD, int slot, int timeout)
    {
        this.kD.put(slot, kD);
    }
    
    public void configSelectedFeedbackSensor(FeedbackDevice fd, int loopIndex, int timeout)
    {
        
    }
    
    public void getSelectedFeedbackSensor (int loopIndex)
    {
        
    }
    
    public void setSelectedFeedbackSensor (int loopIndex)
    {
        
    }
}
