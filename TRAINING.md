# Training
Welcome to advanced programming training!

You will learn WPILib coding and general FRC programming.

> Note: Please refrain from copying the code snippets from the instructions into your code.

## Setup

### GitHub
First, you must *fork* your own copy of the `Team-1280-Training.robot-simulation` repository because you don't have write access for that repo.

Near the top, click the `Fork` button. In the opened page, you can use the default configuration; press `Create fork`.

Once you have created the repository, open your WPILib VS Code 2026 application.

### VS Code

#### Cloning
In VS Code, open the *Command Palette*, by using a keyboard shortcut or clicking the top search bar and typing a `>`. \
Windows, Linux: `F1` or `Ctrl`+`Shift`+`P` \
Mac: `fn`+`F1` or `Command`+`Shift`+`P`

Then, find and select the `>Git: Clone` command. \
If it asks you to sign into GitHub, do that. \
Then press `Clone from GitHub` and select the repository you created earlier.

Select or create a new folder that the repository folder will be in.

> Tip: I recommend creating a folder on your computer called `dev` or `development` that holds all of your programming projeects.

When prompted, click `Open` to open it in the current VS Code window.

#### View
On the left, open `TRAINING.md`. Preview the file using `Ctrl`/`Command`+`Shift`+`V`. \
You can now read from VS Code instead of GitHub.

> Tip: When programming later, drag the `Preview TRAINING.md` tab (near the top) to the right side, to split the window.

> Tip: If at any time you want more space, press `Ctrl`/`Command`+`B` to hide the side bar, or press the sidebar's selected icon.

#### Extension Installation
You will need to install an extension that automatically formats your code upon saving.

Find and select the `>Extensions: Show Recommended Extensions` command.

Install the *Workspace Recommendations* extension: `Google Java Format for VS Code`. Now, auto-formatting will work.

> Note: Many useful settings are enabled in this workspace at `.vscode/settings.json`, near the bottom of the file. If you would like to also have these settings in all workspaces, copy them to your User Settings JSON file (openable with a command).

## Dashboards
Before you write a single line of code, let's learn how to run the robot simulation and view all of the information from the *dashboards*, applications that display operational information about the running robot.

Since this is a simulation (no physical robot), we will also use the dashboards to visualize what the robot looks like and what its mechanisms are doing.

### Running the Robot
For a physical robot, you would normally connect to the robot's WiFi, and then deploy the code to the robot with `>WPILib: Deploy Robot Code` (`Shift`+`F5`). \
Then, you'd use FRC Driver Station to Enable and Disable the robot.

> Tip: If you ever want to build (compile) the codebase without running it anywhere, you can use `>WPILib: Build Robot Code`. This checks that your code compiles, and may resolve uncached dependencies.

Since there is no physical robot, you just need to simulate the robot code instead with `>WPILib: Simulate Robot Code`. \
Run that command now.

Once it builds, the simulation will open a specific dashboard: the *simulation GUI*.

### Simulation GUI
There is a ton of information on the simulation GUI, so let's go through the important parts.

> Tip: To switch windows easily, use `Alt`+`Tab` (Windows/Linux) or `Command`+`Tab` (macOS) keyboard shortcut

Currently, you are viewing the default layout of the sim GUI. The edits you make to the layout are saved locally (to some JSON files in the workspace) for the next time you open the sim GUI.

#### Terminology
Each window on the GUI is a *widget*. Widgets can be dragged around, collapsed, and closed. Some widgets are resizable.

*NetworkTables* is an FRC communications protocol for sharing data over the network. The data is structured into hierarchical tables.

Most of the useful data is available under the *SmartDashboard* table. Later, you will be able to add even more information to it.

*FMS* stands for Field Management System, which is an electronics core that controls an FRC match: networking, robot control, match scores, match periods, etc.

