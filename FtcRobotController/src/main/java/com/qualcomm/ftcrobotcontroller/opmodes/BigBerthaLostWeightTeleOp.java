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
    private static int game1config = 0;
    private static int game2config = 0;
    private static boolean sweeperOff = false;
    private static boolean aux1ScaleOff = false;
    //public static boolean isSweeperOff () {return sweeperOff;}
    //public static boolean isAux1ScaleOff () {return aux1ScaleOff;}
    public static int getGame1config() {return game1config;}
    public static int getGame2config() {return game2config;}
    /**
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        // ------------DC Motors------------
        if (gamepad1.guide) {
            if (game1config == 0){
                sweeperOff = false;
                aux1ScaleOff = false;
                game1config = 1;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (game1config == 1) {
                sweeperOff = true;
                aux1ScaleOff = true;
                game1config = 2;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (game1config == 2) {
                sweeperOff = false;
                aux1ScaleOff = true;
                game1config = 3;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else {
                sweeperOff = true;
                aux1ScaleOff = false;
                game1config = 0;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (gamepad1.start) {
            sweeperOff = false;
            aux1ScaleOff = false;
            game1config = 0;
        }
        if (gamepad2.guide) {
            if (game2config == 0) {
                game2config = 1;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (game2config == 1) {
                game2config = 2;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (game2config == 2) {
                game2config = 3;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else {
                game2config = 0;
                try {
                    Thread.sleep(500);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (gamepad2.start) {
            game2config = 0;
        }

        float leftDrivePower = 0.0f;
        float rightDrivePower = 0.0f;
        float backLeftPower = 0.0f;
        float backRightPower = 0.0f;
        double chainHooksPower = 0.80;
        double liftPower = 0.8;
        float sweeperPower = 0.0f;
        float backSweeperPower = 0.0f;
        float sweeperPosition = 0.5f;
        float backSweeperPosition = 0.5f;

        float liftUpScale = 0.0f;
        float liftDownScale = 0.0f;
        float chainHooksUpScale = 0.0f;
        float chainHooksDownScale = 0.0f;

        if (game1config == 0) {
            leftDrivePower = scaleMotorPower(-gamepad1.left_stick_y/8*5);
            rightDrivePower = scaleMotorPower(-gamepad1.right_stick_y/8*5);
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                chainHooksUpScale = scaleMotorPower(gamepad1.right_trigger);
                clipMotorPositive(chainHooksUpScale = chainHooksUpScale / 4);
                chainHooksDownScale = scaleMotorPower(-gamepad1.left_trigger);
                clipMotorNegative(chainHooksDownScale = (chainHooksDownScale / 5) * 4);
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
        }
        if (game1config == 1) {
            leftDrivePower = scaleMotorPower(gamepad1.left_trigger/8*5);
            rightDrivePower = scaleMotorPower(gamepad1.right_trigger/8*5);
            if (gamepad1.left_bumper)
                leftDrivePower = -leftDrivePower;
            if (gamepad1.right_bumper)
                rightDrivePower = -rightDrivePower;
        }
        if (game1config == 2) {
            leftDrivePower = scaleMotorPower(-gamepad1.left_stick_y/8*5);
            rightDrivePower = scaleMotorPower(-gamepad1.right_stick_y/8*5);
        }
        if (game1config == 3) {
            leftDrivePower = scaleMotorPower(-gamepad1.left_stick_y/8*5);
            rightDrivePower = scaleMotorPower(-gamepad1.right_stick_y/8*5);
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                chainHooksUpScale = scaleMotorPower(gamepad1.right_trigger);
                clipMotorPositive(chainHooksUpScale = chainHooksUpScale / 4);
                chainHooksDownScale = scaleMotorPower(-gamepad1.left_trigger);
                clipMotorNegative(chainHooksDownScale = (chainHooksDownScale / 5) * 4);
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
        }

        if (game2config == 0) {
        }
        if (game2config == 1) {
        }
        if (game2config == 2) {
        }
        if (game2config == 3) {
        }
        // Obtain the current values of the joystick controllers.
        // The DC motors are scaled to make it easier to control them at slower speeds.
        // Note that x and y equal -1 when the joystick is pushed all of the way forward.
        backLeftPower = scaleMotorPower(-gamepad1.left_stick_y/8*5);
        backRightPower = scaleMotorPower(-gamepad1.right_stick_y/8*5);
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

        if (gamepad1.left_stick_button) {
            leftDrivePower = leftDrivePower/5*8;
            backLeftPower = backLeftPower/5*8;
        }
        if (gamepad1.right_stick_button) {
            rightDrivePower = rightDrivePower/5*8;
            backRightPower = backRightPower/5*8;
        }
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

        if (gamepad2.dpad_up) {
            double bucketPower = 0.8;
            double bucketPosition = 0.8;
            if (gamepad2.right_trigger > 0) {
                bucketPower = scaleMotorPower(gamepad2.right_trigger);
                bucketPosition = scaleServoPosition(gamepad2.right_trigger);
            }
            setBucketPower(bucketPower);
            setBucketPosition(bucketPosition);
        }
        else if (gamepad2.dpad_down) {
            double bucketPower = -0.8;
            double bucketPosition = -0.8;
            if (gamepad2.left_trigger < -0) {
                bucketPower = scaleMotorPower(-gamepad2.left_trigger);
                bucketPosition = scaleServoPosition(-gamepad2.left_trigger);
            }
            setBucketPower(bucketPower);
            setBucketPosition(bucketPosition);
        }
        else {
            setBucketPower(0);
            setBucketPosition(0);
        }
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
        //else
            //;setHookPosition(0.25);

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
        //else
            //setFlagPosition(0.75);
        ////////////////////////////---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------setHookPosition (0.5

        //------------Telemetry------------
        // Send telemetry data to the driver station.
        updateTelemetry(); // Update common telemetry
        updateGamepadTelemetry();
        telemetry.addData("73", "Gamepad 1 Config: " + game1config);
        telemetry.addData("74", "Gamepad 2 Config: " + game2config);
    } //--------------------------------------------------------------------------loop
} //------------------------------------------------------------------------------BigBerthaTeleOp
