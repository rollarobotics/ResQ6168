package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 * Created by spmce on 12/1/2015.
 */
/**
 * Provide telemetry provided by the PushBotHardware class.
 *
 * Insert this class between a custom op-mode and the PushBotHardware class to
 * display telemetry available from the hardware class.
 *
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-01-06-01-----2015-12-01
 *
 */
public class BigBerthaTelemetry extends BigBerthaHardware
{
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaTelemetry () {
        // Initialize base classes and class members.
        // All via self-construction.
    } // BigBerthaTelemetry
    /**
     * Update the telemetry with current values from the base class.
     */
    public void update_telemetry () {
        if (a_warning_generated ())
            set_first_message (a_warning_message ());
        // Send telemetry data to the driver station.
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("bucket door", "bucket door:  " + a_bucket_door_position());
        telemetry.addData("hook", "hook:  " + a_hook_position());
        telemetry.addData("left tgt pwr",  "left  pwr: " + a_left_drive_power() + ", " + a_left_encoder_count());
        telemetry.addData("right tgt pwr", "right pwr: " + a_right_drive_power () + ", " + a_right_encoder_count ());
        telemetry.addData("liftArm tgt pwr",  "liftArm  pwr: " + a_lift_arm_power() + ", " + a_lift_arm_encoder_count());
        telemetry.addData("lift tgt pwr", "lift pwr: " +  a_lift_arm_power() + ", " + a_lift_arm_encoder_count());
        telemetry.addData("chain hooks tgt pwr", "chain hooks pwr: " + a_chain_hooks_power() + ", " + a_chain_hooks_encoder_count());
        telemetry.addData("spinner tgt pwr", "spinner pwr: " + a_spinner_power() + ", " + a_spinner_encoder_count());
        telemetry.addData("bucket tgt pwr", "bucket pwr: " + a_bucket_power() + ", " + a_bucket_encoder_count());
        telemetry.addData("sweeper tgt pwr", "sweeper pwr: " + a_sweeper_power() + ", " + a_sweeper_encoder_count());
    } // update_telemetry
    /**
     * Update the telemetry with current gamepad readings.
     */
    public void update_gamepad_telemetry () {
        // Send telemetry data concerning gamepads to the driver station.
        telemetry.addData ("01", "Bucket Door GP1 Y: " + gamepad1.y);
        telemetry.addData ("02", "Bucket Door GP1 X: " + gamepad1.x);
        telemetry.addData ("03", "Hook GP2 B: " + gamepad1.b);
        telemetry.addData ("04", "Hook GP2 A: " + gamepad2.a);
        telemetry.addData ("05", "Left Drive GP1 Left Stick: " + -gamepad1.left_stick_y);
        telemetry.addData ("06", "Right Drive GP1 Right Stick: " + -gamepad1.right_stick_y);
        telemetry.addData ("07", "Lift Arm GP2 Left: " + -gamepad2.left_stick_y);
        telemetry.addData ("08", "Lift GP2 Dpad Up: " + gamepad2.dpad_up);
        telemetry.addData ("09", "Lift GP2 Dpad Down: " + gamepad2.dpad_down);
        telemetry.addData ("10", "Chain Hooks GP1 Left Bumper: " + gamepad1.left_bumper);
        telemetry.addData ("11", "Chain Hooks GP1 Right Bumper: " + gamepad1.right_bumper);
        telemetry.addData ("12", "Spinner GP2 Right Trigger: " + gamepad2.right_trigger);
        telemetry.addData ("13", "Reverse Spinner GP2 Left Trigger: " + -gamepad2.left_trigger);
        telemetry.addData ("14", "Bucket GP1 Right Trigger: " + gamepad1.right_trigger);
        telemetry.addData ("15", "Reverse Bucket GP1 Left Trigger: " + -gamepad1.left_trigger);
        telemetry.addData ("16", "Sweeper GP2 Left Stick: " + -gamepad2.left_stick_y);
    } // update_gamepad_telemetry
    /**
     * Update the telemetry's first message with the specified message.
     */
    public void set_first_message (String p_message) {telemetry.addData ( "00", p_message);} // set_first_message
    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void set_error_message (String p_message) {set_first_message ("ERROR: " + p_message);} // set_error_message
} // BigBerthaTelemetry
