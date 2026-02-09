package frc.robot.elevator;

import static edu.wpi.first.units.Units.Meters;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(ElevatorConst.MOTOR_ID);

    public ElevatorSubsystem() {
        motor.getConfigurator().apply(ElevatorConfig.motorConfig);
    }

    public void moveHeightFraction(double heightFraction) {
        motor.setControl(new MotionMagicVoltage(heightFraction));
    }

    public double getHeightFraction() {
        return motor.getPosition().getValueAsDouble();
    }

    public Distance getHeight() {
        return ElevatorConst.MIN_HEIGHT.plus(
                ElevatorConst.MAX_HEIGHT
                        .minus(ElevatorConst.MIN_HEIGHT)
                        .times(getHeightFraction())); // Simple linear interpolation
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("height (m)", () -> getHeight().in(Meters), null);
        builder.addDoubleProperty("height fraction", this::getHeightFraction, null);
    }
}
