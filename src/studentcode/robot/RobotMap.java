package studentcode.robot;

/**
 * Stores all of the important constants to be used.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class RobotMap
{
    public static final int AUTON_TIME_MS = 15000;
    public static final int TELEOP_TIME_MS = Integer.MAX_VALUE;//135000;
    
    public static final int LEFT_MOTOR_ENABLE = 4; //#32
    public static final int LEFT_MOTOR_FORWARD = 13; //#23
    public static final int LEFT_MOTOR_BACKWARD = 16; //#19
    
    public static final int RIGHT_MOTOR_ENABLE = 5; //33 
    public static final int RIGHT_MOTOR_FORWARD = 12; //10 
    public static final int RIGHT_MOTOR_BACKWARD = 14; //21
    
    public static final int LEFT_ENCODER_ORANGE = 0; // BCM 17
    public static final int LEFT_ENCODER_BROWN = 2; // BCM 27
    public static final int RIGHT_ENCODER_ORANGE = 27; // BCM 16
    public static final int RIGHT_ENCODER_BROWN = 28; // BCM 20
    
    public static final int ARM_SERVO_GPIO = 23; // BCM 13
    public static final int CLAW_SERVO_GPIO = 26; // BCM 12
    
    public static final double MAX_SPEED = 1;
    public static final double MAX_TURN = 0.15;
    
    public static final int PID_PRIMARY = 0;
    public static final int PID_AUXILIARY = 1;
    public static final int TIMEOUT = 10;
    
    public static final int ARM_OUTTAKE_ANGLE = 15;
    public static final int ARM_INTAKE_ANGLE = 75;
    
    public static final int CLAW_CLOSE_ANGLE = 37;
    public static final int CLAW_OPEN_ANGLE = 10;
    
    public static final int POS_PID_SLOT = 0;
    public static final double POS_KF_L = 0;
    public static final double POS_KP_L = 0.01;
    public static final double POS_KI_L = 0;
    public static final double POS_KD_L = 0;
    
    public static final double POS_KF_R = 0;
    public static final double POS_KP_R = 0.017;
    public static final double POS_KI_R = 0;
    public static final double POS_KD_R = 0;
    
	public static final int POS_ALLOWABLE_ERROR = 10;
	
    public static class KeyboardCharacters
    {
        public static final String FORWARD = "w";
        public static final String BACKWARD = "s";
        public static final String LEFT = "a";
        public static final String RIGHT = "d";
        public static final String STOP = "o";
        public static final String STOP_DRIVETRAIN = "q";
        public static final String ARM_OUTTAKE = "i";
        public static final String ARM_INTAKE = "k";
        public static final String CLAW_OPEN = "j";
        public static final String CLAW_CLOSE = "l";
    }
}
