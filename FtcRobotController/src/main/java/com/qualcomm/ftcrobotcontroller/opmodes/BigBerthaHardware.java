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
import java.lang.String;
import java.lang.Object;
import com.qualcomm.ftccommon.CommandList;


/**
 * Provides a single hardware access point between custom op-modes and the OpMode class for BigBertha.
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-13-20-04-----2015-12-01
 */
public class BigBerthaHardware extends OpMode {
    //------------Private Variables------------
    private boolean warningGenerated = false;
    private String warningMessage;
    private DcMotor motorLeftDrive;
    private DcMotor motorRightDrive;
    private DcMotor motorLiftArm;
    private DcMotor motorLift;
    private DcMotor motorChainHooks;
    private DcMotor motorSweeper;
    private DcMotor motorBucket;
    private DcMotor motorSpinner;
    private Servo servoBucketDoor;
    private Servo servoHook;
    private Servo servoMan;
    //------------Virtual Values of Motors and Servos for Testing Code Without Robot------------
    private static double leftDriveValue;
    private static double rightDriveValue;
    private static double liftArmValue;
    private static double liftValue;
    private static double chainHooksValue;
    private static double sweeperValue;
    private static double bucketValue;
    private static double spinnerValue;
    private static double bucketDoorValue;
    private static double hookValue;
    private static double manValue;
    //------------initial positions------------
    double initLeftDrivePower;
    double initRightDrivePower;
    double initLiftArmPower;
    double initLiftPower;
    double initChainHooksPower;
    double initSweeperPower;
    double initBucketPower;
    double initSpinnerPower;
    //0.5 is off, 1 is forwards, and 0 is backwards
    private double initBucketDoorPosition = 0.5;
    private double initHookPosition = 0.5;
    private double initManPosition = 0.5;

    public BigBerthaHardware () {
    }
    public void hardwareInit () {
        // Use the hardwareMap to associate class members to hardware ports.
        warningGenerated = false; // Provide telemetry data to a class user
        warningMessage = "Can't map; ";
        // This class prevents the custom op-mode from throwing an exception at runtime.
        //------------DcMotors------------
        try {
            motorLeftDrive = hardwareMap.dcMotor.get ("left");
            motorLeftDrive.setPower(initLeftDrivePower);
        } catch (Exception opModeException) {
            setWarningMessage("leftDrive");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            leftDriveValue = initLeftDrivePower;
        }
        try {
            motorRightDrive = hardwareMap.dcMotor.get ("right");
            motorRightDrive.setPower(initRightDrivePower);
            motorRightDrive.setDirection (DcMotor.Direction.REVERSE);
        } catch (Exception opModeException) {
            setWarningMessage("rightDrive");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            rightDriveValue = initRightDrivePower;
        }
        try {
            motorLiftArm = hardwareMap.dcMotor.get ("liftArm");
            motorLiftArm.setPower(initLiftArmPower);
            motorLiftArm.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception opModeException) {
            setWarningMessage("leftArm");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            liftArmValue = initLiftArmPower;
        }
        try {
            motorLift = hardwareMap.dcMotor.get ("lift");
            motorLift.setPower(initLiftPower);
        } catch (Exception opModeException) {
            setWarningMessage("lift");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            liftValue = initLiftPower;
        }
        try {
            motorChainHooks = hardwareMap.dcMotor.get ("chainHooks");
            motorChainHooks.setPower(initChainHooksPower);
        } catch (Exception opModeException) {
            setWarningMessage("chainHooks");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            //motorChainHooks = null;
            chainHooksValue = initChainHooksPower;
        }
        try {
            motorSweeper = hardwareMap.dcMotor.get ("sweeper");
            motorSweeper.setPower(initSweeperPower);
        } catch (Exception opModeException) {
            setWarningMessage("sweeper");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            sweeperValue = initSweeperPower;
        }
        try {
            motorBucket = hardwareMap.dcMotor.get ("bucket");
            motorBucket.setPower(initBucketPower);
        } catch (Exception opModeException) {
            setWarningMessage("bucket");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            bucketValue = initBucketPower;
        }
        try {
            motorSpinner = hardwareMap.dcMotor.get ("spinner");
            motorSpinner.setPower(initSpinnerPower);
        } catch (Exception opModeException) {
            setWarningMessage("spinner");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            spinnerValue = initSpinnerPower;
        }
        //------------Servos------------
        try {
            servoBucketDoor = hardwareMap.servo.get ("bucketDoor");
            servoBucketDoor.setPosition (initBucketDoorPosition);
        } catch (Exception opModeException) {
            setWarningMessage("bucketDoor");
            DbgLog.msg (opModeException.getLocalizedMessage ());
            bucketDoorValue = initBucketDoorPosition;
        }
        try {
            servoHook = hardwareMap.servo.get ("hook");
            servoHook.setPosition (initHookPosition);
        } catch (Exception opModeException) {
            setWarningMessage("hook");
            DbgLog.msg(opModeException.getLocalizedMessage());
            hookValue = initHookPosition;
        }
        try {
            servoMan = hardwareMap.servo.get ("man");
            servoMan.setPosition (initManPosition);
        } catch (Exception opModeException) {
            setWarningMessage("man");
            DbgLog.msg(opModeException.getLocalizedMessage());
            manValue = initManPosition;
        }
    }
    //------------Warnings------------
    boolean getWarningGenerated () {return warningGenerated;}

