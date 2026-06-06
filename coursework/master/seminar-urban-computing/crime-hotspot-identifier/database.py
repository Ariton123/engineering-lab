import sqlite3

# Function to get data for a city (crime reports and weather)
def get_city_data(city):
    conn = sqlite3.connect('swiss_crime_data.db')
    c = conn.cursor()
    
    # Fetch the latest data for the given city
    c.execute("SELECT crime_reports, weather FROM crimes WHERE city = ?", (city,))
    data = c.fetchone()
    
    conn.close()
    
    # Return a dictionary of the fetched data
    return {'crime_reports': data[0], 'weather': data[1]}

