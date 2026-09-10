package org.firstinspires.ftc.teamcode;
//imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "Main Teleop")
public class Teleop extends LinearOpMode{


    @Override
    public void runOpMode(){
        //create subsystem objects here
        odo odo = new odo(hardwareMap);
        ArmSubsystem arm=new ArmSubsystem(hardwareMap);
        //Adding in the Arm Subsystem!
        waitForStart();


        while (opModeIsActive()) {
            odo.update();
            telemetry.addData("X", odo.getX());
            telemetry.addData("Y", odo.getY());
            telemetry.addData("Heading", odo.getHeading());
            //Fetching stuff for drivers

            arm.update();
            if (gamepad1.a ){
                arm.aimAtTheCore();
            }

        }

    }


}
