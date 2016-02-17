/**
 * Created by spmce on 2/9/2016.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;

public class RobotTeleOp extends RobotTelemetry {

    private double l,r,bL,bR;

    public RobotTeleOp() {
        setDriveTrain(0.0);
    }

    @Override
    public void loop() {
        l = -gamepad1.left_trigger;
        r = -gamepad1.right_trigger;
        bL = gamepad1.left_stick_y;
        bR = gamepad1.right_stick_y;

        setDriveTrain(l,r,bL,bR);
    }
}
