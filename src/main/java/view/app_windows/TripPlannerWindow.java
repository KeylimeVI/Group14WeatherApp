package view.app_windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import entity.WeatherLocation;
import use_case.weather.TripLocation;
import use_case.weather.TripPlannerController;
import use_case.weather.WeatherController;

/**
 * TripPlannerWindow provides a GUI to fetch weather information
 * for multiple locations in a trip, including user-specified dates.
 */
public class TripPlannerWindow extends JFrame {

    private static final int WINDOW_SIZE = 550;
    private static final int BORDER_GAP = 10;

    private final JTextArea locationsArea;
    private final JTextArea resultArea;
    private final JButton fetchButton;
    private final TripPlannerController tripPlannerController;

    public TripPlannerWindow() {
        super("Trip Planner Weather");
        tripPlannerController = new TripPlannerController(new WeatherController());

        final int locationRows = 8;
        final int locationColumns = 30;
        final int resultRows = 15;
        final int resultColumns = 40;
        locationsArea = new JTextArea(locationRows, locationColumns);
        resultArea = new JTextArea(resultRows, resultColumns);

        resultArea.setEditable(false);
        fetchButton = new JButton("Get Trip Weather");

        setupUiComponents();
    }

    private void setupUiComponents() {
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setLayout(new BorderLayout(BORDER_GAP, BORDER_GAP));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Enter locations (name,lat,lon,yyyy-mm-dd per line):"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(locationsArea), BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(fetchButton, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        fetchButton.addActionListener(this::onFetchTripWeather);

        setLocationRelativeTo(null);
    }

    private void onFetchTripWeather(final ActionEvent actionEvent) {
        final List<TripLocation> locations = parseLocations();
        if (locations.isEmpty()) {
            showNoLocationsMessage();
        }
        else {
            fetchAndDisplayTripWeather(locations);
        }
    }

    private List<TripLocation> parseLocations() {
        final List<TripLocation> locations = new ArrayList<>();
        final String[] lines = locationsArea.getText().split("\\r?\\n");
        final int tripLocationParts = 4;
        final int nameIndex = 0;
        final int latIndex = 1;
        final int lonIndex = 2;
        final int dateIndex = 3;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            final String[] parts = line.split(",");
            if (parts.length != tripLocationParts) {
                continue;
            }

            try {
                final String name = parts[nameIndex].trim();
                final double lat = Double.parseDouble(parts[latIndex].trim());
                final double lon = Double.parseDouble(parts[lonIndex].trim());
                final LocalDate date = LocalDate.parse(parts[dateIndex].trim());
                locations.add(new TripLocation(name, lat, lon, date));
            }
            catch (NumberFormatException | java.time.format.DateTimeParseException ex) {
                // Skip invalid line
            }
        }
        return locations;
    }

    private void showNoLocationsMessage() {
        JOptionPane.showMessageDialog(
                this,
                "No valid locations entered.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void fetchAndDisplayTripWeather(final List<TripLocation> locations) {
        try {
            final double nearbyRadius = 5.0;
            // km
            final List<WeatherLocation> tripWeather =
                    tripPlannerController.getTripWeatherWithNearby(locations, nearbyRadius);
            final StringBuilder resultText = new StringBuilder();
            for (WeatherLocation loc : tripWeather) {
                appendWeatherLocation(resultText, loc, null);
                // no specific TripLocation for nearby
            }

            resultArea.setText(resultText.toString());
        }
        catch (org.json.JSONException jsonEx) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error parsing weather data: " + jsonEx.getMessage(),
                    "Parsing Error",
                    JOptionPane.ERROR_MESSAGE
            );
            jsonEx.printStackTrace();
        }
    }

    private void appendWeatherLocation(StringBuilder resultBuilder, WeatherLocation loc, TripLocation tripLoc) {
        String savedText = "";
        if (loc.isSaved()) {
            savedText = " (Saved)";
        }
        resultBuilder.append("Location: ").append(loc.getName()).append(savedText)
                .append("\nDate: ").append(tripLoc.getDate())
                .append("\nTemperature: ").append(loc.getTemperature()).append(" Celsius\n")
                .append("Elevation: ").append(loc.getElevation()).append(" m\n")
                .append("Description: ").append(loc.getDescription())
                .append("\n\n");
    }
}
