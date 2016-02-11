package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by team on 12/11/2015.
 */
class AutonomousMethods extends BigBerthaTelemetry
{
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    public AutonomousMethods(DcMotor leftDrive, DcMotor rightDrive)
    {
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;

    }

    public void moveForward(float distance, float speed) //enter distance in inches
    {
        //resetLeftDriveEncoder();
        //distance = distance*1400;

        //runUsingLeftDriveEncoder(distance, leftDrivePower);
        //runUsingRightDriveEncoder(distance, rightDrivePower);
    }
//SHANE IS A SCRUB.
//SHANE MUST CONSTRUCT MORE PYLONS.
//SHANE GET OFF ME.
//SHANE:WHAT DO YOU CALL A BIRD IN A HOUSE?
//SHANE JUST HIT A THREE-PEAT.
    /*private void runUsingRightDriveEncoder(float distance, float rightDrivePower)
    {
        rightDrive.setTargetPosition();
    }*/

    private void runUsingLeftDriveEncoder(float distance, float leftDrivePower)
    {

    }




}
