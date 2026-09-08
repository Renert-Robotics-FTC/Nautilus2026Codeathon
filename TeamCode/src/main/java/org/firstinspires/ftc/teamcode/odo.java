package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class odo {

    private GoBildaPinpointDriver pinpoint;

    private double x;
    private double y;
    private double heading;

    public odo(HardwareMap hardwareMap) {

        // "odo" is the name you would give the Pinpoint
        // in the robot configuration
        pinpoint = hardwareMap.get(
                GoBildaPinpointDriver.class,
                "odo"
        );

        /*
         * ROBOT-SPECIFIC VALUES
         * These cannot be finalized until the robot exists.
         *
         * xOffset = sideways position of forward pod
         * yOffset = forward/backward position of strafe pod
         *
         * Units are millimeters.
         */
        pinpoint.setOffsets(0, 0);

        /*
         * Change this depending on which odometry pods
         * your team uses.
         */
        pinpoint.setEncoderResolution(
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
        );

        /*
         * These may need to be changed to REVERSED
         * once the actual robot exists.
         */
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );
    }

    public void reset() {

        // Robot must be stationary when this is called
        pinpoint.resetPosAndIMU();
    }

    public void update() {

        // Tell the Pinpoint to get the newest measurements
        pinpoint.update();

        Pose2D pose = pinpoint.getPosition();

        x = pose.getX(DistanceUnit.INCH);
        y = pose.getY(DistanceUnit.INCH);
        heading = pose.getHeading(AngleUnit.DEGREES);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeading() {
        return heading;
    }

    public Pose2D getPose() {
        return pinpoint.getPosition();
    }
}