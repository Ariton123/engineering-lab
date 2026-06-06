import numpy as np
import skfuzzy as fuzz
from skfuzzy import control as ctrl

# Function to calculate crime risk using fuzzy logic
def calculate_crime_risk(crime_reports, weather, time_of_day):
    # Define fuzzy variables
    crime = ctrl.Antecedent(np.arange(0, 11, 1), 'crime_reports')
    weather = ctrl.Antecedent(np.arange(0, 11, 1), 'weather')
    time_of_day = ctrl.Antecedent(np.arange(0, 24, 1), 'time_of_day')  # Adding time as a factor
    risk = ctrl.Consequent(np.arange(0, 11, 1), 'risk')

    # Membership functions for time_of_day (morning, afternoon, evening/night)
    time_of_day['morning'] = fuzz.trimf(time_of_day.universe, [0, 6, 12])
    time_of_day['afternoon'] = fuzz.trimf(time_of_day.universe, [10, 15, 18])
    time_of_day['evening_night'] = fuzz.trimf(time_of_day.universe, [16, 20, 24])

    # Auto-membership functions for inputs and output
    crime.automf(3)  # Poor, average, good crime levels
    weather.automf(3)  # Bad, average, good weather conditions
    risk.automf(3)  # Low, medium, high risk

    # Define fuzzy rules based on time, crime reports, and weather conditions
    rule1 = ctrl.Rule(crime['poor'] & weather['average'] & time_of_day['morning'], risk['low'])
    rule2 = ctrl.Rule(crime['average'] & weather['good'] & time_of_day['afternoon'], risk['medium'])
    rule3 = ctrl.Rule(crime['good'] & weather['poor'] & time_of_day['evening_night'], risk['high'])

    # Control system
    risk_ctrl = ctrl.ControlSystem([rule1, rule2, rule3])
    risk_simulation = ctrl.ControlSystemSimulation(risk_ctrl)

    # Input values for the city
    risk_simulation.input['crime_reports'] = crime_reports
    risk_simulation.input['weather'] = weather
    risk_simulation.input['time_of_day'] = time_of_day
    risk_simulation.compute()

    # Return the calculated crime risk
    return risk_simulation.output['risk']
