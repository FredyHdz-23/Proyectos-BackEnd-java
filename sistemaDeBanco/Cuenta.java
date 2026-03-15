package sistemaDeBanco;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private static int contadorCuentas;
    private final long numeroCuenta;
    private final TipoCuenta tipoCuenta;
    private double saldo;
    private List<Movimiento> movimientos;


    // Constructor
    public Cuenta(long numeroCuenta, TipoCuenta tipoCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldoInicial;
        this.movimientos = new ArrayList<>();
        contadorCuentas ++;
    }


    //Getters y Setters
    public int getContadorCuentas(){
        return contadorCuentas;
    }
    public long getNumeroCuenta(){
        return this.numeroCuenta;
    }
    public TipoCuenta getTipoCuenta(){
        return this.tipoCuenta;
    }

    public double getSaldo(){
        return this.saldo;
    }
    private void setSaldo(double saldo){
        this.saldo = saldo;
    }
    public List<Movimiento> getMovimientos(){
        return List.copyOf(movimientos);
    }
    public void agregarMovimiento(Movimiento m){
        movimientos.add(m);
    }

    //toString
    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", tipoCuenta=" + tipoCuenta +
                ", saldo=" + saldo +
                '}';
    }

    // método depositar
    public String depositar(double monto){
        if (monto < 0) throw new IllegalArgumentException("El monto debe ser positivo ");

        double saldoAntes = this.getSaldo();
        this.setSaldo(this.getSaldo() + monto);
        Banco.getInstance().generarMovimiento(this.getNumeroCuenta(), TipoOperaciones.DEPOSITAR, monto, saldoAntes, this.getSaldo() );
        return "Depósito exitoso. Saldo actualizado " + this.getSaldo();

    }

    //método retirar
    public String retirar (double monto){
        if (monto > this.getSaldo()) throw new IllegalArgumentException("El retiro no puede superar el saldo de tu cuenta. Tienes: " + this.getSaldo());
        if (monto < 0) throw new IllegalArgumentException("El monto debe ser positivo ");

        double saldoAntes = this.getSaldo();
        this.setSaldo(this.getSaldo() - monto);
        Banco.getInstance().generarMovimiento(this.getNumeroCuenta() ,TipoOperaciones.RETIRAR, monto,  saldoAntes, this.getSaldo());
        return "Retiro exitoso. Saldo actualizado " + this.getSaldo();
    }

    //método transferir
    public String transferir(long origen, long destino,  double montoTranferir){

        Cuenta cuentaOrigen = Banco.getInstance().buscarCuenta(origen);
        Cuenta cuentaDestino = Banco.getInstance().buscarCuenta(destino);
        if (montoTranferir > cuentaOrigen.getSaldo()) throw new IllegalArgumentException("No puedes transferir más dinero del que tienes. Tienes: " + cuentaOrigen.getSaldo());
        if (montoTranferir < 0) throw new IllegalArgumentException("El monto debe ser positivo ");

        double saldoAntesOrigen = cuentaOrigen.getSaldo();
        double saldoAntesDestino = cuentaDestino.getSaldo();
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - montoTranferir);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + montoTranferir);
        Banco.getInstance().generarMovimiento(origen, TipoOperaciones.TRANSFERENCIA_SALIDA, montoTranferir, destino, saldoAntesOrigen, cuentaOrigen.getSaldo());
        Banco.getInstance().generarMovimiento(destino, TipoOperaciones.TRANSFERENCIA_ENTRADA, montoTranferir, origen, saldoAntesDestino, cuentaDestino.getSaldo());

        return "Transacción exitosa, la persona con esta cuenta " + destino + " ya recibió el dinero.";
    }



    //metodo historialMovimientos
    public List<Movimiento> mostrarMovimientos(){
        return this.getMovimientos();
    }





}