#### Widgets
The top left widget `Robot State` displays the (theoretical/simulated) status of the robot:
- **Disconnected**: the computer is not connected to the robot (via WiFi or ethernet)
- **Disabled**: the robot is not enabled, so motors are all disabled
- **Autonomous**: the robot is in autonomous mode, running without human operation
- **Teleoperated**: the robot is controllable by a human operator
- **Test**: a customizable debug mode (no built-in features)

Under it, the `Timing` widget displays the match time. There are many sources, but FPGA (Field-Programmable Gate Array) time is the primary time source. \
In the sim GUI, time can be paused, stepped incrementally, and resumed.

The `System Joysticks` widget shows what controllers and keyboard-emulated controllers there are. \
I have already added Xbox Controller bindings for `Keyboard 0`, viewable by right-clicking it and pressing `Keyboard 0 Settings`.

In the sim GUI, you need to drag a system joystick to one of the joystick ports under the `Joysticks` widget. \
Add `Keyboard 0` as `Joystick[0]`. Now, you can use your keyboard as the controller later.

The `FMS` widget shows more FMS information.

The `NetworkTables Info` widget (nearer to top) shows debug information for NetworkTables.

The `NetworkTables` widget is the most important widget. It displays all of the NetworkTables data. \
The most important data is under `Transitory Values` in the `SmartDashboard` table. Later, you can send values to it in the code, to track information, especially for debugging.

The `Other Devices` widget shows information about devices such as motor voltage, rotor or encoder positions, etc. It can be very useful to track certain hardware information, if you know their device IDs.

> Tip: You can collapse or close the `Timing`, `System Joysticks`, `Joysticks`, `FMS`, `NetworkTables Info`, and `Other Devices` widgets for space.

At the top, the *menu bar* has a couple of different menus, but the only important one is `NetworkTables`. \

Under `NetworkTables / SmartDashboard`, enable both `Mechanism` and `Field` widgets. Resize both as you wish.

The `Field` widget displays the robot's estimated position and orientation. (It comes from a `Field2d` object published to SmartDashboard in the code.)

The `Mechanism` widget shows our rudimentary simulated mechanisms, which will be explained later.

#### Driving the Robot
To drive the robot, first set the Robot Mode to `Teleoperated`.

Then, use the keyboard controller bindings:
- `WASD` (Left Joystick): move robot
- `IJKL` (Right Joystick): rotate robot
- `TFGH` (Xbox Face Buttons `YXAB`): (no controls currently)

Note that driving is *field-oriented*: it does not depend on the robot rotation, and the forward direction is away from the driver (forward is towards the right) which corresponds to up (`W`) on the joystick.

> Tip: If you are having trouble with the orientation of driving due to the horizontal field widget, imagine you are a driver on the left side of the widget, facing right.

> Note: If the robot isn't responding to controller buttons, check that the Robot Mode is teleoperated. Also, the sim GUI window must be focused for the controls to work.

#### Plotting Values
You can add plots for almost any value on the sim GUI. \

To do this, in the menu bar, select `Plot` and then `New Plot Window`.

This adds a plot window widget `Plot <0>` that can have one or more plots. \
To plot a value, drag it into the plot.

Plot the robot's rotation (degrees), which is in `NetworkTables - Transitory Values - SmartDashboard/Field/Robot`: click the `double[]` dropdown, and drag the `[2]` left value to the plot.

Then, click the three bars on the top right of the widget, and customize the Plot 0:
- Set plot name to `Robot rotation (deg)`
- Set Y-axis minimum and maximum to `-180`, `180`
- Optionally, change the span of time for the plot width, from `10.0` seconds to a shorter or longer period

Optionally, resize the plot window.

Now, try rotating the robot with `J` and `L` keybinds. You can see the plotted value change over time. \
(Note that the rotation value wraps around from `-180` to `180` or vice versa.)

### Glass
The Glass dashboard has an interface just like the simulation GUI, but can also be used for the physical robot.

Simulate the code, and then find and open the Glass 2026 application.

