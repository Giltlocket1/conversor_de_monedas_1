package com.desafio.convertidormonedas;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class ApiHandler {

    // ✅ Usa tu API key real aquí
    private static final String API_KEY = "076a07f24f166a9419c80541";

    // ✅ URL correcta para la API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    /**
     * Obtiene las tasas de conversión para una moneda base dada.
     */
    public ConversionRate getRates(String baseCode) throws IOException {

        // ✅ Se agrega "/" para evitar errores con la URL
        String url = BASE_URL + "/" + baseCode;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("Código de respuesta inesperado: " + response.code());
            }

            return gson.fromJson(response.body().string(), ConversionRate.class);
        }
    }
}
