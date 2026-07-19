# Coursework and Methodology Notice

This repository is an archived and cleaned version of Bachelor-level coursework completed for the Engineering Mathematics course during the 2020/2021 academic year.

## Scope

The project is an educational comparison of three iterative root-finding methods. It is not intended to be a production numerical-analysis library or a comprehensive benchmark.

## Important limitations

- The comparison uses a small number of example equations and primarily compares iteration counts rather than execution time, numerical stability, or per-iteration computational cost.
- Newton-Raphson performance depends on a suitable initial value, a usable derivative, and the behavior of the function near the root.
- The secant method avoids an explicit derivative but can fail when successive function values produce a near-zero denominator.
- The bisection method converges more slowly, but it remains practically valuable because of its strong reliability when a continuous function changes sign over a valid interval. A broad statement in the original report that it has little practical use should therefore not be treated as a general numerical-analysis conclusion.
- The original report contains minor typographical and sign inconsistencies in the description of one test equation and its reported root.
- The original source archive preserved runnable implementations only for `x - cos(x) = 0`; code for the report's additional polynomial experiment was not present.
- The bibliography and linked external resources are preserved as part of the historical report and may no longer be available at their original URLs.

## Source-code cleanup

The original C++ logic was lightly modernized for portability and clarity. The public version uses standard C++ headers, `double` precision, explicit iteration limits, basic numerical guards, and CMake. Generated binaries, object files, IDE project files, and duplicate text copies were excluded.

## Language

The academic report is preserved in Macedonian. Repository documentation and build instructions are provided in English for broader portfolio accessibility.
