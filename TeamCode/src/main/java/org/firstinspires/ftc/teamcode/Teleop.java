package org.firstinspires.ftc.teamcode;

//imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Main Teleop")
public class Teleop extends LinearOpMode {

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Teleop extends LinearOpMode {

    @Override


    public void runOpMode() {

        //create drive subsystem
        DriveSubsystem drive = new DriveSubsystem(hardwareMap);

        telemetry.addLine("Ready");
        telemetry.update();

        public void runOpMode () {
            //create subsystem objects here
            boolean intake = false;
            boolean shoot = false;

            IntakeSubsystem intakeSubsystem = new IntakeSubsystem(hardwareMap);


            waitForStart();
            ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

            while (opModeIsActive()) {


                //getting driver controls
                double y = -gamepad1.left_stick_y;
                double x = gamepad1.left_stick_x;
                double rx = gamepad1.right_stick_x;

                // Tell the drive subsystem what to do
                drive.drive(y, x, rx);


            }

            //put loop code here
            intake = gamepad1.a;
            if (intake) {
                intakeSubsystem.setPower(1.0);
            } else {
                intakeSubsystem.setPower(0);
            }


            telemetry.update();
            shooter.update(intake, shoot);
        }


    }
}
