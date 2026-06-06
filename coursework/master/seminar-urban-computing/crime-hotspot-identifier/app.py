from flask import Flask, jsonify, render_template, redirect, url_for, Response
from fuzzy_logic import calculate_crime_risk
from model import predict_crime_risk
from database import get_city_data
import folium
import os
import matplotlib
matplotlib.use('Agg')  # Use non-GUI backend
import matplotlib.pyplot as plt
import io
import base64

# Initialize Flask app
app = Flask(__name__)

# Root route (/) - redirect to Bern's crime map
@app.route('/')
def index():
    return redirect(url_for('crime_map', city='Bern'))

# Route to get crime risk for a city
@app.route('/crime-risk/<city>')
def crime_risk(city):
    city = city.capitalize()  # Capitalize the city name
    data = get_city_data(city)
    crime_reports = data['crime_reports']
    weather = data['weather']
    
    # Get fuzzy risk level
    fuzzy_risk = calculate_crime_risk(crime_reports, weather, time_of_day=12)

    # Predict crime risk using machine learning model
    predicted_risk = predict_crime_risk(crime_reports, weather)
    
    # Return JSON response
    return jsonify({
        'city': city,
        'fuzzy_risk': fuzzy_risk,
        'predicted_risk': predicted_risk
    })

# Function to return city-specific hotspots
def get_city_hotspots(city):
    print(f"City being processed: {city}")  # Debugging print

    if city == 'Bern':
        print("Returning Bern hotspots")
        return [
            {"location": [46.948, 7.447], "risk": "very high"},  # One high-risk area
            {"location": [46.950, 7.455], "risk": "moderate"},
            {"location": [46.945, 7.440], "risk": "moderate"},
            {"location": [46.955, 7.470], "risk": "moderate"},
            {"location": [46.970, 7.460], "risk": "low"},
            {"location": [46.980, 7.480], "risk": "low"},
            {"location": [46.990, 7.490], "risk": "minimal"}
        ]
    elif city == 'Zurich':
        print("Returning Zurich hotspots")
        return [
            {"location": [47.3769, 8.5417], "risk": "very high"},  # One high-risk area
            {"location": [47.3800, 8.5500], "risk": "moderate"},
            {"location": [47.3700, 8.5300], "risk": "moderate"},
            {"location": [47.3950, 8.5650], "risk": "moderate"},
            {"location": [47.4050, 8.5800], "risk": "low"},
            {"location": [47.4150, 8.5950], "risk": "low"},
            {"location": [47.4300, 8.6050], "risk": "minimal"}
        ]
    else:
        print("Returning default hotspots")
        return [
            {"location": [46.948, 7.447], "risk": "very high"}
        ]


# Route to display crime map
@app.route('/crime-map/<city>')
def crime_map(city):
    city = city.capitalize()  # Ensure city name is capitalized
    
    # Coordinates for cities
    city_location = {'Bern': [46.948, 7.447], 'Zurich': [47.3769, 8.5417]}
    city_coords = city_location.get(city, [46.948, 7.447])  # Default to Bern if city not found

    # Create a map centered on the city
    crime_map = folium.Map(location=city_coords, zoom_start=12)  # Adjust zoom to cover a larger area

    # Define crime hotspots based on the city
    crime_hotspots = get_city_hotspots(city)

    # Refined risk terms with different colors and sizes (removed extremely high)
    risk_color = {
        "minimal": "lightgreen",
        "low": "green",
        "relatively low": "skyblue",  # Change to skyblue for better visibility
        "moderate": "orange",
        "high": "red",
        "very high": "darkred"
    }

    # Adjusted marker sizes to be more visible, especially for low-risk areas
    risk_size = {
        "minimal": 10,
        "low": 12,
        "relatively low": 14,
        "moderate": 16,
        "high": 18,
        "very high": 20
    }

    # Add multiple hotspots with markers
    for hotspot in crime_hotspots:
        folium.CircleMarker(
            location=hotspot["location"],
            radius=risk_size[hotspot["risk"]],
            color=risk_color[hotspot["risk"]],
            fill=True,
            fill_color=risk_color[hotspot["risk"]],
            popup=f'Risk: {hotspot["risk"].capitalize()}'
        ).add_to(crime_map)

    # Add a legend to explain marker colors and sizes
    legend_html = '''
    <div style="position: fixed; 
                bottom: 50px; left: 50px; width: 220px; height: 160px; 
                background-color: white; border:2px solid grey; z-index:9999; font-size:14px;">
    &nbsp;<b>Crime Risk Levels</b><br>
    &nbsp;<i class="fa fa-circle" style="color:darkred"></i>&nbsp;Very High<br>
    &nbsp;<i class="fa fa-circle" style="color:red"></i>&nbsp;High<br>
    &nbsp;<i class="fa fa-circle" style="color:orange"></i>&nbsp;Moderate<br>
    &nbsp;<i class="fa fa-circle" style="color:skyblue"></i>&nbsp;Relatively Low<br>
    &nbsp;<i class="fa fa-circle" style="color:green"></i>&nbsp;Low<br>
    &nbsp;<i class="fa fa-circle" style="color:lightgreen"></i>&nbsp;Minimal
    </div>
    '''
    crime_map.get_root().html.add_child(folium.Element(legend_html))

    # Ensure the map is zoomed to fit all markers
    crime_map.fit_bounds([marker["location"] for marker in crime_hotspots])

    # Save the map in the static/maps folder
    if not os.path.exists('static/maps'):
        os.makedirs('static/maps')
    
    crime_map.save(f'static/maps/{city}_crime_map.html')

    # Render the map in a template
    return render_template('index.html', city=city, map_file=f'{city}_crime_map.html')

# Route to display the crime risk distribution chart
@app.route('/crime-chart/<city>')
def crime_chart(city):
    city = city.capitalize()  # Ensure city name is capitalized

    crime_hotspots = get_city_hotspots(city)

    # Print crime hotspots to debug the data being used for the chart
    print(f"Crime hotspots for {city}: {crime_hotspots}")

    # Calculate the number of each risk type
    risk_levels = [hotspot['risk'] for hotspot in crime_hotspots]

    # Print the risk levels to debug the distribution of risks
    print(f"Risk levels for {city}: {risk_levels}")

    # Maintain consistent color assignment based on the legend
    risk_color_map = {
        "very high": "darkred",
        "high": "red",
        "moderate": "orange",
        "low": "green",
        "minimal": "lightgreen"
    }

    # Count the occurrences of each risk level in the actual data
    risk_categories = list(risk_color_map.keys())
    risk_counts = [risk_levels.count(risk) for risk in risk_categories]

    # Only include categories that have a non-zero count
    filtered_risk_categories = [category for category, count in zip(risk_categories, risk_counts) if count > 0]
    filtered_risk_counts = [count for count in risk_counts if count > 0]
    filtered_colors = [risk_color_map[category] for category in filtered_risk_categories]

    # Create a pie chart with aligned colors
    fig, ax = plt.subplots()
    ax.pie(filtered_risk_counts, labels=filtered_risk_categories, colors=filtered_colors, autopct='%1.1f%%', startangle=90)
    ax.axis('equal')  # Equal aspect ratio ensures that pie chart is drawn as a circle.

    # Save chart to a bytes buffer
    img = io.BytesIO()
    plt.savefig(img, format='png')
    img.seek(0)

    # Close the figure after rendering to free up memory
    plt.close(fig)

    # Encode the image to base64
    img_base64 = base64.b64encode(img.getvalue()).decode('utf-8')

    return render_template('chart.html', img_base64=img_base64, city=city)


if __name__ == '__main__':
    app.run(debug=True)
