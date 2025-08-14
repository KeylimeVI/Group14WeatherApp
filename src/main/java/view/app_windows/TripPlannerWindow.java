package view.app_windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
import use_case.weather.TripPlannerController;
import use_case.weather.TripPlannerController.TripLocation;
import use_case.weather.WeatherController;

/**
 * TripPlannerWindow provides a GUI to fetch weather information
 * for multiple locations in a trip.
 */
public class TripPlannerWindow extends JFrame {

    private static final int WINDOW_SIZE = 500;
    private static final int BORDER_GAP = 10;

    private final JTextArea locationsArea;
    private final JTextArea resultArea;
    private final JButton fetchButton;
    private final TripPlannerController tripPlannerController;

    /**
     * Constructs a TripPlannerWindow and initializes GUI components.
     */
    public TripPlannerWindow() {
        super("Trip Planner Weather");
        tripPlannerController = new TripPlannerController(new WeatherController());

        locationsArea = new JTextArea();
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        fetchButton = new JButton("Get Trip Weather");

        setupUiComponents();
    }

    /**
     * Sets up the GUI layout and components.
     */
    private void setupUiComponents() {
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setLayout(new BorderLayout(BORDER_GAP, BORDER_GAP));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Enter locations (name,lat,lon per line):"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(locationsArea), BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(fetchButton, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        fetchButton.addActionListener(this::onFetchTripWeather);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Handles fetching weather for all trip locations entered by the user.
     *
     * @param actionEvent the ActionEvent triggered by clicking the button
     */
    private void onFetchTripWeather(final ActionEvent actionEvent) {
        final List<TripLocation> locations = new ArrayList<>();
        final String[] lines = locationsArea.getText().split("\\r?\\n");
        final int tripLocationParts = 3;

        try {
            for (final String line : lines) {
                final String[] parts = line.split(",");
                if (parts.length != tripLocationParts) {
                    continue;
                }

                final String name = parts[0].trim();
                final double lat = Double.parseDouble(parts[1].trim());
                final double lon = Double.parseDouble(parts[2].trim());
                locations.add(new TripLocation(name, lat, lon));
            }

            // Use TripPlannerController to fetch weather for all locations
            final List<WeatherLocation> tripWeather = tripPlannerController.getTripWeather(locations);

            final StringBuilder resultText = new StringBuilder();
            for (final WeatherLocation loc : tripWeather) {
                resultText.append("Location: ").append(loc.getName())
                        .append("\nTemperature: ").append(loc.getTemperature()).append(" Celsius\n")
                        .append("Elevation: ").append(loc.getElevation()).append(" m\n")
                        .append("Description: ").append(loc.getDescription())
                        .append("\n\n");
            }

            resultArea.setText(resultText.toString());

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for latitude and longitude.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );
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
}
