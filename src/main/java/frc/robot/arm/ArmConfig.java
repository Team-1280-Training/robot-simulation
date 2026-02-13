package frc.robot.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

public final class ArmConfig {
    public static final TalonFXConfiguration motorConfig = new TalonFXConfiguration();

    static {

        // PID unit: Rotations
        motorConfig.Slot0.kP = 30.0;
        motorConfig.Slot0.kI = 0.0;
        motorConfig.Slot0.kD = 1.0;
        motorConfig.Slot0.kS = 0.0;
        motorConfig.Slot0.kV = 0.0;
        motorConfig.Slot0.kA = 0.0;
        motorConfig.Slot0.kG = 0.7;
        motorConfig.Slot0.GravityType = GravityTypeValue.Arm_Cosine;
        motorConfig.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseVelocitySign;
        motorConfig.MotionMagic.MotionMagicCruiseVelocity = 0.2; // Target cruise velocity in rps
        motorConfig.MotionMagic.MotionMagicAcceleration = 0.4; // Target acceleration in rps/s
        motorConfig.MotionMagic.MotionMagicJerk = 0.8; // Target jerk in rps/(s^2)

        // Use the encoder for PID feedback
        motorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
        motorConfig.Feedback.FeedbackRemoteSensorID = ArmConst.ENCODER_ID;
    }

    public static final CANcoderConfiguration encoderConfig = new CANcoderConfiguration();

    static {
        encoderConfig.MagnetSensor.MagnetOffset = 0.0;
    }
}
