
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import pandas as pd

# Function to predict crime risk using a trained ML model
def predict_crime_risk(crime_reports, weather):
    # Load pre-processed Swiss crime data (this should be a pre-trained model)
    df = pd.read_csv('swiss_crime_data.csv')
    X = df[['crime_reports', 'weather_conditions']]
    y = df['crime_risk']

    # Split the dataset
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    
    # Train a linear regression model
    model = LinearRegression()
    model.fit(X_train, y_train)

    # Predict crime risk based on the input values
    predicted_risk = model.predict([[crime_reports, weather]])[0]
    
    return predicted_risk
