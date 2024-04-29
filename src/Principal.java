import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateAPI api = new ExchangeRateAPI();

        boolean salir = false;
        while (!salir) {
            System.out.println("***********************************");
            System.out.println("Bienvenido al Conversor de Monedas!");
            System.out.println("1. Dolares =>> Pesos Argentinos");
            System.out.println("2. Pesos Argentinos =>> Dolares");
            System.out.println("3. Dolares =>> Reales Brazileños");
            System.out.println("4. Reales Brazileños =>> Dolares");
            System.out.println("5. Dolares =>> Pesos Colombianos");
            System.out.println("6. Pesos Colombianos =>> Pesos Colombianos");
            System.out.println("7. Salir");
            System.out.print("""
                    Ingrese el numero de la opcion deseada: 
                    *************************************""");

            int opcionSeleccionada = scanner.nextInt();

            switch (opcionSeleccionada) {
                case 1:
                    convertCurrency(api, "USD", "ARS");
                    break;
                case 2:
                    convertCurrency(api, "ARS", "USD");
                    break;
                case 3:
                    convertCurrency(api, "USD", "BRL");
                    break;
                case 4:
                    convertCurrency(api, "BRL", "USD");
                    break;
                case 5:
                    convertCurrency(api, "USD", "COP");
                    break;
                case 6:
                    convertCurrency(api, "COP", "USD");
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, ingrese un numero del 1 al 7.");
            }
        }
        scanner.close();
    }

    private static void convertCurrency(ExchangeRateAPI api, String desdeMoneda, String haciaMoneda) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad que desea convertir: ");
        double cantidad = scanner.nextDouble();

        // Pedir la informacion al API para que haga la conversion
        double cantidadConvertida = api.convertCurrency(desdeMoneda, haciaMoneda, cantidad);

        System.out.println("La cantidad que seleccionaste: " + cantidad + " " + desdeMoneda + " Es igual a: " + cantidadConvertida + " " + haciaMoneda);
    }
}