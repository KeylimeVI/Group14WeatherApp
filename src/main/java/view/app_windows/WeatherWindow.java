package view.app_windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.WeatherLocation;
import use_case.weather.WeatherAdvisor;
import use_case.weather.WeatherController;

/**
 * WeatherWindow provides a GUI to fetch, display, and track
 * weather and elevation information for a given location,
 * including nearby weather and recommendations.
 */
public class WeatherWindow extends JFrame {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 500;
    private static final int BORDER_GAP = 10;
    private static final int INPUT_HGAP = 5;
    private static final int INPUT_VGAP = 5;
    private static final int RESULT_ROWS = 5;
    private static final int RESULT_COLUMNS = 20;
    private static final int HISTORY_ROWS = 8;
    private static final int HISTORY_COLUMNS = 20;

    private final JTextField latField;
    private final JTextField lonField;
    private final JTextField nameField;
    private final JButton fetchButton;
    private final JButton nearbyButton;
    private final JTextArea resultArea;
    private final JTextArea historyArea;
    private final JTextArea recommendationArea;
    private final WeatherController controller;

    public WeatherWindow() {
        super("Weather Checker");
        controller = new WeatherController();

        nameField = new JTextField();
        latField = new JTextField();
        lonField = new JTextField();
        fetchButton = new JButton("Get Weather");
        nearbyButton = new JButton("Show Nearby Weather");

        resultArea = new JTextArea(RESULT_ROWS, RESULT_COLUMNS);
        resultArea.setEditable(false);

        historyArea = new JTextArea(HISTORY_ROWS, HISTORY_COLUMNS);
        historyArea.setEditable(false);

        recommendationArea = new JTextArea(RESULT_ROWS, RESULT_COLUMNS);
        recommendationArea.setEditable(false);

        fetchButton.addActionListener(this::onFetchWeather);
        nearbyButton.addActionListener(this::onShowNearbyWeather);

        setupUi();
    }

    private void setupUi() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(BORDER_GAP, BORDER_GAP));

        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, INPUT_HGAP, INPUT_VGAP));
        inputPanel.add(new JLabel("Location Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Latitude:"));
        inputPanel.add(latField);
        inputPanel.add(new JLabel("Longitude:"));
        inputPanel.add(lonField);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(fetchButton);
        buttonPanel.add(nearbyButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        final JPanel displayPanel = new JPanel(new BorderLayout(BORDER_GAP, BORDER_GAP));
        displayPanel.add(new JScrollPane(resultArea), BorderLayout.NORTH);
        displayPanel.add(new JScrollPane(recommendationArea), BorderLayout.CENTER);
        displayPanel.add(new JLabel("History:"), BorderLayout.SOUTH);
        displayPanel.add(new JScrollPane(historyArea), BorderLayout.SOUTH);

        add(displayPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onFetchWeather(final ActionEvent event) {
        final String locationName = nameField.getText();

        try {
            final double latitude = Double.parseDouble(latField.getText());
            final double longitude = Double.parseDouble(lonField.getText());

            final WeatherLocation location = controller.getWeather(locationName, latitude, longitude);

            resultArea.setText(buildLocationText(location));
            recommendationArea.setText(WeatherAdvisor.getRecommendations(location));
            updateHistory();
        }
        catch (NumberFormatException parseEx) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for latitude and longitude.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        catch (IOException ioEx) {
            JOptionPane.showMessageDialog(this,
                    "Error connecting to the weather API: " + ioEx.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            ioEx.printStackTrace();
        }
        catch (org.json.JSONException jsonEx) {
            JOptionPane.showMessageDialog(this,
                    "Error parsing weather data: " + jsonEx.getMessage(),
                    "Parsing Error",
                    JOptionPane.ERROR_MESSAGE);
            jsonEx.printStackTrace();
        }
    }

    private void onShowNearbyWeather(final ActionEvent event) {
        final String locationName = nameField.getText();

        try {
            final double latitude = Double.parseDouble(latField.getText());
            final double longitude = Double.parseDouble(lonField.getText());

            final WeatherLocation center = new WeatherLocation(locationName, latitude, longitude);
            final List<WeatherLocation> nearbyList = controller.getNearbyWeather(center, 5);

            final StringBuilder sb = new StringBuilder();
            for (WeatherLocation loc : nearbyList) {
                sb.append(buildLocationText(loc)).append("\n----------------\n");
            }

            resultArea.setText(sb.toString());
            nearbyList.forEach(controller.getHistory()::add);
            updateHistory();
        }
        catch (NumberFormatException parseEx) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for latitude and longitude.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        catch (IOException | org.json.JSONException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error fetching nearby weather: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private String buildLocationText(final WeatherLocation loc) {
        String savedText = "";
        if (loc.isSaved()) {
            savedText = " (Saved)";
        }
        return "Location: " + loc.getName() + savedText + "\n"
                + "Temperature: " + loc.getTemperature() + " Celsius\n"
                + "Elevation: " + loc.getElevation() + " m\n"
                + "Description: " + loc.getDescription();
    }

    private void updateHistory() {
        final List<WeatherLocation> history = controller.getHistory();
        final StringBuilder sb = new StringBuilder();
        for (final WeatherLocation loc : history) {
            sb.append(buildLocationText(loc)).append("\n----------------\n");
        }
        historyArea.setText(sb.toString());
    }
}
