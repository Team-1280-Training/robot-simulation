package frc.robot.arm;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(ArmConst.MOTOR_ID);
    private final CANcoder encoder = new CANcoder(ArmConst.ENCODER_ID);

    public ArmSubsystem() {
        motor.getConfigurator().apply(ArmConfig.motorConfig);
        encoder.getConfigurator().apply(ArmConfig.encoderConfig);
    }

    public void moveAngle(Angle angle) {
        motor.setControl(new MotionMagicVoltage(angle));
    }

    public Angle getAngle() {
        return encoder.getPosition().getValue();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("angle (rot)", () -> getAngle().in(Rotations), null);
    }
}
