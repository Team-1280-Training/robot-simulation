# Robot Simulation
Current year: 2026

## Training
This `solution` branch holds the reference solution for the training.

## Custom Assets
TODO: https://docs.advantagescope.org/more-features/custom-assets/


## Xbox Controller Mappings
What each control is in SmartDashboard, in Keyboard Settings.

Set Keyboard 0 controls to these when you need each specific control.

Axes (6):
| Axis # | Description | Increase+ | Decrease- | Key Rate | Decay Rate |
| - | - | - | - | - | - |
| 0 | Left stick X axis | `d` | `a` | ~0.050 | ~0.050 |
| 1 | Left stick Y axis | `s` | `w` | ~0.050 | ~0.050 |
| 2 | Left trigger | `q` | (None) | 1.000 | 1.000 |
| 3 | Right trigger | `o` | (None) | 1.000 | 1.000 |
| 4 | Right stick X axis | `l` | `j` | ~0.050 | ~0.050 |
| 5 | Right stick Y axis | `k` | `i` | ~0.050 | ~0.050 |

(All axes have Max Absolute Value as 1)

Buttons (10):
| Button # | Description | Control |
| - | - | - |
| 1 | A button | `g` |
| 2 | B button | `h` |
| 3 | X button | `f` |
| 4 | Y button | `t` |
| 5 | Left Bumper | `e` |
| 6 | Right Bumper | `u` |
| 7 | Back button | `v` |
| 8 | Start button | `b` |
| 9 | Left stick button | `x` |
| 10 | Right stick button | `m` |

POVs (1): \
POV 0:
| Degrees | Description | Control |
| - | - | - |
| 0 | Up | `Up` |
| 45 | Up-Right | (None) |
| 90 | Right | `Right` |
| 135 | Down-Right | (None) |
| 180 | Down | `Down` |
| 225 | Down-Left | (None) |
| 270 | Left | `Left` |
| 315 | Up-Left | (None) |

These only go by angles; avoid pressing more than one button at once.

### Sources
- https://hal7df.github.io/pauls-tutorials/wpi/ch2/user-input.html
- https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html
- https://docs.wpilib.org/en/stable/docs/software/basic-programming/coordinate-system.html#joystick-and-controller-coordinate-system