In Glass, in `NetworkTable Settings`, set the `Mode` to `Client (NT4)`. Then set the Team/IP to `localhost` (which is an IP that routes back to your own computer). For a physical robot, you would use `1280` instead.

Then click `Apply`. This will connect to the simulation. \
During the season, you will primarily use this dashboard for debugging.

### AdvantageScope
AdvantageScope is a modern dashboard with features such as a 3D field and (when used with a log replay framework such as AdvantageKit) replay abilities.

Simulate the code, and then find and open the AdvantageScope 2026 application. You can also start it with `>WPILib: Start Tool` command.

First, connect it to the simulator. Under `File`, go to `Connect to Simulator`, then click `NetworkTables 4`. The data should appear.

> Note: For a real robot, you would connec to the 10.12.80.2 IP address, where the 12.80 is from our team number.

Next, click on the 3D Field tab.

In the bottom right, choose the 2025 Field (Welded) since the robot design is based off that season.

Then, from the sidebar, drag the `Robot Pose` value to the *Poses* area.

Additionally, drag the `Mechanism` value under `SmartDashboard` dropdown to the new robot in *Poses*.

If you move the camera around by `WASD`, `QE`, and `IJKL`, you will find a small robot with some rectangles at the blue allliance corner.

If you then drive the robot around (must be on simulation GUI to move), you can see it move on the 3D field.

### Robot Design
<!-- TODO: add actual robot CAD image -->

As visible in AdvantageScope or the simulation GUI, the robot has an elevator (gray) and an arm attached to the elevator (dark gray).

The elevator is run by a single motor and can go up and down.

The arm is also run by a single motor at the pivot and can rotate up and down, with a range of approximately 180 degrees.

## Programming

### Elevator Subsystem
First, you will be implementing the elevator subsystem from scratch.

Open the `src/main/java/frc/robot/elevator/ElevatorSubsystem.java` file.

First, let's make the `ElevatorSubsystem` class inherit from `SubsystemBase` so that it is a real subsystem.

Add ` extends SubsystemBase` right after the `ElevatorSubsytem` name.
```java
public class ElevatorSubsystem extends SubsystemBase {}
```
You may notice a red squiggly line under `SubsystemBase`. This is because that class has not been imported yet. Generally, you should autocomplete the class or any other symbol when typing it, by pressing `Tab` to accept a dropdown suggestion; this will automatically import it.

Luckily, in some cases including this one, you can just save the file with `Ctrl`+`S` and it will still auto-import `SubsystemBase`.

#### Initializing the Motor
Recall that in Java, a *field* is a variable on the *class*.

Let's add a field in `ElevatorSubsystem` for the single motor. \
The motor controller class is called `TalonFX` (for Kraken motors).