    String getWarningMessage () {return warningMessage;}

    void setWarningMessage (String opModeExceptionMessage) {
        if (warningGenerated)
            warningMessage += ", ";
        warningGenerated = true;
        warningMessage += opModeExceptionMessage;
    }
    //------------OpMode Methods------------
    @Override public void init () {}//The system calls this member once when the OpMode is enabled.
    @Override public void start() {}//The system calls this member once when the OpMode is enabled.
    @Override public void loop () {}//The system calls this member repeatedly while the OpMode is running.
    @Override public void stop () {}//The system calls this member once when the OpMode is disabled.
    //------------Clip Power------------
    float clipMotor(float power) {return Range.clip (power, -1, 1);}
    double clipMotor(double power) {return Range.clip (power, -1, 1);}
    double clipMotorPositive(double power) {return Range.clip(power, 0, 1);}
    double clipMotorNegative(double power) {return Range.clip(power, -1, 0);}
    double clipServo(double position) {return Range.clip (position, 0, 1);}

    float scaleMotorPower (float power) {
        power = clipMotor(power);
        float scale;
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
    }
    //------------------------------------TeleOp Methods-------------------------------------
    //------------Get and Set Methods------------
    double getFullValue (double forwards, double backwards) {
        clipMotorPositive(forwards);
        clipMotorNegative(backwards);
        if (forwards == 0 && backwards < 0)
            return backwards;
        return forwards;
    }
    //------------Dc Motors------------
    double getLeftDrivePower () {
        if (motorLeftDrive != null)
            return motorLeftDrive.getPower ();
        return leftDriveValue;
    }
    double getRightDrivePower () {
        if (motorRightDrive != null)
            return motorRightDrive.getPower ();
        return rightDriveValue;
    }
    void setDrivePower (double leftPower, double rightPower) {
        if (motorLeftDrive != null)
            motorLeftDrive.setPower (leftPower);
        leftDriveValue = leftPower;
        if (motorRightDrive != null)
            motorRightDrive.setPower (rightPower);
        rightDriveValue = rightPower;
    }
    double getLiftArmPower () {
        if (motorLiftArm != null)
            return motorLiftArm.getPower ();
        return liftArmValue;
    }
    void setLiftArmPower (double liftArmPower) {
        if (motorLiftArm != null)
            motorLiftArm.setPower(liftArmPower);
        liftArmValue = liftArmPower;
    }
    double getLiftPower () {
        if (motorLift != null)
            return motorLift.getPower ();
        return liftValue;
    }
    void setLiftPower (double liftPower) {
        liftPower = clipMotor(liftPower);
        if (motorLift != null)
            motorLift.setPower(liftPower);
        liftValue = liftPower;
    }
    double getChainHooksPower () {
        if (motorChainHooks != null)
            return motorChainHooks.getPower ();
        return chainHooksValue;
    }
    void setChainHooksPower (double chainHooksPower) {
        chainHooksPower = clipMotor(chainHooksPower);
        if (motorChainHooks != null)
            motorChainHooks.setPower(chainHooksPower);
        chainHooksValue = chainHooksPower;
    }
    double getSweeperPower () {
        if (motorSweeper != null)
            return motorSweeper.getPower ();
        return sweeperValue;
    }
    void setSweeperPower (double sweeperPower, double backSweeperPower) {
        double fullSweeperPower = getFullValue(sweeperPower,backSweeperPower);
        if (motorSweeper != null)
            motorSweeper.setPower(fullSweeperPower);
        sweeperValue = fullSweeperPower;
    }
    double getBucketPower () {
        if (motorBucket != null)
            return motorBucket.getPower ();
        return bucketValue;
    }
    void setBucketPower (double bucketPower, double backBucketPower) {
        double fullBucketPower = getFullValue(bucketPower,backBucketPower);
        if (motorBucket != null)
            motorBucket.setPower(fullBucketPower);
        bucketValue = fullBucketPower;
    }
    double getSpinnerPower () {
        if (motorSpinner != null)
            return motorSpinner.getPower ();
        return spinnerValue;
    }
    void setSpinnerPower (double spinnerPower) {
        if (motorSpinner != null)
            motorSpinner.setPower(spinnerPower);
        spinnerValue = spinnerPower;
    }
    //------------Servos------------
    double getBucketDoorPosition () {
        if (servoBucketDoor != null)
            return servoBucketDoor.getPosition();
        return bucketDoorValue;
    }
    void setBucketDoorPosition (double bucketDoorPosition) {
        bucketDoorPosition = clipServo(bucketDoorPosition);
        if (servoBucketDoor != null)
            servoBucketDoor.setPosition(bucketDoorPosition);
        bucketDoorValue = bucketDoorPosition;
    }
    double getHookPosition () {
        if (servoHook != null)
            return servoHook.getPosition();
        return hookValue;
    }
    void setHookPosition (double hookPosition) {
        hookPosition = clipServo(hookPosition);
        if (servoHook != null)
            servoHook.setPosition (hookPosition);
        hookValue = hookPosition;
    }
    double getManPosition () {
        if (servoMan != null)
            return servoMan.getPosition();
        return manValue;
    }
    void setManPosition (double manPosition) {
        manPosition = clipServo(manPosition);
        if (servoMan != null)
            servoMan.setPosition (manPosition);
        manValue = manPosition;
    }
    //------------------------------------Autonomous Methods-------------------------------------
    void allServosInitialPosition() {
        if (servoBucketDoor != null)
            servoBucketDoor.setPosition (initBucketDoorPosition);
        if (servoHook != null)
            servoHook.setPosition (initHookPosition);
        if (servoMan != null)
            servoMan.setPosition (initManPosition);
        bucketDoorValue = initBucketDoorPosition;
        hookValue = initHookPosition;
        manValue = initManPosition;
    }
    //------------ Set With Motor Wheel Encoders------------
    public void runUsingLeftDriveEncoder () {
        if (motorLeftDrive != null)
            motorLeftDrive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingRightDriveEncoder () {
        if (motorRightDrive != null)
            motorRightDrive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingLiftArmEncoder () {
        if (motorLiftArm != null)
            motorLiftArm.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingLiftEncoder () {
        if (motorLift != null)
            motorLift.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingChainHooksEncoder () {
        if (motorChainHooks != null)
            motorChainHooks.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingSweeperEncoder () {
        if (motorSweeper != null)
            motorSweeper.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingBucketEncoder () {
        if (motorBucket != null)
            motorBucket.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void runUsingSpinnerEncoder () {
        if (motorSpinner != null)
            motorSpinner.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    public void RUN_USING_ENCODERS () {
        runUsingLeftDriveEncoder();
        runUsingRightDriveEncoder();
        runUsingLiftArmEncoder();
        runUsingLiftEncoder();
        runUsingChainHooksEncoder();
        runUsingSweeperEncoder();
        runUsingBucketEncoder();
        runUsingSpinnerEncoder();
    }
    //------------Set Without Motor Wheel Encoders------------
    public void runWithoutLeftDriveEncoder () {
        if (motorLeftDrive != null) {
            if (motorLeftDrive.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorLeftDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutRightDriveEncoder () {
        if (motorRightDrive != null) {
            if (motorRightDrive.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorRightDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutDriveEncoders () {
        runWithoutLeftDriveEncoder();
        runWithoutRightDriveEncoder();
    }
    public void runWithoutLiftArmEncoder () {
        if (motorLiftArm != null) {
            if (motorLiftArm.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorLiftArm.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutLiftEncoder () {
        if (motorLift != null) {
            if (motorLift.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorLift.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutChainHooksEncoder () {
        if (motorChainHooks != null) {
            if (motorChainHooks.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorChainHooks.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutSweeperEncoder () {
        if (motorSweeper != null) {
            if (motorSweeper.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorSweeper.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutBucketEncoder () {
        if (motorBucket != null) {
            if (motorBucket.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorBucket.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    public void runWithoutSpinnerEncoder () {
        if (motorSpinner != null) {
            if (motorSpinner.getChannelMode () == DcMotorController.RunMode.RESET_ENCODERS)
                motorSpinner.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    //------------Reset Motor Wheel Encoders------------
    public void resetLeftDriveEncoder () {
        if (motorLeftDrive != null)
            motorLeftDrive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetRightDriveEncoder () {
        if (motorRightDrive != null)
            motorRightDrive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetDriveEncoders () {
        resetLeftDriveEncoder();
        resetRightDriveEncoder();
    }
    public void resetLiftArmEncoder () {
        if (motorLiftArm != null)
            motorLiftArm.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetLiftEncoder () {
        if (motorLift != null)
            motorLift.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetChainHooksEncoder () {
        if (motorChainHooks != null)
            motorChainHooks.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetSweeperEncoder () {
        if (motorSweeper != null)
            motorSweeper.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetBucketEncoder () {
        if (motorBucket != null)
            motorBucket.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void resetSpinnerEncoder () {
        if (motorSpinner != null)
            motorSpinner.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    //------------Get Motor Wheel Encoder Count------------
    int getLeftEncoderCount () {
        if (motorLeftDrive != null)
            return motorLeftDrive.getCurrentPosition ();
        return 0;
    }
    int getRightEncoderCount () {
        if (motorRightDrive != null)
            return motorRightDrive.getCurrentPosition ();
        return 0;
    }
    int getLiftArmEncoderCount () {
        if (motorLiftArm != null)
            return motorLiftArm.getCurrentPosition ();
        return 0;
    }
    int getLiftEncoderCount () {
        if (motorLift != null)
            return motorLift.getCurrentPosition ();
        return 0;
    }
    int getChainHooksEncoderCount () {
        if (motorChainHooks != null)
            return motorChainHooks.getCurrentPosition ();
        return 0;
    }
    int getSweeperEncoderCount () {
        if (motorSweeper != null)
            return motorSweeper.getCurrentPosition ();
        return 0;
    }
    int getBucketEncoderCount () {
        if (motorBucket != null)
            return motorBucket.getCurrentPosition();
        return 0;
    }
    int getSpinnerEncoderCount () {
        if (motorSpinner != null)
            return motorSpinner.getCurrentPosition();
        return 0;
    }
    //------------Indicate Motor Wheel Encoders Value------------
    boolean hasLeftDriveEncoderReached (double count) {
        if (motorLeftDrive != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorLeftDrive.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean hasRightDriveEncoderReached (double count) {
        if (motorRightDrive != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorRightDrive.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean haveDriveEncodersReached (double leftCount, double rightCount) {return hasLeftDriveEncoderReached(leftCount)
            && hasRightDriveEncoderReached(rightCount);}
    boolean hasLiftArmEncoderReached (double count) {
        if (motorLiftArm != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorLiftArm.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean hasLiftEncoderReached (double count) {
        if (motorLift != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorLift.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean hasChainHooksEncoderReached (double count) {
        if (motorChainHooks != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorChainHooks.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean hasSweeperEncoderReached (double count) {
        if (motorSweeper != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorSweeper.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean hasBucketEncoderReached (double count) {
        if (motorBucket != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorBucket.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    boolean hasSpinnerEncoderReached (double count) {
        if (motorSpinner != null) {
            // TODO Implement stall code using these variables.
            if (Math.abs (motorSpinner.getCurrentPosition ()) > count)
                return true;
        }
        return false;
    }
    //------------Indicate whether the motor wheel encoders have reached a value------------
    boolean driveUsingEncoders (double leftPower, double rightPower, double leftCount, double rightCount) {
        RUN_USING_ENCODERS();
        setDrivePower(leftPower, rightPower);
        if (haveDriveEncodersReached(leftCount, rightCount)) {
            resetDriveEncoders();
            setDrivePower(0.0f, 0.0f);
            return true;
        }
        return false;
    }
    boolean runLiftArmUsingEncoder (double liftArmPower, double liftArmCount) {
        RUN_USING_ENCODERS();
        setLiftArmPower(liftArmPower);
        if (hasLiftArmEncoderReached(liftArmCount)) {
            resetLiftArmEncoder();
            setLiftArmPower(0.0f);
            return true;
        }
        return false;
    }
    boolean runLiftUsingEncoder (double liftPower, double liftCount) {
        RUN_USING_ENCODERS();
        setLiftPower(liftPower);
        if (hasLiftEncoderReached(liftCount)) {
            resetLiftEncoder();
            setLiftPower(0.0f);
            return true;
        }
        return false;
    }
    boolean runChainHooksUsingEncoder (double chainHooksPower, double chainHooksCount) {
        RUN_USING_ENCODERS();
        setChainHooksPower(chainHooksPower);
        if (hasChainHooksEncoderReached(chainHooksCount)) {
            resetChainHooksEncoder();
            setChainHooksPower(0.0f);
            return true;
        }
        return false;
    }
    boolean runSweeperUsingEncoder (double sweeperPower, double backSweeperPower, double sweeperCount) {
        RUN_USING_ENCODERS();
        setSweeperPower(sweeperPower, backSweeperPower);
        if (hasSweeperEncoderReached(sweeperCount)) {
            resetSweeperEncoder();
            setSweeperPower(0.0f, 0.0f);
            return true;
        }
        return false;
    }
    boolean runBucketUsingEncoder (double bucketPower, double backBucketPower, double bucketCount) {
        RUN_USING_ENCODERS();
        setBucketPower(bucketPower, backBucketPower);
        if (hasBucketEncoderReached(bucketCount)) {
            resetBucketEncoder();
            setBucketPower(0.0f, 0.0f);
            return true;
        }
        return false;
    }
    boolean runSpinnerUsingEncoder (double spinnerPower, double spinnerCount) {
        RUN_USING_ENCODERS();
        setSpinnerPower(spinnerPower);
        if (hasSpinnerEncoderReached(spinnerCount)) {
            resetSpinnerEncoder();
            setSpinnerPower(0.0f);
            return true;
        }
        return false;
    }
    //------------Indicate If Motor Wheel Encoder Has Reset------------
    boolean hasLeftDriveEncoderReset () {return getLeftEncoderCount() == 0;}
    boolean hasRightDriveEncoderReset() {return getRightEncoderCount() == 0;}
    boolean haveDriveEncodersReset   () {return hasLeftDriveEncoderReset() && hasRightDriveEncoderReset();}
    boolean hasLiftArmEncoderReset   () {return (getLiftArmEncoderCount() == 0);}
    boolean hasLiftEncoderReset      () {return (getLiftEncoderCount() == 0);}
    boolean hasChainHooksEncoderReset() {return (getChainHooksEncoderCount() == 0);}
    boolean hasSweeperEncoderReset   () {return (getSweeperEncoderCount() == 0);}
    boolean hasBucketEncoderReset    () {return (getBucketEncoderCount() == 0);}
    boolean hasSpinnerEncoderReset   () {return (getSpinnerEncoderCount() == 0);}
}
