/**
 * Created by spmce on 2/9/2016.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.Range;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Provides a single hardware access point between custom op-modes and the OpMode class for this Robot.
 * @author Shane McEnaney
 * @version 2016
 */
public class RobotHardware extends OpMode {

    private boolean warningGenerated = false;
    private String warningMessage;

    protected DcMotor left, right, backLeft, backRight;
    protected Servo hook, spinner;

    public RobotHardware() {
        warningGenerated = false; // Provide telemetry data to a class user
        warningMessage = "Can't map: ";
        mapDriveTrain();
        mapServos();
    }

    @Override
    public void init() {
        telemetry.addData("3", "Hi");
    }

    public void init_loop() {
        init();
        telemetry.addData("4","Hello");
    }

    @Override
    public void loop() {

    }

    boolean getWarningGenerated () {return warningGenerated;}

    String getWarningMessage () {return warningMessage;}

    void setWarningMessage (String opModeExceptionMessage) {
        if (warningGenerated)
            warningMessage += ", ";
        warningGenerated = true;
        warningMessage += opModeExceptionMessage;
    }

    private void mapDevice (HardwareDevice device) {
        try {
            device = hardwareMap.dcMotor.get(String.valueOf(device));
        } catch (Exception opModeException) {
            setWarningMessage(String.valueOf(device));
            DbgLog.msg(opModeException.getLocalizedMessage());
        }
    }

    private void reverseDirection(DcMotor motor) {
        if (motor != null)
            motor.setDirection(DcMotor.Direction.REVERSE);
    }

    private void mapDriveTrain() {
        mapDevice(left);
        reverseDirection(right);
        mapDevice(right);
        mapDevice(backLeft);
        reverseDirection(backRight);
        mapDevice(backRight);
    }

    private void mapServos() {
        mapDevice(hook);
        mapDevice(spinner);
    }
    
    double getPower(DcMotor motor) {
        if (motor != null)
            return motor.getPower();
        return 0.0;
    }
    void setPower(DcMotor motor, double power) {
        if (motor != null)
            motor.setPower(power);
    }
    
    double getPosition(Servo servo) {
        if (servo != null)
            return servo.getPosition();
        return 0.5;
    }
    void setPosition(Servo servo, double position) {
        if (servo != null)
            servo.setPosition(position);
    }
    
    void setDriveTrain(double power) {
        if (left != null)
            left.setPower(power);
        if (right != null)
            right.setPower(power);
        if (backLeft != null)
            backLeft.setPower(power);
        if (backRight != null)
            backRight.setPower(power);
    }
    void setDriveTrain(double left ,double right) {
        if (this.left != null)
            this.left.setPower(left);
        if (this.right != null)
            this.right.setPower(right);
        if (backLeft != null)
            backLeft.setPower(left);
        if (backRight != null)
            backRight.setPower(right);
    }
    void setDriveTrain(double left ,double right, double backLeft, double backRight) {
        if (this.left != null)
            this.left.setPower(left);
        if (this.right != null)
            this.right.setPower(right);
        if (this.backLeft != null)
            this.backLeft.setPower(backLeft);
        if (this.backRight != null)
            this.backRight.setPower(backRight);
    }
}
