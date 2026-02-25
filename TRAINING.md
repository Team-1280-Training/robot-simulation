# Training
Welcome to advanced programming training!

You will learn WPILib coding and general FRC programming.

> Note: Please refrain from copying the code snippets from the instructions into your code.

## Table of Contents
- [Table of Contents](#table-of-contents)
- [Attribution](#attribution)
- [Setup Part 1](#setup-part-1)
    - [WPILib Installation](#wpilib-installation)
    - [GitHub](#github)
    - [Cloning](#cloning)
- [Setup Part 2](#setup-part-2)
    - [View](#view)
    - [Extension Installation](#extension-installation)
- [Dashboards](#dashboards)
    - [Running the Robot](#running-the-robot)
    - [Simulation GUI](#simulation-gui)
        - [Dashboard Terminology](#dashboard-terminology)
        - [Widgets](#widgets)
        - [Driving the Robot](#driving-the-robot)
        - [Plotting Values](#plotting-values)
    - [Glass](#glass)
    - [AdvantageScope](#advantagescope)
    - [Robot Design](#robot-design)
- [Programming](#programming)
    - [Java Recap](#java-recap)
    - [Elevator Subsystem](#elevator-subsystem)
        - [Initializing the Motor](#initializing-the-motor)
        - [Configuring the Motor](#configuring-the-motor)
        - [Adding Subsystems to the Robot](#adding-subsystems-to-the-robot)
        - [Using the Motor](#using-the-motor)
            - [Testing Mechanism Movement](#testing-mechanism-movement)
            - [Movement Method](#movement-method)
            - [Adding Safety Limits](#adding-safety-limits)
        - [Adding Bindings to the Robot](#adding-bindings-to-the-robot)
            - [Runnables and Lambdas](#runnables-and-lambdas)
            - [Commands](#commands)
            - [Testing the Bindings](#testing-the-bindings)
        - [Adding Dashboard Information](#adding-dashboard-information)
            - [Measured Height Fraction](#measured-height-fraction)
            - [Sendables, Builders, and Properties](#sendables-builders-and-properties)
            - [Seeing Properties on the Dashboard](#seeing-properties-on-the-dashboard)
            - [Target Height Fraction](#target-height-fraction)
            - [Measured Height Distance](#measured-height-distance)
    - [Arm Subsystem](#arm-subsystem)
        - [Notes](#notes)
        - [Requirements](#requirements)
        - [Review](#review)
    - [Control Theory](#control-theory)
        - [Control Terminology](#control-terminology)
        - [Feedback Control](#feedback-control)
            - [PID Spring Analogy](#seeing-properties-on-the-dashboard)
            - [PID Definition](#pid-definition)
            - [Using Velocity as the Control Unit](#using-velocity-as-the-control-unit)
        - [Feedforward Control](#feedforward-control)
        - [Motion Profiling](#motion-profiling)
        - [Tuning Gains](#tuning-gains)
            - [Elevator Tuning Sandbox](#elevator-tuning-sandbox)
            - [Tuning the Robot Elevator](#tuning-the-robot-elevator)
            - [Tuning the Robot Arm](#tuning-the-robot-arm)
- [Reference Solution Codebase](#reference-solution-codebase)
- [End of Training](#end-of-training)

## Attribution
All of almost all of this file and the codebase were written by @aatle on GitHub.

*No AI was used to write any parts of this project.*

## Setup Part 1

### WPILib Installation
Go to https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html, and download the installer.

Once downloaded, run the installer. Click `Start`.

Select `Everything` and click `Install for this User`.

Click `Download for this computer only (fastest)`, the top left option. After it finishes, click `Next`.

Wait for it to install, then click `Finish`.

Make sure to put the WPILib VS Code for the current year in an accessible location, such as by adding a shortcut. \
Also, avoid mixing up your WPILib VS Code with your regular VS Code. WPILib's VS Code shows a small WPILib icon in the top right of the window. 

MacOS users: \
On the Finder file explorer that opens and shows `VS Code`, select the `VS Code`, right click, and press `Make Alias`. \
Then rename the alias to something like `FRC VS Code 20XX`, where `20XX` is the current year. \
Next, move this alias into your `Applications` folder in the left sidebar. \
You can now run WPILib's VS Code from your Launchpad; it is recommended to also `Keep in Dock` (pin) this application to the bottom dock bar.

### GitHub
First, you must *fork* your own copy of the `Team-1280-Training.robot-simulation` repository because you don't have write access for that repo.

Near the top, click the `Fork` button. In the opened page, you can use the default configuration; press `Create fork`.

Once you have created the repository, open your WPILib VS Code application.

### Cloning
In WPILib VS Code, open the *Command Palette*, by using a keyboard shortcut or clicking the top search bar and typing a `>`. \
Windows, Linux: `F1` or `Ctrl`+`Shift`+`P` \
Mac: `fn`+`F1` or `Command`+`Shift`+`P`

Then, find and select the `>Git: Clone` command. \
If it asks you to sign into GitHub, do that.

Then press `Clone from GitHub` and select the repository you created earlier.

Select or create a new folder that the repository folder will be in.

> Tip: I recommend creating a folder on your computer called `dev` or `development` that holds all of your programming projeects.

When prompted, click `Open` to open it in the current VS Code window. \
Then press `Yes, I trust the authors` (do not check the checkbox).

## Setup Part 2

### View
On the left, open `TRAINING.md`. Preview the file using `Ctrl`/`Command`+`Shift`+`V`.

> Tip: When programming later, drag the `Preview TRAINING.md` tab (near the top) to the right side, to split the window.

> Tip: If at any time you want more space, press `Ctrl`/`Command`+`B` to hide the side bar, or press the sidebar's selected icon.

### Extension Installation
You will need to install an extension that automatically formats your code upon saving.

Find and select the `>Extensions: Show Recommended Extensions` command.

Install the *Workspace Recommendations* extension: `Google Java Format for VS Code`. \
Click that you trust the author, when prompted.

Now, auto-formatting will work when you save your code with `Ctrl`/`Command`+`S`.

> Note: Many useful settings are enabled in this workspace at `.vscode/settings.json`, near the bottom of the file. If you would like to also have these settings in all workspaces, copy them to your User Settings JSON file (openable with a command).

## Dashboards
Before you write a single line of code, let's learn how to run the robot simulation and view all of the information from the *dashboards*, applications that display operational information about the running robot.

Since this is a simulation (no physical robot), we will also use the dashboards to visualize what the robot looks like and what its mechanisms are doing.

### Running the Robot
> Note: For a physical robot, you would normally connect to the robot's WiFi, and then deploy the code to the robot with `>WPILib: Deploy Robot Code` (`Shift`+`F5`). \
> Then, you'd use FRC Driver Station to Enable and Disable the robot.

> Tip: If you ever want to build (compile) the codebase without running it anywhere, you can use `>WPILib: Build Robot Code`. This checks that your code compiles, and may resolve uncached dependencies.

Since there is no physical robot, you will just simulate the robot code instead with `>WPILib: Simulate Robot Code` command. \
Run that command now.

> Note: You must redeploy or re-simulate your code if you want any new code changes to take effect.

Once it builds, the simulation will open a specific dashboard called the *simulation GUI*.

### Simulation GUI
There is a ton of information on the simulation GUI, so let's go through the important parts.

> Tip: To switch windows easily, use `Alt`+`Tab` (Windows/Linux) or `Command`+`Tab` (macOS) keyboard shortcut

> Note: Currently, you are viewing the default layout of the sim GUI. The edits you make to the layout are saved locally (to some JSON files in the workspace) for the next time you open the sim GUI.

#### Dashboard Terminology
Each window on the GUI is a *widget*. Widgets can be dragged around, collapsed, and closed. Some widgets are resizable.

*NetworkTables* is an FRC communications protocol for sharing data over the network. The data is structured into hierarchical tables.

Most of the useful data is available under the *SmartDashboard* table. Later, you will be able to add even more information to it.

*FMS* stands for Field Management System, which is an electronics core that controls an FRC match: networking, robot control, match scores, match periods, etc.

*DS* stands for Driver Station.

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

At the top, the *menu bar* has a couple of different menus, but the only important one is `NetworkTables`.

Under `NetworkTables / SmartDashboard`, enable both `Mechanism` and `Field` widgets. Resize both as you wish.

The `Field` widget displays the robot's estimated position and orientation. (It comes from a `Field2d` object published to SmartDashboard in the code.)

The `Mechanism` widget shows our rudimentary simulated mechanisms, which will be explained later.

> Note: sometimes the `Field` or `Mechanism` widget doesn't work, it seems.

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
You can add plots for almost any value on the sim GUI.

To do this, in the menu bar, select `Plot` and then `New Plot Window`.

This adds a plot window widget `Plot <0>` that can have one or more plots. Click `Add Plot`. \
To plot a value, drag it into the plot.

Plot the robot's rotation (degrees), which is in `NetworkTables - Transitory Values - SmartDashboard/Field/Robot`: click the `double[]` dropdown, and drag the `[2]` left value to the plot.

Then, click the three bars on the top right of the widget, and customize the Plot 0 (click its dropdown):
- Set plot name to `Robot rotation (deg)`
- Set Y-axis minimum and maximum to `-180`, `180`, then click `Apply`
- Optionally, change the span of time for the plot width, from `10.0` seconds to a shorter or longer period, at `View Time`

Optionally, resize the plot window.

Now, try rotating the robot with `J` and `L` keybinds. \
You can see the plotted value change over time!

(Note that the rotation value wraps around from `-180` to `180` or vice versa.)

> Tip: Double-clicking the plot also automatically resizes the minimum and maximum.

### Glass
The Glass dashboard has an interface just like the simulation GUI, but can also be used for the physical robot.

Simulate the code, and then find and open the Glass application, by searching up the application on your computer. \
On mac, press `Command`+`Space` to search it up. \
The Glass application icon is a flask with a blue substance.

In Glass, in `NetworkTable Settings`, set the `Mode` to `Client (NT4)`. Then set the Team/IP to `localhost` (which is an IP that routes back to your own computer).

> Note: For a physical robot, you would use `1280` instead of `localhost`.

Then click `Apply`. This will connect to the simulation.

Now you know how to use Glass. \
During the season, Glass will be one of the primary dashboards.

### AdvantageScope
AdvantageScope is a modern dashboard with features such as a 3D field and (when used with a log replay framework such as AdvantageKit) replay abilities.

Simulate the code, and then find and open the AdvantageScope application. You can also start it with `>WPILib: Start Tool` command.

First, connect it to the simulator. Under `File` in menu bar at top left, go to `Connect to Simulator`, then click `NetworkTables 4`. The data should appear.

> Note: For a real robot, you would connect to the 10.12.80.2 IP address, where the 12.80 is from our team number.

Next, click on the 3D Field tab.

In the bottom right, choose the 2025 Field (Welded) since the robot design is based off that season. However, if it is not one of the options, then this step can be skipped.

Then, from the sidebar, drag the `Robot Pose` value to the *Poses* area.

Additionally, drag the `Mechanism` value under the `SmartDashboard` dropdown to the new robot in *Poses*.

If you move the camera around by `WASD`, `QE`, and `IJKL`, you will find a small robot with some rectangles at the blue allliance corner.

Now go back to the simulation GUI window. The simulation GUI must be focused, to drive the simulated robot. Resize it so that you can see the AdvantageScope field.

If you then drive the robot around, you can see it move on the 3D field!

### Robot Design
<!-- TODO: add actual robot CAD image -->

As visible in AdvantageScope or the simulation GUI, the robot has an elevator (gray) and an arm attached to the elevator (dark gray).

The elevator is run by a single motor and can go up and down.

The arm is also run by a single motor at the pivot and can rotate up and down, with a range of approximately 180 degrees (from up to down position).

## Programming
You can now close all of the dashboards, stopping the simulation.

### Java Recap
Before we start WPILib programming, let's recap some Java syntax and terminology.

A *variable* stores a value of the correct *type*. All variables have a name (use `camelCase` convention) and a type. \
E.g. `int count = 2;`

A *class* is a type that can create individual *instances* (objects). Users can define new classes. Classes use the `PascalCase` naming convention. \
E.g. `String`, `IntakeSubsystem`.
```java
public class Person { // Person class

}
```

A class can have *fields*, which are variables that belong to each instance. Fields can optionally have a default value.
```java
public class Person {
    String name;
    boolean alive = true;
}
```
Fields can be accessed with the `.` dot operator on an instance.
```java
System.out.println(person.name); // Field: name, of person
```
However, inside of the same class, the field can be referred to directly with just its name.
```java
System.out.println(name); // Inside of Person class
```

A class can have *methods*, which are runnable blocks of code. \
The method definition starts with the *return type* (`void` if none), followed by the method's name, and then a pair of parentheses, and then curly braces enclosing the method body (method code).
```java
public class Person {
    void greet() { // greet method
        System.out.println("Hello!");
    }
}
```
Methods can be *called* on an instance using the `.` dot operator.
```java
Person me = <...>;
me.greet(); // Method call
```

Methods can have *parameters*, which are variables that are used to input values, called *arguments*, from the method call. Parameters are placed in the method declaration parentheses. Arguments are placed in the parentheses of a method call.
```java
public class Person {
    void greet(String name1, String name2) { // Parameters: name1, name2
        System.out.println("Hello " + name + " and " + name2 + "!");s
    }
}
```
So, arguments are passed into a method call to fill the parameters.
```java
me.greet("Mittens", "Dracula");  // Arguments: "Mittens", "Dracula"
// Output: 'Hello Mittens and Dracula!'
```

*Modifiers* affect methods and fields:
- Access modifiers: `public`/`private`/(`protected`) - affect if other classes can access the method or field
- `final` modifier: makes the variable or field unassignable (constant); for methods, makes it not overridable
- `static` modifier: makes the method or field belong to the class instead of an instance, so that it can be accessed with the class itself
```java
public class Person {
    public String name; // Changeable, public field
    public final String birthday; // Constant, public field
    private final int ssn; // Constant, private field

    public void greet() { // Public method
        // ...
    }
}
```

A *constructor* is a special method for the class that is invoked when creating a new instance with the `new` keyword. \
It has no return type, and the method name is the same as the class name
```java
public class Person {
    private String name;
    private int age;

    public Person(String personName, int personAge) { // Constructor for Person class
        name = personName;
        age = personAge;
    }
}
```
```java
Person trainee = new Person("E", 5); // Create a new Person instance with the new keyword and the constructor
```
Comments start with `//`. Multi-line comments start with `/*` and end with `*/`. \
Javadoc comments start with `/**`, end with `*/`, and are put right before a method, field, or class.
```java
class Person {
    /** First and last name */
    public String name;

    /** 
     * Greet a person.
     */
    public void greet() {
        System.out.println("Hello!"); // cheerful
    }

    /*
     * Long
     * comment
     */
}
```

### Elevator Subsystem
First, you will be implementing the elevator subsystem from scratch.

Open the `src/main/java/frc/robot/elevator/ElevatorSubsystem.java` file (start by opening the `src` folder).

Alternatively, press `Ctrl`/`Command`+`E`/`P` and search up the file name `ElevatorSubsystem.java`.

First, let's make the `ElevatorSubsystem` class *inherit* from `SubsystemBase` so that it is a real subsystem. \
Recall that inheritance is when a class inherits the functionality of a base class.

Add ` extends SubsystemBase` right after the `ElevatorSubsytem` name.
```java
public class ElevatorSubsystem extends SubsystemBase {}
```
You may notice a red squiggly line under `SubsystemBase`. This is because that class has not been imported yet. \
Generally, you should autocomplete the class or any other symbol when typing it, by pressing `Tab` to accept a dropdown suggestion; this will automatically import it.

Luckily, in some cases including this one, you can just save the file with `Ctrl`/`Command`+`S` and it will still auto-import `SubsystemBase`.

#### Initializing the Motor
Let's add a field in `ElevatorSubsystem` for the single motor. \
The motor controller class is called `TalonFX` (for Kraken motors).

Inside of the curly braces `{}` of the `ElevatorSubsystem`, make a new line. \
On it, create a declaration for a private and final `TalonFX` field named `motor`. Don't forget the semicolon.
```java
public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX motor;
}
```
The `motor` name has a red squiggly because it is not initialized. Then, add an `=` assignment for the field, setting it to a new `TalonFX` *instance*. Use the `new` keyword and call the `TalonFX` *constructor* (a special method that initializes an instance) to create the instance, (adding to the same line):
```java
private final TalonFX motor = new TalonFX();
```
There is an error because you need to pass an *argument* (input) into the `TalonFX` constructor for the integer device ID. \
(If you autocompleted the constructor then you'll notice that Java filled it with a placeholder `0` that you can delete.)

I have already provided the device ID as a constant in another file. Open the `ElevatorConst.java` file that is in the same directory.

The value we need is `ElevatorConst.MOTOR_ID`. \
In `ElevatorSubsystem.java`, pass `ElevatorConst.MOTOR_ID` to the `TalonFX` constructor, on the same line you wrote.
```java
new TalonFX(ElevatorConst.MOTOR_ID)
```

#### Configuring the Motor
The motor needs to be configured correctly.

The elevator motor configuration is provided in `ElevatorConfig` called `motorConfig`.

Add the following code inside and at the start of the `static {}` block in `ElevatorConfig`:
```java
motorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT.in(Amps);
motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // positive is up
```
Let's look at each line of these 4 lines (no need to understand the details fully):
1. Set the current limit to the configuration constant
2. When the elevator motor is idle or disabled, set the motor to brake (as opposed to coast)
3. Set the positive motor rotation direction to be clockwise, so the elevator goes up for positive output

Whenever configuring a motor, you should set a current limit, give a neutral mode, and set the motor direction.

> Note: The other existing motor configuration tells the motor how its movement should behave for control requests.

In `ElevatorSubsystem`, write a constructor for the class that takes no arguments. A constructor is similar to a *method* except it has no return type and its name must be the same as the class's.

On a new line, write the `public`, parameterless constructor:
```java
public ElevatorSubsystem() {}
```
To apply the motor configuration in `ElevatorConfig` to `motor`, use `TalonFX`'s `getConfigurator()` method followed by an `apply()` method, inside of the `ElevatorSubsystem` constructor (in a new line inside the curly braces):
```java
motor.getConfigurator().apply(ElevatorConfig.motorConfig)
```

Be sure to save your code periodically, to automatically format the code.

#### Adding Subsystems to the Robot
Even though we've written this nice class, it isn't used anywhere yet.

Navigate to `Robot.java`. Scroll down past the import statements to the start of the `Robot` class.

In it, you can see that the very first `Robot` field is `drivetrain`. \
On a new line after it, add an `ElevatorSubsystem` field named `elevator`, instantiated with a new instance of `ElevatorSubsystem`.
```java
private final ElevatorSubsystem elevator = new ElevatorSubsystem();
```

Now the elevator subsystem is activated and usable. (Ignore the orange squiggly warning.)

#### Using the Motor
Go back to `ElevatorSubsystem.java`

In order to get the elevator to move, the motor has to be controlled. There are a few different methods on `TalonFX` to do this:

- `set(double speed)`: set the motor to a fraction/percentage of its maximum output, from `-1.0` (reverse) to `1.0`; this fraction is sometimes called "duty cycle"
- `setVoltage(double volts)`: set the motor voltage directly, a value from around -12 to 12 (the battery is 12V)
- `setControl(ControlRequest request)`: set a specific control request instance, such as requesting a specific motor position or velocity, or braking or coasting

See the [CTRE Phoenix 6 Java API docs](https://api.ctr-electronics.com/phoenix6/stable/java/com/ctre/phoenix6/hardware/TalonFX.html#set(double)) for the official information on `set` and `setVoltage` methods. \
Note that you can also view this information in VS Code when the method is suggested as a completion, e.g. if `motor.se` was partially typed.

##### Testing Mechanism Movement
First, let's test that our motor and elevator mechanism works at all.

In the elevator subsystem constructor (on a new line), add a method call to `motor`'s `set()` method with a low speed, e.g. `0.1`:
```java
motor.set(0.1);
```
Note that this is just for testing; motors should generally not be controlled from a constructor.

Now, simulate the robot code with the previous VS Code command.

It will open the simulation GUI. Make sure you can see the `Mechanism` widget.

Once you set the robot to Teleoperated, you'll see the elevator moving up until it reaches its limit.

If the elevator hit its maximum height in the real robot, it could possibly cause damange; best to disable the robot before that happens.

To get the elevator to go to a specific height, we can use a control request. \
Replace the call to `set()` with a call to `setControl()`, with the argument being a new `MotionMagicVoltage` instance created with a height fraction (e.g. 0.5) as a constructor argument:
```java
motor.setConrol(new MotionMagicVoltage(0.5));
```
The *height fraction* is a number from `0.0` to `1.0`, where `0.0` is the minimum height and `1.0` is the maximum.

Simulating the code again now, you should see the elevator expand to half of its height!

Afterwards, delete the `setControl()` line in the constructor since we are done testing it.

##### Movement Method
Let's add a convenience method to move the elevator to a height fraction.

Create a public method named `moveHeightFraction` that requests the elevator motor to move to some desired input height fraction.

If you are having trouble writing the method definition, ask yourself:
- Does the method return anything? What type is it?
- Does the method need some sort of input parameter? If so, what type are they?

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

Use the `MathUtil.clamp()` utility method on height fraction before it is passed in `MotionMagicVoltage` constructor:
Replace `heightFraction` in the `MotionMagicVoltage` constructor, with a call to `MathUtil.clamp()` with three arguments passed in.

> Tip: Hover over the `clamp` method name to see the documentation on how to use it.

<details><summary>Method body</summary>

```java
motor.setControl(new MotionMagicVoltage(MathUtil.clamp(heightFraction, 0.0, 1.0)));
```
Simply replace the orignal `heightFraction` with a clamped height fraction between 0 and 1.

</details>

Now, the elevator cannot be set to unsafe positions.

#### Adding Bindings to the Robot
We'd like to use a controller to move the subsystems.

Open `Robot.java` file.

Notice the `controller` field, an `XboxController` on port `0`.

Find the `initBindings()` method, which all of the bindings (controls) are configured in.

Let's set the `Y` controller button (keyboard `t`) to make the elevator go to max height, and then set the `A` controller button (keyboard `g`) to make the elevator go to min height.

On a new line at the end of the method (under `// operator bindings`), start with the code `controller.y()`. \
This expression returns a `Trigger` object, with additional methods for registering commands to be invoked when the `Y` button is pressed.

The most common `Trigger` method is `onTrue(Command command)` that takes a command object. (Have `null` as the `onTrue()` argument, as a *placeholder* (temporary value).)
```java
controller.y().onTrue(null);
```
Before getting into commands, let's learn what runnables are.

##### Runnables and Lambdas
A *runnable* is like a miniature method or function, that is treated like an object but can be *invoked* (run) to run its code. \
Unlike regular methods, you can pass around a runnable like an object, especially as an argument to methods.

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

> Note: Lambda expressions can also a code block enclosed in curly braces at the end, instead of just one expression. \
> `() -> { method1(); method2(); return value; }`

> Note: The types of the lambda parameters can be omitted.

##### Commands
Commands are used internally by the WPILib package to perform actions. They are typically just wrappers around runnables.

One type of command is the `InstantCommand(Runnable runnable)` which simply executes the given runnable once and then terminates.

Another type of command is the `RunCommand(Runnable runnable)`, which repeatedly executes the given runnable every *periodic* until it is interrupted or terminated.

Typically, instead of calling the constructors directly, we use convenience methods on the subsystems: `runOnce()` for `InstantCommand`, and `run()` for `RunCommand`.

Let's finish the controller binding. \
Replace the `null` with a call to `elevator`'s `runOnce()` command, passing in a lambda that sets the elevator height to `1.0`.
```java
controller.y().onTrue(elevator.runOnce(() -> elevator.moveHeightFraction(1.0)));
```
Breaking it down: \
*The `controller`'s `y()` trigger, when it is pressed (`onTrue()`), shall run an `elevator` subsystem command (from `runOnce()`) that invokes a lambda runnable (`() -> ...`) that moves the elevator height fraction to `1.0` (`elevator.moveHeightFraction(1.0)`).*

Add another similar line for setting the elevator height fraction to `0.0` upon pressing the `A` controller button.

##### Testing the Bindings
Now, simulate the robot code. Put the mechanism widget in view.

Once you set the robot to teleoperated, you can now control the elevator with `T` and `G` keyboard controls!

#### Adding Dashboard Information
We have a method for telling our elevator subsystem to do something, but we should also have methods for getting information from the elevator, to display on the dashboard.

What information would we want to know about the elevator?
- The current *measured* height fraction
- The *target* height fraction that was last set with `moveHeightFraction`
- The real height distance, in meters, of the elevator

##### Measured Height Fraction
Define a new method to get the height fraction of the elevator.

First, let's start with the method declaration (ignoring the body/implementation). Try to write it yourself.

Stuck? Here are the questions you should be asking, and how you should answer them.
<details><summary>Questions and Answers</summary>

- Should the method be accessible to other classes?
    - Yes, the method should be `public`
- What should it be named?
    - `getHeightFraction` is a conventional and descriptive name
- Does it return something? If so, what type?
    - Yes, it returns a height fraction, which has a type of `double`
- Does it take any inputs as parameters? What type?
    - No, the method shouldn't take any inputs, so it has 0 parameters

</details>

<details><summary>Method declaration</summary>

```java
public double getHeightFraction() {}
```

</details>

To implement the method body, we have to get the position of the motor.

Start typing in `return motor.` into the method body, and you'll see a bunch of completions. \
We want a method that gives position, so continue by typing `position`; now we can see a bunch of `motor` methods with `position` in its name.

Let's try the first suggestion, `getPosition()`. Autocomplete it.
```java
return motor.getPosition();
```
However, you'll notice that it is giving an error. Hover over the line to see the error.
```
Type mismatch: cannot convert from StatusSignal<Angle> to double, Java(16777235)
```
In simple terms, Java is saying that the method return type is supposed to be `double` but the actual returned value is of type `StatusSignal<Angle>` instead of `double`.

Luckily, this weird object has a method to convert itself into a `double value`; `getValueAsDouble()`. \
Add this method call to the end of the expression.
```java
return motor.getPosition().getValueAsDouble();
```
Now our method works.

> Note: The motor position is supposed to be an angle, but I have configured the motor so that the motor's measured position is actually an elevator height fraction (0.0 at bottom, 1.0 at top).

But this information getter is of little use if we can't send and display it on a dashboard.

##### Sendables, Builders, and Properties
A sendable is something we can *send* over NetworkTables to the dashboard. \
We must *initialize* ("init") it using a *builder* of sendables. This entails putting *properties* on the sendable.

Here, the sendable is the `ElevatorSubsystem`, and the property is the (measured/true) height fraction.

To log it, we need to override a method in `ElevatorSubsystem`. \
On a new line in the class, type in `initSendable` and autocomplete with the suggestion.

Inside `initSendable()`, add a call to the builder's `addDoubleProperty()` method.

Autocompleting it fills it with 3 arbitrary arguments that we need to replace.

Hover over `addDoubleProperty` to see documentation.

We see that the first parameter is a string for the property name; a label. Let's use `"height fraction"` for this (don't forget the quotes to make it a string).

The next parameter is a getter that gives the value of the property to display on the dashboard. Its type is `DoubleSupplier`.

This is actually just a specialized *runnable* that takes no arguments and returns a `double`.

For this parameter, write a lambda that returns the height fraction of the elevator using the other method we wrote earlier.
```java
() -> getHeightFraction()
```

The last parameter is the setter, which allows a user to change the value on the dashboard, invoking the setter. \
We will leave this as `null`, which means it doesn't have a setter.

The final builder method call should look like this:
```java
builder.addDoubleProperty("height fraction", () -> getHeightFraction(), null);
```

> Note: You can add slashes `/` to the property key to use or create dropdowns and hierarchies.

While the elevator sendable is now initialized properly, the elevator itself still needs to be sent to the dashboard.

In `Robot.java`, in the `initDashboard()` method, add another call to `SmartDashboard.putData()` at the end, similar to as for `field` and `mechanism`.

Name the data `"Elevator"`, and send in the `elevator` field.
```java
SmartDashboard.putData("Elevator", elevator);
```

The dashboard now receives information about the elevator height fraction.

##### Seeing Properties on the Dashboard
Simulate the robot code.

In the simulation GUI, go to the `NetworkTables` widget.

Under `Transitory Values`, in `SmartDashboard`, in `Elevator`, you will find your `height fraction` property.

Move the elevator using the `T` and `G` controls. \
Don't forget to set the mode to Teleoperated.

You should now see the property value change as the elevator moves up and down!

Plot the height fraction by selecting `Plot`, `New Plot Window`, `Add Plot`, and then dragging the `height fraction` from `NetworkTables` into the plot.

Then, change the plot's min and max to `-0.001` and `1.001` so it is easier to see values of `0.0` and `1.0`.

Now, when you move the elevator, the plot draws a nice graph of the position over time!

##### Target Height Fraction
Close the simulation as we will do more programming now.

In some cases, it is useful to keep track of the last set target height fraction.

We can do this with a field. Define a private field in the class named `targetHeightFraction`, initialized to `0.0`, to store the last set height fraction.

To keep it up to date, we just need to update it to the target whenever the target changes.

Conveniently, the target is only changed through the `moveHeightFraction()` method, so we can update `targetHeightFraction` there.

In `moveHeightFraction`, set the `targetHeightFraction` field to the new height fraction.
```java
targetHeightFraction = MathUtil.clamp(heightFraction, 0.0, 1.0);
motor.setControl(new MotionMagicVoltage(targetHeightFraction));
```

Now, add a builder double property for the field. The getter should just return the field.

Additionally, add a setter. The setter type is `DoubleConsumer`, which is simply a runnable that takes a single double parameter, and whose return type doesn't matter.

Write a lambda that uses the `moveHeightFraction()` method for this.
```java
(heightFraction) -> moveHeightFraction(heightFraction)
```
<details><summary>Builder method call</summary>

```java
builder.addDoubleProperty("target height fraction", () -> targetHeightFraction, (heightFraction) -> moveHeightFraction(heightFraction));
```

</details>

Try simulating the robot code now.

The target height fraction switches between `1.0` and `0.0` as you control the elevator.

Use the dashboard to set the value of `target height fraction` to a value like `0.5`, which uses the property setter. You should see the target height jump to `0.5` and stay there.

If setting this does not work and the target height fraction does not change and stay, then check your setter lambda.

Try setting it to `2.0`. What happens, and why?

Add the target height fraction property to the same plot as the measured height fraction. Now you can see the elevator's measured position responding to the desired position, all in one graph!

##### Measured Height Distance
The height fraction is a very useful measure and unit, but perhaps we'd like to know the exact height in meters that the top of the elevator is at.

To do this, we must convert height fraction into a distance height by using the known constants of the minimum height and maximum height.

These are provided in `ElevatorConst.java`. Go to this file and see the fields.

You'll notice that they aren't `double`s. They are of type `Distance`.

In robotics programming, one common problem is documenting the units of physical measures and values. For example, if a height is in feet or meters.

To solve this, there is an optional library (package) called the `Units` library which provides classes that document this directly in the variable type, in addition to making unit conversions explicit and automatic.

**Read the Units library documentation [here](https://docs.wpilib.org/en/stable/docs/software/basic-programming/java-units.html)**, skipping the `Using Composite Unit Types` section, and reading up until before the `Mutability and Object Creation` section.

Once you are done, read `ElevatorConst.java` again and try to understand it.

Now, in `ElevatorSubsystem`, write the declaration for a public method named `getHeight` that returns the current `Distance` height of the elevator.

**Challenge problem:** \
Implement this method correctly. \
Think of how to get a value from the motor and then convert it to a `Distance` correctly, using constants.

<details><summary>Hint 1</summary>

You will need to use `getHeightFraction()`. Use this value to calculate the height distance using `MIN_HEIGHT` and `MAX_HEIGHT`.

</details>

<details><summary>Hint 2</summary>

The operation needed is called a *linear interpolation*, abbreviated "lerp". Search up the formula for "lerp".

</details>

> Note: One might want to make utility methods for converting height fractions to heights and vice versa, but we don't need to implement that currently unless we feel it will probably be used.

It's best to always test the code whenever you implement something new. Test your new method.

Add a `double` property (`builder.addDoubleProperty()`) in `ElevatorSubsystem` for the elevator height in meters. Be sure to include the distance unit in the property key. \
Also remember that the setter should be `null` since there is nothing to set.

When putting this data on the dashboard, it is often best to convert the distance to a double in a standard unit, so that it is a numeric value that can be plotted.

> Hint: Use the `in(DistanceUnit unit)` method of a `Distance` object to convert it into a double quanitty of a specific unit.

Simulate the robot code, and add a new plot for the meter `height` of the robot. Then, control the elevator and test if the values are correct.

> Tip: At elevator bottom, it should be equal to the value from `MIN_HEIGHT`. At elevator top, it should be equal to the value from `MAX_HEIGHT`.

Debug your code and fix it until it works.

> Tip: To debug your code, look at what happens in your code when the `getHeightFraction()` returns `0.0` (elevator is at bottom), and what happens when it returns `1.0`. \
> Just write out the numbers and see what it results in.

<details><summary>Method code</summary>

```java
double heightFraction = getHeightFraction();
return ElevatorConst.MIN_HEIGHT.times(1 - heightFraction).plus(ElevatorConst.MAX_HEIGHT.times(heightFraction));
```
Or:
```java
return ElevatorConst.MIN_HEIGHT.plus(ElevatorConst.MAX_HEIGHT.minus(ElevatorConst.MIN_HEIGHT).times(getHeightFraction()));
```
Or:
```java
return Meters.of(ElevatorConst.MIN_HEIGHT.in(Meters) + getHeightFraction() * (ElevatorConst.MAX_HEIGHT.in(Meters) - ElevatorConst.MIN_HEIGHT.in(Meters)));
```

</details>

### Arm Subsystem
Now that you've implemented `ElevatorSubsystem` under detailed instruction, let's see how you do with minimal guidance.

Implement the `ArmSubsystem` according to the descriptive requests (requirements) from the instructions.

If you are stuck, try to reference your `ElevatorSubsystem` code or previous instructions on the concept (but as little as possible), or search up what you need help with.

This is intended to help you become an independent programmer that can perform a lot of work without the assistance of a more experienced programmer.

#### Notes
`ArmSubsystem.java` will need to be created from scratch.

You will need to research and use the encoder (type `CANCoder` from wpilib) that is on the arm and properly calibrated.

A rotation of zero degrees is point horizontally straight out (to the right in the `Mechanism` widget in sim GUI). A positive rotation goes upwards (counterclockwise in the `Mechanism` widget).

Tip: if your arm is not moving, test both clockwise and counterclockwise motor direction (in `ArmConfig.java`). Your motor may be inverted.

#### Requirements
Implement the arm subsystem with proper logging, safety, documentation, and code structure.

Controls:
- `B` controller button (`H`): move the arm down to minimum angle
- `X` controller button (`F`): move the arm up to maximum angle

The arm always starts at the maximum angle.

#### Review
Make sure to test your code frequently while programming.

Once you are done, have your code reviewed by an instructor.

### Control Theory
In robotics and engineering in general, a core problem is figuring out how to get a motor to do what we want.

For our robot, this often means getting the motor mechanism to a desired position in a timely but controlled manner, despite changes in motor load. \
If you tried doing this by setting motor voltage or duty cycle directly, it would be difficult to implement and tune.

There are two primary mechanism control types:
- **Feedback control** (AKA closed-loop control) applies effort based on how far the current position is from the desired position
- **Feedforward control** (AKA open-loop control) depends on a 'model' or guess of how the mechanism operates, and applies effort solely based on how the desired position changes (regardless of the current position)

In practice, it is often best to use both at the same time, to achieve the advantages of both.

#### Control Terminology
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

> Note: Use *target position* to refer to the raw desired position, and *setpoint* to refer to the motion profiled trajectory points.

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

##### Elevator Tuning Sandbox
Go to https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/tuning-elevator.html#motion-profiled-feedforward-and-feedback-control and try tuning the elevator according the the procedure given above. Experiment and play around with it.

Then, compare your solution to the given tuning solution.

##### Tuning the Robot Elevator
In `ElevatorConfig`, there are gains for both feedback, forward, and motion profiling (via Motion Magic).

The current configured gains are decent but they need a lot of improvement. For example, the elevator has a noticeable delay when switching to moving up while moving down. It also is quite slow.

To tune the elevator, use the simulation GUI or Glass to assist you.

Control the elevator with the controller bindings or with the dashboard property setters.

Plot useful height fraction values on one plot.

One additional helpful value to add to dashboard and plot is the motion profiling setpoint, which is separate from both the target height fraction and the measured height fraction. \
It is gettable with `motor.getClosedLoopReference().getValue()` (this give a `double` in units of rotations).

Additionally, you can add properties for the gains and constants such as `kP`, so you can edit them without restarting the simulation. You can do this by having a property setter that both sets the gain field and applies the configuration.

For organization, you can put these tuning constants under another dropdown `tuning`, by having the key be `tuning/kP` e.g.

<details><summary>Gain property</summary>

```java
builder.addDoubleProperty(
        "tuning/kP",
        () -> ElevatorConfig.motorConfig.Slot0.kP,
        (kP) -> {
            ElevatorConfig.motorConfig.Slot0.kP = kP;
            motor.getConfigurator().apply(ElevatorConfig.motorConfig.Slot0);
        });
```
> Tip: You can also extract out `ElevatorConfig.motorConfig.Slot0` into a local variable in `initSendable`, to condense the code.

</details>

> Warning: For a physical robot, you should be careful around the elevator limits. Crashing into the minimum and maximum heights is a safety hazard and may damage the robot. \
> Try tuning in a smaller range such as from `0.1` to `0.9` to avoid hitting the limits like you would for a real robot.

##### Tuning the Robot Arm
The arm gains are also not optimal. Tune those constants too, being careful not to crash into the arm's limits.

You can also reference https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/tuning-vertical-arm.html#combined-feedforward-and-feedback-control for a user-friendly sandbox.

Test your tuning constants with a variety of situations including abruptly changing targets, and unmoving targets.

## Reference Solution Codebase
<details><summary>Solution</summary>

The full solution is available at https://github.com/Team-1280-Training/robot-simulation/tree/solution.

Check your work against the solution and notice any differences.

</details>

## End of Training
Congratulations! You have finished advanced programming training.

This training gave you an introduction to the essential concepts of FRC programming. It is not intended to be the the entirety and the end of what you learn.

The purpose of this training is to give you enough experience to be able to learn without guidance in the future. Once you have reached that point, you have become a capable and qualified programmer.

You will continue to learn as you advance to programming on the season codebase as a subteam member.
