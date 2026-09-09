package org.firstinspires.ftc.teamcode;
//imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {
        //create subsystem objects here
        boolean intake = false;
        boolean shoot = false;

        IntakeSubsystem intakeSubsystem = new IntakeSubsystem(hardwareMap);

        waitForStart();
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        while (opModeIsActive()) {
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
