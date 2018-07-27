package studentcode.robot;

/**
 * Stores all of the important constants to be used.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class RobotMap
{
    public static final int AUTON_TIME_MS = 15000;
    public static final int TELEOP_TIME_MS = 135000;
    
    public static final int LEFT_MOTOR_ENABLE = 23; //#32
    public static final int LEFT_MOTOR_FORWARD = 13; //#23
    public static final int LEFT_MOTOR_BACKWARD = 16; //#19
    
    public static final int RIGHT_MOTOR_ENABLE = 26; //33 
    public static final int RIGHT_MOTOR_FORWARD = 12; //10 
    public static final int RIGHT_MOTOR_BACKWARD = 14; //21
    
    public static final int LEFT_ENCODER_ORANGE = 0;
    public static final int LEFT_ENCODER_BROWN = 2;
    public static final int RIGHT_ENCODER_ORANGE = 27;
    public static final int RIGHT_ENCODER_BROWN = 28;
    
    public static final int ARM_SERVO_GPIO = 23; // BCM 23
    public static final int CLAW_SERVO_GPIO = -1;
    
    public static final double MAX_SPEED = 0.4;
    public static final double MAX_TURN = 0.4;
    
    public static final int PID_PRIMARY = 0;
    public static final int PID_AUXILIARY = 1;
    public static final int TIMEOUT = 10;
    
    public static class KeyboardCharacters
    {
        public static final String FORWARD = "w";
        public static final String BACKWARD = "s";
        public static final String LEFT = "a";
        public static final String RIGHT = "d";
        public static final String STOP = "o";
        public static final String SERVO_MAX_UP = "r";
        public static final String SERVO_MAX_DOWN = "f";
    }
}
