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
        waitForStart();
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        while (opModeIsActive()) {
            //put loop code here
            telemetry.update();
            shooter.update(intake, shoot);
        }


}
}
