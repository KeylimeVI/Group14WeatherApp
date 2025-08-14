package use_case.weather;

import entity.WeatherLocation;

/**
 * WeatherAdvisor provides recommendations for clothing and activities
 * based on current weather, description, and elevation.
 */
public class WeatherAdvisor {

    /**
     * Generates recommendations for clothing and activities based on weather and elevation.
     *
     * @param location the WeatherLocation object
     * @return a string containing suggestions
     */
    public static String getRecommendations(final WeatherLocation location) {
        final StringBuilder sb = new StringBuilder();
        final double temp = location.getTemperature();
        final String desc = location.getDescription().toLowerCase();
        final double elevation = location.getElevation();

        // Clothing thresholds
        final int freezingTemp = 0;
        final int coldTemp = 10;
        final int mildTemp = 20;
        final int highAltitude = 2000;

        // Clothing suggestions
        if (temp <= freezingTemp) {
            sb.append("Wear heavy winter clothing. ");
        }
        else if (temp <= coldTemp) {
            sb.append("Wear a coat or warm layers. ");
        }
        else if (temp <= mildTemp) {
            sb.append("Wear light layers. ");
        }
        else {
            sb.append("Wear summer clothing. ");
        }

        // Activity suggestions
        if (desc.contains("rain")) {
            sb.append("Consider indoor activities.");
        }
        else if (desc.contains("snow")) {
            sb.append("Good for skiing or snow activities.");
        }
        else {
            sb.append("Outdoor activities recommended.");
        }

        // Elevation warning
        if (elevation > highAltitude) {
            sb.append(" Be cautious of high-altitude effects.");
        }

        return sb.toString();
    }
}
