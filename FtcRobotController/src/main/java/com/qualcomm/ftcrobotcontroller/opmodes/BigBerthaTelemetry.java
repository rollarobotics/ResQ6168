/**
 * Created by spmce on 12/1/2015.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 * Provide telemetry provided by the BigBerthaHardware class.
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-02-13-57-----2015-12-01
 */
public class BigBerthaTelemetry extends BigBerthaHardware
{
    /**
     * Construct the class.
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaTelemetry () {
        // Initialize base classes and class members.
        // All via self-construction.
    } //--------------------------------------------------------------------------BigBerthaTelemetry
    /**
     * Update the telemetry with current values from the base class.
     */
    public void updateTelemetry () {
        if (getWarningGenerated())
             setFirstMessage(getWarningMessage());
        // Send telemetry data to the driver station.
        telemetry.addData("17 Bucket Door Servo Position" , getBucketDoorPosition());
        telemetry.addData("18 Hook Servo Position"        , getHookPosition());
        telemetry.addData("19 Left Drive Power" , getLeftDrivePower() + ", " + getLeftEncoderCount());
        telemetry.addData("20 Right Drive Power", getRightDrivePower()+ ", " + getRightEncoderCount());
        telemetry.addData("21 Lift Arm Power"   , getLiftArmPower()   + ", " + getLiftArmEncoderCount());
        telemetry.addData("22 Lift Power"       , getLiftPower()      + ", " + getLiftEncoderCount());
        telemetry.addData("23 Chain Hooks Power", getChainHooksPower()+ ", " + getChainHooksEncoderCount());
        telemetry.addData("24 Spinner Power"    , getSpinnerPower()   + ", " + getSpinnerEncoderCount());
        telemetry.addData("25 Bucket Power"     , getBucketPower()    + ", " + getBucketEncoderCount() + ", " + BigBerthaTeleOp.getBucketValue());
        telemetry.addData("26 Sweeper Power"    , getSweeperPower()   + ", " + getSweeperEncoderCount());
    } //--------------------------------------------------------------------------updateTelemetry
    /**
     * Update the telemetry with current gamepad readings.
     */
    public void updateGamepadTelemetry () {
        // Send telemetry data concerning gamepads to the driver station.
        telemetry.addData ("01 Bucket Door GP1 Y" , gamepad1.y);
        telemetry.addData ("02 Bucket Door GP1 X" , gamepad1.x);
        telemetry.addData ("03 Hook GP2 Y"        , gamepad2.y);
        telemetry.addData ("04 Hook GP2 X"        , gamepad2.x);
        telemetry.addData ("05 Left Drive GP1 Left Stick"        ,-gamepad1.left_stick_y);
        telemetry.addData ("06 Right Drive GP1 Right Stick"      ,-gamepad1.right_stick_y);
        telemetry.addData ("07 Lift Arm GP2 Right Stick"         , gamepad2.right_stick_y);
        telemetry.addData ("08 Lift GP2 Dpad Up"                 , gamepad2.dpad_up);
        telemetry.addData ("09 Lift GP2 Dpad Down"               , gamepad2.dpad_down);
        telemetry.addData ("10 Chain Hooks GP1 Left Bumper"      , gamepad1.left_bumper);
        telemetry.addData ("11 Chain Hooks GP1 Right Bumper"     , gamepad1.right_bumper);
        telemetry.addData ("12 Spinner GP2 Right Trigger"        , gamepad2.right_trigger);
        telemetry.addData ("13 Reverse Spinner GP2 Left Trigger" ,-gamepad2.left_trigger);
        telemetry.addData ("14 Bucket GP1 Right Trigger"         , gamepad1.right_trigger);
        telemetry.addData ("15 Reverse Bucket GP1 Left Trigger"  ,-gamepad1.left_trigger);
        telemetry.addData ("16 Sweeper GP2 Left Stick"           ,-gamepad2.left_stick_y);
    } //--------------------------------------------------------------------------updateGamepadTelemetry
    /**
     * Update the telemetry's first message with the specified message.
     */
    public void setFirstMessage (String pMessage) {telemetry.addData("00", pMessage);}
    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void setErrorMessage (String pMessage) {setFirstMessage("ERROR: " + pMessage);}
} //------------------------------------------------------------------------------BigBerthaTelemetry
