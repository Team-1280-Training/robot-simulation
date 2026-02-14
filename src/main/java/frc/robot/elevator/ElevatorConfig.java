package frc.robot.elevator;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import edu.wpi.first.units.measure.Current;

public final class ElevatorConfig {
    public static final Current CURRENT_LIMIT = Amps.of(80.0);

    public static final TalonFXConfiguration motorConfig = new TalonFXConfiguration();

    static {
        motorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT.in(Amps);
        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // positive is up

        // PID unit: Height fraction (0.0-1.0)
        motorConfig.Slot0.kP = 40.0;
        motorConfig.Slot0.kI = 0.0;
        motorConfig.Slot0.kD = -0.5;
        motorConfig.Slot0.kS = 0.0;
        motorConfig.Slot0.kV = 5.0;
        motorConfig.Slot0.kA = 0.05;
        motorConfig.Slot0.kG = 0.101;
        motorConfig.Slot0.GravityType = GravityTypeValue.Elevator_Static;
        motorConfig.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseVelocitySign;
        motorConfig.MotionMagic.MotionMagicCruiseVelocity = 0.45;
        motorConfig.MotionMagic.MotionMagicAcceleration = 2.0;
        motorConfig.MotionMagic.MotionMagicJerk = 0.0;

        motorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;
        // Make the motor mechanism position represent height fraction (0.0 to 1.0)
        motorConfig.Feedback.SensorToMechanismRatio =
                ElevatorConst.ROTOR_TO_MECHANISM_RATIO.in(Rotations);
    }
}
