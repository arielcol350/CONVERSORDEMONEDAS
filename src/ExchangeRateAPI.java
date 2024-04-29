import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateAPI {
    private static final String API_KEY = "YOUR API KEY"; // Reemplezar con tu KEY
    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public double convertCurrency(String desdeMoneda, String haciaMoneda, double cantidad) {
        try {
            // Crea la URL para poder hacer la solicitud
            String apiUrl = BASE_URL + desdeMoneda;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // obtener respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Responde con un Json, debemos obtener el exchange rate del Json
                double exchangeRate = parseExchangeRateFromJson(response.toString(), haciaMoneda);

                // hagamos la conversion
                return cantidad * exchangeRate;
            } else {
                System.out.println("No pude encontrar el intercambio. Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    //esto es para convertir el Json en un objeto y asi poder interpretarlo usando Gson
    private double parseExchangeRateFromJson(String json, String hacia) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("rates");

        if (rates.has(hacia)) {
            return rates.get(hacia).getAsDouble();
        } else {
            System.out.println("El intercambio para la moneda '" + hacia + "' no fue encontrado en el Json.");
            return 0.0;
        }
    }
}
