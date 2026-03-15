package sistemaDeBanco;
import java.time.*;

public class Movimiento {
    private static int contadorMovimientos;
    private final int idMovimiento;
    private final LocalDateTime fechaMovimiento;
    private final TipoOperaciones tipoOperacion;
    private final double monto;
    private final Long cuentaDestino;
    private final double dineroAntes;
    private final double dineroDespues;

    //Bloque de instancia

    // constructor
    public Movimiento(Builder builder){
        this.fechaMovimiento = LocalDateTime.now();
        this.tipoOperacion = builder.tipoOperacion;
        this.monto = builder.monto;
        contadorMovimientos ++;
        this.idMovimiento = contadorMovimientos;
        this.cuentaDestino = builder.cuentaDestino;
        this.dineroAntes = builder.dineroAntes;
        this.dineroDespues = builder.dineroDespues;

    }

    public static class Builder {
        private TipoOperaciones tipoOperacion;
        private  double monto;
        private long cuentaDestino;
        private double dineroAntes;
        private double dineroDespues;


        public Builder tipoOperacion(TipoOperaciones tipoOperacion){
            this.tipoOperacion = tipoOperacion;
            return this;
        }

        public Builder monto(double monto){
            this.monto = monto;
            return this;
        }

        public Builder cuentaDestino(long cuentaDestino){
            this.cuentaDestino = cuentaDestino;
            return this;
        }

        public Builder dineroAntes(double dineroAntes){
            this.dineroAntes = dineroAntes;
            return this;
        }

        public Builder dineroDespues(double dineroDespues){
            this.dineroDespues = dineroDespues;
            return this;
        }

        public Movimiento build(){
            return new Movimiento(this);
        }

    }

    @Override
    public String toString() {
        return "Fecha: " + fechaMovimiento +
                "Tipo: " + tipoOperacion +
                ", Monto: " + monto +
                ", destinatario: " + cuentaDestino +
                ", Saldo antes: " + dineroAntes +
                ", Saldo después: " + dineroDespues;
    }



    //getters
    public int getIdMovimiento(){
        return idMovimiento;
    }


}
