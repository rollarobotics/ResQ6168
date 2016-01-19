/**
 * Created by spmce on 12/1/2015.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.Gamepad;

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
        telemetry.addData("01" , "Init Bucket Door Servo Position: "+ getBucketDoorPosition());
        telemetry.addData("02" , "Init Hook Servo Position: "       +  getHookPosition());
        telemetry.addData("03" , "Init Man Servo Position: "        + getManPosition());
        telemetry.addData("04" , "Init Left Drive Power: " + getLeftDrivePower());
        telemetry.addData("05" , "Init Right Drive Power: "+ getRightDrivePower());
        telemetry.addData("06" , "Init Lift Arm Power: "   + getLiftArmPower());
        telemetry.addData("07" , "Init Lift Power: "       + getLiftPower());
        telemetry.addData("08" , "Init Chain Hooks Power: "+ getChainHooksPower());
        telemetry.addData("09" , "Init Sweeper Power: "    + getSweeperPower());
        telemetry.addData("10" , "Init Bucket Power: "     + getBucketPower());
        telemetry.addData("11" , "Init Spinner Power: "    + getSpinnerPower());
    }
    /**
     * Update the telemetry with current gamepad readings.
     */
    public double zero (double value) {
        if (value == -0.0)
            return 0.0;
        return value;
    }
    /**
     * Update the telemetry with current values from the base class.
     */
    public void updateTelemetry () {
        if (getDriveWarningGenerated())
            setDriveFirstMessage(getDriveWarningMessage());
        if (getWarningGenerated())
            setFirstMessage(getWarningMessage());
        String game1 = gamepad1.toString();
        String game2 = gamepad2.toString();
        telemetry.addData("01" , "Robot:");
        telemetry.addData("02" , " ");
        telemetry.addData("03" , "Gamepad 1 Configuration: " + BigBerthaLostWeightTeleOp.getGame1config() + " " + game1);
        telemetry.addData("04" , " ");
        telemetry.addData("05" , "Gamepad 2 Configuration: " + BigBerthaLostWeightTeleOp.getGame2config() + " " + game2);
        telemetry.addData("06" , " ");
        telemetry.addData("07" , "Servo Position:");
        telemetry.addData("08" , "Bucket Door Servo Position: "+ getBucketDoorPosition());
        telemetry.addData("09" , "Hook Servo Position: "       + getHookPosition());
        telemetry.addData("10" , "Man Servo Position: "        + getManPosition());
        telemetry.addData("11" , "Flag Servo Position: "       + getFlagPosition());
        telemetry.addData("12" , "Motor Power:");
        telemetry.addData("13" , "Right Drive Power: "+ getRightDrivePower()+ ", " + getRightEncoderCount());
        telemetry.addData("14" , "Left Drive Power: " + getLeftDrivePower() + ", " + getLeftEncoderCount());
        telemetry.addData("15" , "Back Right Power: " + getBackRightPower() + ", " + getRightEncoderCount());
        telemetry.addData("16" , "Back Left Power: "  + getBackLeftPower()  + ", " + getLeftEncoderCount());
        telemetry.addData("17" , "Right Arm Power: "  + getRightArmPower()  + ", " + getLiftArmEncoderCount());
        telemetry.addData("18" , "Left Arm Power: "   + getLeftArmPower()   + ", " + getLiftArmEncoderCount());
        telemetry.addData("19" , "Right Lift Power: " + getRightLiftPower() + ", " + getLiftEncoderCount());
        telemetry.addData("20" , "Left Lift Power: "  + getLeftLiftPower()  + ", " + getLiftEncoderCount());
        telemetry.addData("21" , "Chain Hooks Power: "+ getChainHooksPower()+ ", " + getChainHooksEncoderCount());
        telemetry.addData("22" , "Sweeper Power: "    + getSweeperPower()   + ", " + getSweeperEncoderCount());
        telemetry.addData("23" , "Bucket Power: "     + getBucketPower()    + ", " + getBucketEncoderCount());
        telemetry.addData("24" , "Spinner Power: "    + getSpinnerPower()   + ", " + getSpinnerEncoderCount());
        //telemetry.addData("22" , "Sweeper Off: "+ BigBerthaLostWeightTeleOp.isSweeperOff());
        //telemetry.addData("23" , "Aux 1 Scale: "+ BigBerthaTeleOp.isAux1ScaleOff());
        //telemetry.addData("24" , "Bucket Off: " + BigBerthaTeleOp.isBucketOff());
        //telemetry.addData("25" , "Aux 2 Scale: "+ BigBerthaTeleOp.isAux2ScaleOff());
    } //--------------------------------------------------------------------------updateTelemetry

    public void updateGamepadTelemetry () {
        // Send telemetry data concerning gamepads to the driver station.
        telemetry.addData("25" , " ");
        telemetry.addData("26" , " ");
        telemetry.addData("27" , "Robot Controllers: ");
        telemetry.addData("28" , "Gamepad 1: ");
        telemetry.addData("29" , "Servos:");
        telemetry.addData("30" , "Flag A: "       + gamepad1.a);
        telemetry.addData("31" , "Flag B: "       + gamepad1.b);
        telemetry.addData("32" , "Bucket Door Y: "+ gamepad1.y);
        telemetry.addData("33" , "Bucket Door X: "+ gamepad1.x);
        telemetry.addData("34" , "Motors:");
        telemetry.addData("35" , "Right Drive Y Stick: "+zero(-gamepad1.right_stick_y));
        telemetry.addData("36" , "Left Drive Y Stick: " +zero(-gamepad1.left_stick_y));
        telemetry.addData("37" , "Right X Stick: "           + gamepad1.right_stick_x);
        telemetry.addData("38" , "Left X Stick: "            + gamepad1.left_stick_x);
        telemetry.addData("39" , "Dpad Up: "                 + gamepad1.dpad_up);
        telemetry.addData("40" , "Dpad Down: "               + gamepad1.dpad_down);
        telemetry.addData("41" , "Dpad Right: "              + gamepad1.dpad_right);
        telemetry.addData("42" , "Dpad Left: "               + gamepad1.dpad_left);
        telemetry.addData("43" , "Chain Right Bumper: "      + gamepad1.right_bumper);
        telemetry.addData("44" , "Rev Chain Left Bumper: "   + gamepad1.left_bumper);
        telemetry.addData("45" , "Sweeper Right Trigger: "   + gamepad1.right_trigger);
        telemetry.addData("46" , "Rev Sweeper Left Trigger: "+-gamepad1.left_trigger);
        telemetry.addData("47" , "Scale/Sweeper Off Start: " + gamepad1.start);
        telemetry.addData("48" , "Scale/Sweeper Res Guide: " + gamepad1.guide);
        telemetry.addData("49" , "Back: "                    + gamepad1.back);
        telemetry.addData("50" , "Gamepad 2:");
        telemetry.addData("51" , " ");
        telemetry.addData("52" , "Servos:");
        telemetry.addData("53" , "Man A: " + gamepad2.a);
        telemetry.addData("54" , "Man B: " + gamepad2.b);
        telemetry.addData("55" , "Hook Y: "+ gamepad2.y);
        telemetry.addData("56" , "Hook X: "+ gamepad2.x);
        telemetry.addData("57" , "Motors:");
        telemetry.addData("58" , "Right Arm Y Stick: "+zero(-gamepad2.right_stick_y));
        telemetry.addData("59" , "Left Arm Y Stick: " +zero(-gamepad2.left_stick_y));
        telemetry.addData("60" , "Right X Stick: "         + gamepad2.right_stick_x);
        telemetry.addData("61" , "Left X Stick: "          + gamepad2.left_stick_x);
        telemetry.addData("62" , "Bucket Dpad Up: "        + gamepad2.dpad_up);
        telemetry.addData("63" , "Bucket Dpad Down: "      + gamepad2.dpad_down);
        telemetry.addData("64" , "Dpad Right: "            + gamepad2.dpad_right);
        telemetry.addData("65" , "Dpad Left: "             + gamepad2.dpad_left);
        telemetry.addData("66" , "Rev Right Lift Bumper: " + gamepad2.right_bumper);
        telemetry.addData("67" , "Rev Left Lift Bumper: "  + gamepad2.left_bumper);
        telemetry.addData("68" , "Right Lift Trigger: "    + gamepad2.right_trigger);
        telemetry.addData("69" , "Left Lift Trigger: "     +-gamepad2.left_trigger);
        telemetry.addData("70" , "Start: "                 + gamepad2.start);
        telemetry.addData("71" , "Guide: "                 + gamepad2.guide);
        telemetry.addData("72" , "Back: "                  + gamepad2.back);
    } //--------------------------------------------------------------------------updateGamepadTelemetry
    /**
     * Update the telemetry's first message with the specified message.
     */
    public void setFirstMessage (String pMessage) {telemetry.addData("00", pMessage);}
    public void setDriveFirstMessage (String pMessage) {telemetry.addData("001", pMessage);}
    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void setErrorMessage (String pMessage) {setFirstMessage("ERROR: " + pMessage);}
} //------------------------------------------------------------------------------BigBerthaTelemetry
