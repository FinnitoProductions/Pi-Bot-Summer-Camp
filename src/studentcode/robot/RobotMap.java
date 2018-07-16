package studentcode.robot;

/**
 * 
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
    
    public static final int ARM_SERVO_GPIO = -1;
    public static final int CLAW_SERVO_GPIO = -1;
}
