/**
 * Created by spmce on 2/9/2016.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;

public class RobotTeleOp extends RobotTelemetry {

    public RobotTeleOp() {
        setMotor(leftDrive,0.0);
        setMotor(rightDrive,0.0);
    }
}
