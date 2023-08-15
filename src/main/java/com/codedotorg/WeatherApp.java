package com.codedotorg;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeatherApp {

    /** The main window to display the app */
    private Stage window;

    /** The width of the primary stage */
    private int width;

    /** The height of the primary stage */
    private int height;

    /** The text field for the user to enter the city */
    private TextField cityText;

    /** The label to display the weather */
    private Label weatherLabel;

    /**
     * Constructs a new WeatherApp object with the given Stage, width and height.
     * Initializes the window, width, height, cityText and weatherLabel.
     *
     * @param primaryStage the primary stage of the application
     * @param width the width of the application window
     * @param height the height of the application window
     */
    public WeatherApp(Stage primaryStage, int width, int height) {
        this.window = primaryStage;
        this.width = width;
        this.height = height;

        cityText = new TextField();
        weatherLabel = new Label();
    }
    
    /**
     * This method starts the WeatherWise application by setting the title
     * of the window, creating the button layout, creating the main layout,
     * creating the scene, and setting the scene and showing it.
     */
    public void startApp() {
        window.setTitle("WeatherWise");

        HBox buttonLayout = createButtonLayout();
        VBox mainLayout = createMainLayout(buttonLayout);
        Scene mainScene = createScene(mainLayout);

        setSceneAndShow(mainScene);
    }

    /**
     * Creates a horizontal box layout containing two buttons:
     * "Get Weather" and "Get 7 Day Forecast".
     * 
     * @return the created HBox layout
     */
    public HBox createButtonLayout() {
        Button weatherButton = createButton("Get Weather");
        Button forecastButton = createButton("Get 7 Day Forecast");

        HBox tempLayout = new HBox(weatherButton, forecastButton);

        tempLayout.setSpacing(10);
        tempLayout.setAlignment(Pos.CENTER);

        return tempLayout;
    }

    /**
     * Creates the main layout of the WeatherApp GUI, which includes a label
     * prompting the user to enter a city, a text field for the user to enter
     * the city, a button layout, and a label to display the weather information.
     *
     * @param buttonLayout the HBox layout containing the buttons for the GUI
     * @return the VBox layout containing the main components of the GUI
     */
    public VBox createMainLayout(HBox buttonLayout) {
        Label titleLabel = new Label("WeatherWise");
        Label cityLabel = new Label("Enter city: ");
        titleLabel.setId("titleLabel");

        VBox tempLayout = new VBox(titleLabel, cityLabel, cityText, buttonLayout, weatherLabel);

        tempLayout.setSpacing(10);
        tempLayout.setPadding(new Insets(20, 20, 20, 20));

        return tempLayout;
    }

    /**
     * Creates a new Scene object with the specified layout, width, and height.
     * 
     * @param currentLayout the layout to be used in the scene
     * @return the newly created Scene object
     */
    public Scene createScene(VBox currentLayout) {
        Scene tempScene = new Scene(currentLayout, width, height);
        tempScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return tempScene;
    }

    /**
     * Sets the current scene and shows the window.
     * 
     * @param currentScene the scene to be set and shown
     */
    public void setSceneAndShow(Scene currentScene) {
        window.setScene(currentScene);
        window.show();
    }

    /**
     * Creates a new button with the given text and sets its action to
     * update the weather label with the corresponding weather option.
     * 
     * @param buttonText the text to display on the button
     * @return the newly created button
     */
    public Button createButton(String buttonText) {
        Button tempButton = new Button(buttonText);
        AppLogic logic = new AppLogic();

        tempButton.setOnAction(event -> {
            String city = cityText.getText();
            weatherLabel.setText(logic.getWeatherOption(city, buttonText));
        });

        return tempButton;
    }

}
