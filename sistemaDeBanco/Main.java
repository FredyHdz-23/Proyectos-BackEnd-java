package sistemaDeBanco;

import java.util.Scanner;

public class Main {

    public static void menu(String nombre){
        String menu = String.format("""
                *** Bienvenido a su banco %s ***
                Selecciona una opción:
                1. Crear Cuenta
                2. Buscar cuenta
                3. Depositar
                4. Retirar
                5. Transferir
                6. Imprimir historial
                7. Mostrar cuentas
                8. Eliminar cuenta
                9. Salir
                : """, nombre);
        System.out.print(menu);
    }

    private static long leerNumeroCuenta(Scanner consola){
        System.out.print("Número de cuenta (6 dígitos): ");
        long numero = consola.nextLong();

        if(numero < 100000 || numero > 999999){
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 6 dígitos.");
        }

        return numero;
    }

    private static TipoCuenta leerTipoCuenta(Scanner consola){
        System.out.print("Tipo de cuenta (AHORROS o CORRIENTE): ");
        String tipo = consola.next().toUpperCase();

        try{
            return TipoCuenta.valueOf(tipo);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Tipo de cuenta inválido.");
        }
    }

    private static double leerMonto(Scanner consola){
        System.out.print("Monto: ");
        double monto = consola.nextDouble();

        if(monto <= 0){
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }

        return monto;
    }

    private static void crearCuenta(Scanner consola, Banco banco){

        long numero = leerNumeroCuenta(consola);
        TipoCuenta tipo = leerTipoCuenta(consola);

        System.out.print("Saldo inicial: ");
        double saldo = consola.nextDouble();

        banco.agregarCuenta(numero, tipo, saldo);
        System.out.println("Cuenta creada correctamente.");
    }

    private static void buscarCuenta(Scanner consola, Banco banco){

        long numero = leerNumeroCuenta(consola);
        Cuenta cuenta = banco.buscarCuenta(numero);

        System.out.println(cuenta);
    }

    private static void depositar(Scanner consola, Banco banco){

        long numero = leerNumeroCuenta(consola);
        double monto = leerMonto(consola);

        Cuenta cuenta = banco.buscarCuenta(numero);
        System.out.println(cuenta.depositar( monto));
    }

    private static void retirar(Scanner consola, Banco banco){

        long numero = leerNumeroCuenta(consola);
        double monto = leerMonto(consola);

        Cuenta cuenta = banco.buscarCuenta(numero);
        System.out.println(cuenta.retirar(monto));
    }

    private static void transferir(Scanner consola, Banco banco){

        System.out.println("Cuenta origen:");
        long origen = leerNumeroCuenta(consola);

        System.out.println("Cuenta destino:");
        long destino = leerNumeroCuenta(consola);

        double monto = leerMonto(consola);

        Cuenta cuenta = banco.buscarCuenta(origen);
        System.out.println(cuenta.transferir(origen, destino, monto));
    }

    private static void imprimirHistorial(Scanner consola, Banco banco){

        long numero = leerNumeroCuenta(consola);

        Cuenta cuenta = banco.buscarCuenta(numero);
        System.out.println(cuenta.mostrarMovimientos());
    }

    private static void eliminarCuenta(Scanner consola, Banco banco){

        long numero = leerNumeroCuenta(consola);
        banco.eliminarCuenta(numero);
    }

    public static void main(String[] args){

        Scanner consola = new Scanner(System.in);

        System.out.print("Ingresa un nombre para tu banco: ");
        String nombre = consola.nextLine();

        Banco.inicializar(nombre);
        Banco banco = Banco.getInstance();

        while(true){

            menu(nombre);
            int opcion = consola.nextInt();

            try{

                switch(opcion){
                    case 1 -> crearCuenta(consola, banco);
                    case 2 -> buscarCuenta(consola, banco);
                    case 3 -> depositar(consola, banco);
                    case 4 -> retirar(consola, banco);
                    case 5 -> transferir(consola, banco);
                    case 6 -> imprimirHistorial(consola, banco);
                    case 7 -> banco.mostrarCuentas();
                    case 8 -> eliminarCuenta(consola, banco);
                    case 9 -> {
                        System.out.println("Gracias por usar el banco.");
                        return;
                    }

                    default -> System.out.println("Opción inválida.");
                }

            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

