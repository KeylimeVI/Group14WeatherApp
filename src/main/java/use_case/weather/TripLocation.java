package use_case.weather;

import java.time.LocalDate;

/**
 * Represents a location in a trip, including coordinates and the planned date.
 */
public class TripLocation {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final LocalDate date;

    public TripLocation(String name, double latitude, double longitude, LocalDate date) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public char[] getDate() {
        return this.date.toString().toCharArray();
    }
}
