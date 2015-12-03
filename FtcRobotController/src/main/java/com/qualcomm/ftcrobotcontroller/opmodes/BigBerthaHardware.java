package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 * Created by spmce on 12/1/2015.
 */
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//--------------------------------------------------------------------------
/**
 * Provides a single hardware access point between custom op-modes and the
 * OpMode class for the Push Bot.
 *
 * This class prevents the custom op-mode from throwing an exception at runtime.
 * If any hardware fails to map, a warning will be shown via telemetry data,
 * calls to methods will fail, but will not cause the application to crash.
 *
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-01-06-01-----2015-12-01
 */
public class BigBerthaHardware extends OpMode {
    //--------------------------------------------------------------------------
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaHardware () {
        // Initialize base classes and class members.
        // All via self-construction.
    } // BigBerthaHardware
    public BigBerthaHardware(double l_bucket_door_position, double l_hook_position) {
        l_bucket_door_position = 0.5;
        l_hook_position = 0.5;
    }
    //--------------------------------------------------------------------------
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void init () {
        // Use the hardwareMap to associate class members to hardware ports.
        //
        // Note that the names of the devices (i.e. arguments to the get method)
        // must match the names specified in the configuration file created by
        // the FTC Robot Controller (Settings-->Configure Robot).
        //
        // The variable below is used to provide telemetry data to a class user.
        v_warning_generated = false;
        v_warning_message = "Can't map; ";
        //------------DcMotors------------
        //left_drive
        try {
            v_motor_left_drive = hardwareMap.dcMotor.get ("left");
        }
        catch (Exception p_exeception) {
            m_warning_message ("left_drive");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_left_drive = null;
        }
        //right_drive
        try {
            v_motor_right_drive = hardwareMap.dcMotor.get ("right");
            v_motor_right_drive.setDirection (DcMotor.Direction.REVERSE);
            // The direction of the right motor is reversed, so joystick inputs can
            // be more generically applied.
        }
        catch (Exception p_exeception) {
            m_warning_message ("right_drive");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_right_drive = null;
        }
        //lift_arm
        try {
            v_motor_lift_arm = hardwareMap.dcMotor.get ("liftArm");
        }
        catch (Exception p_exeception) {
            m_warning_message ("left_arm");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_lift_arm = null;
        }
        //lift
        try {
            v_motor_lift = hardwareMap.dcMotor.get ("lift");
        }
        catch (Exception p_exeception) {
            m_warning_message ("lift");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_lift = null;
        }
        //chain_hooks
        try {
            v_motor_chain_hooks = hardwareMap.dcMotor.get ("chainHooks");
        }
        catch (Exception p_exeception) {
            m_warning_message ("chain_hooks");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_chain_hooks = null;
        }
        //spinner
        try {
            v_motor_spinner = hardwareMap.dcMotor.get ("spinner");
        }
        catch (Exception p_exeception) {
            m_warning_message ("spinner");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_spinner = null;
        }
        //bucket
        try {
            v_motor_bucket = hardwareMap.dcMotor.get ("bucket");
        }
        catch (Exception p_exeception) {
            m_warning_message ("bucket");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_bucket = null;
        }//sweeper
        try {
            v_motor_sweeper = hardwareMap.dcMotor.get ("sweeper");
        }
        catch (Exception p_exeception) {
            m_warning_message ("sweeper");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_motor_sweeper = null;
        }
        //------------Servos------------
        //position of servos
        //0.5 is off, 1 is forwards, and 0 is backwards
        double l_bucket_door_position = 0.5;
        double l_hook_position = 0.5;
        new BigBerthaHardware(l_bucket_door_position,l_hook_position);
        //bucket_door
        try {
            v_servo_bucket_door = hardwareMap.servo.get ("bucket_door");
            v_servo_bucket_door.setPosition (l_bucket_door_position);
        }
        catch (Exception p_exeception) {
            m_warning_message ("bucket_door");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_servo_bucket_door = null;
        }
        //hook
        try {
            v_servo_hook = hardwareMap.servo.get ("hook");
            v_servo_hook.setPosition (l_hook_position);
        }
        catch (Exception p_exeception) {
            m_warning_message ("hook");
            DbgLog.msg (p_exeception.getLocalizedMessage ());

            v_servo_hook = null;
        }
    } // init
    //--------------------------------------------------------------------------
    /**
     * Access whether a warning has been generated.
     */
    boolean a_warning_generated () {return v_warning_generated;}
    //--------------------------------------------------------------------------
    /**
     * Access the warning message.
     */
    String a_warning_message () {return v_warning_message;}
    //--------------------------------------------------------------------------
    /**
     * Mutate the warning message by ADDING the specified message to the current
     * message; set the warning indicator to true.
     *
     * A comma will be added before the specified message if the message isn't
     * empty.
     */
    void m_warning_message (String p_exception_message) {
        if (v_warning_generated)
            v_warning_message += ", ";
        v_warning_generated = true;
        v_warning_message += p_exception_message;
    } // m_warning_message
    //--------------------------------------------------------------------------
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void start () {
        // Only actions that are common to all Op-Modes (i.e. both automatic and
        // manual) should be implemented here.
        //
        // This method is designed to be overridden.
    } // start
    //--------------------------------------------------------------------------
    /**
     * Perform any actions that are necessary while the OpMode is running.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        // Only actions that are common to all OpModes (i.e. both auto and\
        // manual) should be implemented here.
        //
        // This method is designed to be overridden.
    } // loop
    //--------------------------------------------------------------------------
    /**
     * Perform any actions that are necessary when the OpMode is disabled.
     *
     * The system calls this member once when the OpMode is disabled.
     */
    @Override public void stop () {
        // Nothing needs to be done for this method.
    } // stop
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    float scale_motor_power (float p_power) {
        // Assume no scaling.
        float l_scale = 0.0f;
        // Ensure the values are legal.
        float l_power = Range.clip (p_power, -1, 1);

        float[] l_array =
                { 0.00f, 0.05f, 0.09f, 0.10f, 0.12f
                        , 0.15f, 0.18f, 0.24f, 0.30f, 0.36f
                        , 0.43f, 0.50f, 0.60f, 0.72f, 0.85f
                        , 1.00f, 1.00f
                };
        // Get the corresponding index for the specified argument/parameter.
        int l_index = (int)(l_power * 16.0);

        if (l_index < 0)
            l_index = -l_index;
        else if (l_index > 16)
            l_index = 16;

        if (l_power < 0)
            l_scale = -l_array[l_index];
        else
            l_scale = l_array[l_index];

        return l_scale;
    } // scale_motor_power
    //--------------------------------------------------------------------------
    /**
     * Access the left_drive motor's power level.
     */
    double a_left_drive_power () {
        double l_return = 0.0;

        if (v_motor_left_drive != null)
            l_return = v_motor_left_drive.getPower ();

        return l_return;
    } // a_left_drive_power
    //--------------------------------------------------------------------------
    /**
     * Access the right_drive motor's power level.
     */
    double a_right_drive_power () {
        double l_return = 0.0;

        if (v_motor_right_drive != null)
            l_return = v_motor_right_drive.getPower ();

        return l_return;
    } // a_right_drive_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_drive_power (double p_left_power, double p_right_power) {
        if (v_motor_left_drive != null)
            v_motor_left_drive.setPower (p_left_power);

