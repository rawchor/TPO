/**
 *
 *  @author Surowiec Borys S20927
 *
 */

package zad1;

import com.google.gson.*;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class Service {
    private final Locale locale;

    public Service(String country) {
        country = country.replaceAll("\\s", "");
        this.locale = getLocaleFromNameOfCountry(country);
    }

    private Locale getLocaleFromNameOfCountry(String country) {
        for (Locale locale : Locale.getAvailableLocales())
            if (country.equals(locale.getDisplayCountry()))
                return locale;
        return null;
    }

    public String getWeather(String city) {
        String weather = null;
        String apiWeatherKey = "b7c138a1d0e6357dbdfdf47d0fa12cbf";
        city = city.replaceAll("\\s", "");
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+locale.getCountry()+"&appid="+apiWeatherKey+"&units=metric");
            URLConnection request = url.openConnection();
            request.connect();

            // google json api
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader(request.getInputStream()));
            weather = jsonElement.toString();

            JsonObject temp = jsonElement.getAsJsonObject();
            System.out.println("JSON temp: \n"+temp);

        } catch (Exception ignored) {

        }
        return weather;
    }

    public Double getRateFor(String currency) {
        Double rate = null;
        try {
            URL url = new URL("https://api.exchangerate.host/latest");

            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader(request.getInputStream()));

            Double selectedCurrency = jsonElement.getAsJsonObject().get("rates").getAsJsonObject().get(currency).getAsDouble();
            Double countryCurrency = jsonElement.getAsJsonObject().get("rates").getAsJsonObject().get(String.valueOf(Currency.getInstance(locale))).getAsDouble();
            rate = countryCurrency/selectedCurrency;

            System.out.println("JSON currency: \n"+jsonElement);
        }catch (Exception ignore){

        }
        return rate;
    }

    public Double getNBPRate(){
        //String[] url = {"http://api.nbp.pl/api/exchangerates/tables/a/?format=json", "http://api.nbp.pl/api/exchangerates/tables/b/?format=json"};

        String[] table = {"http://api.nbp.pl/api/exchangerates/rates/a", "http://api.nbp.pl/api/exchangerates/rates/b"};
        Double rateNBP = null;
        //String page = "a";
        Currency countryCode = Currency.getInstance(locale);
        for (int i = 0; i < 2 && rateNBP == null; i++) {
            try {
                if (Objects.equals(countryCode.toString(), "PLN")) {
                    return 1.0;
                } else {
                    System.out.println(countryCode);
                    //URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/"+page+"/"+countryCode+"/?format=json");
                    URL url = new URL(table[i] + "/" + countryCode + "/?format=json");
                    //String url1 = "https://www.geeksforgeeks.org/";
                    System.out.println(url);
                    URLConnection request = url.openConnection();
                    request.connect();

                    // google json api
                    JsonParser jsonParser = new JsonParser();
                    JsonElement jsonElement = jsonParser.parse(new InputStreamReader(request.getInputStream()));

                    //System.out.println(nbpRate);
                    System.out.println("JSON nbp: \n" + jsonElement);

                    System.out.println("c: " + countryCode);
                    //String countryCurrency = jsonElement.getAsJsonObject().get("rates").getAsJsonObject().get("mid").getAsString();
                    //System.out.println("countrycurr: "+countryCurrency);
                    //String conditions = jsonElement.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();

                    rateNBP = jsonElement.getAsJsonObject().get("rates").getAsJsonArray().get(0).getAsJsonObject().get("mid").getAsDouble();

                    //System.out.println(countryCurrency);
                    //rateNBP = 1.0/Double.parseDouble(countryCurrency);
                    System.out.println("curr: " + rateNBP);
                }
            } catch (Exception ignored) {

            }
        }
        return 1.0/rateNBP;
    }
}