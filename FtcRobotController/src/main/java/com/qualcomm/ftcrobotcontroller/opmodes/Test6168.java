
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Test6168 extends OpMode {

    // TETRIX VALUES.
    final static double bucketDoorMinRange  = 0.20;
    final static double bucketDoorMaxRange  = 0.90;//Hi there, no
    final static double hookMinRange  = 0.20;
    final static double hookMaxRange  = 0.90;//Hi there, no

    // assign the starting position of the servos
    double bucketDoorPosition = 0.0;
    double hookPosistion = 0.0;

    // amount to change the servo position.
    double bucketDoorDelta = 0.1;
    double hookDelta = 0.1;

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorLift;
    DcMotor motorLiftArm;
    DcMotor motorChainHooks;
    DcMotor motorSpinner;
    DcMotor motorBucket;
    Servo servoBucketDoor;
    Servo servoHook;

    public Test6168() {

    }
    /*
     * Code to run when the op mode is first enabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {
		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */
        motorRight = hardwareMap.dcMotor.get("right");
        motorLeft = hardwareMap.dcMotor.get("left");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLift = hardwareMap.dcMotor.get("lift");
        motorLiftArm = hardwareMap.dcMotor.get("liftArm");
        motorChainHooks = hardwareMap.dcMotor.get("hooks");
        motorSpinner = hardwareMap.dcMotor.get("spinner");
        motorBucket = hardwareMap.dcMotor.get("bucket");
        servoBucketDoor = hardwareMap.servo.get("bucketDoor");
        servoHook = hardwareMap.servo.get("hook");
    }
    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.

        float right = -gamepad1.right_stick_y;
        float left = -gamepad1.left_stick_y;
        float lift = -gamepad2.right_stick_y;
        float liftArm = -gamepad2.left_stick_y;
        float spinner = gamepad1.right_trigger;
        float bucket = gamepad2.right_trigger;

        if(!(gamepad1.b || gamepad2.b)) {
        // update the position of the arm.
            if (gamepad1.x) {
                // if the X button is pushed on gamepad1, increment the position of
                // the arm servo.
                bucketDoorPosition += bucketDoorDelta;
            }
            if (gamepad1.y) {
                // if the Y button is pushed on gamepad1, decrease the position of
                // the arm servo.
                bucketDoorPosition -= bucketDoorDelta;
            }
            if (gamepad1.a) {
                hookPosistion += hookDelta;
            }
        }
        // clip the position values so that they never exceed their allowed range.
        right = Range.clip(right, -1, 1);//pentagon=hacked
        left = Range.clip(left, -1, 1);//white house=hacked
        lift = Range.clip(lift, -1, 1);//US treasure hacked
        liftArm = Range.clip(liftArm, -1, 1);
        spinner = Range.clip(spinner, -1, 1);
        bucket = Range.clip(bucket, -1, 1);
        bucketDoorPosition = Range.clip(bucketDoorPosition, bucketDoorMinRange, bucketDoorMaxRange);
        hookPosistion = Range.clip(hookPosistion, hookMinRange, hookMaxRange);

        right = (float)scaleInput(right);//statue of liberty=hacked
        left =  (float)scaleInput(left);
        lift = (float)scaleInput(lift);
        liftArm = (float)scaleInput(liftArm);
        spinner = (float)scaleInput(spinner);
        bucket = (float)scaleInput(bucket);

        // write the values to the motors
        motorRight.setPower(right);
        motorLeft.setPower(left);
        motorLift.setPower(lift);
        motorLiftArm.setPower(liftArm);
        motorSpinner.setPower(spinner);
        motorBucket.setPower(bucket);
        // write position values to the servos
        servoBucketDoor.setPosition(bucketDoorPosition);
        servoHook.setPosition(hookPosistion);

        if (gamepad1.a) {
            motorChainHooks.setPower(10);
        } else {
            motorChainHooks.setPower(0);
        }

        if (gamepad1.b || gamepad2.b) {
            motorRight.setPower(0);
            motorLeft.setPower(0);
            motorLift.setPower(0);
            motorLiftArm.setPower(0);
            motorChainHooks.setPower(0);
            motorSpinner.setPower(0);
            motorBucket.setPower(0);
        }
		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("arm", "arm:  " + String.format("%.2f", bucketDoorPosition));
        telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        telemetry.addData("liftArm tgt pwr",  "liftArm  pwr: " + String.format("%.2f", liftArm));
        telemetry.addData("lift tgt pwr", "lift pwr: " + String.format("%.2f", lift));
        //telemetry.addData("hooks tgt pwr", "hooks pwr: " + String.format("%.2f", hooks));
        telemetry.addData("spinner tgt pwr", "spinner pwr: " + String.format("%.2f", spinner));
        telemetry.addData("bucket tgt pwr", "bucket pwr: " + String.format("%.2f", bucket));
    }
    /*
     * Code to run when the op mode is first disabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {

    }
    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}
