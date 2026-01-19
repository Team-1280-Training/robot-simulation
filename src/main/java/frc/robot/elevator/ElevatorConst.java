package frc.robot.elevator;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Rotations;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;

public final class ElevatorConst {
    public static final int MOTOR_ID = 18;

    public static final Distance MIN_HEIGHT = Meters.of(0.17);
    public static final Distance MAX_HEIGHT = Meters.of(1.73);

    /** Amount of rotor turning when going from min height to max height (0% to 100%) */
    public static final Angle ROTOR_TO_MECHANISM_RATIO = Rotations.of(135.0);
}
