package frc.robot.elevator;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(ElevatorConst.MOTOR_ID);

    public ElevatorSubsystem() {
        motor.getConfigurator().apply(ElevatorConfig.motorConfig);
    }

    public void moveHeightFraction(double heightFraction) {
        motor.setControl(new MotionMagicVoltage(MathUtil.clamp(heightFraction, 0, 1)));
    }
}
