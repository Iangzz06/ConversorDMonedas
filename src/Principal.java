import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();

        String[][] opciones = {
                {"USD", "ARS", "Dólar a Peso Argentino"},
                {"ARS", "USD", "Peso Argentino a Dólar"},
                {"USD", "BRL", "Dólar a Real Brasileño"},
                {"BRL", "USD", "Real Brasileño a Dólar"},
                {"USD", "COP", "Dólar a Peso Colombiano"},
                {"COP", "USD", "Peso Colombiano a Dólar"}
        };

        int opcion = 0;

        while (opcion != 8) {
            // Imprimir el menú dinámicamente
            System.out.println("--------------");
            System.out.println("Bienvenidos al conversor de monedas\n");
            System.out.println("Ingresa la conversión que deseas realizar:\n");
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ". " + opciones[i][2]);
            }
            System.out.println("7. Convertir otra moneda");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");

            // Validar entrada
            if (lectura.hasNextInt()) {
                opcion = lectura.nextInt();
                lectura.nextLine(); // Consumir el salto de línea

                if (opcion >= 1 && opcion <= opciones.length) {
                    // Realizar conversión predefinida
                    String monedaBase = opciones[opcion - 1][0];
                    String monedaTarget = opciones[opcion - 1][1];
                    ConvertirMoneda.convertir(monedaBase, monedaTarget, consulta, lectura);
                } else if (opcion == 7) {
                    // Convertir otra moneda
                    ConvertirMoneda.convertirOtraMoneda(consulta, lectura);
                } else if (opcion == 8) {
                    System.out.println("Saliendo...");
                } else {
                    System.out.println("Opción no válida. Por favor, selecciona un número entre 1 y 8.");
                }
            } else {
                // Manejar entrada inválida
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                lectura.nextLine(); // Limpiar el buffer
            }
        }

        lectura.close(); // Cerrar el scanner al final del programa
    }
}
