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
        telemetry.addData ("01" , "Gamepad 1:");
        telemetry.addData ("02" , "Servos:");
        telemetry.addData ("03" , "Flag A: "       + gamepad1.a);
        telemetry.addData ("04" , "Flag B: "       + gamepad1.b);
        telemetry.addData ("05" , "Bucket Door Y: "+ gamepad1.y);
        telemetry.addData ("06" , "Bucket Door X: "+ gamepad1.x);
        telemetry.addData ("07" , "Motors:");
        telemetry.addData ("08" , "Right Drive Y Stick: "+zero(-gamepad1.right_stick_y));
        telemetry.addData ("09" , "Left Drive Y Stick: " +zero(-gamepad1.left_stick_y));
        telemetry.addData ("10" , "Right X Stick: "           + gamepad1.right_stick_x);
        telemetry.addData ("11" , "Left X Stick: "            + gamepad1.left_stick_x);
        telemetry.addData ("12" , "Dpad Up: "                 + gamepad1.dpad_up);
        telemetry.addData ("13" , "Dpad Down: "               + gamepad1.dpad_down);
        telemetry.addData ("14" , "Dpad Right: "              + gamepad1.dpad_right);
        telemetry.addData ("15" , "Dpad Left: "               + gamepad1.dpad_left);
        telemetry.addData ("16" , "Chain Right Bumper: "      + gamepad1.right_bumper);
        telemetry.addData ("17" , "Rev Chain Left Bumper: "   + gamepad1.left_bumper);
        telemetry.addData ("18" , "Sweeper Right Trigger: "   + gamepad1.right_trigger);
        telemetry.addData ("19" , "Rev Sweeper Left Trigger: "+-gamepad1.left_trigger);
        telemetry.addData ("20" , "Scale/Sweeper Off Start: " + gamepad1.start);
        telemetry.addData ("21" , "Scale/Sweeper Res Guide: " + gamepad1.guide);
        telemetry.addData ("22" , "Back: "                    + gamepad1.back);
        telemetry.addData ("23" , "Gamepad 2:");
        telemetry.addData ("24" , "Servos:");
        telemetry.addData ("25" , "Man A: " + gamepad2.a);
        telemetry.addData ("26" , "Man B: " + gamepad2.b);
        telemetry.addData ("27" , "Hook Y: "+ gamepad2.y);
        telemetry.addData ("28" , "Hook X: "+ gamepad2.x);
        telemetry.addData ("29" , "Motors:");
        telemetry.addData ("30" , "Right Arm Y Stick: "+zero(-gamepad2.right_stick_y));
        telemetry.addData ("31" , "Left Arm Y Stick: " +zero(-gamepad2.left_stick_y));
        telemetry.addData ("32" , "Right X Stick: "         + gamepad2.right_stick_x);
        telemetry.addData ("33" , "Left X Stick: "          + gamepad2.left_stick_x);
        telemetry.addData ("34" , "Bucket Dpad Up: "        + gamepad2.dpad_up);
        telemetry.addData ("35" , "Bucket Dpad Down: "      + gamepad2.dpad_down);
        telemetry.addData ("36" , "Dpad Right: "            + gamepad2.dpad_right);
        telemetry.addData ("37" , "Dpad Left: "             + gamepad2.dpad_left);
        telemetry.addData ("38" , "Rev Right Lift Bumper: " + gamepad2.right_bumper);
        telemetry.addData ("39" , "Rev Left Lift Bumper: "  + gamepad2.left_bumper);
        telemetry.addData ("40" , "Right Lift Trigger: "    + gamepad2.right_trigger);
        telemetry.addData ("41" , "Left Lift Trigger: "     +-gamepad2.left_trigger);
        telemetry.addData ("42" , "Start: "                 + gamepad2.start);
        telemetry.addData ("43" , "Guide: "                 + gamepad2.guide);
        telemetry.addData ("44" , "Back: "                  + gamepad2.back);
    } //--------------------------------------------------------------------------updateGamepadTelemetry
    /**
     * Update the telemetry with current values from the base class.
     */
    public void updateTelemetry () {
        if (getWarningGenerated())
            setFirstMessage(getWarningMessage());
        // Send telemetry data to the driver station.
        telemetry.addData ("45" , "");
        telemetry.addData ("46" , "Robot:");
        telemetry.addData ("47" , "Servo Position:");
        telemetry.addData ("48" , "Bucket Door Servo Position: "+ getBucketDoorPosition());
        telemetry.addData ("49" , "Hook Servo Position: "       + getHookPosition());
        telemetry.addData ("50" , "Man Servo Position: "        + getManPosition());
        telemetry.addData ("51" , "Flag Servo Position: "       + getFlagPosition());
        telemetry.addData ("52" , "Motor Power:");
        telemetry.addData ("53" , "Right Drive Power: "+ getRightDrivePower()+ ", " + getRightEncoderCount());
        telemetry.addData ("54" , "Left Drive Power: " + getLeftDrivePower() + ", " + getLeftEncoderCount());
        telemetry.addData ("55" , "Back Right Power: " + getBackRightPower() + ", " + getRightEncoderCount());
        telemetry.addData ("56" , "Back Left Power: "  + getBackLeftPower()  + ", " + getLeftEncoderCount());
        telemetry.addData ("57" , "Right Arm Power: "  + getRightArmPower()  + ", " + getLiftArmEncoderCount());
        telemetry.addData ("58" , "Left Arm Power: "   + getLeftArmPower()   + ", " + getLiftArmEncoderCount());
        telemetry.addData ("59" , "Right Lift Power: " + getRightLiftPower() + ", " + getLiftEncoderCount());
        telemetry.addData ("60" , "Left Lift Power: "  + getLeftLiftPower()  + ", " + getLiftEncoderCount());
        telemetry.addData ("61" , "Chain Hooks Power: "+ getChainHooksPower()+ ", " + getChainHooksEncoderCount());
        telemetry.addData ("62" , "Sweeper Power: "    + getSweeperPower()   + ", " + getSweeperEncoderCount());
        telemetry.addData ("63" , "Bucket Power: "     + getBucketPower()    + ", " + getBucketEncoderCount());
        telemetry.addData ("64" , "Spinner Power: "    + getSpinnerPower()   + ", " + getSpinnerEncoderCount());
        telemetry.addData ("65" , "Sweeper Off: "+ BigBerthaTeleOp.isSweeperOff());
        telemetry.addData ("66" , "Aux 1 Scale: "+ BigBerthaTeleOp.isAux1ScaleOff());
        telemetry.addData ("67" , "Bucket Off: " + BigBerthaTeleOp.isBucketOff());
        telemetry.addData ("68" , "Aux 2 Scale: "+ BigBerthaTeleOp.isAux2ScaleOff());
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
