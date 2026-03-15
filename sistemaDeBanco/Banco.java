package sistemaDeBanco;

import java.util.*;

public class Banco {
    private static Banco instancia;
    private final String nombre;
    private Map<Long, Cuenta> cuentas;

    //Constructor
    private Banco(String nombre){
        this.nombre = nombre;
        this.cuentas = new HashMap<>();
    }


    public static void inicializar(String nombre) {
        if (instancia == null)  instancia = new Banco(nombre);
    }


    // getters
    public static Banco getInstance() {
        if (instancia == null)  throw new IllegalStateException("Banco no inicializado");
        return instancia;
    }
    public Map<Long, Cuenta> getCuentas(){
        return Map.copyOf(cuentas);
    }


//    Banco banco = Banco.getInstance("THE BILLIONAIRES");

    //metodo agregarCuenta
    public Cuenta agregarCuenta(long numeroCuenta, TipoCuenta tipoCuenta, double saldoInicial){
        if (saldoInicial < 0) throw new IllegalArgumentException("No se puede inicializar una cuenta con aldo negativo. ");
        if (cuentas.containsKey(numeroCuenta)) throw new IllegalArgumentException("Ya existe una cuenta con ese número. ");
        Cuenta n = new Cuenta(numeroCuenta, tipoCuenta, saldoInicial);
        cuentas.put(n.getNumeroCuenta(), n);

        return n;
    }

    //método buscarCuenta
    public Cuenta buscarCuenta(long numeroCuenta){
        if (!cuentas.containsKey(numeroCuenta)) throw new NoSuchElementException("Elemento no encontrado: " + numeroCuenta);

        return cuentas.get(numeroCuenta);
    }

    //método generarMovimiento
    public void generarMovimiento(long numeroCuenta, TipoOperaciones tipoOperaciones, double monto, double dineroAntes, double dineroDespues){
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        Movimiento movimiento = new Movimiento.Builder()
                .tipoOperacion(tipoOperaciones)
                .monto(monto)
                .dineroAntes(dineroAntes)
                .dineroDespues(dineroDespues)
                .build();
        cuenta.agregarMovimiento(movimiento);
    }

    //método generarMovimiento para transferir
    public void generarMovimiento(long origen, TipoOperaciones tipoOperaciones, double monto, long destino, double dineroAntes, double dineroDespues){
        Movimiento movimiento = new Movimiento.Builder()
                .tipoOperacion(tipoOperaciones)
                .monto(monto)
                .cuentaDestino(destino)
                .dineroAntes(dineroAntes)
                .dineroDespues(dineroDespues)
                .build();
        Cuenta cuentaOrigen = buscarCuenta(origen);
        cuentaOrigen.agregarMovimiento(movimiento);
    }


    //metodo eliminarCuenta
    public boolean eliminarCuenta(long numeroCuenta){
        if (!cuentas.containsKey(numeroCuenta)){
            throw new IllegalArgumentException("La cuenta que se quiere eliminar no existe");
        }
        cuentas.remove(numeroCuenta);
        System.out.println("Cuenta " + numeroCuenta + " ha sido eliminada correctamente. ");
        return true;
    }




    //metodo mostrar cuentas
    public void mostrarCuentas(){
        System.out.println(cuentas);

    }


}
