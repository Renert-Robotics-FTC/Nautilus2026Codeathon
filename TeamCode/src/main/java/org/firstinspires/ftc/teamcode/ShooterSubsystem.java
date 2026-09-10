package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;
public class ShooterSubsystem {

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


    }
    public void update(boolean intake, boolean score) {
        boolean hasCharge = beambreak.getState();

        if (intake) {
            shooter.setPower(-0.8);

            if (hasCharge) {
                indexer.setPower(0);
            } else {
                indexer.setPower(-0.8);
            }
        }else if (score) {
            shooter.setPower(1.0);
            indexer.setPower(1.0);
        } else {
            shooter.setPower(0);
            indexer.setPower(0);
        }
    }
}