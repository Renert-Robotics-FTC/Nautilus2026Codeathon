package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveSubsystem {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    private IMU imu;

    public DriveSubsystem(HardwareMap hardwareMap) {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        //reversing the motors
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));

        imu.initialize(parameters);
    }


    public void drive(double x, double y, double turn){

        //getting angles and stuff
        double heading = imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.RADIANS);

        //joystick mvt on robot heading
        double cos = Math.cos(heading);
        double sin = Math.sin(heading);

        double fieldX = x * cos - y * sin;
        double fieldY = x * sin + y * cos;

        //calculating power for the wheels
        double frontRightPower = fieldY - fieldX - turn;
        double frontLeftPower = fieldY + fieldX + turn;
        double backRightPower = fieldY + fieldX - turn;
        double backLeftPower = fieldY - fieldX + turn;

        //finding the biggest motor power
        double max = Math.abs(frontRightPower);

        max = Math.max(max, Math.abs(frontLeftPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        max = Math.max(max, 1.0);

        //now sending power to the motors
        frontRight.setPower(frontRightPower / max);
        frontLeft.setPower(frontLeftPower / max);
        backRight.setPower(backRightPower / max);
        backLeft.setPower(backLeftPower / max);

        }

    public void stop(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    //reset
    public void resetHeading() {
        imu.resetYaw();
    }


    }




