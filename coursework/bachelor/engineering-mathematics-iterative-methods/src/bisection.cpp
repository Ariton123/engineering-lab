#include <cmath>
#include <iomanip>
#include <iostream>
#include <stdexcept>

namespace {
constexpr double kTolerance = 1e-6;
constexpr int kMaxIterations = 1000;

double function(double x) {
    return x - std::cos(x);
}
}

int main() {
    double left = 0.0;
    double right = 1.0;

    if (function(left) * function(right) >= 0.0) {
        throw std::invalid_argument("The interval does not bracket a root.");
    }

    double midpoint = left;
    int iterations = 0;

    while ((right - left) >= kTolerance && iterations < kMaxIterations) {
        midpoint = (left + right) / 2.0;

        if (function(midpoint) == 0.0) {
            break;
        }

        if (function(left) * function(midpoint) < 0.0) {
            right = midpoint;
        } else {
            left = midpoint;
        }

        ++iterations;
    }

    std::cout << std::fixed << std::setprecision(9)
              << "Bisection root: " << midpoint << '\n'
              << "Iterations: " << iterations << '\n';

    return 0;
}
