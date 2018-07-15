package lib;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a PID controller.
 * @author Finn Frankis
 * @version Jul 15, 2018
 */
public class PIDController
{
    private Map<Integer, Double> kF;
    private Map<Integer, Double> kP;
    private Map<Integer, Double> kI;
    private Map<Integer, Double> kD;
    private Map<Integer, Double> iZone;
    
    private int primarySlot;
    private int auxiliarySlot;
    
    private double lastError;
    private double lastTime;
    private double errorSum;
    private boolean hasRun;
    
    private double minOutput;
    private double maxOutput;
    
    
    public PIDController (double minOutput, double maxOutput)
    {
        kF = new HashMap<Integer, Double>();
        kP = new HashMap<Integer, Double>();
        kI = new HashMap<Integer, Double>();
        kD = new HashMap<Integer, Double>();
        errorSum = 0;
        lastError = 0;
        hasRun = false;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
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
    
    public void config_IntegralZone(double iZone, int slot, int timeout)
    {
        this.iZone.put(slot, iZone);
    }
    
    public void selectProfileSlot(int slotIndex, int loopIndex)
    {
        if (loopIndex == 0)
            primarySlot = slotIndex;
        else
            auxiliarySlot = slotIndex;
    }
    
    /**
     * Gets the necessary motor output given the actual position and the setpoint.
     * @param actual the current position (likely as input from a sensor such as a gyro
     * or encoder)
     * @param setpoint the desired final position
     * @return the output required for the motors
     */
    public double getOutput (double actual, double setpoint)
    {
        double error = setpoint - actual;
        
        double output_F = kF.get(primarySlot) * setpoint;
        
        double output_P = kP.get(primarySlot) * error;
        
        if (Math.abs(error) >= iZone.get(primarySlot))
            errorSum = 0;
        double output_I = kI.get(primarySlot) * errorSum;
        errorSum += error;
        
        double output_D = 0; 
        if (hasRun)
            output_D = -kD.get(primarySlot) * (error - lastError) 
                / (System.currentTimeMillis() - lastTime);

        lastError = error;
        lastTime = System.currentTimeMillis();
        
        return restrictValue(output_F + output_P + output_I + output_D, minOutput, maxOutput);
    }
    
    public double getClosedLoopError (double loopIndex)
    {
        return lastError;
    }
    private double restrictValue(double value, double min, double max)
    {
        return (value < min) ? min : Math.max(value, max);
    }
}
