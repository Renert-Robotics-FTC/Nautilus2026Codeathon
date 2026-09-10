package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;




public class DriveSubsystem {
    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private IMU imu;

    public DriveSubsystem(HardwareMap hardwareMap) {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        // Set directions
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Init IMU for Field-Centric
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));
        imu.initialize(parameters);
    }

    public void drive(double x, double y, double turn) {
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double cos = Math.cos(heading);
        double sin = Math.sin(heading);
        double fieldX = x * cos - y * sin;
        double fieldY = x * sin + y * cos;

        double fr = fieldY - fieldX - turn;
        double fl = fieldY + fieldX + turn;
        double br = fieldY + fieldX - turn;
        double bl = fieldY - fieldX + turn;

        double max = Math.max(1.0, Math.max(Math.abs(fr), Math.max(Math.abs(fl), Math.max(Math.abs(br), Math.abs(bl)))));

        frontRight.setPower(fr / max);
        frontLeft.setPower(fl / max);
        backRight.setPower(br / max);
        backLeft.setPower(bl / max);
    }

    public void resetHeading() {
        imu.resetYaw();
    }
}