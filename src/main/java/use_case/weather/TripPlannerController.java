package use_case.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import entity.WeatherLocation;

public class TripPlannerController {

    private final WeatherController weatherController;

    public TripPlannerController(final WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    /**
     * Fetches weather data for a list of trip locations.
     * Skips locations with invalid data or failed API requests.
     *
     * @param locations the list of trip locations
     * @return a list of WeatherLocation objects with weather information
     */
    public List<WeatherLocation> getTripWeather(final List<TripLocation> locations) {
        final List<WeatherLocation> tripWeather = new ArrayList<>();

        for (final TripLocation loc : locations) {
            try {
                final WeatherLocation weatherLoc = weatherController.getWeather(
                        loc.getName(), loc.getLatitude(), loc.getLongitude()
                );
                tripWeather.add(weatherLoc);
            }
            catch (IOException ioEx) {
                System.err.println("Connection error for " + loc.getName() + ": " + ioEx.getMessage());
            }
            catch (JSONException jsonEx) {
                System.err.println("Parsing error for " + loc.getName() + ": " + jsonEx.getMessage());
            }
        }

        return tripWeather;
    }

    // Helper class to represent a trip location
    public static class TripLocation {
        private final String name;
        private final double latitude;
        private final double longitude;

        public TripLocation(final String name, final double latitude, final double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