        if (v_motor_right_drive != null)
            v_motor_right_drive.setPower (p_right_power);
    } // set_drive_power
    //--------------------------------------------------------------------------
    /**
     * Access the lift_arm motor's power level.
     */
    double a_lift_arm_power () {
        double l_return = 0.0;

        if (v_motor_lift_arm != null)
            l_return = v_motor_lift_arm.getPower ();

        return l_return;
    } // a_lift_arm_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_lift_arm_power (double p_lift_arm_power) {
        if (v_motor_lift_arm != null)
            v_motor_lift_arm.setPower(p_lift_arm_power);
    } // set_lift_arm_power
    //--------------------------------------------------------------------------
    /**
     * Access the lift motor's power level.
     */
    double a_lift_power () {
        double l_return = 0.0;

        if (v_motor_lift != null)
            l_return = v_motor_lift.getPower ();

        return l_return;
    } // a_lift_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_lift_power (double p_lift_power) {
        if (v_motor_lift != null)
            v_motor_lift.setPower(p_lift_power);
    } // set_lift_power
    //--------------------------------------------------------------------------
    /**
     * Access the chain_hooks motor's power level.
     */
    double a_chain_hooks_power () {
        double l_return = 0.0;

        if (v_motor_chain_hooks != null)
            l_return = v_motor_chain_hooks.getPower ();

        return l_return;
    } // a_chain_hooks_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_chain_hooks_power (double p_chain_hooks_power) {
        if (v_motor_chain_hooks != null)
            v_motor_chain_hooks.setPower(p_chain_hooks_power);
    } // set_lift_power
    //--------------------------------------------------------------------------
    /**
     * Access the spinner motor's power level.
     */
    double a_spinner_power () {
        double l_return = 0.0;

        if (v_motor_spinner != null)
            l_return = v_motor_spinner.getPower ();

        return l_return;
    } // a_spinner_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_spinner_power (double p_spinner_power) {
        if (v_motor_spinner != null)
            v_motor_spinner.setPower(p_spinner_power);
    } // set_spinner_power
    // --------------------------------------------------------------------------
    /**
     * Access the bucket motor's power level.
     */
    double a_bucket_power () {
        double l_return = 0.0;

        if (v_motor_bucket != null)
            l_return = v_motor_bucket.getPower ();

        return l_return;
    } // a_bucket_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_bucket_power (double p_bucket_power) {
        if (v_motor_bucket != null)
            v_motor_bucket.setPower(p_bucket_power);
    } // set_bucket_power
    //--------------------------------------------------------------------------
    /**
     * Access the sweeper motor's power level.
     */
    double a_sweeper_power () {
        double l_return = 0.0;

        if (v_motor_sweeper != null)
            l_return = v_motor_sweeper.getPower ();

        return l_return;
    } // a_sweeper_power
    //--------------------------------------------------------------------------
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_sweeper_power (double p_sweeper_power) {
        if (v_motor_sweeper != null)
            v_motor_sweeper.setPower(p_sweeper_power);
    } // set_sweeper_power
    //--------------------------------------------------------------------------
    /**
     * Set the left_drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_left_drive_encoder () {
        if (v_motor_left_drive != null) {
            v_motor_left_drive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_left_drive_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the right_drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_right_drive_encoder () {
        if (v_motor_right_drive != null) {
            v_motor_right_drive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_right_drive_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the lift_arm wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_lift_arm_encoder () {
        if (v_motor_lift_arm != null) {
            v_motor_lift_arm.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_lift_arm_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the lift wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_lift_encoder () {
        if (v_motor_lift != null) {
            v_motor_lift.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_lift_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the chain_hooks wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_chain_hooks_encoder () {
        if (v_motor_chain_hooks != null) {
            v_motor_chain_hooks.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_chain_hooks_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the spinner wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_spinner_encoder () {
        if (v_motor_spinner != null) {
            v_motor_spinner.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_spinner_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the bucket wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_bucket_encoder () {
        if (v_motor_bucket != null) {
            v_motor_bucket.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_bucket_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the sweeper wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_sweeper_encoder () {
        if (v_motor_sweeper != null) {
            v_motor_sweeper.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } // run_using_sweeper_encoder
    //--------------------------------------------------------------------------
    /**
     * Set both drive wheel encoders to run, if the mode is appropriate.
     */
    public void run_using_encoders () {
        // Call other members to perform the action on both motors.
        run_using_left_drive_encoder ();
        run_using_right_drive_encoder();
        run_using_lift_arm_encoder();
        run_using_lift_encoder ();
        run_using_chain_hooks_encoder();
        run_using_spinner_encoder ();
        run_using_bucket_encoder();
        run_using_sweeper_encoder();
    } // run_using_encoders
    //--------------------------------------------------------------------------
    /**
     * Set the left_drive wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_left_drive_encoder () {
        if (v_motor_left_drive != null) {
            if (v_motor_left_drive.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_left_drive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_left_drive_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the right_drive wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_right_drive_encoder () {
        if (v_motor_right_drive != null) {
            if (v_motor_right_drive.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_right_drive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_right_drive_encoder
    //--------------------------------------------------------------------------
    /**
     * Set both drive wheel encoders to not run, if the mode is appropriate.
     */
    public void run_without_drive_encoders () {
        // Call other members to perform the action on both motors.
        run_without_left_drive_encoder();
        run_without_right_drive_encoder();
    } // run_without_drive_encoders
    //--------------------------------------------------------------------------
    /**
     * Set the lift wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_lift_encoder () {
        if (v_motor_lift != null) {
            if (v_motor_lift.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_lift.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_lift_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the lift_arm wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_lift_arm_encoder () {
        if (v_motor_lift_arm != null) {
            if (v_motor_lift_arm.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_lift_arm.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_lift_arm_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the chain_hooks wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_chain_hooks_encoder () {
        if (v_motor_chain_hooks != null) {
            if (v_motor_chain_hooks.getChannelMode () ==
                    DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_chain_hooks.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_chain_hooks_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the spinner wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_spinner_encoder () {
        if (v_motor_spinner != null) {
            if (v_motor_spinner.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_spinner.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_spinner_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the bucket wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_bucket_encoder () {
        if (v_motor_bucket != null) {
            if (v_motor_bucket.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_bucket.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_bucket_encoder
    //--------------------------------------------------------------------------
    /**
     * Set the sweeper wheel encoder to not run, if the mode is appropriate.
     */
    public void run_without_sweeper_encoder () {
        if (v_motor_sweeper != null) {
            if (v_motor_sweeper.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS) {
                v_motor_sweeper.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    } // run_without_sweeper_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the left_drive wheel encoder.
     */
    public void reset_left_drive_encoder () {
        if (v_motor_left_drive != null) {
            v_motor_left_drive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_left_drive_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the right_drive wheel encoder.
     */
    public void reset_right_drive_encoder () {
        if (v_motor_right_drive != null) {
            v_motor_right_drive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_right_drive_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset both drive wheel encoders.
     */
    public void reset_drive_encoders () {
        // Reset the motor encoders on the drive wheels.
        reset_left_drive_encoder ();
        reset_right_drive_encoder ();
    } // reset_drive_encoders
    //--------------------------------------------------------------------------
    /**
     * Reset the lift wheel encoder.
     */
    public void reset_lift_encoder () {
        if (v_motor_lift != null) {
            v_motor_lift.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_lift_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the lift_arm wheel encoder.
     */
    public void reset_lift_arm_encoder () {
        if (v_motor_lift_arm != null) {
            v_motor_lift_arm.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_lift_arm_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the chain_hooks wheel encoder.
     */
    public void reset_chain_hooks_encoder () {
        if (v_motor_chain_hooks != null) {
            v_motor_chain_hooks.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_chain_hooks_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the spinner wheel encoder.
     */
    public void reset_spinner_encoder () {
        if (v_motor_spinner != null) {
            v_motor_spinner.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_spinner_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the bucket wheel encoder.
     */
    public void reset_bucket_encoder () {
        if (v_motor_bucket != null) {
            v_motor_bucket.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_bucket_encoder
    //--------------------------------------------------------------------------
    /**
     * Reset the sweeper wheel encoder.
     */
    public void reset_sweeper_encoder () {
        if (v_motor_sweeper != null) {
            v_motor_sweeper.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    } // reset_sweeper_encoder
    //--------------------------------------------------------------------------
    /**
     * Access the left encoder's count.
     */
    int a_left_encoder_count () {
        int l_return = 0;

        if (v_motor_left_drive != null)
            l_return = v_motor_left_drive.getCurrentPosition ();

        return l_return;
    } // a_left_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the right encoder's count.
     */
    int a_right_encoder_count () {
        int l_return = 0;

        if (v_motor_right_drive != null)
            l_return = v_motor_right_drive.getCurrentPosition ();

        return l_return;
    } // a_right_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the lift encoder's count.
     */
    int a_lift_encoder_count () {
        int l_return = 0;

        if (v_motor_lift != null)
            l_return = v_motor_lift.getCurrentPosition ();

        return l_return;
    } // a_lift_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the lift_arm encoder's count.
     */
    int a_lift_arm_encoder_count () {
        int l_return = 0;

        if (v_motor_lift_arm != null)
            l_return = v_motor_lift_arm.getCurrentPosition ();

        return l_return;
    } // a_lift_arm_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the chain_hooks encoder's count.
     */
    int a_chain_hooks_encoder_count () {
        int l_return = 0;

        if (v_motor_chain_hooks != null)
            l_return = v_motor_chain_hooks.getCurrentPosition ();

        return l_return;
    } // a_chain_hooks_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the spinner encoder's count.
     */
    int a_spinner_encoder_count () {
        int l_return = 0;

        if (v_motor_spinner != null)
            l_return = v_motor_spinner.getCurrentPosition ();

        return l_return;
    } // a_spinner_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the bucket encoder's count.
     */
    int a_bucket_encoder_count () {
        int l_return = 0;

        if (v_motor_bucket != null)
            l_return = v_motor_bucket.getCurrentPosition ();

        return l_return;
    } // a_bucket_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Access the sweeper encoder's count.
     */
    int a_sweeper_encoder_count () {
        int l_return = 0;

        if (v_motor_sweeper != null)
            l_return = v_motor_sweeper.getCurrentPosition ();

        return l_return;
    } // a_sweeper_encoder_count
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the left_drive motor's encoder has reached a value.
     */
    boolean has_left_drive_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_left_drive != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_left_drive.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_left_drive_encoder_reached
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the right_drive motor's encoder has reached a value.
     */
    boolean has_right_drive_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;
        if (v_motor_right_drive != null) {
            // Have the encoders reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_right_drive.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_right_drive_encoder_reached
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean have_drive_encoders_reached (double p_left_count, double p_right_count) {
        // Assume failure.
        boolean l_return = false;
        // Have the encoders reached the specified values?
        if (has_left_drive_encoder_reached (p_left_count) &&
                has_right_drive_encoder_reached (p_right_count)) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // have_encoders_reached
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the lift motor's encoder has reached a value.
     */
    boolean has_lift_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_lift != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_lift.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_lift_encoder_reached
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the lift_arm motor's encoder has reached a value.
     */
    boolean has_lift_arm_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_lift_arm != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_lift_arm.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_lift_arm_encoder_reached
//--------------------------------------------------------------------------
    /**
     * Indicate whether the chain_hooks motor's encoder has reached a value.
     */
    boolean has_chain_hooks_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_chain_hooks != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_chain_hooks.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_chain_hooks_encoder_reached
//--------------------------------------------------------------------------
    /**
     * Indicate whether the spinner motor's encoder has reached a value.
     */
    boolean has_spinner_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_spinner != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_spinner.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_spinner_encoder_reached
//--------------------------------------------------------------------------
    /**
     * Indicate whether the bucket motor's encoder has reached a value.
     */
    boolean has_bucket_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_bucket != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_bucket.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_bucket_encoder_reached
//--------------------------------------------------------------------------
    /**
     * Indicate whether the sweeper motor's encoder has reached a value.
     */
    boolean has_sweeper_encoder_reached (double p_count) {
        // Assume failure.
        boolean l_return = false;

        if (v_motor_sweeper != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (v_motor_sweeper.getCurrentPosition ()) > p_count) {
                // Set the status to a positive indication.
                l_return = true;
            }
        }
        // Return the status.
        return l_return;
    } // has_sweeper_encoder_reached
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean drive_using_encoders (double p_left_power, double p_right_power, double p_left_count
            , double p_right_count) {
        // Assume the encoders have not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoders will be used.
        run_using_encoders ();
        // Start the drive wheel motors at full power.
        set_drive_power(p_left_power, p_right_power);
        // Have the motor shafts turned the required amount?
        // If they haven't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (have_drive_encoders_reached(p_left_count, p_right_count)) {
            // Reset the encoders to ensure they are at a known good value.
            reset_drive_encoders();
            // Stop the motors.
            set_drive_power(0.0f, 0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // drive_using_encoders
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the lift motor's encoder has reached a value.
     */
    boolean lift_using_encoder (double p_lift_power, double p_lift_count) {
        // Assume the encoder has not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoder will be used.
        run_using_encoders ();
        // Start the lift wheel motor at full power.
        set_lift_power(p_lift_power);
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (has_lift_encoder_reached(p_lift_count)) {
            // Reset the encoder to ensure it is at a known good value.
            reset_lift_encoder();
            // Stop the motor.
            set_lift_power(0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // lift_using_encoder
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the lift_arm motor's encoder has reached a value.
     */
    boolean lift_arm_using_encoder (double p_lift_arm_power, double p_lift_arm_count) {
        // Assume the encoder has not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoder will be used.
        run_using_encoders ();
        // Start the lift_arm wheel motor at full power.
        set_lift_arm_power(p_lift_arm_power);
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (has_lift_arm_encoder_reached(p_lift_arm_count)) {
            // Reset the encoder to ensure it is at a known good value.
            reset_lift_arm_encoder();
            // Stop the motor.
            set_lift_arm_power(0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // lift_arm_using_encoder
    // --------------------------------------------------------------------------
    /**
     * Indicate whether the chain_hooks motor's encoder has reached a value.
     */
    boolean chain_hooks_using_encoder (double p_chain_hooks_power, double p_chain_hooks_count) {
        // Assume the encoder has not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoder will be used.
        run_using_encoders ();
        // Start the chain_hooks wheel motor at full power.
        set_chain_hooks_power(p_chain_hooks_power);
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (has_chain_hooks_encoder_reached(p_chain_hooks_count)) {
            // Reset the encoder to ensure it is at a known good value.
            reset_chain_hooks_encoder();
            // Stop the motor.
            set_chain_hooks_power(0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // chain_hooks_using_encoder
    // --------------------------------------------------------------------------
    /**
     * Indicate whether the spinner motor's encoder has reached a value.
     */
    boolean spinner_using_encoder (double p_spinner_power, double p_spinner_count) {
        // Assume the encoder has not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoder will be used.
        run_using_encoders ();
        // Start the spinner wheel motor at full power.
        set_spinner_power(p_spinner_power);
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (has_spinner_encoder_reached(p_spinner_count)) {
            // Reset the encoder to ensure it is at a known good value.
            reset_spinner_encoder();
            // Stop the motor.
            set_spinner_power(0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // spinner_using_encoder
    // --------------------------------------------------------------------------
    /**
     * Indicate whether the bucket motor's encoder has reached a value.
     */
    boolean bucket_using_encoder (double p_bucket_power, double p_bucket_count) {
        // Assume the encoder has not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoder will be used.
        run_using_encoders ();
        // Start the bucket wheel motor at full power.
        set_bucket_power(p_bucket_power);
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (has_bucket_encoder_reached(p_bucket_count)) {
            // Reset the encoder to ensure it is at a known good value.
            reset_bucket_encoder();
            // Stop the motor.
            set_bucket_power(0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // bucket_using_encoder
    // --------------------------------------------------------------------------
    /**
     * Indicate whether the sweeper motor's encoder has reached a value.
     */
    boolean sweeper_using_encoder (double p_sweeper_power, double p_sweeper_count) {
        // Assume the encoder has not reached the limit.
        boolean l_return = false;
        // Tell the system that motor encoder will be used.
        run_using_encoders ();
        // Start the sweeper wheel motor at full power.
        set_sweeper_power(p_sweeper_power);
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        if (has_sweeper_encoder_reached(p_sweeper_count)) {
            // Reset the encoder to ensure it is at a known good value.
            reset_sweeper_encoder();
            // Stop the motor.
            set_sweeper_power(0.0f);
            // Transition to the next state when this method is called again.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // sweeper_using_encoder
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the left_drive encoder has been completely reset.
     */
    boolean has_left_drive_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the left encoder reached zero?
        if (a_left_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_left_drive_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the right_drive encoder has been completely reset.
     */
    boolean has_right_drive_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the right encoder reached zero?
        if (a_right_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_right_drive_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the encoders have been completely reset.
     */
    boolean have_drive_encoders_reset () {
        // Assume failure.
        boolean l_return = false;
        // Have the encoders reached zero?
        if (has_left_drive_encoder_reset() && has_right_drive_encoder_reset ()) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // have_drive_encoders_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the lift encoder has been completely reset.
     */
    boolean has_lift_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the lift encoder reached zero?
        if (a_lift_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_lift_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the lift_arm encoder has been completely reset.
     */
    boolean has_lift_arm_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the lift_arm encoder reached zero?
        if (a_lift_arm_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_lift_arm_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the chain_hooks encoder has been completely reset.
     */
    boolean has_chain_hooks_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the chain_hooks encoder reached zero?
        if (a_chain_hooks_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_chain_hooks_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the spinner encoder has been completely reset.
     */
    boolean has_spinner_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the spinner encoder reached zero?
        if (a_spinner_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_spinner_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the bucket encoder has been completely reset.
     */
    boolean has_bucket_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the bucket encoder reached zero?
        if (a_bucket_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_bucket_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Indicate whether the sweeper encoder has been completely reset.
     */
    boolean has_sweeper_encoder_reset () {
        // Assume failure.
        boolean l_return = false;
        // Has the sweeper encoder reached zero?
        if (a_sweeper_encoder_count() == 0) {
            // Set the status to a positive indication.
            l_return = true;
        }
        // Return the status.
        return l_return;
    } // has_sweeper_encoder_reset
    //--------------------------------------------------------------------------
    /**
     * Access the bucket_door position.
     */
    double a_bucket_door_position () {
        double l_return = 0.5;

        if (v_servo_bucket_door != null)
            l_return = v_servo_bucket_door.getPosition ();

        return l_return;
    } // a_bucket_door_position
    //--------------------------------------------------------------------------
    /**
     * Access the hook position.
     */
    double a_hook_position () {
        double l_return = 0.5;

        if (v_servo_hook != null)
            l_return = v_servo_hook.getPosition ();

        return l_return;
    } // a_servo_bucket_door_position
    //--------------------------------------------------------------------------
    /**
     * Mutate all_servos position.
     */
    void m_set_all_servos_position (double p_bucket_door_position, double p_hook_position) {
        // Ensure the specific value is legal.
        double l_bucket_door_position = Range.clip( p_bucket_door_position, Servo.MIN_POSITION
                , Servo.MAX_POSITION);
        double l_hook_position = Range.clip( p_hook_position, Servo.MIN_POSITION, Servo.MAX_POSITION);
        // Set the value.
        if (v_servo_bucket_door != null)
            v_servo_bucket_door.setPosition (l_bucket_door_position);

        if (v_servo_hook != null)
            v_servo_hook.setPosition (l_hook_position);
    } // m_set_all_servos_position
    //--------------------------------------------------------------------------
    /**
     * Set all servos to starting position
     */
    void all_servos_starting_position () {
        double startingPosition = 0.5;
        // Set the value.
        if (v_servo_bucket_door != null)
            v_servo_bucket_door.setPosition (startingPosition);
        if (v_servo_hook != null)
            v_servo_hook.setPosition (startingPosition);
    } // all_servos_starting_position
    //--------------------------------------------------------------------------
    /**
     * Indicate whether a message is a available to the class user.
     */
    private boolean v_warning_generated = false;
    //--------------------------------------------------------------------------
    /**
     * Store a message to the user if one has been generated.
     */
    private String v_warning_message;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the left_drive motor.
     */
    private DcMotor v_motor_left_drive;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the right_drive motor.
     */
    private DcMotor v_motor_right_drive;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the lift_arm motor.
     */
    private DcMotor v_motor_lift_arm;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the lift motor.
     */
    private DcMotor v_motor_lift;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the chain_hooks motor.
     */
    private DcMotor v_motor_chain_hooks;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the spinner motor.
     */
    private DcMotor v_motor_spinner;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the bucket motor.
     */
    private DcMotor v_motor_bucket;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the sweeper motor.
     */
    private DcMotor v_motor_sweeper;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the bucket_door servo.
     */
    private Servo v_servo_bucket_door;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the hook servo.
     */
    private Servo v_servo_hook;
} // BigBerthaHardware
