package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;


    public class odo {
        private GoBildaPinpointDriver pinpoint;
        private double x, y, heading;

        public odo(HardwareMap hardwareMap) {
            pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
            pinpoint.setOffsets(0, 0, DistanceUnit.INCH); // You'll tune these later
            pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
            pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        }

        public void update() {
            pinpoint.update();
            Pose2D pose = pinpoint.getPosition();
            x = pose.getX(DistanceUnit.INCH);
            y = pose.getY(DistanceUnit.INCH);
            heading = pose.getHeading(AngleUnit.DEGREES);
        }

        public double getX() { return x; }
        public double getY() { return y; }
        public double getHeading() { return heading; }
    }
