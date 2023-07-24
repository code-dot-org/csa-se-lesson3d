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

    private Stage window;
    private Label cityLabel;
    private TextField cityText;
    private Button weatherButton;
    private Button forecastButton;
    private Label weatherLabel;

    public WeatherApp() {
        this.cityLabel = new Label("Enter city: ");
        this.cityText = new TextField();
        this.weatherButton = new Button("Get Weather");
        this.forecastButton = new Button("Get 7 Day Forecast");
        this.weatherLabel = new Label();
    }
    
    public void startApp(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("WeatherWise");

        setWeatherButtonAction();
        setForecastButtonAction();

        HBox hbox = new HBox(weatherButton, forecastButton);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(cityLabel, cityText, hbox, weatherLabel);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(vbox, 500, 500);
        window.setScene(scene);
        window.show();
    }

    private void setWeatherButtonAction() {
        weatherButton.setOnAction(event -> {
            String city = cityText.getText();
            String response = WeatherAPI.getWeather(city);
            weatherLabel.setText(response);
        });
    }

    private void setForecastButtonAction() {
        forecastButton.setOnAction(event -> {
            

            
        });
    }

}
