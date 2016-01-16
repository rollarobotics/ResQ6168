/**
 * Created by spmce on 12/30/2015.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 *TeleOp for Big Bertha
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-01-06-01-----2015-12-30
 */
public class BigBerthaLostWeightTeleOp extends BigBerthaTelemetry {
    /**
     * Construct the class.
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaLostWeightTeleOp () {
        // Initialize base classes and class members.
        // All via self-construction.
    } //--------------------------------------------------------------------------BigBerthaTeleOp
    @Override public void init () {
        hardwareInit ();
        initTelemetry();
    }
    private static boolean sweeperOff = false;
    private static boolean aux1ScaleOff = false;
    public static boolean isSweeperOff () {return sweeperOff;}
    public static boolean isAux1ScaleOff () {return aux1ScaleOff;}
    /**
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        // ------------DC Motors------------
        if (gamepad1.guide && !gamepad1.start) {
            sweeperOff = true;
            aux1ScaleOff = false;
        }
        if (gamepad1.right_stick_button && gamepad1.left_stick_button) {
            sweeperOff = false;
            aux1ScaleOff = false;
        }
        if (!gamepad1.guide && gamepad1.start) {
            sweeperOff = false;
            aux1ScaleOff = true;
        }
        double chainHooksPower = 0.80;
        double liftPower = 0.8;
        float sweeperPower = 0.0f;
        float backSweeperPower = 0.0f;
        float sweeperPosition = 0.5f;
        float backSweeperPosition = 0.5f;
        // Obtain the current values of the joystick controllers.
        // The DC motors are scaled to make it easier to control them at slower speeds.
        // Note that x and y equal -1 when the joystick is pushed all of the way forward.
        float leftDrivePower = scaleMotorPower(-gamepad1.left_stick_y);
        float rightDrivePower = scaleMotorPower(-gamepad1.right_stick_y);
        float backLeftPower = scaleMotorPower(-gamepad1.left_stick_y);
        float backRightPower = scaleMotorPower(-gamepad1.right_stick_y);
        float leftArmPower = scaleMotorPower(-gamepad2.left_stick_y);
        float rightArmPower = scaleMotorPower(-gamepad2.right_stick_y);
        if ((!(gamepad1.right_bumper || gamepad1.left_bumper)) || aux1ScaleOff) {
            if (!sweeperOff) {
                sweeperPower = scaleMotorPower(gamepad1.right_trigger);
                backSweeperPower = scaleMotorPower(-gamepad1.left_trigger);
                sweeperPosition = scaleServoPosition((gamepad1.right_trigger + 1) / 2);
                backSweeperPosition = scaleServoPosition(-(gamepad1.left_trigger - 1) / 2);
            }
        }
        float leftLiftPower = scaleMotorPower(-gamepad2.left_trigger);
        if (gamepad2.left_bumper)
            leftLiftPower = -leftLiftPower;
        float rightLiftPower = scaleMotorPower(gamepad2.right_trigger);
        if (gamepad2.right_bumper)
            rightLiftPower = -rightLiftPower;

        if (gamepad1.left_stick_button)
            leftDrivePower = leftDrivePower / 4;
        if (gamepad1.right_stick_button)
            rightDrivePower = rightDrivePower / 4;
        if (gamepad1.left_stick_button)
            backLeftPower = leftDrivePower;
        if (gamepad1.right_stick_button)
            backRightPower = rightDrivePower;
        if (gamepad2.left_stick_button)
            leftArmPower = leftArmPower / 2;
        if (gamepad2.right_stick_button)
            rightArmPower = rightArmPower / 2;
        // The setPower methods write the motor power values to the DcMotor
        // class, but the power levels aren't applied until this method ends.
        setDrivePower(leftDrivePower, rightDrivePower, backLeftPower, backRightPower);
        setLiftPower(leftLiftPower, rightLiftPower);
        setSweeperPower(sweeperPower, backSweeperPower);
        setSweeperPosition(sweeperPosition, backSweeperPosition);
        setLiftArmPower(leftArmPower, rightArmPower);

        float liftUpScale = 0.0f;
        float liftDownScale = 0.0f;
        float chainHooksUpScale = 0.0f;
        float chainHooksDownScale = 0.0f;

        if (gamepad2.dpad_up) {
            setBucketPower(scaleMotorPower(gamepad2.right_trigger));
            setBucketPosition(scaleServoPosition(gamepad2.right_trigger));
        }
        else if (gamepad2.dpad_down) {
            setBucketPower(scaleMotorPower(-gamepad2.left_trigger));
            setBucketPosition(scaleServoPosition(-gamepad2.left_trigger));
        }
        else {
            setBucketPower(0);
            setBucketPosition(0);
        }
        if (!aux1ScaleOff) {
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                chainHooksUpScale = scaleMotorPower(gamepad1.right_trigger);
                clipMotorPositive(chainHooksUpScale = chainHooksUpScale / 4);
            }
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                chainHooksDownScale = scaleMotorPower(-gamepad1.left_trigger);
                clipMotorNegative(chainHooksDownScale = (chainHooksDownScale / 5) * 4);
            }
        }
        if (gamepad1.right_bumper)
            chainHooksPower = clipMotorPositive(chainHooksPower + chainHooksUpScale + chainHooksDownScale);
        if (gamepad1.left_bumper)
            chainHooksPower = clipMotorPositive(chainHooksPower + chainHooksUpScale + chainHooksDownScale);

        if (gamepad1.right_bumper)
            setChainHooksPower(chainHooksPower);
        else if (gamepad1.left_bumper)
            setChainHooksPower(-chainHooksPower);
        else
            setChainHooksPower (0);
        //------------Servo Motors------------
        // The mPosition methods write the motor power values to the Servo
        // class, but the positions aren't applied until this method ends.
        if (gamepad2.y)
            setBucketDoorPosition (1.0); //1.0 is forward at full speed
        else if (gamepad2.x)
            setBucketDoorPosition (0.0); //0.0 is backward at full speed
        else
            setBucketDoorPosition (0.5); //0.5 is stopped

        if (gamepad1.y)
            setHookPosition (1.0);
        else if (gamepad1.x)
            setHookPosition (0.0);
        else
            setHookPosition(0.5);

        if (gamepad2.b)
            setManPosition (1.0);
        else if (gamepad2.a)
            setManPosition (0.0);
        else
            setManPosition(0.5);

        if (gamepad1.b)
            setFlagPosition (1.0);
        else if (gamepad1.a)
            setFlagPosition (0.0);
        else
            setFlagPosition(0.5);
        ////////////////////////////---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------setHookPosition (0.5

        //------------Telemetry------------
        // Send telemetry data to the driver station.
        updateTelemetry(); // Update common telemetry
        updateGamepadTelemetry ();
    } //--------------------------------------------------------------------------loop
} //------------------------------------------------------------------------------BigBerthaTeleOp
