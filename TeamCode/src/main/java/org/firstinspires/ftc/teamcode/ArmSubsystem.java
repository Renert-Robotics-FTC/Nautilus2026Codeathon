package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
//initializing things

public class ArmSubsystem {
    private DcMotorEx armMotor;
    public ArmSubsystem(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotorEx.class, "arm");
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        //Sets up encoder
    }

        public int getCurrentPosition() {
            return armMotor.getCurrentPosition();
            //Reads current encoder
        }

        private int targetPosition;
    private static final int INTAKE_POSITION=200;
    private static final int NODE_POSITION=900;
    //makeshift values

    public void setTargetPosition(int Position) {
        targetPosition = Position;
    }
public void goToIntake() {
    setTargetPosition(INTAKE_POSITION);
}
public void goToNode(){
        setTargetPosition(NODE_POSITION);
}

//Calculating some errors so it knows how far to move!!
    public int getError(){
        return targetPosition-getCurrentPosition();

    }

    //Involving the motors which is P with a placehold value
    private static final double P=0.005;
    public double getPOutput=getError()*P;

    //add a derivative to slow it down
    private int previousError;
    private static final double D = 0.001;

    public double getDOutput() {
        int currentError = getError();
        double derivative = currentError - previousError;
        previousError = currentError;

        return derivative * D;
    }

    public double getPOutput() {
        return getError() * P;
    }

    public double getMotorPower() {
        return getPOutput() + getDOutput();
    }

    // limits the motor power
    public void update() {
        double power = getMotorPower();

        if (Math.abs(getError()) <= POSITION_TOLERANCE) {
            power = 0;
        }

        power = Math.max(-1.0, Math.min(1.0, power));

        armMotor.setPower(power);
    }
    //building a tolerance so it stops calculating after a while
    private static final int POSITION_TOLERANCE=20;

    public void resetEncoder(){
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
//Using arm methods



    }















