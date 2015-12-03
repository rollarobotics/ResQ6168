package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 * Created by spmce on 12/1/2015.
 */
/**
 *TeleOp for Big Bertha
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
        // Initialize base classes and class members.
        // All via self-construction.
    } // BigBerthaTeleOp
    /**
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        // DC Motors
        double chainHooks = 0.80;
        double lift = 1.0;
        // Obtain the current values of the joystick controllers.
        // The DC motors are scaled to make it easier to control them at slower speeds.
        // Note that x and y equal -1 when the joystick is pushed all of the way forward.
        float l_left_drive_power = scale_motor_power (-gamepad1.left_stick_y);
        float l_right_drive_power = scale_motor_power (-gamepad1.right_stick_y);
        float l_lift_arm_power = scale_motor_power (-gamepad2.right_stick_y);
        float l_spinner_power = scale_motor_power (gamepad2.right_trigger);
        float l_backSpinner_power = scale_motor_power (-gamepad2.left_trigger);
        float l_bucket_power = scale_motor_power (gamepad1.right_trigger);
        float l_backBucket_power = scale_motor_power (-gamepad1.left_trigger);
        float l_sweeper_power = scale_motor_power (-gamepad2.left_stick_y);
        // The setPower methods write the motor power values to the DcMotor
        // class, but the power levels aren't applied until this method ends.
        set_drive_power (l_left_drive_power, l_right_drive_power);
        set_lift_arm_power(l_lift_arm_power);
        set_spinner_power(l_spinner_power, l_backSpinner_power);
        set_bucket_power(l_bucket_power, l_backBucket_power);
        set_sweeper_power(l_sweeper_power);

        if (gamepad1.right_bumper)
            set_chain_hooks_power(chainHooks);
        else if (gamepad1.left_bumper)
            set_chain_hooks_power(-chainHooks);
        else
            set_chain_hooks_power (0);

        if (gamepad2.dpad_up)
            set_lift_power(lift);
        else if (gamepad2.dpad_down)
            set_lift_power(-lift);
        else
            set_lift_power(0);
        // Servo Motors
        // The m_position methods write the motor power values to the Servo
        // class, but the positions aren't applied until this method ends.
        if (gamepad1.y)
            m_bucket_door_position(a_bucket_door_position() + 0.5);
        else if (gamepad1.x)
            m_bucket_door_position(a_bucket_door_position() - 0.5);
        else
            m_bucket_door_position (a_bucket_door_position ());
        // Send telemetry data to the driver station.
        update_telemetry (); // Update common telemetry
        update_gamepad_telemetry ();
    } // loop
} // BigBerthaTeleOp
