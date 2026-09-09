package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class IntakeSubsystem {
    private DcMotor intakeMotor;
    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

    }
    public void setPower(double power) {
        intakeMotor.setPower(power);
    }
}
