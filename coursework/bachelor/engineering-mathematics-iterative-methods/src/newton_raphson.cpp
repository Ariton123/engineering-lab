#include <cmath>
#include <iomanip>
#include <iostream>
#include <stdexcept>

namespace {
constexpr double kTolerance = 1e-6;
constexpr double kDerivativeThreshold = 1e-12;
constexpr int kMaxIterations = 100;

double function(double x) {
    return x - std::cos(x);
}

double derivative(double x) {
    return 1.0 + std::sin(x);
}
}

int main() {
    double x = 0.0;
    int iterations = 0;

    for (int i = 1; i <= kMaxIterations; ++i) {
        const double slope = derivative(x);
        if (std::abs(slope) < kDerivativeThreshold) {
            throw std::runtime_error("Derivative is too close to zero.");
        }

        const double step = function(x) / slope;
        x -= step;
        iterations = i;

        if (std::abs(step) < kTolerance) {
            break;
        }
    }

    std::cout << std::fixed << std::setprecision(9)
              << "Newton-Raphson root: " << x << '\n'
              << "Iterations: " << iterations << '\n';

    return 0;
}
