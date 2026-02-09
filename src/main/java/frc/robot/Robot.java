// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.arm.ArmConst;
import frc.robot.arm.ArmSubsystem;
import frc.robot.drivetrain.CommandSwerveDrivetrain;
import frc.robot.drivetrain.TunerConstants;
import frc.robot.elevator.ElevatorConst;
import frc.robot.elevator.ElevatorSubsystem;
import frc.robot.simulation.ArmSimulation;
import frc.robot.simulation.ElevatorSimulation;

public class Robot extends TimedRobot {
    private final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    private final ElevatorSubsystem elevator = new ElevatorSubsystem();
    private final ArmSubsystem arm = new ArmSubsystem();

    private final CommandXboxController controller = new CommandXboxController(0);

    private final Field2d field = new Field2d();
    private final StructPublisher<Pose2d> posePublisher =
            NetworkTableInstance.getDefault().getStructTopic("Robot Pose", Pose2d.struct).publish();

    public Robot() {
        initDashboard();
        initBindings();
    }

    private void initDashboard() {
        SmartDashboard.putData("Field", field);
        posePublisher.set(Pose2d.kZero);
        SmartDashboard.putData("Mechanism", mechanism);
        SmartDashboard.putData("Elevator", elevator);
        SmartDashboard.putData("Arm", arm);
    }

    private void initBindings() {
        // drive bindings
        double speed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond) * 0.15;
        double angularSpeed = RotationsPerSecond.of(1.0).in(RadiansPerSecond);
        final SwerveRequest.FieldCentric driveRequest =
                new SwerveRequest.FieldCentric()
                        .withDeadband(speed * 0.1)
                        .withRotationalDeadband(angularSpeed * 0.1)
                        .withDriveRequestType(DriveRequestType.Velocity);
        drivetrain.setDefaultCommand(
                drivetrain.applyRequest(
                        () ->
                                driveRequest
                                        .withVelocityX(controller.getLeftY() * speed)
                                        .withVelocityY(controller.getLeftX() * speed)
                                        .withRotationalRate(
                                                controller.getRightX() * angularSpeed)));

        // elevator bindings
        controller.y().onTrue(elevator.runOnce(() -> elevator.moveHeightFraction(1.0)));
        controller.a().onTrue(elevator.runOnce(() -> elevator.moveHeightFraction(0.0)));

        // arm bindings
        controller.b().onTrue(arm.runOnce(() -> arm.moveAngle(ArmConst.MIN_ANGLE)));
        controller.x().onTrue(arm.runOnce(() -> arm.moveAngle(ArmConst.MAX_ANGLE)));
        controller.povUp().onTrue(arm.runOnce(() -> arm.moveAngle(Rotations.of(0.0))));
    }

    public Pose2d getPose() {
        return drivetrain.getState().Pose;
    }

    @Override
    public void robotInit() {}

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        field.setRobotPose(getPose());
        posePublisher.set(getPose());
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {}

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}

    // simulation code below: trainees, ignore this
    private final ElevatorSimulation elevatorSim = new ElevatorSimulation();
    private final ArmSimulation armSim = new ArmSimulation();

    private final Mechanism2d mechanism = new Mechanism2d(1.5, 2.5);
    private final MechanismRoot2d mechanismRoot = mechanism.getRoot("root", 0.75, 0.05);
    private final MechanismLigament2d elevatorLigament =
            mechanismRoot.append(
                    new MechanismLigament2d(
                            "elevator",
                            ElevatorConst.MIN_HEIGHT.in(Meters),
                            90,
                            16,
                            new Color8Bit(Color.kGray)));
    private final MechanismLigament2d armLigament =
            elevatorLigament.append(
                    new MechanismLigament2d(
                            "arm",
                            ArmConst.LENGTH.in(Meters),
                            ArmConst.MAX_ANGLE.in(Degrees) - 90,
                            10,
                            new Color8Bit(64, 64, 64)));

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {
        elevatorSim.simulationPeriodic();
        armSim.simulationPeriodic();

        elevatorLigament.setLength(elevatorSim.getSimHeight().in(Meters));
        armLigament.setAngle(armSim.getSimAngle().in(Degrees) - 90);
    }
}
