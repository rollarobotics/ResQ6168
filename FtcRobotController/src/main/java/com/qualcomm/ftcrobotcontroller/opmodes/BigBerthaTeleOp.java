/**
 * Created by spmce on 12/1/2015.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 *TeleOp for Big Bertha
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-01-06-01-----2015-12-01
 */
public class BigBerthaTeleOp extends BigBerthaTelemetry {
    /**
     * Construct the class.
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaTeleOp () {
        // Initialize base classes and class members.
        // All via self-construction.
    } //--------------------------------------------------------------------------BigBerthaTeleOp
    @Override public void init () {
        hardwareInit ();
        initTelemetry();
    }
    /**
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        // ------------DC Motors------------
        double chainHooksPower = 0.80;
        double liftPower = 0.8;
        // Obtain the current values of the joystick controllers.
        // The DC motors are scaled to make it easier to control them at slower speeds.
        // Note that x and y equal -1 when the joystick is pushed all of the way forward.
        float leftDrivePower = scaleMotorPower (-gamepad1.left_stick_y);
        float rightDrivePower = scaleMotorPower (-gamepad1.right_stick_y);
        float liftArmPower = scaleMotorPower (-gamepad2.right_stick_y);
        float sweeperPower = scaleMotorPower (gamepad1.right_trigger);
        float backSweeperPower = scaleMotorPower (-gamepad1.left_trigger);
        float bucketPower = scaleMotorPower (gamepad2.right_trigger);
        float backBucketPower = scaleMotorPower (-gamepad2.left_trigger);
        float spinnerPower = scaleMotorPower (-gamepad2.left_stick_y);
        // The setPower methods write the motor power values to the DcMotor
        // class, but the power levels aren't applied until this method ends.
        setDrivePower (leftDrivePower, rightDrivePower);
        setLiftArmPower(liftArmPower);
        setSweeperPower(sweeperPower, backSweeperPower);
        setBucketPower(bucketPower, backBucketPower);
        setSpinnerPower(spinnerPower);

        if (gamepad1.right_bumper)
            setChainHooksPower(chainHooksPower);
        else if (gamepad1.left_bumper)
            setChainHooksPower(-chainHooksPower);
        else
            setChainHooksPower (0);

        if (gamepad2.dpad_up)
            setLiftPower(liftPower);
        else if (gamepad2.dpad_down)
            setLiftPower(-liftPower);
        else
            setLiftPower(0);
        //------------Servo Motors------------
        // The mPosition methods write the motor power values to the Servo
        // class, but the positions aren't applied until this method ends.
        if (gamepad1.y)
            setBucketDoorPosition (1.0); //1.0 is forward at full speed
        else if (gamepad1.x)
            setBucketDoorPosition (0.0); //0.0 is backward at full speed
        else
            setBucketDoorPosition (0.5); //0.5 is stopped

        if (gamepad2.y)
            setHookPosition (1.0);
        else if (gamepad2.x)
            setHookPosition (0.0);
        else
            setHookPosition(0.5);

        if (gamepad2.a)
            setManPosition (1.0);
        else if (gamepad2.b)
            setManPosition (0.0);
        else
            setManPosition(0.5);
            ////////////////////////////---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------setHookPosition (0.5

        //------------Telemetry------------
        // Send telemetry data to the driver station.
        updateTelemetry(); // Update common telemetry
        updateGamepadTelemetry ();
    } //--------------------------------------------------------------------------loop
} //------------------------------------------------------------------------------BigBerthaTeleOp
