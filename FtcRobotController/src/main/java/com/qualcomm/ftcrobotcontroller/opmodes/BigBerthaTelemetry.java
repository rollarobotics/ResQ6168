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
    public void initTelemetry () {
        telemetry.addData("01 Init Bucket Door Servo Position", getBucketDoorPosition());
        telemetry.addData("02 Init Hook Servo Position"       , getHookPosition());
        telemetry.addData("03 Init Man Servo Position"        , getManPosition());
        telemetry.addData("04 Init Left Drive Power" , getLeftDrivePower());
        telemetry.addData("05 Init Right Drive Power", getRightDrivePower());
        telemetry.addData("06 Init Lift Arm Power"   , getLiftArmPower());
        telemetry.addData("07 Init Lift Power"       , getLiftPower());
        telemetry.addData("08 Init Chain Hooks Power", getChainHooksPower());
        telemetry.addData("09 Init Sweeper Power"    , getSweeperPower());
        telemetry.addData("10 Init Bucket Power"     , getBucketPower());
        telemetry.addData("11 Init Spinner Power"    , getSpinnerPower());
    }
    /**
     * Update the telemetry with current gamepad readings.
     */
    public double zero (double value) {
        if (value == -0.0)
            return 0.0;
        return value;
    }
    public void updateGamepadTelemetry () {
        // Send telemetry data concerning gamepads to the driver station.
        telemetry.addData ("01" , "Bucket Door GP1 Y: "+ gamepad1.y);
        telemetry.addData ("02" , "Bucket Door GP1 X: "+ gamepad1.x);
        telemetry.addData ("03" , "Hook GP2 Y: "       + gamepad2.y);
        telemetry.addData ("04" , "Hook GP2 X: "       + gamepad2.x);
        telemetry.addData ("05" , "Man GP2 A: "        + gamepad2.a);
        telemetry.addData ("06" , "Man GP2 B: "        + gamepad2.b);
        telemetry.addData ("07" , "Right GP1 Right Stick: "   +zero(-gamepad1.right_stick_y));
        telemetry.addData ("08" , "Left GP1 Left Stick: "     +zero(-gamepad1.left_stick_y));
        telemetry.addData ("09" , "Lift Arm GP2 Right Stick: "+zero(-gamepad2.right_stick_y));
        telemetry.addData ("10" , "Lift GP2 Dpad Up: "             + gamepad2.dpad_up);
        telemetry.addData ("11" , "Lift GP2 Dpad Down: "           + gamepad2.dpad_down);
        telemetry.addData ("12" , "Chain Hooks GP1 Right Bumper: " + gamepad1.right_bumper);
        telemetry.addData ("13" , "Chain Hooks GP1 Left Bumper: "  + gamepad1.left_bumper);
        telemetry.addData ("14" , "Sweeper GP1 Right Trigger: "    + gamepad1.right_trigger);
        telemetry.addData ("15" , "Back Sweeper GP1 Left Trigger: "+-gamepad1.left_trigger);
        telemetry.addData ("16" , "Bucket GP2 Right Trigger: "     + gamepad2.right_trigger);
        telemetry.addData ("17" , "Back Bucket GP2 Left Trigger: " +-gamepad2.left_trigger);
        telemetry.addData ("18" , "Spinner GP2 Left Stick: "  +zero(-gamepad2.left_stick_y));
    } //--------------------------------------------------------------------------updateGamepadTelemetry
    /**
     * Update the telemetry with current values from the base class.
     */
    public void updateTelemetry () {
        if (getWarningGenerated())
            setFirstMessage(getWarningMessage());
        // Send telemetry data to the driver station.
        telemetry.addData ("19" , "Bucket Door Servo Position: "+ getBucketDoorPosition());
        telemetry.addData ("20" , "Hook Servo Position: "       + getHookPosition());
        telemetry.addData ("21" , "Man Servo Position: "        + getManPosition());
        telemetry.addData ("22" , "Right Drive Power: "+ getRightDrivePower()+ ", " + getRightEncoderCount());
        telemetry.addData ("23" , "Left Drive Power: " + getLeftDrivePower() + ", " + getLeftEncoderCount());
        telemetry.addData ("24" , "Lift Arm Power: "   + getLiftArmPower()   + ", " + getLiftArmEncoderCount());
        telemetry.addData ("25" , "Lift Power: "       + getLiftPower()      + ", " + getLiftEncoderCount());
        telemetry.addData ("26" , "Chain Hooks Power: "+ getChainHooksPower()+ ", " + getChainHooksEncoderCount());
        telemetry.addData ("27" , "Sweeper Power: "    + getSweeperPower()   + ", " + getSweeperEncoderCount());
        telemetry.addData ("28" , "Bucket Power: "     + getBucketPower() + ", " + getBucketEncoderCount());
        telemetry.addData ("29" , "Spinner Power: "    + getSpinnerPower()   + ", " + getSpinnerEncoderCount());
        telemetry.addData ("30" , "Sweeper Off: " + BigBerthaTeleOp.isSweeperOff());
        telemetry.addData ("31" , "Aux 1 Scale: " + BigBerthaTeleOp.isAux1ScaleOff());
        telemetry.addData ("32" , "Bucket Off: " + BigBerthaTeleOp.isBucketOff());
        telemetry.addData ("33" , "Aux 2 Scale: " + BigBerthaTeleOp.isAux2ScaleOff());
        telemetry.addData ("34" , "back1: " + gamepad1.back);
        telemetry.addData ("35" , "back2: " + gamepad2.back);
    } //--------------------------------------------------------------------------updateTelemetry
    /**
     * Update the telemetry's first message with the specified message.
     */
    public void setFirstMessage (String pMessage) {telemetry.addData("00", pMessage);}
    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void setErrorMessage (String pMessage) {setFirstMessage("ERROR: " + pMessage);}
} //------------------------------------------------------------------------------BigBerthaTelemetry
