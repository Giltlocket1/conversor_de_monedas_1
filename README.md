💱 Conversor de Monedas (Java + Swing + API ExchangeRate)

Aplicación desarrollada en Java con Swing que permite convertir valores
entre distintas monedas en tiempo real, utilizando la API pública de
ExchangeRate-API para obtener las tasas de cambio actualizadas.

------------------------------------------------------------------------

🚀 Funcionalidades

✅ Convierte monedas en tiempo real
✅ Actualiza los valores directamente desde la API
✅ Interfaz gráfica creada con Java Swing
✅ Manejo de errores cuando la API no responde
✅ Código limpio y estructurado con uso de OkHttp y Gson

------------------------------------------------------------------------

🛠️ Tecnologías utilizadas

  Tecnología / Librería   Uso
  ----------------------- ---------------------------------------
  Java 11+                Lenguaje base del proyecto
  Swing                   Creación de la interfaz gráfica
  OkHttp (Square)         Peticiones HTTP a la API
  Gson (Google)           Conversión JSON → Objetos Java
  ExchangeRate API        Servicio para obtener tasas de cambio

------------------------------------------------------------------------

🧩 Estructura del proyecto

    /src
     ├── model
     │     └── ConversionRate.java         # Clase para mapear la respuesta JSON
     ├── service
     │     └── CurrencyService.java        # Clase que se conecta a la API
     └── ui
           └── ConversorMonedasUI.java     # Interfaz gráfica con Swing

------------------------------------------------------------------------

🔑 Configuración de la API

Antes de ejecutar el proyecto, reemplaza la constante con tu propia API
KEY:

    private final String API_KEY = "TU_API_KEY_AQUI";
    private static final String API_URL =
            "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

Ejemplo final usado en este proyecto:

    private final String API_KEY = "076a07f24f166a9419c80541";
    private static final String API_URL =
            "https://v6.exchangerate-api.com/v6/076a07f24f166a9419c80541/latest/";

------------------------------------------------------------------------

🔧 Método que obtiene las tasas de conversión

    public ConversionRate getRates(String baseCode) throws IOException {
        String url = API_URL + baseCode;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Código de respuesta inesperado: " + response);
            }

            return gson.fromJson(response.body().string(), ConversionRate.class);
        }
    }

------------------------------------------------------------------------

▶️ Cómo ejecutar el proyecto

1.  Clonar el repositorio o descargarlo como ZIP.

2.  Asegúrate de tener Java 11 o superior.

3.  Agrega las dependencias:

    -   okhttp-<version>.jar
    -   gson-<version>.jar

4.  Ejecuta tu clase ConversorMonedasUI.

------------------------------------------------------------------------

🖥️ Interfaz gráfica

La aplicación carga la lista de monedas y permite convertir de manera
visual:

    -------------------------------------
    | Convertir     USD   →   MXN       |
    | Cantidad:     [ 100.00 ]          |
    | Resultado:    $ 1,826.53 MXN      |
    -------------------------------------

------------------------------------------------------------------------

🛡️ Manejo de errores

La app manejará:

-   API no disponible
-   Error en la conexión
-   Monedas inválidas

------------------------------------------------------------------------

📄 Licencia

Este proyecto es de uso libre bajo la licencia MIT.

------------------------------------------------------------------------

✨ Autor

Desarrollado por [Galicia Olvera Adrian (Giltlocket)]
📧 Contacto: (opcional)
💼 LinkedIn: (opcional)
🌐 GitHub: (opcional)
