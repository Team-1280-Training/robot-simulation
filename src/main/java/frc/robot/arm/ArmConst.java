package frc.robot.arm;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.MomentOfInertia;

public final class ArmConst {
    public static final int MOTOR_ID = 16;
    public static final int ENCODER_ID = 57;

    public static final Angle MIN_ANGLE = Degrees.of(-90.0);
    public static final Angle MAX_ANGLE = Degrees.of(90.0);

    public static final double GEARING = 45.0;
    public static final MomentOfInertia MOMENT = KilogramSquareMeters.of(0.8); // Estimate
    public static final Distance LENGTH = Meters.of(0.64);
}
