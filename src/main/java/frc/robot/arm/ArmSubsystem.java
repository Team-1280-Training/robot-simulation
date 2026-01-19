package frc.robot.arm;

import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.CANcoderSimState;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(ArmConst.MOTOR_ID);
    private final CANcoder encoder = new CANcoder(ArmConst.ENCODER_ID);

    private final TalonFXSimState motorSim = motor.getSimState();
    private final CANcoderSimState encoderSim = encoder.getSimState();
    private final SingleJointedArmSim armSim =
            new SingleJointedArmSim(
                    DCMotor.getKrakenX60(1),
                    ArmConst.GEARING,
                    ArmConst.MOMENT.in(KilogramSquareMeters),
                    ArmConst.LENGTH.in(Meters),
                    ArmConst.MIN_ANGLE.in(Radians),
                    ArmConst.MAX_ANGLE.in(Radians),
                    true,
                    ArmConst.MAX_ANGLE.in(Radians));

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
        builder.addDoubleProperty("motor sim voltage", motorSim::getMotorVoltage, null);
    }

    @Override
    public void simulationPeriodic() {
        motorSim.setSupplyVoltage(RobotController.getBatteryVoltage());
        encoderSim.setSupplyVoltage(RobotController.getBatteryVoltage());
        armSim.setInputVoltage(motorSim.getMotorVoltageMeasure().in(Volts));
        armSim.update(0.020);
        // NOTE: arm simulation does not account for motor braking

        Angle angle = Radians.of(armSim.getAngleRads());
        encoderSim.setRawPosition(angle);
    }
}
