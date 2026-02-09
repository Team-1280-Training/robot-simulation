package frc.robot.simulation;

import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.CANcoderSimState;
import com.ctre.phoenix6.sim.ChassisReference;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

import frc.robot.arm.ArmConst;

/** Class that adds simulation capabilities to the arm subsystem internally */
public class ArmSimulation {
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

    public ArmSimulation() {
        motorSim.Orientation = ChassisReference.CounterClockwise_Positive;
    }

    public Angle getSimAngle() {
        return encoder.getPosition().getValue();
    }

    /** Call this method in a simulationPeriodic() method */
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
