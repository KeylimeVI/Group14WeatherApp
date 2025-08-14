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
        final int intermediatePoints = 2;
        // number of intermediate points between each pair

        for (int i = 0; i < locations.size(); i++) {
            final TripLocation loc = locations.get(i);
            try {
                final WeatherLocation weatherLoc = weatherController.getWeather(
                        loc.getName(), loc.getLatitude(), loc.getLongitude()
                );
                tripWeather.add(weatherLoc);
            }
            catch (IOException | JSONException ex) {
                System.err.printf("Skipping %s: %s%n", loc.getName(), ex.getMessage());
            }

            // Add intermediate locations between this and the next trip location
            if (i < locations.size() - 1) {
                tripWeather.addAll(getIntermediateWeather(loc, locations.get(i + 1), intermediatePoints));
            }
        }

        return tripWeather;
    }

    /**
     * Generates intermediate points between two locations and fetches weather for them.
     *
     * @param start starting trip location
     * @param end ending trip location
     * @param numPoints number of intermediate points to generate
     * @return a list of WeatherLocation objects for the intermediate points
     */
    private List<WeatherLocation> getIntermediateWeather(TripLocation start, TripLocation end, int numPoints) {
        final List<WeatherLocation> intermediateWeather = new ArrayList<>();
        final double latStep = (end.getLatitude() - start.getLatitude()) / (numPoints + 1);
        final double lonStep = (end.getLongitude() - start.getLongitude()) / (numPoints + 1);

        for (int i = 1; i <= numPoints; i++) {
            final double lat = start.getLatitude() + i * latStep;
            final double lon = start.getLongitude() + i * lonStep;
            final String name = start.getName() + " to " + end.getName() + " intermediate " + i;
            try {
                final WeatherLocation loc = weatherController.getWeather(name, lat, lon);
                intermediateWeather.add(loc);
            }
            catch (IOException | JSONException event) {
                System.err.printf("Skipping intermediate location %s: %s%n", name, event.getMessage());
            }
        }

        return intermediateWeather;
    }

    /**
     * Fetches weather data for all trip locations and their nearby locations within a given radius.
     *
     * @param locations the list of trip locations
     * @param nearbyRadiusKm the radius (in kilometers) around each location to fetch nearby weather
     * @return a list of WeatherLocation objects including main and nearby locations
     */
    public List<WeatherLocation> getTripWeatherWithNearby(final List<TripLocation> locations,
                                                          final double nearbyRadiusKm) {
        final List<WeatherLocation> tripWeather = new ArrayList<>();

        for (final TripLocation loc : locations) {
            try {
                // Main location weather
                final WeatherLocation mainWeather = weatherController.getWeather(loc.getName(),
                        loc.getLatitude(), loc.getLongitude());
                tripWeather.add(mainWeather);

                // Nearby weather
                final List<WeatherLocation> nearby = weatherController.getNearbyWeather(mainWeather, nearbyRadiusKm);
                tripWeather.addAll(nearby);

            }
            catch (IOException | JSONException ex) {
                System.err.printf("Skipping %s: %s%n", loc.getName(), ex.getMessage());
            }
        }

        return tripWeather;
    }
}
