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
        motorConfig.MotorOutput.Inverted =
                InvertedValue.Clockwise_Positive; // positive should make it go up
        // Assuming CANcoder is after gear reduction, and encoder is zeroed at horizontal
        motorConfig.Slot0.GravityType = GravityTypeValue.Elevator_Static;

        // PID unit: Height fraction (0.0-1.0)
        motorConfig.Slot0.kG = -0.3; // NOTE: a spring exists on the elevator, holding it up
        motorConfig.Slot0.kS = 0.0;
        motorConfig.Slot0.kV = 15.0;
        motorConfig.Slot0.kA = 0.0;
        motorConfig.Slot0.kP = 40.0;
        motorConfig.Slot0.kI = 0.0;
        motorConfig.Slot0.kD = 2.0;
        motorConfig.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseVelocitySign;
        // https://www.chiefdelphi.com/t/motion-magic-help-ctre/483319/2
        motorConfig.MotionMagic.MotionMagicCruiseVelocity = 2.0; // Target cruise velocity in rps
        motorConfig.MotionMagic.MotionMagicAcceleration = 4.0; // Target acceleration in rps/s
        motorConfig.MotionMagic.MotionMagicJerk = 40.0; // Target jerk in rps/(s^2)
        // Use the internal rotor sensor; no remote sensor or encoder
        motorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;
        // Make the motor mechanism position represent height fraction (0.0 to 1.0)
        motorConfig.Feedback.SensorToMechanismRatio =
                ElevatorConst.ROTOR_TO_MECHANISM_RATIO.in(Rotations);
        // Note: RotorToSensorRatio does not apply (not remote sensor)
        motorConfig.ClosedLoopGeneral.ContinuousWrap = false;
    }
}
