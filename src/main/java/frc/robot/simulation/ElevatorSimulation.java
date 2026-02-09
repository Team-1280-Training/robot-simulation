package frc.robot.simulation;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.ChassisReference;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;

import frc.robot.elevator.ElevatorConst;

public class ElevatorSimulation {
    // Multiple TalonFX instances with the same motor ID share the same state
    private final TalonFX motor = new TalonFX(ElevatorConst.MOTOR_ID);

    private final TalonFXSimState motorSim = motor.getSimState();
    private final ElevatorSim elevatorSim =
            new ElevatorSim(
                    2.5,
                    0.01,
                    DCMotor.getKrakenX60(1),
                    ElevatorConst.MIN_HEIGHT.in(Meters),
                    ElevatorConst.MAX_HEIGHT.in(Meters),
                    true,
                    ElevatorConst.MIN_HEIGHT.in(Meters));

    public ElevatorSimulation() {
        motorSim.Orientation = ChassisReference.Clockwise_Positive;
    }

    public Distance getSimHeight() {
        return ElevatorConst.MIN_HEIGHT.plus(
                ElevatorConst.MAX_HEIGHT
                        .minus(ElevatorConst.MIN_HEIGHT)
                        .times(
                                motor.getPosition()
                                        .getValueAsDouble())); // Simple linear interpolation
    }

    /** Call this method in a simulationPeriodic() method */
    public void simulationPeriodic() {
        motorSim.setSupplyVoltage(RobotController.getBatteryVoltage());
        elevatorSim.setInputVoltage(motorSim.getMotorVoltageMeasure().in(Volts));
        elevatorSim.update(0.020);

        Distance height = Meters.of(elevatorSim.getPositionMeters());
        Dimensionless heightFraction =
                height.minus(ElevatorConst.MIN_HEIGHT)
                        .div(ElevatorConst.MAX_HEIGHT.minus(ElevatorConst.MIN_HEIGHT));
        Angle rotorPosition = ElevatorConst.ROTOR_TO_MECHANISM_RATIO.times(heightFraction);
        motorSim.setRawRotorPosition(rotorPosition);
    }
}
