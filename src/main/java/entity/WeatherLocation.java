package entity;

public class WeatherLocation {
    private String name;
    private double latitude;
    private double longitude;
    // Celsius
    private double temperature;
    // meters
    private double elevation;
    // e.g., "Clear sky"
    private String description;

    public WeatherLocation(String name, double lat, double lon) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
