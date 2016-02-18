/**
 * Created by spmce on 2/9/2016.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;

public class RobotTelemetry extends RobotHardware {

    public RobotTelemetry() {
        telemetry.addData("003" , "Robot Telemetry Constructor");
    }

    public void init() {
        super.init();
        telemetry.addData("1", "Init Hook Position: " + getPosition(hook));
        telemetry.addData("2", "Init Spinner Position: " + getPosition(spinner));
        telemetry.addData("3" , "Init Left Drive Power: " + getPower(left) + " , " + leftDrive);
        telemetry.addData("4" , "Init Right Drive Power: " + getPower(right)+ " , " + rightDrive);
        telemetry.addData("5" , "Init Back Left Drive Power: " + getPower(backLeft) + " , " + backLeftDrive);
        telemetry.addData("6" , "Init Back Right Drive Power: " + getPower(backRight) + " , " + backRightDrive);
    }
    public void loop() {
        super.init();
        init();
    }
}
