# Engineering Mathematics - Iterative Methods for Nonlinear Equations

Bachelor coursework for the **Engineering Mathematics** course, completed during the 2020/2021 academic year. The project compares three numerical methods for approximating roots of nonlinear equations:

- Bisection method
- Newton-Raphson method
- Secant method

The accompanying report is written in Macedonian and is titled **"Компаративна анализа на некои итеративни методи за приближно решавање на нелинеарни равенки"** (Comparative Analysis of Selected Iterative Methods for the Approximate Solution of Nonlinear Equations).

## Project scope

The coursework introduces the mathematical assumptions and convergence behavior of the three methods, then compares their iteration counts on selected nonlinear equations. The preserved C++ programs reproduce the report's second experiment for:

```text
f(x) = x - cos(x) = 0
```

using a tolerance of `1e-6`.

## Preserved results

With the initial values used in the coursework, the cleaned programs produce approximately:

| Method | Initial values | Approximate root | Iterations |
|---|---|---:|---:|
| Bisection | `[0, 1]` | `0.739085` | 20 |
| Newton-Raphson | `x0 = 0` | `0.739085` | 5 |
| Secant | `x0 = 0`, `x1 = 1` | `0.739085` | 4 |

Iteration counts depend on the stopping criterion and initialization, so these values should be interpreted within the specific setup used here.

## Repository structure

```text
engineering-mathematics-iterative-methods/
├── README.md
├── NOTICE.md
├── .gitignore
├── CMakeLists.txt
├── src/
│   ├── bisection.cpp
│   ├── newton_raphson.cpp
│   └── secant.cpp
└── report/
    └── comparative-analysis-iterative-methods-mk.pdf
```

## Build and run

A C++17 compiler and CMake 3.16 or newer are required.

```bash
cmake -S . -B build
cmake --build build
```

Run the programs from the generated build directory. On Linux/macOS with a single-configuration generator:

```bash
./build/bisection
./build/newton_raphson
./build/secant
```

On Windows, the executables may be placed in a configuration subdirectory such as `build/Debug/`.

## Public portfolio cleanup

The public version:

- removes compiled executables and object files;
- removes Code::Blocks workspace and layout files;
- removes duplicate `.txt` copies of the C++ source;
- removes the student identification number from the report;
- normalizes filenames and adds a portable CMake build;
- lightly modernizes the C++ implementations while preserving the original numerical tasks.

See [`NOTICE.md`](NOTICE.md) for methodological and archival limitations.

## Author

**Ariton Verush**