Inside of the curly braces `{}` of the `ElevatorSubsystem`, make a new line. \
On it, create a declaration for a private and final `TalonFX` field named `motor`. Don't forget the semicolon.
```java
public class ElevatorSubsystem extends ElevatorSimulationBase {
    private final TalonFX motor;
}
```
Then, add an `=` assignment for the field, setting it to a new `TalonFX` *instance*. Use the `new` keyword and call the `TalonFX` *constructor* method to create the instance:
```java
private final TalonFX motor = new TalonFX();
```
You need to pass an *argument* (input) into the `TalonFX` constructor for the integer device ID. (If you autocompleted the constructor then you'll notice that Java filled it with a placeholder `0`.)

I have already provided the device ID as a constant in another file. Open the `ElevatorConst.java` file that is in the same directory.

The value we need is `ElevatorConst.MOTOR_ID`. \
In `ElevatorSubsystem.java`, pass `ElevatorConst.MOTOR_ID` to the `TalonFX` constructor.
```java
new TalonFX(ElevatorConst.MOTOR_ID)
```

#### Configuring the Motor
The motor needs to be configured correctly.

The elevator motor configuration is provided in `ElevatorConfig` called `motorConfig`. \
It sets a current limit, tells it to brake when neutral, gives values needed for controlling the motor, etc.

In `ElevatorSubsystem`, write a constructor for the class that takes no arguments. A constructor is similar to a *method* except it has no return type and its name must be the same as the class's.

On a new line, write the `public`, parameterless constructor:
```java
public ElevatorSubsystem() {}
```
To apply the motor configuration in `ElevatorConfig` to `motor`, use `TalonFX`'s `getConfigurator()` method followed by an `apply()` method, inside of the `ElevatorSubsystem` constructor:
```java
motor.getConfigurator().apply(ElevatorConfig.motorConfig)
```

#### Adding the Subsystem to the Robot
Even though we've written this nice class, it isn't used anywhere yet.

Navigate to `Robot.java`.

In it, you can see that the very first `Robot` field is `drivetrain`. \
On a new line after it, add an `ElevatorSubsystem` field named `elevator`, instantiated with a new instance of `ElevatorSubsystem`.
```java
private final ElevatorSubsystem elevator = new ElevatorSubsystem();
```

Now the elevator subsystem is activated and usable.

#### Using the Motor
In order to get the elevator to move, the motor has to be controlled. There are a few methods to do this:

- `set(double speed)`: set the motor to a fraction/percentage of its maximum output, from `-1.0` (reverse) to `1.0`; this fraction is sometimes called "duty cycle"
- `setVoltage(double volts)`: set the motor voltage directly, a value from around -12 to 12 (the battery is 12V)
- `setControl(ControlRequest request)`: set a specific control request instance, such as requesting a specific motor position or velocity, or braking or coasting

##### Testing Mechanism Movement
First, let's test that our motor and elevator mechanism works at all. \
In the subsystem constructor, add a call to `motor`'s `set()` method with a low speed, e.g. `0.1`. \
Note that this is just for testing; motors should generally not be controlled from a constructor.

Now, simulate the robot code. \
Once you set the robot to Teleoperated, you'll see the elevator moving up until it reaches its limit.

If the elevator hit its maximum height in the real robot, it could possibly cause damange; best to disable the robot before that happens.

To get the elevator to go to a specific height, we can use a control request. \
Replace the call to `set()` with a call to `setControl()`, with the argument being a new `MotionMagicVoltage` instance created with a height fraction (e.g. 0.5) as a constructor argument.

Simulating the code now, you should see the elevator expand to half of its height and stay there.

Afterwards, delete the `setControl()` line in the constructor.

##### Movement Method
Let's add a convenience *method* to move the elevator to a height fraction. \
Create a public method named `moveHeightFraction` that requests the elevator motor to move to some desired input height fraction.

<details><summary>Method code</summary>

```java
public void moveHeightFraction(double heightFraction) {
    motor.setControl(new MotionMagicVoltage(heightFraction));
}
```
- `public`: The method is accessible to other classes
- `void`: The method does not return a value
- `moveHeightFraction`: method name
- `double heightFraction`: method pararameter, of type `double`

</details>

Now we can use this method to easily set the height fraction, instead of setting the control on the motor manually every time.

This is called abstraction, since we are making a convenience method that hides the actual lengthy implementation.

##### Adding Safety Limits
However, this method is currently unsafe because it doesn't have any safety limits or checks. If someone were to set it to `2.5` or `-1.0` accidentally, the elevator could break itself.

You should always implement software limits that protect the hardware limits, preventing buggy code from physically breaking the robot.

Think about the possible range of the elevator's height fraction. \
Since it is a fraction/percentage, it must be between `0.0` and `1.0`.

We could add a check that skips the movement if the input is outside of that range, a perfectly valid safety check.

But in order to prevent confusion and improve usability, we will instead *clamp* the input to be between `0` and `1`; if it is outside of the range, then it is set to be `0` or `1` instead.

Use the `MathUtil.clamp()` utility method on height fraction before it is passed in `MotionMagicVoltage` constructor.

> Tip: Hover over the `clamp` method name to see the documentation on how to use it.

Now, the elevator cannot be set to unsafe positions.

#### Adding Bindings to the Robot
We'd like to use a controller to move the subsystems.

Open `Robot.java` file.

The `controller` field has an `XboxController` on port `0`.

All of the bindings (controls) are configured in the `initBindings()` method.

Let's set the `Y` controller button (keyboard `t`) to make the elevator go to max height, and then set the `A` controller button (keyboard `g`) to make the elevator go to min height.

On a new line at the end of the method, start by calling `controller.y()`. This expression returns a `Trigger` method with additional methods for registering commands to be invoked when the `Y` button is pressed.

The most common method is `onTrue(Command command)`.
```java
controller.y().onTrue(  );
```
We can create commands from their classes like `InstantCommand`, but it is better to use the utility method that subsystems provide: `runOnce(Runnable action)` and `run(Runnable action)`.

##### Runnables and Lambdas
A *runnable* is like a miniature method or function, that is treated like an object but can be invoked to run its code.

The most common way to define it is a *lambda expression*, a short block of code that defines a mini function.

To write a lambda, start with a set of parentheses (representing the parameter list of a function), followed by an arrow: `() -> ` \
Then, add expression to run (and return), at the end of the arrow.

Example of a runnable that, when invoked, prints `Hello`.
```java
() -> System.out.println("Hello")
```
Analogous to:
```java
public void runnable() {
    System.out.println("Hello");
}
```
Example of a runnable that, when invoked with integer arguments `a` and `b`, returns their product:
```java
(int a, int b) -> a * b
```
Analogous to:
```java
public int runnable(int a, int b) {
    return a * b;
}
```

> Note: Lambda expressions can also a code block enclosed in curly braces at the end, instead of just one expression.

#####

#### Adding Information Getters

##### Tracking the Setpoint
In some cases, it is useful to keep track of the last set height fraction.

We can do this with a field. Define a private field in the class named `targetHeightFraction` to store the last set height fraction.

To keep it up to date, we just need to update it to the setpoint whenever the setpoint changes.

Conveniently, the setpoint is only changed through the `moveHeightFraction()` method, so we can update `targetHeightFraction` there.

In `moveHeightFraction`, set the `targetHeightFraction` field to the new height fraction.

### Arm Subsystem

### Control Theory
In robotics and engineering in general, a core problem is figuring out how to get a motor to do what we want.

For our robot, this often means getting the motor mechanism to a desired position in a timely but controlled manner, despite changes in motor load. \
If you tried doing this by setting motor voltage or duty cycle directly, it would be difficult to implement and tune.

There are two primary mechanism control types:
- **Feedback control** (AKA closed-loop control) applies effort based on how far the current position is from the desired position
- **Feedforward control** (AKA open-loop control) depends on a 'model' or guess of how the mechanism operates, and applies effort solely based on how the desired position changes (regardless of the current position)

In practice, it is often best to use both at the same time, to achieve the advantages of both.

#### Terminology
The *setpoint* is the desired position or state of the mechanism. The programmer can choose the appropriate units, e.g. rotations, meters, or even angular velocity (where the motor tries to achieve a specified speed instead of a position).

The *error* is the difference between the *setpoint* and the true position. Same units as setpoint.

The *effort* is what the control algorithms give as a result. 
The primary two effort types are *voltage* and *duty cycle*.

Duty cycle (fraction of supply voltage from `-1.0` to `1.0`) is useful for mechanisms that often need maximum speed possible, but is inconsistent if the supply voltage drops.

For our motors, prefer voltage effort.
Higher voltage = more force and speed, negative voltage = reverse direction.

A *gain* is a constant used and tuned for the loop, which defines how much effort the motor should give for some specific information like error.

#### Feedback Control
The most common type of feedback control is called PID (proportion-integral-derivative) control.

##### PID Spring Analogy
Imagine feedback control like a spring (with some mass attached to the end).

Suppose the spring's distance from its rest length (the error) is $X$.

<!-- TODO: add diagrams -->

If you stretch the spring to $X = 1m$, then the spring applies a restoring force to bring itself back to $X = 0m$.

The force the spring applies, $F$ (in newtons $N$), is *proportional* to the error, and is also in the opposite direction of it.

$F=-K_pX$, where $K_p$ is some "spring constant" with units of $N/m$.

Suppose $K_p = 3N/m$. \
Then for $X = 1m$, $F_p=(-3N/m)(1m)$ = $-3N$.

As error increases, the restoring force increases. \
If you stretch out the spring to $X = 2m$, then the force doubles to $-6N$. \
If you contract the spring to $X = -0.5m$, then the force becomes $1.5N$.

Suppose the spring is released from $X = 1m$, and it begins restoring itself to $X = 0m$. \
One current problem with this is that the spring will *oscillate* around $X = 0m$ since it always overshoots the rest length.

The spring will reach $X = -1m$ after a bit of time, and then it will shoot back to $1m$, oscillating infinitely. \
Even though $F_p = 0$ at $X = 0m$, the velocity $V$ of the spring there is not $0m/s$.

The spring needs damping so that it slows down while approaching $X = 0m$.

This is achieved by a damping force, which is proportional and opposite of the spring's velocity: \
$F_d = -K_dV$ \
$K_p$ is some damping constant in units of $N/(m/s)$

As the spring accelerates while it goes to $X = 0m$, velocity increases and causes $F_d$ to grow, eventually slowing it down.

If $K_d$ is too large, then the spring is too slow and reaches $X = 0m$ very slowly. If $K_d$ is too small, then the spring still overshoots $X = 0m$ (but by less).

This spring is very close, almost analogous, to feedback control. \
The spring goes to a rest length (setpoint) by applying a force (effort) based on its displacement (error) and velocity (rate of change of error), with constants $K_p$ and $K_d$.

##### PID Definition
PID has three gains: $K_p$, $K_i$, and $K_d$.

$K_p$ is the amount of effort per unit of error. \
A higher value makes the mechanism more responsive and aggressive while reaching a setpoint.

$K_d$ is the amount of effort per *rate of change of error* (e.g. rotations per second). \
A higher value makes the mechanism smoother and slow down while reaching a setpoint; damping.

$K_i$ is the amount of effort per *accumulated error over time*. \
This gain is not recommended for FRC use and should be $0$, as it is almost always better to use feedforward instead of $K_i$. \ You do not need to understand it.

Since each PID component depends on the *error* which depends on the current measured position, PID is a feedback (closed-loop) control type.

##### Using Velocity as the Control Unit
Instead of having the setpoint and error be a position, it can instead be a velocity (especially angular).

This is most often used for flywheels.

Here, all of the PID gains work the same except they operate on the velocity error instead of position error.

Note: the different control unit only changes the behavior of PID gains, not the following feedforward and motion profiling variables.

#### Feedforward Control
Feedforward control does not use knowledge of the measured motor state, and instead applies an estimate of the amount of effort needed.

The most common and basic use of feedforward is to accurately counteract gravity.

If we know the weight of an object, then we can simply apply a constant force to try to keep it still. This is feedforward control because we don't care about where the object is.

Another example is simple joystick control; technically, using a joystick to directly control motor voltage is feedforward control.

The reason why PID alone is often not enough is because if there is an applied force such as gravity, then PID will struggle to reach the setpoint because it will settle slightly below it (since the applied effort from $K_p$ is very small).

$K_g$ is effort applied to counteract gravity. Often this is just a constant term, but for a rotating arm, $K_g$ can be applied based on what angle the arm is at - both feedforward and feedback, since a sideways arm position has a greater gravity torque than a vertical arm.

$K_s$ is effort applied to overcome static friction; getting the motor from being motionless to barely moving.

$K_v$ and $K_a$ are velocity and acceleration gains. Sometimes, in addition to the position setpoint, velocity and acceleration setpoints are also needed. \
These gains apply effort proportional to the request velocity and acceleration setpoints.

#### Motion Profiling
There is one last enhancement we can make to our motor control.

Both feedback and feedforward rely on a setpoint. \
In the code, we may naively put the setpoint at the desired state immediately. But this creates a large and instant jump in the setpoint, making the effort jerky and sudden.

To fix this, we can generate a sequence of setpoints that smoothly go (interpolate) from the current state to the desired state over time, which greatly improves mechanism handling.

This 'trajectory' of setpoints is called a *motion proifle*.

Additionally, motion profiles automatically provide velocity and acceleration setpoints for use with the $K_v$ and $K_g$ feedforward gains.

Motion profiles are usually generated from a set of constraints on maximum velocity, maximum velocity, and optionally maximum jerk (rate of change of acceleration).

In the code, the motion profile settings are configured once, and we only just need to periodically set a setpoint (just as if motion profiling is not used).

The most common, and most simple, motion profile is the trapezoidal profile. \
Imagine the plot of the velocity setpoint. First, it starts accelerating at a constant rate (maximum acceleration) from 0. Then, it reaches a maximum *cruise* velocity and keeps constant there. Finally, it decelerates at a constant rate to 0. \
The shape of the plot is a trapezoid.

<!-- TODO: add image from https://v6.docs.ctr-electronics.com/en/2024/_images/trapezoidal-profile1.png here -->

#### Tuning Gains
With all of these control algorithms, we can use a motion-profiled PIDF (F is for feedforward) controller to smoothly move our mechanism.

First, determine what unit of setpoint and error is best suited (rotations, angular velocity, meters, fraction of mechanism range, etc.). Usually, this should be based on the actual mechanism and not the motor rotor.

Now, the rest of the work for controlling motors boils down to finding (tuning) the right gains and constants for our motion profile, PID, and feedforward.

To find these values, use either manual tuning through trial and error, or use a tuning or generation tool such as:
- [WPILib's System Identification tool, SysId](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/index.html)
    - Simple motor (flywheel, turret, horizontal slider) identification
    - Elevator identification
    - (Vertical) arm identification
- Phoenix Tuner X:
    - [Swerve Project Generator](https://v6.docs.ctr-electronics.com/en/stable/docs/tuner/tuner-swerve/index.html)
    - [Elevator Generator](https://v6.docs.ctr-electronics.com/en/stable/docs/tuner/tuner-elev/index.html)

To manually tune, follow these steps:
1. Set all gains to zero
2. Choose a decently slow maximum velocity and acceleration
3. If applicable, increase $K_g$ as much as you can without the mechanism moving upwards
    - For `TalonFX`, select the gravity type - elevator static for elevators, and arm cosine for vertical arms
4. Optionally, increase $K_s$ until just before the motor starts moving from rest
    - For `TalonFX`, choose the static feedforward sign as usually 'use velocity sign'
5. Increase $K_v$ until the mechanism cruise *velocity* (when it is steady/constant) closes matches the velocity setpoint or requested cruise velocity
6. Increase $K_a$ until the mechanism acceleration closely matches the requested acceleration
    - The mechanism position curve should now match the motion profile curve
7. Increase $K_p$ until the mechanism starts to overshoot or oscillate
8. Either decrease $K_p$ or increase $K_d$ or both, until the oscillation stops
    - For $K_d$, avoid introducing jittering (from amplifying measurement noise)
9. Increase maximum velocity and acceleration as desired, tweaking feedforward gains if necessary
    - Optionally, set a maximum jerk for smoother but slower motion

Note that by step 7, the feedforward control (combined with motion profiling) alone should provide a lot of mechanism control already; the feedback control is supplementary.

> Warning: Tuning PID is dangerous to both the robot and people, always be ready to disable the robot.