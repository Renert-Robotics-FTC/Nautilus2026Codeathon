package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;
public class ShooterSubsystem {

    IntakeSubsystem intaker;
    DcMotor shooter;
    DcMotor indexer;
    DigitalChannel beambreak;
    public ShooterSubsystem (HardwareMap hwmap) {
        shooter = hwmap.get(DcMotor.class, "shooter");
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        indexer = hwmap.get(DcMotor.class, "indexer");
        indexer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        indexer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        beambreak = hwmap.get(DigitalChannel.class,"beambreak" );
        beambreak.setMode(DigitalChannel.Mode.INPUT);

        intaker = new IntakeSubsystem(hwmap);


    }
    public void update () {
        boolean hasCharge = beambreak.getState();

            if(hasCharge) {
                shooter.setPower(-1);
                indexer.setPower(-1);
                intaker.setPower(1);
            } else {
                shooter.setPower(1);
                indexer.setPower(1);
                intaker.setPower(-1);

            }



        }
}