package lib;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinEdge;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * 
 * @author Finn Frankis 
 * @version Jul 20, 2018
 */
public class Encoder
{
    private GpioPinDigitalInput orangeInput, brownInput;
    private int encoderCount;
    
    public Encoder (int orangePort, int brownPort)
    {        
        encoderCount = 0;
        GpioController currentController = GpioFactory.getInstance();
        
        orangeInput = currentController.provisionDigitalInputPin(RaspiPin.getPinByAddress(orangePort), PinPullResistance.PULL_DOWN);
        brownInput = currentController.provisionDigitalInputPin(RaspiPin.getPinByAddress(brownPort), PinPullResistance.PULL_DOWN);
        
        orangeInput.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        brownInput.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        
        orangeInput.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent changeEvent)
            {
                System.out.println("ORANGE: " + (changeEvent.getEdge().equals(PinEdge.RISING) ? 1 : -1));
            }
                });
        brownInput.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent changeEvent)
            {
                System.out.println("BROWN: " + (changeEvent.getEdge().equals(PinEdge.RISING) ? 1 : -1));
            }
                });
    }
    
    public int getEncoderCount()
    {
        return encoderCount;
    }
}
