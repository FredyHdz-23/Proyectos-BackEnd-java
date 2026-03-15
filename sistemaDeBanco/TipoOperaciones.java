package sistemaDeBanco;


//clase enum, que sirve para definir un conjunto fijo de constantes relacionadas con nombre,
// mejorando la seguridad de tipos, la legibilidad y el mantenimiento del código

public enum TipoOperaciones {
    DEPOSITAR ("depositar"),
    RETIRAR ("retirar"),
    TRANSFERENCIA_SALIDA ("envié transferencia"),
    TRANSFERENCIA_ENTRADA ("recibí tranferencia");
    private String value;


    //Constructor
    TipoOperaciones(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}

