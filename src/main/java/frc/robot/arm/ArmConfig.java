package frc.robot.arm;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import edu.wpi.first.units.measure.Current;

public final class ArmConfig {
    public static final Current CURRENT_LIMIT = Amps.of(80.0);

    public static final TalonFXConfiguration motorConfig = new TalonFXConfiguration();

    static {
        motorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT.in(Amps);
        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        motorConfig.MotorOutput.Inverted =
                InvertedValue.CounterClockwise_Positive; // positive should make it go up
        // Assuming CANcoder is after gear reduction, and encoder is zeroed at horizontal
        motorConfig.Slot0.GravityType = GravityTypeValue.Arm_Cosine;
        // PID unit: Rotations
        motorConfig.Slot0.kG = 0.7;
        motorConfig.Slot0.kS = 0.0;
        motorConfig.Slot0.kV = 0.0;
        motorConfig.Slot0.kA = 0.0;
        motorConfig.Slot0.kP = 30.0;
        motorConfig.Slot0.kI = 0.0;
        motorConfig.Slot0.kD = 1.0;
        motorConfig.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseVelocitySign;
        // https://www.chiefdelphi.com/t/motion-magic-help-ctre/483319/2
        motorConfig.MotionMagic.MotionMagicCruiseVelocity = 0.2; // Target cruise velocity in rps
        motorConfig.MotionMagic.MotionMagicAcceleration = 0.4; // Target acceleration in rps/s
        motorConfig.MotionMagic.MotionMagicJerk = 0.8; // Target jerk in rps/(s^2)
        motorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
        motorConfig.Feedback.FeedbackRemoteSensorID = ArmConst.ENCODER_ID;
        motorConfig.Feedback.SensorToMechanismRatio = 1.0;
        motorConfig.ClosedLoopGeneral.ContinuousWrap = false;
    }

    public static final CANcoderConfiguration encoderConfig = new CANcoderConfiguration();

    static {
        // (unknown encoder configuration: never saved to code)
    }
}
