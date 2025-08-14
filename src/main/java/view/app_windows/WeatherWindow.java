package view.app_windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.WeatherLocation;
import use_case.weather.WeatherController;

/**
 * WeatherWindow provides a simple GUI to fetch and display
 * weather and elevation information for a given location.
 */
public class WeatherWindow extends JFrame {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 300;
    private static final int BORDER_GAP = 10;
    private static final int INPUT_HGAP = 5;
    private static final int INPUT_VGAP = 5;

    private final JTextField latField;
    private final JTextField lonField;
    private final JTextField nameField;
    private final JButton fetchButton;
    private final JTextArea resultArea;
    private final WeatherController controller;

    /**
     * Constructs a WeatherWindow and initializes GUI components.
     */
    public WeatherWindow() {
        super("Weather Checker");
        controller = new WeatherController();

        // Initialize components
        nameField = new JTextField();
        latField = new JTextField();
        lonField = new JTextField();
        fetchButton = new JButton("Get Weather");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        setupUi();
    }

    /**
     * Sets up the GUI layout and components.
     */
    private void setupUi() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(BORDER_GAP, BORDER_GAP));

        // Input panel
        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, INPUT_HGAP, INPUT_VGAP));
        inputPanel.add(new JLabel("Location Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Latitude:"));
        inputPanel.add(latField);
        inputPanel.add(new JLabel("Longitude:"));
        inputPanel.add(lonField);

        fetchButton.addActionListener(this::onFetchWeather);

        add(inputPanel, BorderLayout.NORTH);
        add(fetchButton, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Handles the action when the "Get Weather" button is clicked.
     *
     * @param event the ActionEvent triggered by the button click
     */
    private void onFetchWeather(final ActionEvent event) {
        final String locationName = nameField.getText();

        try {
            final double latitude = Double.parseDouble(latField.getText());
            final double longitude = Double.parseDouble(lonField.getText());

            final WeatherLocation location = controller.getWeather(locationName, latitude, longitude);

            final StringBuilder resultText = new StringBuilder();
            resultText.append("Location: ").append(location.getName()).append("\n")
                    .append("Temperature: ").append(location.getTemperature()).append(" Celcius\n")
                    .append("Elevation: ").append(location.getElevation()).append(" m\n")
                    .append("Description: ").append(location.getDescription());

            resultArea.setText(resultText.toString());

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
    
    /**
     * Entry point for the WeatherWindow application.
     *
     * @param args command-line arguments (not used)
     */
    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:SuppressWarnings"})
    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(WeatherWindow::new);
    }
}
