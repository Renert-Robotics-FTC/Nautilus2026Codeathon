package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
public class CoreSubsystem {

    public CoreSubsystem() {
    }

    public double getAutoAimPower(double currentX, double currentY, double currentHeading) {
        double angleToGoal = Math.toDegrees(Math.atan2(Constants.TARGET_Y - currentY, Constants.TARGET_X - currentX));
        double error = angleToGoal - currentHeading;
        while (error > 180) error -= 360;
        while (error < -180) error += 360;
        return error * Constants.TURN_P;

    }
}