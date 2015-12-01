package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 * Created by spmce on 12/1/2015.
 */
/**
 * Provide a basic manual operational mode that uses the left and right
 * drive motors, six other motors, servo motors and gamepad input from two
 * gamepads for Big Bertha.
 *
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-01-06-01-----2015-12-01
 */
public class BigBerthaTeleOp extends BigBerthaTelemetry {
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaTeleOp () {
        // Initialize base classes.
        // All via self-construction.
        // Initialize class members.
        // All via self-construction.
    } // BigBerthaTeleOp
    /**
     * Implement a state machine that controls the robot during
     * manual-operation.  The state machine uses gamepad input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        //
        // DC Motors
        //
        // Obtain the current values of the joystick controllers.
        //
        // Note that x and y equal -1 when the joystick is pushed all of the way
        // forward (i.e. away from the human holder's body).
        //
        // The clip method guarantees the value never exceeds the range +-1.
        //
        // The DC motors are scaled to make it easier to control them at slower
        // speeds.
        //
        // The setPower methods write the motor power values to the DcMotor
        // class, but the power levels aren't applied until this method ends.
        //

        //
        // Manage the drive wheel motors.
        //
        float l_left_drive_power = scale_motor_power (-gamepad1.left_stick_y);
        float l_right_drive_power = scale_motor_power (-gamepad1.right_stick_y);

        float l_lift_arm_power = scale_motor_power (-gamepad2.right_stick_y);
        float l_spinner_power = scale_motor_power (gamepad2.right_trigger);
        float l_backSpinner_power = scale_motor_power (-gamepad2.left_trigger);
        float l_bucket_power = scale_motor_power (gamepad1.right_trigger);
        float l_backBucket_power = scale_motor_power (-gamepad1.left_trigger);
        float l_sweeper_power = scale_motor_power (-gamepad2.left_stick_y);

        set_drive_power (l_left_drive_power, l_right_drive_power);
        set_lift_arm_power (l_lift_arm_power);

        //
        // Manage the arm motor.
        //
        float l_left_arm_power = scale_motor_power (-gamepad2.left_stick_y);
        m_left_arm_power (l_left_arm_power);

        //----------------------------------------------------------------------
        //
        // Servo Motors
        //
        // Obtain the current values of the gamepad 'x' and 'b' buttons.
        //
        // Note that x and b buttons have boolean values of true and false.
        //
        // The clip method guarantees the value never exceeds the allowable range of
        // [0,1].
        //
        // The setPosition methods write the motor power values to the Servo
        // class, but the positions aren't applied until this method ends.
        //
        if (gamepad2.x)
        {
            m_hand_position (a_hand_position () + 0.05);
        }
        else if (gamepad2.b)
        {
            m_hand_position (a_hand_position () - 0.05);
        }

        //
        // Send telemetry data to the driver station.
        //
        update_telemetry (); // Update common telemetry
        update_gamepad_telemetry ();

    } // loop

} // BigBerthaTeleOp
