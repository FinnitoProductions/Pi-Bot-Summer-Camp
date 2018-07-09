package lib;

import java.util.Map;

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
    
    DigitalMotor motor;
    
    public TalonSRX(int[] ports)
    {
        motor = new DigitalMotor(ports[0], ports[1], ports[2]);
    }
    
    public void set(ControlMode mode, double magnitude)
    {
        if (mode == ControlMode.PercentOutput)
        {
            motor.set(magnitude);
        }
    }
    
    public void set(ControlMode mode, double magnitude, DemandType dt, double demandValue)
    {
        if (mode == ControlMode.PercentOutput)
        {
            if (dt == DemandType.FeedForward)
                motor.set(magnitude + demandValue);
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
