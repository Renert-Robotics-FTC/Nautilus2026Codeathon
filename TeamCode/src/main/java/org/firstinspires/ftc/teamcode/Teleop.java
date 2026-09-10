package org.firstinspires.ftc.teamcode;
//imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * CONTROL SCHEME:
 * - Left Stick: Move robot (Field Centric)
 * - Right Stick: Rotate robot
 * - Left Bumper: HOLD for Core Auto-Aim (Odometry based)
 * - Button A: Intake Charge (Automatic stop via Beam Break)
 * - Button X: Score/Shoot Charge
 * - Button Y: Reset Gyro Heading
 */
@TeleOp(name = "Main Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() {
        DriveSubsystem drive = new DriveSubsystem(hardwareMap);
        CoreSubsystem core = new CoreSubsystem();
        odo odometry = new odo(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();
        //drive subsystem for core-auto-aim

        while (opModeIsActive()) {
            //put loop code here
            shooter.update();
            odometry.update();
            //1) auto-aim logic
            double autoAim;
            if (gamepad1.left_bumper) {
                autoAim = core.getAutoAimPower(odometry.getX(), odometry.getY(), odometry.getHeading());
            } else{autoAim = 0;
            }
            //2) drive logic (field-centric + auto-aim)
            drive.drive(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    -gamepad1.right_stick_x + autoAim
            );

            //3)reset heading (in case the gyro drifts)
            if (gamepad1.y) {
                drive.resetHeading();
            }
            telemetry.update();



        }

    }
}