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
        telemetry.addData ("01" , "Flag GP1 A: "       + gamepad1.a);
        telemetry.addData ("02" , "Flag GP1 B: "       + gamepad1.b);
        telemetry.addData ("03" , "Bucket Door GP1 Y: "+ gamepad1.y);
        telemetry.addData ("04" , "Bucket Door GP1 X: "+ gamepad1.x);
        telemetry.addData ("05" , "Man GP2 A: "        + gamepad2.a);
        telemetry.addData ("06" , "Man GP2 B: "        + gamepad2.b);
        telemetry.addData ("07" , "Hook GP2 Y: "       + gamepad2.y);
        telemetry.addData ("08" , "Hook GP2 X: "       + gamepad2.x);
        telemetry.addData ("09" , "Right GP1 Right Stick: "      +zero(-gamepad1.right_stick_y));
        telemetry.addData ("10" , "Left GP1 Left Stick: "        +zero(-gamepad1.left_stick_y));
        telemetry.addData ("11" , "Right Arm GP2 Right Stick: "  +zero(-gamepad2.right_stick_y));
        telemetry.addData ("12" , "Left Arm GP2 Left Stick:  "   +zero(-gamepad2.left_stick_y));
        telemetry.addData ("13" , "Bucket GP2 Dpad Up: "              + gamepad2.dpad_up);
        telemetry.addData ("14" , "Bucket GP2 Dpad Down: "            + gamepad2.dpad_down);
        telemetry.addData ("15" , "Chain Hooks GP1 Right Bumper: "    + gamepad1.right_bumper);
        telemetry.addData ("16" , "Chain Hooks GP1 Left Bumper: "     + gamepad1.left_bumper);
        telemetry.addData ("17" , "Sweeper GP1 Right Trigger: "       + gamepad1.right_trigger);
        telemetry.addData ("18" , "Back Sweeper GP1 Left Trigger: "   +-gamepad1.left_trigger);
        telemetry.addData ("19" , "Right Lift GP2 Right Trigger: "    + gamepad2.right_trigger);
        telemetry.addData ("20" , "Left Lift GP2 Left Trigger: "      +-gamepad2.left_trigger);
        telemetry.addData ("21" , "Back Right Lift GP2 Right Bumper: "+ gamepad2.right_bumper);
        telemetry.addData ("22" , "Back Left Lift GP2 Left Bumper: "  + gamepad2.left_bumper);
    } //--------------------------------------------------------------------------updateGamepadTelemetry
    /**
     * Update the telemetry with current values from the base class.
     */
    public void updateTelemetry () {
        if (getWarningGenerated())
            setFirstMessage(getWarningMessage());
        // Send telemetry data to the driver station.
        telemetry.addData ("23" , "Bucket Door Servo Position: "+ getBucketDoorPosition());
        telemetry.addData ("24" , "Hook Servo Position: "       + getHookPosition());
        telemetry.addData ("25" , "Man Servo Position: "        + getManPosition());
        telemetry.addData ("26" , "Flag Servo Position: "       + getFlagPosition());
        telemetry.addData ("27" , "Right Drive Power: "+ getRightDrivePower()+ ", " + getRightEncoderCount());
        telemetry.addData ("28" , "Left Drive Power: " + getLeftDrivePower() + ", " + getLeftEncoderCount());
        telemetry.addData ("29" , "Back Right Power: " + getBackRightPower() + ", " + getRightEncoderCount());
        telemetry.addData ("30" , "Back Left Power: "  + getBackLeftPower()  + ", " + getLeftEncoderCount());
        telemetry.addData ("31" , "Right Arm Power: "  + getRightArmPower()  + ", " + getLiftArmEncoderCount());
        telemetry.addData ("32" , "Left Arm Power: "   + getLeftArmPower()   + ", " + getLiftArmEncoderCount());
        telemetry.addData ("33" , "Right Lift Power: " + getRightLiftPower() + ", " + getLiftEncoderCount());
        telemetry.addData ("34" , "Left Lift Power: "  + getLeftLiftPower()
                + ", " + getLiftEncoderCount());
        telemetry.addData ("35" , "Chain Hooks Power: "+ getChainHooksPower()+ ", " + getChainHooksEncoderCount());
        telemetry.addData ("36" , "Sweeper Power: "    + getSweeperPower()   + ", " + getSweeperEncoderCount());
        telemetry.addData ("37" , "Bucket Power: "     + getBucketPower()    + ", " + getBucketEncoderCount());
        telemetry.addData ("38" , "Spinner Power: "    + getSpinnerPower()   + ", " + getSpinnerEncoderCount());
        telemetry.addData ("39" , "Sweeper Off: " + BigBerthaTeleOp.isSweeperOff());
        telemetry.addData ("40" , "Aux 1 Scale: " + BigBerthaTeleOp.isAux1ScaleOff());
        telemetry.addData ("41" , "Bucket Off: "  + BigBerthaTeleOp.isBucketOff());
        telemetry.addData ("42" , "Aux 2 Scale: " + BigBerthaTeleOp.isAux2ScaleOff());
        telemetry.addData ("43" , "back1: " + gamepad1.back);
        telemetry.addData ("44" , "back2: " + gamepad2.back);
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
