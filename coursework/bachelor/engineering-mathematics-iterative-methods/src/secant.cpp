#include <cmath>
#include <iomanip>
#include <iostream>
#include <stdexcept>

namespace {
constexpr double kTolerance = 1e-6;
constexpr double kDenominatorThreshold = 1e-12;
constexpr int kMaxIterations = 100;

double function(double x) {
    return x - std::cos(x);
}
}

int main() {
    double previous = 0.0;
    double current = 1.0;
    double next = current;
    int iterations = 0;

    for (int i = 1; i <= kMaxIterations; ++i) {
        const double f_previous = function(previous);
        const double f_current = function(current);
        const double denominator = f_current - f_previous;

        if (std::abs(denominator) < kDenominatorThreshold) {
            throw std::runtime_error("Secant denominator is too close to zero.");
        }

        next = current - f_current * (current - previous) / denominator;
        iterations = i;

        if (std::abs(function(next)) < kTolerance) {
            break;
        }

        previous = current;
        current = next;
    }

    std::cout << std::fixed << std::setprecision(9)
              << "Secant root: " << next << '\n'
              << "Iterations: " << iterations << '\n';

    return 0;
}
