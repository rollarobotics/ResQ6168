/**
 * Created by spmce on 2/9/2016.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;

public class RobotTelemetry extends RobotHardware {
  
  public RobotTelemetry() {
    
  }
  
  public void init() {
    telemetry.addData("1" , "Init Hook Position" + getPosition(hook));
    telemetry.addData("2" , "Init Spinner Position" + getPosition(spinner));
  }
}
