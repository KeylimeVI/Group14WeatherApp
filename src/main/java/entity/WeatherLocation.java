package entity;

import java.time.LocalDateTime;

/**
 * Represents weather information for a specific location,
 * including timestamp for history tracking and an optional saved flag.
 */
public class WeatherLocation {
    private String name;
    private double latitude;
    private double longitude;
    private double temperature;
    // Celsius
    private double elevation;
    // meters
    private String description;
    // e.g., "Clear sky"
    private LocalDateTime timestamp;
    // When this weather info was fetched
    private boolean saved;
    // Indicates if the user has saved this location

    /**
     * Constructs a WeatherLocation for a specific name and coordinates.
     *
     * @param name the name of the location
     * @param lat  the latitude of the location
     * @param lon  the longitude of the location
     */
    public WeatherLocation(final String name, final double lat, final double lon) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
        this.timestamp = LocalDateTime.now();
        this.saved = false;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(final double elevation) {
        this.elevation = elevation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(final boolean saved) {
        this.saved = saved;
    }
}
