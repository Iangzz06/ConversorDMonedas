import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {
    private static final String API_KEY = "4fa45915d38daf9c25af7cf4";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public Monedas buscarMoneda(String monedaBase, String monedaTarget) {
        // Construcción de la URL de la API
        String url = BASE_URL + API_KEY + "/pair/" + monedaBase + "/" + monedaTarget;

        // Crear la URI
        URI direccion = URI.create(url);

        // Crear cliente HTTP y solicitud
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // Enviar solicitud y obtener respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Validar código de estado HTTP
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error en la consulta: Código de estado " + response.statusCode());
            }

            // Parsear JSON a objeto Monedas
            return new Gson().fromJson(response.body(), Monedas.class);
        } catch (Exception e) {
            System.err.println("Error al consultar la moneda: " + e.getMessage());
            throw new RuntimeException("No se encontró la moneda o ocurrió un error en la consulta.");
        }
    }
}
