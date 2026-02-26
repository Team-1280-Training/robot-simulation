package frc.robot.elevator;

import static edu.wpi.first.units.Units.Meters;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(ElevatorConst.MOTOR_ID);
    private double targetHeightFraction = 0.0;

    public ElevatorSubsystem() {
        motor.getConfigurator().apply(ElevatorConfig.motorConfig);
    }

    public void moveHeightFraction(double heightFraction) {
        targetHeightFraction = MathUtil.clamp(heightFraction, 0.0, 1.0);
        motor.setControl(new MotionMagicVoltage(targetHeightFraction));
    }

    public double getHeightFraction() {
        return motor.getPosition().getValueAsDouble();
    }

    public Distance getHeight() {
        double range = ElevatorConst.MAX_HEIGHT.in(Meters) - ElevatorConst.MIN_HEIGHT.in(Meters);
        double height = range * getHeightFraction() + ElevatorConst.MIN_HEIGHT.in(Meters);
        return Meters.of(height);
    }

    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("height fraction", () -> getHeightFraction(), null);
        builder.addDoubleProperty("Height", () -> getHeight().in(Meters), null);
        builder.addDoubleProperty(
                "target height fraction",
                () -> targetHeightFraction,
                (heightFraction) -> moveHeightFraction(heightFraction));
    }
}
