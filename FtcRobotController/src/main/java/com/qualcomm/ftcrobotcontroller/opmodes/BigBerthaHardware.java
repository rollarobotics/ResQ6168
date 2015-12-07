/**
 * Created by spmce on 12/1/2015.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Provides a single hardware access point between custom op-modes and the OpMode class for BigBertha.
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-13-20-04-----2015-12-01
 */
public class BigBerthaHardware extends OpMode {
    /**
     * Construct the class.
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaHardware () {
        // Initialize base classes and class members.
        // All via self-construction.
    } //--------------------------------------------------------------------------BigBerthaHardware
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void init () {
        // Use the hardwareMap to associate class members to hardware ports.
        // The variable below is used to provide telemetry data to a class user.
        warningGenerated = false;
        warningMessage = "Can't map; ";
        // This class prevents the custom op-mode from throwing an exception at runtime.
        // If any hardware fails to map, a warning will be shown via telemetry data,
        // calls to methods will fail, but will not cause the application to crash.
        //------------DcMotors------------
        try {motorLeftDrive = hardwareMap.dcMotor.get ("left");}
        catch (Exception opModeException) {
            setWarningMessage("leftDrive");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorLeftDrive = null;
        } //------------------------------------------------------------leftDrive
        try {
            motorRightDrive = hardwareMap.dcMotor.get ("right");
            motorRightDrive.setDirection (DcMotor.Direction.REVERSE);
            // The direction of the right motor is reversed, so joystick inputs can
            // be more generically applied.
        } catch (Exception opModeException) {
            setWarningMessage("rightDrive");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorRightDrive = null;
        } //------------------------------------------------------------rightDrive
        try {motorLiftArm = hardwareMap.dcMotor.get ("liftArm");}
        catch (Exception opModeException) {
            setWarningMessage("leftArm");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorLiftArm = null;
        } //------------------------------------------------------------liftArm
        try {motorLift = hardwareMap.dcMotor.get ("lift");}
        catch (Exception opModeException) {
            setWarningMessage("lift");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorLift = null;
        } //------------------------------------------------------------lift
        try {motorChainHooks = hardwareMap.dcMotor.get ("chainHooks");}
        catch (Exception opModeException) {
            setWarningMessage("chainHooks");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorChainHooks = null;
        } //------------------------------------------------------------chainHooks
        try {motorSpinner = hardwareMap.dcMotor.get ("spinner");}
        catch (Exception opModeException) {
            setWarningMessage("spinner");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorSpinner = null;
        } //------------------------------------------------------------spinner
        try {motorBucket = hardwareMap.dcMotor.get ("bucket");}
        catch (Exception opModeException) {
            setWarningMessage("bucket");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorBucket = null;
        } //------------------------------------------------------------bucket
        try {motorSweeper = hardwareMap.dcMotor.get ("sweeper");}
        catch (Exception opModeException) {
            setWarningMessage("sweeper");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            motorSweeper = null;
        } //------------------------------------------------------------sweeper
        if (motorLeftDrive != null)
            motorLeftDrive.setPower(0);
        if (motorRightDrive != null)
            motorRightDrive.setPower(0);
        if (motorLift != null)
            motorLift.setPower(0);
        if (motorLiftArm != null)
            motorLiftArm.setPower(0);
        if (motorChainHooks != null)
            motorChainHooks.setPower(0);
        if (motorSpinner != null)
            motorSpinner.setPower(0);
        if (motorBucket != null)
            motorBucket.setPower(0);
        if (motorSweeper != null)
            motorSweeper.setPower(0);
        //------------Servos------------
        //starting position of servos
        //0.5 is off, 1 is forwards, and 0 is backwards
        double startBucketDoorPosition = 0.5;
        double startHookPosition = 0.5;
        try {
            servoBucketDoor = hardwareMap.servo.get ("bucketDoor");
            servoBucketDoor.setPosition (startBucketDoorPosition);
        } catch (Exception opModeException) {
            setWarningMessage("bucketDoor");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            servoBucketDoor = null;
        } //------------------------------------------------------------bucketDoor
        try {
            servoHook = hardwareMap.servo.get ("hook");
            servoHook.setPosition (startHookPosition);
        } catch (Exception opModeException) {
            setWarningMessage("hook");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            servoHook = null;
        } //------------------------------------------------------------hook
    } //--------------------------------------------------------------------------init
    /**
     * Access whether a warning has been generated.
     */
    boolean getWarningGenerated () {return warningGenerated;} //-------------------
    /**
     * Access the warning message.
     */
    String getWarningMessage () {return warningMessage;} //------------------------
    /**
     * Mutate the warning message by ADDING the specified message to the current message; set the warning indicator to true.
     * A comma will be added before the specified message if the message isn't empty.
     */
    void setWarningMessage (String opModeExceptionMessage) {
        if (warningGenerated)
            warningMessage += ", ";
        warningGenerated = true;
        warningMessage += opModeExceptionMessage;
    } //--------------------------------------------------------------------------setWarningMessage
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void start () {
        // Only actions that are common to all OpModes (i.e. both auto and tele) should be implemented here.
        // This method is designed to be overridden.
    } //--------------------------------------------------------------------------start
    /**
     * Perform any actions that are necessary while the OpMode is running.
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
        // Only actions that are common to all OpModes (i.e. both auto and tele) should be implemented here.
        // This method is designed to be overridden.
    } //--------------------------------------------------------------------------loop
    /**
     * Perform any actions that are necessary when the OpMode is disabled.
     * The system calls this member once when the OpMode is disabled.
     */
    @Override public void stop () {
        // Nothing needs to be done for this method.
    } //--------------------------------------------------------------------------stop
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    float clipMotor(float power) {return Range.clip (power, -1, 1);} // Ensure the values are legal.
    double clipMotor(double power) {return Range.clip (power, -1, 1);} // Ensure the values are legal.
    double clipServo(double position) {return Range.clip (position, 0, 1);} // Ensure the values are legal.

