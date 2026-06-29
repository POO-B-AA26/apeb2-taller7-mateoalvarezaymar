
import java.util.ArrayList;


/**
 *Problema 6 - Sistema Un Banco
El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen 
a cada una de las cuentas consisten en el número de cuenta, el nombre del cliente 
y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. 
El método constructor debe aceptar como parámetros el número de cuenta y el nombre. 
Debe proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el balance actual.
El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. 
Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero 
una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad 
que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir 
cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta 
de ahorros debe proporcionar un método que sea invocado para calcular el interés. Además, el banco 
está pensando en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores 
de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
 * @author Mateo Alvarez
 * version 1.0
 */
class BancoControlador {
    private Banco banco;
    public BancoControlador(Banco banco) {
        this.banco = banco;
    }
    public Banco getBanco() {
        return banco;
    }
    public void agregarCuenta(Cuenta c) {
        banco.agregarCuenta(c);
    }
    public void depositar(Cuenta c, double cantidad) {
        c.depositar(cantidad);
    }
    public void retirar(Cuenta c, double cantidad) {
        c.retirar(cantidad);
    }
    public void calcularInteres(CuentaAhorros c) {
        c.calcularInteres();
    }
    public void calcularInteres(CuentaPlatino c) {
        c.calcularInteres();
    }
    public String obtenerEstadoCuentas() {
        return banco.toString();
    }
}
 class Banco {
    private String nombre;
    private ArrayList<Cuenta> cuentas;
    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<Cuenta>();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }
    public void agregarCuenta(Cuenta c) {
        cuentas.add(c);
    }
    @Override
    public String toString() {
        String resultado = "======= " + nombre + " =======\n";
        for (Cuenta c : cuentas) {
            resultado += c.toString() + "\n";
        }
        return resultado;
    }
}
 class Cuenta {
    private String numeroCuenta;
    private String nombreCliente;
    protected double balance;
    public Cuenta(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public double getBalance() {
        return balance;
    }
    public void depositar(double cantidad) {
        balance += cantidad;
    }
    public void retirar(double cantidad) {
        balance -= cantidad;
    }
    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta +
               " | Cliente: " + nombreCliente +
               " | Balance: $" + balance;
    }
}
 class CuentaAhorros extends Cuenta {
    private double tasaInteres;
    public CuentaAhorros(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = 0.05;
    }
    public double getTasaInteres() {
        return tasaInteres;
    }
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    @Override
    public void retirar(double cantidad) {
        if (cantidad > balance) {
            System.out.println("Retiro rechazado para " + getNombreCliente() +
                               ": cuenta de ahorros no permite sobregiro.");
        } else {
            balance -= cantidad;
        }
    }
    public void calcularInteres() {
        balance += balance * tasaInteres;
    }
    @Override
    public String toString() {
        return "[Ahorros]  " + super.toString() +
               " | Tasa: " + (tasaInteres * 100) + "%";
    }
}
 class CuentaCheques extends Cuenta {
    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    @Override
    public void retirar(double cantidad) {
        balance -= cantidad;
    }
    @Override
    public String toString() {
        return "[Cheques]  " + super.toString();
    }
}
class CuentaPlatino extends Cuenta {
    private double tasaInteres;
    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = 0.10;
    }
    public double getTasaInteres() {
        return tasaInteres;
    }
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    @Override
    public void retirar(double cantidad) {
        balance -= cantidad;
    }
    public void calcularInteres() {
        balance += balance * tasaInteres;
    }
    @Override
    public String toString() {
        return "[Platino]  " + super.toString() +
               " | Tasa: " + (tasaInteres * 100) + "%";
    }
}
public class Problema_6_EjecutorBanco {
        public static void main(String[] args) {

        Banco banco = new Banco("UN BANCO");
        BancoControlador controlador = new BancoControlador(banco);

        CuentaCheques c1 = new CuentaCheques("001", "Ana Lopez");
        CuentaAhorros c2 = new CuentaAhorros("002", "Mateo Alvarez");
        CuentaPlatino c3 = new CuentaPlatino("003", "Maria Garcia");
        CuentaCheques c4 = new CuentaCheques("004", "Luis Torres");
        CuentaAhorros c5 = new CuentaAhorros("005", "Sofia Ruiz");

        controlador.agregarCuenta(c1);
        controlador.agregarCuenta(c2);
        controlador.agregarCuenta(c3);
        controlador.agregarCuenta(c4);
        controlador.agregarCuenta(c5);

        c1.depositar(500.0);  c1.retirar(700.0);
        c2.depositar(1000.0); c2.retirar(1500.0);
        c3.depositar(2000.0); c3.retirar(2500.0);
        c4.depositar(800.0);  c4.retirar(300.0);
        c5.depositar(3000.0);

        c2.calcularInteres();
        c3.calcularInteres();
        c5.calcularInteres();

        System.out.println(controlador.obtenerEstadoCuentas());
    }
}
/**
 *Retiro rechazado para Mateo Alvarez: cuenta de ahorros no permite sobregiro.
======= UN BANCO =======
[Cheques]  Cuenta: 001 | Cliente: Ana Lopez | Balance: $-200.0
[Ahorros]  Cuenta: 002 | Cliente: Mateo Alvarez | Balance: $1050.0 | Tasa: 5.0%
[Platino]  Cuenta: 003 | Cliente: Maria Garcia | Balance: $-550.0 | Tasa: 10.0%
[Cheques]  Cuenta: 004 | Cliente: Luis Torres | Balance: $500.0
[Ahorros]  Cuenta: 005 | Cliente: Sofia Ruiz | Balance: $3150.0 | Tasa: 5.0%
 */