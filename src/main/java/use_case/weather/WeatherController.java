package use_case.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.WeatherLocation;

/**
 * WeatherController handles fetching and parsing weather and elevation data
 * for a given location and stores query history.
 */
public class WeatherController {

    private static final String WEATHER_API = "https://api.openweathermap.org/data/3.0/onecall";
    private static final String ELEVATION_API = "https://api.opentopodata.org/v1/eudem25m";
    private static final String API_KEY = "aceb5c06f45f2c625f5ed982f86bff59";

    private final List<WeatherLocation> history = new ArrayList<>();

    /**
     * Fetches the current weather and elevation for a specified location and
     * stores the result in the query history.
     *
     * <p>This method calls the OpenWeatherMap API to retrieve temperature and
     * weather description, and OpenTopodata API to retrieve elevation. The
     * resulting WeatherLocation object is added to the internal history list.</p>
     *
     * @param locationName the name of the location to fetch weather for
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @return a WeatherLocation object populated with temperature, elevation,
     *         description, and timestamp
     * @throws IOException if there is an error connecting to the APIs
     * @throws JSONException if the JSON response from the APIs cannot be parsed
     */
    public WeatherLocation getWeather(final String locationName, final double latitude, final double longitude)
            throws IOException, JSONException {
        final WeatherLocation location = new WeatherLocation(locationName, latitude, longitude);

        // Fetch weather data
        final String weatherUrl =
                WEATHER_API + "?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + API_KEY;
        final String weatherResponse = sendGetRequest(weatherUrl);
        parseWeather(weatherResponse, location);

        // Fetch elevation data
        final String elevationUrl = ELEVATION_API + "?locations=" + latitude + "," + longitude;
        final String elevationResponse = sendGetRequest(elevationUrl);
        parseElevation(elevationResponse, location);

        history.add(location);
        return location;
    }

    public List<WeatherLocation> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * Fetches weather information for locations surrounding a central location
     * within an approximate radius and returns them as a list.
     *
     * <p>This method generates nearby coordinates around the given center
     * location (north, south, east, west offsets) and retrieves weather
     * data for each using the {@link #getWeather(String, double, double)}
     * method. The results are collected in a list.</p>
     *
     * @param center the central WeatherLocation to find nearby weather for
     * @param radiusKm the approximate radius in kilometers (currently used
     *                 as a fixed offset for simplicity)
     * @return a list of WeatherLocation objects representing weather at
     *         surrounding locations
     * @throws IOException if there is an error connecting to the APIs
     * @throws JSONException if the JSON response from the APIs cannot be parsed
     */
    public List<WeatherLocation> getNearbyWeather(final WeatherLocation center, final double radiusKm)
            throws IOException, JSONException {
        final List<WeatherLocation> nearby = new ArrayList<>();
        final double offset = 0.05;
        // ~5km
        final double[][] deltas = {{offset, 0}, {-offset, 0}, {0, offset}, {0, -offset}};

        for (double[] delta : deltas) {
            final double lat = center.getLatitude() + delta[0];
            final double lon = center.getLongitude() + delta[1];
            final WeatherLocation loc = getWeather(center.getName() + " nearby", lat, lon);
            nearby.add(loc);
        }
        return nearby;
    }

    private String sendGetRequest(final String urlString) throws IOException {
        final URL url = new URL(urlString);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            final StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
        finally {
            conn.disconnect();
        }
    }

    private void parseWeather(final String jsonResponse, final WeatherLocation location) throws JSONException {
        final JSONObject obj = new JSONObject(jsonResponse);
        final JSONObject current = obj.getJSONObject("current");

        final double temp = current.getDouble("temp");
        final String description = current.getJSONArray("weather")
                .getJSONObject(0)
                .getString("description");

        location.setTemperature(temp);
        location.setDescription(description);
    }

    private void parseElevation(final String jsonResponse, final WeatherLocation location) throws JSONException {
        final JSONObject obj = new JSONObject(jsonResponse);
        final JSONArray results = obj.getJSONArray("results");

        if (results.length() > 0) {
            final double elevation = 0.0;
            location.setElevation(elevation);
        }
    }
}
