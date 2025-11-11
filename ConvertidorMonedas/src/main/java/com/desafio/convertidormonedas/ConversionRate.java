package com.desafio.convertidormonedas;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/**
 * Clase para modelar la respuesta de la API de ExchangeRate.
 * @author adrian
 */
public class ConversionRate {

    private String result;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}