    float scaleMotorPower (float power) {
        power = clipMotor(power);
        float scale = 0.0f; // Assume no scaling.
        float[] array = {0.00f, 0.05f, 0.09f, 0.10f, 0.12f, 0.15f, 0.18f, 0.24f
                , 0.30f, 0.36f, 0.43f, 0.50f, 0.60f, 0.72f, 0.85f, 1.00f, 1.00f};
        int index = (int)(power * 16.0); // Get the corresponding index for the specified argument/parameter.
        if (index < 0)
            index = -index;
        else if (index > 16)
            index = 16;
        if (power < 0)
            scale = -array[index];
        else
            scale = array[index];
        return scale;
    } //--------------------------------------------------------------------------scaleMotorPower
    //------------Get and Set Methods------------
    //------------Dc Motors------------
    /**
     * Access the leftDrive motor's power level.
     */
    double getLeftDrivePower () {
        double returnLevel = 0.0;
        if (motorLeftDrive != null)
            returnLevel = motorLeftDrive.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getLeftDrivePower
    /**
     * Access the rightDrive motor's power level.
     */
    double getRightDrivePower () {
        double returnLevel = 0.0;
        if (motorRightDrive != null)
            returnLevel = motorRightDrive.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getRightDrivePower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setDrivePower (double leftPower, double rightPower) {
        if (motorLeftDrive != null)
            motorLeftDrive.setPower (leftPower);
        if (motorRightDrive != null)
            motorRightDrive.setPower (rightPower);
    } //--------------------------------------------------------------------------setDrivePower
    /**
     * Access the liftArm motor's power level.
     */
    double getLiftArmPower () {
        double returnLevel = 0.0;
        if (motorLiftArm != null)
            returnLevel = motorLiftArm.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getLiftArmPower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setLiftArmPower (double liftArmPower) {
        if (motorLiftArm != null)
            motorLiftArm.setPower(liftArmPower);
    } //--------------------------------------------------------------------------setLiftArmPower
    /**
     * Access the lift motor's power level.
     */
    double getLiftPower () {
        double returnLevel = 0.0;
        if (motorLift != null)
            returnLevel = motorLift.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getLiftPower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setLiftPower (double liftPower) {
        liftPower = clipMotor(liftPower);
        if (motorLift != null)
            motorLift.setPower(liftPower);
    } //--------------------------------------------------------------------------setLiftPower
    /**
     * Access the chainHooks motor's power level.
     */
    double getChainHooksPower () {
        double returnLevel = 0.0;
        if (motorChainHooks != null)
            returnLevel = motorChainHooks.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getChainHooksPower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setChainHooksPower (double chainHooksPower) {
        chainHooksPower = clipMotor(chainHooksPower);
        if (motorChainHooks != null)
            motorChainHooks.setPower(chainHooksPower);
    } //--------------------------------------------------------------------------setChainHooksPower
    /**
     * Access the spinner motor's power level.
     */
    double getSpinnerPower () {
        double returnLevel = 0.0;
        if (motorSpinner != null)
            returnLevel = motorSpinner.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getSpinnerPower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setSpinnerPower (double spinnerPower, double backSpinnerPower) {
        if (motorSpinner != null) {
            if (spinnerPower == 0)
                spinnerPower = -backSpinnerPower;
            motorSpinner.setPower(spinnerPower);
        }
    } //--------------------------------------------------------------------------setSpinnerPower
    /**
     * Access the bucket motor's power level.
     */
    double getBucketPower () {
        double returnLevel = 0.0;
        if (motorBucket != null)
            returnLevel = motorBucket.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getBucketPower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setBucketPower (double bucketPower, double backBucketPower) {
        if (motorBucket != null) {
            if (bucketPower == 0)
                bucketPower = -backBucketPower;
            motorBucket.setPower(bucketPower);
        }
    } //--------------------------------------------------------------------------setBucketPower
    /**
     * Access the sweeper motor's power level.
     */
    double getSweeperPower () {
        double returnLevel = 0.0;
        if (motorSweeper != null)
            returnLevel = motorSweeper.getPower ();
        return returnLevel;
    } //--------------------------------------------------------------------------getSweeperPower
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void setSweeperPower (double sweeperPower) {
        if (motorSweeper != null)
            motorSweeper.setPower(sweeperPower);
    } //--------------------------------------------------------------------------setSweeperPower
    //------------Servos------------
    /**
     * Access the bucketDoor position.
     */
    double getBucketDoorPosition () {
        double returnLevel = 0.5;
        if (servoBucketDoor != null)
            returnLevel = servoBucketDoor.getPosition();
        return returnLevel;
    } //--------------------------------------------------------------------------getBucketDoorPosition
    /**
     * Mutate bucketDoor position.
     */
    void setBucketDoorPosition (double bucketDoorPosition) {
        bucketDoorPosition = clipServo(bucketDoorPosition); // Ensure the specific value is legal.
        if (servoBucketDoor != null)
            servoBucketDoor.setPosition(bucketDoorPosition); // Set the Position.
    } //--------------------------------------------------------------------------setBucketDoorPosition
    /**
     * Access the hook position.
     */
    double getHookPosition () {
        double returnLevel = 0.5;
        if (servoHook != null)
            returnLevel = servoHook.getPosition();
        return returnLevel;
    } //--------------------------------------------------------------------------getHookPosition
    /**
     * Mutate hook position.
     */
    void setHookPosition (double hookPosition) {
        hookPosition = clipServo(hookPosition); // Ensure the specific value is legal.
        if (servoHook != null)
            servoHook.setPosition (hookPosition); // Set the Position.
    } //--------------------------------------------------------------------------setHookPosition
    /**
     * Set all servos to starting position
     */
    void allServosStartingPosition () {
        double bucketDoorPosition = 0.5;
        double hookPosition = 0.5;
        if (servoBucketDoor != null)
            servoBucketDoor.setPosition (bucketDoorPosition); // Set the Position.
        if (servoHook != null)
            servoHook.setPosition (hookPosition); // Set the Position.
    } //--------------------------------------------------------------------------allServosStartingPosition
    //------------ Motor Wheel Encoders------------
    /**
     * Set the leftDrive wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingLeftDriveEncoder () {
        if (motorLeftDrive != null) {
            motorLeftDrive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingLeftDriveEncoder
    /**
     * Set the rightDrive wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingRightDriveEncoder () {
        if (motorRightDrive != null) {
            motorRightDrive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingRightDriveEncoder
    /**
     * Set the liftArm wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingLiftArmEncoder () {
        if (motorLiftArm != null) {
            motorLiftArm.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingLiftArmEncoder
    /**
     * Set the lift wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingLiftEncoder () {
        if (motorLift != null) {
            motorLift.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingLiftEncoder
    /**
     * Set the chainHooks wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingChainHooksEncoder () {
        if (motorChainHooks != null) {
            motorChainHooks.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingChainHooksEncoder
    /**
     * Set the spinner wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingSpinnerEncoder () {
        if (motorSpinner != null) {
            motorSpinner.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingSpinnerEncoder
    /**
     * Set the bucket wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingBucketEncoder () {
        if (motorBucket != null) {
            motorBucket.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingBucketEncoder
    /**
     * Set the sweeper wheel encoder to run, if the mode is appropriate.
     */
    public void runUsingSweeperEncoder () {
        if (motorSweeper != null) {
            motorSweeper.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    } //--------------------------------------------------------------------------runUsingSweeperEncoder
    /**
     * Set both drive wheel encoders to run, if the mode is appropriate.
     */
    public void RUN_USING_ENCODERS () {
        // Call other members to perform the action on both motors.
        runUsingLeftDriveEncoder();
        runUsingRightDriveEncoder();
        runUsingLiftArmEncoder();
        runUsingLiftEncoder();
        runUsingChainHooksEncoder();
        runUsingSpinnerEncoder();
        runUsingBucketEncoder();
        runUsingSweeperEncoder();
    } //--------------------------------------------------------------------------RUN_USING_ENCODERS
    //------------Set Motor Wheel Encoders------------
    /**
     * Set the leftDrive wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutLeftDriveEncoder () {
        if (motorLeftDrive != null) {
            if (motorLeftDrive.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorLeftDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutLeftDriveEncoder
    /**
     * Set the rightDrive wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutRightDriveEncoder () {
        if (motorRightDrive != null) {
            if (motorRightDrive.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorRightDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutRightDriveEncoder
    /**
     * Set both drive wheel encoders to not run, if the mode is appropriate.
     */
    public void runWithoutDriveEncoders () {
        // Call other members to perform the action on both motors.
        runWithoutLeftDriveEncoder();
        runWithoutRightDriveEncoder();
    } //--------------------------------------------------------------------------runWithoutDriveEncoders
    /**
     * Set the liftArm wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutLiftArmEncoder () {
        if (motorLiftArm != null) {
            if (motorLiftArm.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorLiftArm.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutLiftArmEncoder
    /**
     * Set the lift wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutLiftEncoder () {
        if (motorLift != null) {
            if (motorLift.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorLift.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutLiftEncoder
    /**
     * Set the chainHooks wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutChainHooksEncoder () {
        if (motorChainHooks != null) {
            if (motorChainHooks.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorChainHooks.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutChainHooksEncoder
    /**
     * Set the spinner wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutSpinnerEncoder () {
        if (motorSpinner != null) {
            if (motorSpinner.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorSpinner.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutSpinnerEncoder
    /**
     * Set the bucket wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutBucketEncoder () {
        if (motorBucket != null) {
            if (motorBucket.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorBucket.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutBucketEncoder
    /**
     * Set the sweeper wheel encoder to not run, if the mode is appropriate.
     */
    public void runWithoutSweeperEncoder () {
        if (motorSweeper != null) {
            if (motorSweeper.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorSweeper.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    } //--------------------------------------------------------------------------runWithoutSweeperEncoder
    //------------Reset Motor Wheel Encoders------------
    /**
     * Reset the leftDrive wheel encoder.
     */
    public void resetLeftDriveEncoder () {
        if (motorLeftDrive != null)
            motorLeftDrive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetLeftDriveEncoder
    /**
     * Reset the rightDrive wheel encoder.
     */
    public void resetRightDriveEncoder () {
        if (motorRightDrive != null)
            motorRightDrive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetRightDriveEncoder
    /**
     * Reset both drive wheel encoders.
     */
    public void resetDriveEncoders () {
        // Reset the motor encoders on the drive wheels.
        resetLeftDriveEncoder();
        resetRightDriveEncoder();
    } //--------------------------------------------------------------------------resetDriveEncoders
    /**
     * Reset the liftArm wheel encoder.
     */
    public void resetLiftArmEncoder () {
        if (motorLiftArm != null)
            motorLiftArm.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetLiftArmEncoder
    /**
     * Reset the lift wheel encoder.
     */
    public void resetLiftEncoder () {
        if (motorLift != null)
            motorLift.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetLiftEncoder
    /**
     * Reset the chainHooks wheel encoder.
     */
    public void resetChainHooksEncoder () {
        if (motorChainHooks != null)
            motorChainHooks.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetChainHooksEncoder
    /**
     * Reset the spinner wheel encoder.
     */
    public void resetSpinnerEncoder () {
        if (motorSpinner != null)
            motorSpinner.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetSpinnerEncoder
    /**
     * Reset the bucket wheel encoder.
     */
    public void resetBucketEncoder () {
        if (motorBucket != null)
            motorBucket.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetBucketEncoder
     /**
     * Reset the sweeper wheel encoder.
     */
    public void resetSweeperEncoder () {
        if (motorSweeper != null)
            motorSweeper.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    } //--------------------------------------------------------------------------resetSweeperEncoder
    //------------Get Motor Wheel Encoder Count------------
    /**
     * Access the left encoder's count.
     */
    int getLeftEncoderCount () {
        int returnLevel = 0;
        if (motorLeftDrive != null)
            returnLevel = motorLeftDrive.getCurrentPosition ();
        return returnLevel;
    } //--------------------------------------------------------------------------getLeftEncoderCount
    /**
     * Access the right encoder's count.
     */
    int getRightEncoderCount () {
        int returnLevel = 0;
        if (motorRightDrive != null)
            returnLevel = motorRightDrive.getCurrentPosition ();
        return returnLevel;
    } //--------------------------------------------------------------------------getRightEncoderCount
    /**
     * Access the liftArm encoder's count.
     */
    int getLiftArmEncoderCount () {
        int returnLevel = 0;
        if (motorLiftArm != null)
            returnLevel = motorLiftArm.getCurrentPosition ();
        return returnLevel;
    } //--------------------------------------------------------------------------getLiftArmEncoderCount
    /**
     * Access the lift encoder's count.
     */
    int getLiftEncoderCount () {
        int returnLevel = 0;
        if (motorLift != null)
            returnLevel = motorLift.getCurrentPosition ();
        return returnLevel;
    } //--------------------------------------------------------------------------getLiftEncoderCount
    /**
     * Access the chainHooks encoder's count.
     */
    int getChainHooksEncoderCount () {
        int returnLevel = 0;
        if (motorChainHooks != null)
            returnLevel = motorChainHooks.getCurrentPosition ();
        return returnLevel;
    } //--------------------------------------------------------------------------getChainHooksEncoderCount
    /**
     * Access the spinner encoder's count.
     */
    int getSpinnerEncoderCount () {
        int returnLevel = 0;
        if (motorSpinner != null)
            returnLevel = motorSpinner.getCurrentPosition ();
        return returnLevel;
    } //--------------------------------------------------------------------------getSpinnerEncoderCount
    /**
     * Access the bucket encoder's count.
     */
    int getBucketEncoderCount () {
        int returnLevel = 0;
        if (motorBucket != null)
            returnLevel = motorBucket.getCurrentPosition();
        return returnLevel;
    } //--------------------------------------------------------------------------getBucketEncoderCount
    /**
     * Access the sweeper encoder's count.
     */
    int getSweeperEncoderCount () {
        int returnLevel = 0;
        if (motorSweeper != null)
            returnLevel = motorSweeper.getCurrentPosition();
        return returnLevel;
    } //--------------------------------------------------------------------------getSweeperEncoderCount
    //------------Indicate Motor Wheel Encoders Value------------
    /**
     * Indicate whether the leftDrive motor's encoder has reached a value.
     */
    boolean hasLeftDriveEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorLeftDrive != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorLeftDrive.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel;  // Return the status.
    } //--------------------------------------------------------------------------hasLeftDriveEncoderReached
    /**
     * Indicate whether the rightDrive motor's encoder has reached a value.
     */
    boolean hasRightDriveEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorRightDrive != null) {
            // Have the encoders reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorRightDrive.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasRightDriveEncoderReached
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean haveDriveEncodersReached (double leftCount, double rightCount) {
        boolean returnLevel = false; // Assume failure.
        // Have the encoders reached the specified values?
        if (hasLeftDriveEncoderReached(leftCount) && hasRightDriveEncoderReached(rightCount))
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------haveDriveEncodersReached
    /**
     * Indicate whether the liftArm motor's encoder has reached a value.
     */
    boolean hasLiftArmEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorLiftArm != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorLiftArm.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasLiftArmEncoderReached
    /**
     * Indicate whether the lift motor's encoder has reached a value.
     */
    boolean hasLiftEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorLift != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorLift.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasLiftEncoderReached
    /**
     * Indicate whether the chainHooks motor's encoder has reached a value.
     */
    boolean hasChainHooksEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorChainHooks != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorChainHooks.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasChainHooksEncoderReached
    /**
     * Indicate whether the spinner motor's encoder has reached a value.
     */
    boolean hasSpinnerEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorSpinner != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorSpinner.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasSpinnerEncoderReached
    /**
     * Indicate whether the bucket motor's encoder has reached a value.
     */
    boolean hasBucketEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorBucket != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorBucket.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasBucketEncoderReached
    /**
     * Indicate whether the sweeper motor's encoder has reached a value.
     */
    boolean hasSweeperEncoderReached (double count) {
        boolean returnLevel = false; // Assume failure.
        if (motorSweeper != null) {
            // Has the encoder reached the specified values?
            // TODO Implement stall code using these variables.
            if (Math.abs (motorSweeper.getCurrentPosition ()) > count)
                returnLevel = true; // Set the status to a positive indication.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasSweeperEncoderReached
    //------------Use Motor Wheel Encoders------------
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean driveUsingEncoders (double leftPower, double rightPower, double leftCount, double rightCount) {
        boolean returnLevel = false; // Assume the encoders have not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoders will be used.
        setDrivePower(leftPower, rightPower); // Start the drive wheel motors at full power.
        // Have the motor shafts turned the required amount?
        // If they haven't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (haveDriveEncodersReached(leftCount, rightCount)) {
            resetDriveEncoders(); // Reset the encoders to ensure they are at a known good value.
            setDrivePower(0.0f, 0.0f); // Stop the motors.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------driveUsingEncoders
    /**
     * Indicate whether the liftArm motor's encoder has reached a value.
     */
    boolean runLiftArmUsingEncoder (double liftArmPower, double liftArmCount) {
        boolean returnLevel = false; // Assume the encoder has not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoder will be used.
        setLiftArmPower(liftArmPower); // Start the lift wheel motor at full power.
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (hasLiftArmEncoderReached(liftArmCount)) {
            resetLiftArmEncoder(); // Reset the encoder to ensure it is at a known good value.
            setLiftArmPower(0.0f); // Stop the motor.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------runLiftArmUsingEncoder
    /**
     * Indicate whether the lift motor's encoder has reached a value.
     */
    boolean runLiftUsingEncoder (double liftPower, double liftCount) {
        boolean returnLevel = false; // Assume the encoder has not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoder will be used.
        setLiftPower(liftPower); // Start the lift wheel motor at full power.
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (hasLiftEncoderReached(liftCount)) {
            resetLiftEncoder(); // Reset the encoder to ensure it is at a known good value.
            setLiftPower(0.0f); // Stop the motor.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------runLiftUsingEncoder
    /**
     * Indicate whether the chainHooks motor's encoder has reached a value.
     */
    boolean runChainHooksUsingEncoder (double chainHooksPower, double chainHooksCount) {
        boolean returnLevel = false; // Assume the encoder has not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoder will be used.
        setChainHooksPower(chainHooksPower); // Start the lift wheel motor at full power.
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (hasChainHooksEncoderReached(chainHooksCount)) {
            resetChainHooksEncoder(); // Reset the encoder to ensure it is at a known good value.
            setChainHooksPower(0.0f); // Stop the motor.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------runChainHooksUsingEncoder
    /**
     * Indicate whether the spinner motor's encoder has reached a value.
     */
    boolean runSpinnerUsingEncoder (double spinnerPower, double backSpinnerPower, double spinnerCount) {
        boolean returnLevel = false; // Assume the encoder has not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoder will be used.
        setSpinnerPower(spinnerPower, backSpinnerPower); // Start the lift wheel motor at full power.
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (hasSpinnerEncoderReached(spinnerCount)) {
            resetSpinnerEncoder(); // Reset the encoder to ensure it is at a known good value.
            setSpinnerPower(0.0f, 0.0f); // Stop the motor.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------runSpinnerUsingEncoder
    /**
     * Indicate whether the bucket motor's encoder has reached a value.
     */
    boolean runBucketUsingEncoder (double bucketPower, double backBucketPower, double bucketCount) {
        boolean returnLevel = false; // Assume the encoder has not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoder will be used.
        setBucketPower(bucketPower, backBucketPower); // Start the lift wheel motor at full power.
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (hasBucketEncoderReached(bucketCount)) {
            resetBucketEncoder(); // Reset the encoder to ensure it is at a known good value.
            setBucketPower(0.0f, 0.0f); // Stop the motor.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------runBucketUsingEncoder
    /**
     * Indicate whether the sweeper motor's encoder has reached a value.
     */
    boolean runSweeperUsingEncoder (double sweeperPower, double sweeperCount) {
        boolean returnLevel = false; // Assume the encoder has not reached the limit.
        RUN_USING_ENCODERS(); // Tell the system that motor encoder will be used.
        setSweeperPower(sweeperPower); // Start the lift wheel motor at full power.
        // Has the motor shaft turned the required amount?
        // If it hasn't, then the op-mode remains in this state (i.e this block will be executed the next time this method is called).
        if (hasSweeperEncoderReached(sweeperCount)) {
            resetSweeperEncoder(); // Reset the encoder to ensure it is at a known good value.
            setSweeperPower(0.0f); // Stop the motor.
            returnLevel = true; // Transition to the next state when this method is called again.
        }
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------runSweeperUsingEncoder
    //------------Indicate If Motor Wheel Encoder Has Reset------------
    /**
     * Indicate whether the leftDrive encoder has been completely reset.
     */
    boolean hasLeftDriveEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the left encoder reached zero?
        if (getLeftEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasLeftDriveEncoderReset
    /**
     * Indicate whether the rightDrive encoder has been completely reset.
     */
    boolean hasRightDriveEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the right encoder reached zero?
        if (getRightEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasRightDriveEncoderReset
    /**
     * Indicate whether the encoders have been completely reset.
     */
    boolean haveDriveEncodersReset () {
        boolean returnLevel = false; // Assume failure.
        // Have the encoders reached zero?
        if (hasLeftDriveEncoderReset() && hasRightDriveEncoderReset())
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------haveDriveEncodersReset
    /**
     * Indicate whether the liftArm encoder has been completely reset.
     */
    boolean hasLiftArmEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the liftArm encoder reached zero?
        if (getLiftArmEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasLiftArmEncoderReset
    /**
     * Indicate whether the lift encoder has been completely reset.
     */
    boolean hasLiftEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the lift encoder reached zero?
        if (getLiftEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasLiftEncoderReset
    /**
     * Indicate whether the chainHooks encoder has been completely reset.
     */
    boolean hasChainHooksEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the chainHooks encoder reached zero?
        if (getChainHooksEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasChainHooksEncoderReset
    /**
     * Indicate whether the spinner encoder has been completely reset.
     */
    boolean hasSpinnerEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the spinner encoder reached zero?
        if (getSpinnerEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasSpinnerEncoderReset
    /**
     * Indicate whether the bucket encoder has been completely reset.
     */
    boolean hasBucketEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the bucket encoder reached zero?
        if (getBucketEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
    } //--------------------------------------------------------------------------hasBucketEncoderReset
    /**
     * Indicate whether the sweeper encoder has been completely reset.
     */
    boolean hasSweeperEncoderReset () {
        boolean returnLevel = false; // Assume failure.
        // Has the sweeper encoder reached zero?
        if (getSweeperEncoderCount() == 0)
            returnLevel = true; // Set the status to a positive indication.
        return returnLevel; // Return the status.
} //--------------------------------------------------------------------------hasSweeperEncoderReset
    //------------Private Variables------------
    /**
     * Indicate whether a message is a available to the class user.
     */
    private boolean warningGenerated = false;
    //--------------------------------------------------------------------------
    /**
     * Store a message to the user if one has been generated.
     */
    private String warningMessage;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the leftDrive motor.
     */
    private DcMotor motorLeftDrive;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the rightDrive motor.
     */
    private DcMotor motorRightDrive;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the liftArm motor.
     */
    private DcMotor motorLiftArm;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the lift motor.
     */
    private DcMotor motorLift;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the chainHooks motor.
     */
    private DcMotor motorChainHooks;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the spinner motor.
     */
    private DcMotor motorSpinner;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the bucket motor.
     */
    private DcMotor motorBucket;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the sweeper motor.
     */
    private DcMotor motorSweeper;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the bucketDoor servo.
     */
    private Servo servoBucketDoor;
    //--------------------------------------------------------------------------
    /**
     * Manage the aspects of the hook servo.
     */
    private Servo servoHook;
} //------------------------------------------------------------------------------BigBerthaHardware
