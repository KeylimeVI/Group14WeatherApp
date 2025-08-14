package use_case.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.WeatherLocation;

/**
 * WeatherController handles fetching and parsing weather and elevation data
 * for a given location.
 */
public class WeatherController {

    private static final String WEATHER_API = "https://api.openweathermap.org/data/3.0/onecall";
    private static final String ELEVATION_API = "https://api.opentopodata.org/v1/eudem25m";

    /**
     * Fetches weather and elevation data for a specified location.
     *
     * @param locationName the name of the location
     * @param latitude     the latitude of the location
     * @param longitude    the longitude of the location
     * @return a WeatherLocation object populated with temperature, elevation, and description
     * @throws IOException   if an I/O error occurs when connecting to the APIs
     * @throws JSONException if parsing the JSON response fails
     */
    public WeatherLocation getWeather(final String locationName, final double latitude, final double longitude)
            throws IOException, JSONException {
        final WeatherLocation location = new WeatherLocation(locationName, latitude, longitude);

        // Fetch weather data
        final String weatherUrl =
                WEATHER_API + "?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=YOUR_KEY_HERE";
        final String weatherResponse = sendGetRequest(weatherUrl);
        parseWeather(weatherResponse, location);

        // Fetch elevation data
        final String elevationUrl = ELEVATION_API + "?locations=" + latitude + "," + longitude;
        final String elevationResponse = sendGetRequest(elevationUrl);
        parseElevation(elevationResponse, location);

        return location;
    }

    /**
     * Sends an HTTP GET request to the specified URL.
     *
     * @param urlString the URL to send the request to
     * @return the response body as a string
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Parses weather JSON and sets temperature and description in the location.
     *
     * @param jsonResponse the JSON response from the weather API
     * @param location     the WeatherLocation to populate
     * @throws JSONException if parsing fails
     */
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

    /**
     * Parses elevation JSON and sets the elevation in the location.
     *
     * @param jsonResponse the JSON response from the elevation API
     * @param location     the WeatherLocation to populate
     * @throws JSONException if parsing fails
     */
    private void parseElevation(final String jsonResponse, final WeatherLocation location) throws JSONException {
        final JSONObject obj = new JSONObject(jsonResponse);
        final JSONArray results = obj.getJSONArray("results");

        if (results.length() > 0) {
            final double elevation = results.getJSONObject(0).getDouble("elevation");
            location.setElevation(elevation);
        }
    }
}
