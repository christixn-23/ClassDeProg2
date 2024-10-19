import java.util.InputMismatchException;
import java.util.Scanner;
class CalculadoraCientifica {
    private static final int MAX_HISTORIAL = 10;
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String[] historial = new String[MAX_HISTORIAL];
        int contadorHistorial = 0;
        boolean continuar = true;
        System.out.println("=== Calculadora Cientifica ===");
        while(continuar) {
            System.out.println("\nSelecciona una operacion: ");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicacion");
            System.out.println("4. Division");
            System.out.println("5. Potencia");
            System.out.println("6. Raiz Cuadrada");
            System.out.println("7. Ver Historial");
            System.out.println("8. Salir");
            System.out.print("Opción: ");
            int opcion = 0;
            try {
                opcion = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERR:Opcion invalida. Intente de nuevo.");
                entrada.next();
                continue;
            }
            switch (opcion) {
                case 1:
                    double[] sumandos = obtenerDosNumeros(entrada);
                    double suma = sumandos[0] + sumandos[1];
                    System.out.println("Resultado: " + suma);
                    agregarAlHistorial(historial, contadorHistorial++, sumandos[0] + " + " + sumandos[1] + " = " + suma);
                    break;
                case 2:
                    double[] restandos = obtenerDosNumeros(entrada);
                    double resta = restandos[0] - restandos[1];
                    System.out.println("Resultado: " + resta);
                    agregarAlHistorial(historial, contadorHistorial++, restandos[0] + " - " + restandos[1] + " = " + resta);
                    break;
                case 3:
                    double[] factores = obtenerDosNumeros(entrada);
                    double multiplicacion = factores[0] * factores[1];
                    System.out.println("Resultado: " + multiplicacion);
                    agregarAlHistorial(historial, contadorHistorial++, factores[0] + " * " + factores[1] + " = " + multiplicacion);
                    break;
                case 4:
                    double[] dividendos = obtenerDosNumeros(entrada);
                    double division = dividendos[0] / dividendos[1];
                    System.out.println("Resultado: " + division);
                    agregarAlHistorial(historial, contadorHistorial++, dividendos[0] + " / " + dividendos[1] + " = " + division);
                    break;
                case 5:
                    System.out.print("Ingresa la base: ");
                    double base = leerNumero(entrada);
                    System.out.print("Ingresa el exponente: ");
                    double exponente = leerNumero(entrada);
                    double potencia = Math.pow(base, exponente);
                    System.out.println("Resultado: " + potencia);
                    agregarAlHistorial(historial, contadorHistorial++, base + " ^ " + exponente + " = " + potencia);
                    break;
                case 6:
                    System.out.print("Ingresa el numero: ");
                    double numero = leerNumero(entrada);
                    if (numero < 0) {
                        System.out.println("ERR:El numero no puede ser negativo.");
                    } else {
                        double raiz = Math.sqrt(numero);
                        System.out.println("Resultado: " + raiz);
                        agregarAlHistorial(historial, contadorHistorial++, "√" + numero + " = " + raiz);
                    }
                    break;
                case 7:
                    System.out.println("Historial de Operaciones");
                    if (contadorHistorial == 0) {
                        System.out.println("Historial vacío.");
                    } else {
                        for (int i = 0; i < contadorHistorial; i++) {
                            System.out.println((i + 1) + ". "+ historial[i]);
                        }
                    }
                    break;
                case 8:
                    System.out.print("Desea salir? (S/N): ");
                    char respuesta = entrada.next().toUpperCase().charAt(0);
                    continuar = (respuesta == 'S') ? false : true;
                    break;
                default:
                    System.out.println("ERR:Opcion invalida. Intente de nuevo.");
            }
            if (contadorHistorial >= MAX_HISTORIAL) {
                System.out.println("Historial lleno. No se puede almacenar mas operaciones.");
                contadorHistorial = MAX_HISTORIAL;
            }
        }
        System.out.println("¡Hasta luego!");
        entrada.close();
    }
    private static double[] obtenerDosNumeros(Scanner entrada) {
        double num1 = 0, num2 = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Ingresa el primer numero: ");
                num1 = entrada.nextDouble();
                System.out.print("Ingresa el segundo numero: ");
                num2 = entrada.nextDouble();
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("ERR:Entrada invlida. Ingresa numeros validos.");
                entrada.next();
            }
        }
        return new double[]{num1, num2};
    }
    public static double leerNumero(Scanner entrada) {
        double numero = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                numero = entrada.nextDouble();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.print("ERR:Entrada invlida. Ingresa numeros validos: ");
                entrada.next();
            }
        }
        return numero;
    }
    private static void agregarAlHistorial(String[] historial, int indice, String operacion) {
        if (indice < historial.length) {
            historial[indice] = operacion;
        }
    }
}