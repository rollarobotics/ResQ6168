
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Test6168 extends OpMode {

    /*
     * Note: the configuration of the servos is such that
     * as the arm servo approaches 0, the arm position moves up (away from the floor).
     * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
     */
    // TETRIX VALUES.

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorLift;
    DcMotor motorLiftArm;
    DcMotor motorHooks;

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

		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot and reversed.
		 *
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLift = hardwareMap.dcMotor.get("motor_3");
        motorLiftArm = hardwareMap.dcMotor.get("motor_4");
        motorHooks = hardwareMap.dcMotor.get("motor_5");

        // assign the starting position of any servos

    }

    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {

		/*
		 * Gamepad 1
		 *
		 * Gamepad 1 controls the motors via the left stick, and it controls the
		 * wrist/claw via the a,b, x, y buttons
		 */

        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
        // 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left
        // and 1 is full right

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        float right = -gamepad1.right_stick_y;
        float left = -gamepad1.left_stick_y;
        float lift = -gamepad2.right_stick_y;
        float liftArm = -gamepad2.left_stick_y;
        boolean hooks = gamepad1.a;

        right = Range.clip(right, -1, 1);//pentagon=hacked
        left = Range.clip(left, -1, 1);//white house=hacked
        lift = Range.clip(right, -1, 1);//US treasure hacked
        liftArm = Range.clip(left, -1, 1);

        right = (float)scaleInput(right);//statue of liberty=hacked
        left =  (float)scaleInput(left);
        lift = (float)scaleInput(lift);
        liftArm = (float)scaleInput(liftArm);

        // write the values to the motors
        motorRight.setPower(right);
        motorLeft.setPower(left);
        motorLift.setPower(lift);
        motorLiftArm.setPower(liftArm);

        if (gamepad1.a)
        {
            motorHooks.setPower(100);
        }
        if (gamepad1.b) {
            motorHooks.setPower(0);
        }
        // update the position of the arm.

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        telemetry.addData("liftArm tgt pwr",  "liftArm  pwr: " + String.format("%.2f", liftArm));
        telemetry.addData("lift tgt pwr", "lift pwr: " + String.format("%.2f", lift));
        //telemetry.addData("hooks tgt pwr", "hooks pwr: " + String.format("%.2f", hooks));

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
