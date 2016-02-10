/**
 * Created by spmce on 2/9/2016.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
/**
 * Provides a single hardware access point between custom op-modes and the OpMode class for this Robot.
 * @author Shane McEnaney
 * @version 2016
 */
public class RobotHardware extends OpMode {

    private boolean warningGenerated = false;
    private String warningMessage;

    protected DcMotor leftDrive;
    protected DcMotor rightDrive;

    public RobotHardware() {
        mapDevice(leftDrive);
        mapDevice(rightDrive);
    }

    @Override
    public void init() {
        warningGenerated = false; // Provide telemetry data to a class user
        warningMessage = "Can't map: ";
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

    public void mapDevice (HardwareDevice device) {
        try {
            device = hardwareMap.dcMotor.get(String.valueOf(device));
        } catch (Exception opModeException) {
            setWarningMessage(String.valueOf(device));
            DbgLog.msg(opModeException.getLocalizedMessage());
        }
    }

    public void setMotor(DcMotor motor, double power) {
        motor.setPower(power);
    }

    public void setMotor(double power) {
        leftDrive.setPower(power);
        rightDrive.setPower(power);
    }
}
