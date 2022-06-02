package zad1;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Objects;

public class WeatherApp extends Application {
    public String[] location;
    private String currency;
    private Service service;
    private final JsonParser jsonParser = new JsonParser();

    Browser browser = new Browser();
    BorderPane borderPane = new BorderPane();
    BorderPane toolBar = new BorderPane();
    BorderPane browserPane = new BorderPane();
    BorderPane weatherPane = new BorderPane();
    FlowPane weatherSort = new FlowPane();

    BorderPane toolBarL = new BorderPane();
    BorderPane toolBarR = new BorderPane();
    BorderPane toolBarC = new BorderPane();

    HBox hBoxLeft = new HBox();
    HBox hBoxCenter = new HBox();
    HBox hBoxRight = new HBox();

    Label labelPLN = new Label("PLN: null");
    Label currencyRate = new Label("Currency rate: null");
    Label temperature = new Label("Temperature: null");
    Label wind = new Label("Wind: null");
    Label humidity = new Label("Humidity: null");
    Label pressure = new Label("Pressure: null");
    //TextField weatherField = new TextField("Warsaw, Poland");
    TextField weatherField = new TextField("Berlin, Germany");
    Label weatherLabel = new Label("Location:");
    TextField currencyField = new TextField("USD");
    Label currencyLabel = new Label("Currency:");

    @Override
    public void start(Stage stage){

        stage.setTitle("WeatherApp");
        stage.setMinWidth(960);
        stage.setMinHeight(540);
        //stage.setMinWidth(1280); stage.setMinHeight(720);

        //Image icon = new Image(getClass().getResourceAsStream("windsock.png"));
        //stage.getIcons().add(icon);
        //stage.getIcons().add(new Image(Main.class.getResourceAsStream("/img/droplet.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/droplet.png")));

        /*
        Browser browser = new Browser();

        BorderPane borderPane = new BorderPane();

        BorderPane toolBar = new BorderPane();

        BorderPane toolBarL = new BorderPane();
        BorderPane toolBarR = new BorderPane();
        BorderPane toolBarC = new BorderPane();

        BorderPane browserPane = new BorderPane();
        BorderPane weatherPane = new BorderPane();
        FlowPane weatherSort = new FlowPane();

        Label labelPLN = new Label("PLN: null");
        Label currencyRate = new Label("Currency rate: null");
        Label temperature = new Label("Temperature: null");
        Label wind = new Label("Wind: null");
        Label humidity = new Label("Humidity: null");
        Label pressure = new Label("Pressure: null");
        //TextField weatherField = new TextField("Warsaw, Poland");
        TextField weatherField = new TextField("Berlin, Germany");
        Label weatherLabel = new Label("Location:");
        TextField currencyField = new TextField("USD");
        Label currencyLabel = new Label("Currency:");
        */
        toolBar.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        weatherPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        borderPane.setCenter(browserPane);
        borderPane.setTop(toolBar);
        browserPane.setCenter(browser);
        browserPane.setTop(weatherPane);
        borderPane.setTop(toolBar);
        //toolBar.setStyle("-fx-background-color: lightgrey");
        toolBar.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        toolBar.setMinHeight(50);

        toolBar.setLeft(toolBarL);
        //weatherPane.setLeft(toolBarC);
        weatherPane.setCenter(weatherSort);
        toolBar.setRight(toolBarR);

        hBoxLeft.setSpacing(10);
        hBoxLeft.setAlignment(Pos.CENTER);
        hBoxLeft.setPadding(new Insets(0,10,0,10));

        hBoxCenter.setSpacing(10);
        hBoxCenter.setAlignment(Pos.CENTER);
        hBoxCenter.setPadding(new Insets(0,10,0,10));

        hBoxRight.setSpacing(10);
        hBoxRight.setAlignment(Pos.CENTER);
        hBoxRight.setPadding(new Insets(0,10,0,10));

        labelPLN.setFont(new Font(20));
        labelPLN.setAlignment(Pos.CENTER);
        labelPLN.setPadding(new Insets(0,5,0,5));

        currencyRate.setFont(new Font(20));
        currencyRate.setAlignment(Pos.CENTER);
        currencyRate.setPadding(new Insets(0,5,0,5));

        temperature.setFont(new Font(20));
        temperature.setAlignment(Pos.CENTER);
        temperature.setPadding(new Insets(0,10,0,10));

        wind.setFont(new Font(20));
        wind.setAlignment(Pos.CENTER);
        wind.setPadding(new Insets(0,10,0,10));

        humidity.setFont(new Font(20));
        humidity.setAlignment(Pos.CENTER);
        humidity.setPadding(new Insets(0,10,0,10));


        pressure.setFont(new Font(20));
        pressure.setAlignment(Pos.CENTER);
        pressure.setPadding(new Insets(0,10,0,10));


        toolBarL.setLeft(hBoxLeft);
        toolBarC.setCenter(hBoxCenter);
        toolBarR.setRight(hBoxRight);

        weatherField.setPrefWidth(150);
        weatherLabel.setFont(new Font(20));

        currencyField.setPrefWidth(50);
        currencyLabel.setFont(new Font(20));

        setData(weatherField);


        setData(currencyField);

        hBoxLeft.getChildren().addAll(weatherLabel,weatherField,currencyLabel,currencyField);
        //hBoxCenter.getChildren().addAll(temperature,wind);
        weatherSort.getChildren().addAll(temperature,wind,humidity,pressure) ;

        hBoxRight.getChildren().addAll(labelPLN,currencyRate);

        Scene scene = new Scene(borderPane,1280,720);
        stage.setScene(scene);
        stage.show();
    }

    private void setData(TextField field) {
        field.setOnAction(event -> {
            //---------------------------------
            location = weatherField.getText().split(",");
            System.out.println(location[0] + " " + location[1]);
            browser.loadPage(location[0]);
            service = new Service(location[1]);
            JsonElement jsonElement = jsonParser.parse(service.getWeather(location[0]));
            String conditions = jsonElement.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
            //String wind = jsonElement.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsString();
            DecimalFormat decimalFormat = new DecimalFormat("##.#");
            temperature.setText("Temperature: "+decimalFormat.format(jsonElement.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble())
                    + "\u2103 " + conditions + "\t");
            wind.setText("Wind: " + decimalFormat.format(jsonElement.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsDouble()) + " km/h\t");
            humidity.setText("Humidity: " + decimalFormat.format(jsonElement.getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsDouble()) + " %\t");
            pressure.setText("Pressure: " + decimalFormat.format(jsonElement.getAsJsonObject().get("main").getAsJsonObject().get("pressure").getAsDouble()) + " Pa");

            //labelPLN.setText("PLN: " + service.getNBPRate());
            DecimalFormat NBPDecimalformat = new DecimalFormat("#.###");
            System.out.println("ok: "+service.getNBPRate());
            labelPLN.setText("PLN: " + NBPDecimalformat.format(service.getNBPRate()));

            //---------------------------------
            DecimalFormat currDecimalFormat = new DecimalFormat("###.###");
            currency = currencyField.getText();
            currencyRate.setText("Currency rate: " + currDecimalFormat.format(service.getRateFor(currency)));
        });
    }
}
