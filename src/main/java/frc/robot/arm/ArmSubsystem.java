package frc.robot.arm;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
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

    private Angle targetAngle = Rotations.of(0);

    public ArmSubsystem() {
        motor.getConfigurator().apply(ArmConfig.motorConfig);
        encoder.getConfigurator().apply(ArmConfig.encoderConfig);
        System.out.println(getAngle().in(Rotations));
        moveAngle(getAngle()); // Initialize the arm to its own position
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

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("angle (rot)", () -> getAngle().in(Rotations), null);
        builder.addDoubleProperty(
                "target angle (rot)",
                () -> targetAngle.in(Rotations),
                (angle) -> moveAngle(Rotations.of(angle)));
        builder.addDoubleProperty(
                "setpoint angle (rot)",
                () -> motor.getClosedLoopReference().getValueAsDouble(),
                null);

        Slot0Configs slot0 = ArmConfig.motorConfig.Slot0;
        builder.addDoubleProperty(
                "tuning/kP",
                () -> slot0.kP,
                (kP) -> {
                    slot0.kP = kP;
                    motor.getConfigurator().apply(slot0);
                });
        builder.addDoubleProperty(
                "tuning/kD",
                () -> slot0.kD,
                (kD) -> {
                    slot0.kD = kD;
                    motor.getConfigurator().apply(slot0);
                });
        builder.addDoubleProperty(
                "tuning/kV",
                () -> slot0.kV,
                (kV) -> {
                    slot0.kV = kV;
                    motor.getConfigurator().apply(slot0);
                });
        builder.addDoubleProperty(
                "tuning/kG",
                () -> slot0.kG,
                (kG) -> {
                    slot0.kG = kG;
                    motor.getConfigurator().apply(slot0);
                });
        builder.addDoubleProperty(
                "tuning/kA",
                () -> slot0.kA,
                (kA) -> {
                    slot0.kA = kA;
                    motor.getConfigurator().apply(slot0);
                });

        MotionMagicConfigs motionMagic = ArmConfig.motorConfig.MotionMagic;
        builder.addDoubleProperty(
                "tuning/vel",
                () -> motionMagic.MotionMagicCruiseVelocity,
                (velocity) -> {
                    motionMagic.MotionMagicCruiseVelocity = velocity;
                    motor.getConfigurator().apply(motionMagic);
                });
        builder.addDoubleProperty(
                "tuning/accel",
                () -> motionMagic.MotionMagicAcceleration,
                (acceleration) -> {
                    motionMagic.MotionMagicAcceleration = acceleration;
                    motor.getConfigurator().apply(motionMagic);
                });
    }
}
