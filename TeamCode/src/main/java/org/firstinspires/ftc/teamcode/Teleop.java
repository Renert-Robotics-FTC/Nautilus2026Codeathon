package org.firstinspires.ftc.teamcode;

//imports
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Main Teleop")
public class Teleop extends LinearOpMode {


    @Override

    public void runOpMode(){

        //create drive subsystem
        DriveSubsystem drive = new DriveSubsystem(hardwareMap);

        telemetry.addLine("Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            //getting driver controls
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // Tell the drive subsystem what to do
            drive.drive(y, x, rx);



        }

    }


}
