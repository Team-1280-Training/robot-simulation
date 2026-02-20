package frc.robot.arm;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(ArmConst.MOTOR_ID);
    private final CANcoder encoder = new CANcoder(ArmConst.ENCODER_ID);
    private Angle targetAngle;

    public ArmSubsystem() {
        motor.getConfigurator().apply(ArmConfig.motorConfig);
        encoder.getConfigurator().apply(ArmConfig.encoderConfig);
        targetAngle = getAngle();
    }

    public void moveAngle(Angle angle) {
        targetAngle =
                Rotations.of(
                        MathUtil.clamp(
                                angle.in(Rotations),
                                ArmConst.MIN_ANGLE.in(Rotations),
                                ArmConst.MAX_ANGLE.in(Rotations)));
        motor.setControl(new MotionMagicVoltage(targetAngle));
    }

    public Angle getAngle() {
        return encoder.getPosition().getValue();
    }

    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("angle degrees", () -> getAngle().in(Degrees), null);
        builder.addDoubleProperty(
                "target angle",
                () -> targetAngle.in(Degrees),
                (angle) -> moveAngle(Degrees.of(angle)));
    }
}
