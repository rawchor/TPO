package zad1;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

//extends StackPane
public class Browser extends StackPane {
    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();

    public Browser() {
        webEngine.load("https://en.wikipedia.org/wiki/Weather");
        //webEngine.load("http://www.infoservice.com.pl/");

        getChildren().add(browser);
    }

    public void loadPage(String city) {
        webEngine.load("https://en.wikipedia.org/wiki/" + city);
    }
}
