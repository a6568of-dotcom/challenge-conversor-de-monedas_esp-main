import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);

        Conversor.exibirMenu();

        System.out.print("Ingrese una opción: ");
        int opcion = lectura.nextInt();


        String apiKey = "2fbe53b4b91bf7427a9bacd1";
        String direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            //System.out.println(response.body());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();



            Gson gson = new Gson();
            Moneda moneda = gson.fromJson(response.body(), Moneda.class);

            System.out.println("Escribe el valor que deseas convertir:");
            double valor = lectura.nextDouble();

//            System.out.println("Moneda base: " + moneda.base_code());
//            System.out.println("Tasas disponibles: " + moneda.conversion_rates());

            double ars = moneda.conversion_rates().get("ARS");
            double bob = moneda.conversion_rates().get("BOB");
            double brl = moneda.conversion_rates().get("BRL");
            double clp = moneda.conversion_rates().get("CLP");
            double cop = moneda.conversion_rates().get("COP");

            switch (opcion) {

                case 1:
                    System.out.println("Conversión de USD a ARS");
                    System.out.println("El valor convertido es: " + (valor * ars) + " ARS");
                    break;

                case 2:
                    System.out.println("Conversión de ARS a USD");
                    System.out.println("El valor convertido es: " + (valor / ars) + " USD");
                    break;

                case 3:
                    System.out.println("Conversión de USD a BRL");
                    System.out.println("El valor convertido es: " + (valor * brl) + " BRL");
                    break;

                case 4:
                    System.out.println("Conversión de BRL a USD");
                    System.out.println("El valor convertido es: " + (valor / brl) + " USD");
                    break;

                case 5:
                    System.out.println("Conversión de USD a COP");
                    System.out.println("El valor convertido es: " + (valor * cop) + " COP");
                    break;

                case 6:
                    System.out.println("Conversión de COP a USD");
                    System.out.println("El valor convertido es: " + (valor / cop) + " USD");
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

//            double valorEnArs = valor * ars;
//            System.out.println("El valor convertido a ARS es: " + valorEnArs);

//            System.out.println("ARS: " + ars);
//            System.out.println("BOB: " + bob);
//            System.out.println("BRL: " + brl);
//            System.out.println("CLP: " + clp);
//            System.out.println("COP: " + cop);


        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener los datos de la API.");
        }
    }
}