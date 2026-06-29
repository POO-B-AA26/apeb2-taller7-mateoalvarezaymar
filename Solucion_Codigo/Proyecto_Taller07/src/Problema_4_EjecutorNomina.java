
import java.util.ArrayList;


/**
 *Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
 *Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI.
 *Además, existen diferentes tipos de trabajadores:
  Fijos Mensuales: que cobran una cantidad fija al mes.
  Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
  Por Horas: cobran un precio por cada una de las horas que han realizado 
 durante el mes. El precio es fijo para las primeras 40 horas y es otro para 
 * las horas realizadas a partir de la 40 hora mensual.
  Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene
 obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno). 
 * El programa  debe permitir dar de alta a trabajadores, así como fijar horas 
 * o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 * Problema 4:
 * @author Mateo Alvarez
 * version 1.0
 */
class NominaControlador {
    private ArrayList<Trabajador> trabajadores;

    public NominaControlador() {
        this.trabajadores = new ArrayList<Trabajador>();
    }

    public void agregarTrabajador(Trabajador t) {
        trabajadores.add(t);
    }

    public void asignarJefe(Trabajador t, Jefe j) {
        t.setJefeAsignado(j);
    }

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public String obtenerNominas() {
        String resultado = "";
        for (Trabajador t : trabajadores) {
            resultado += t.toString() + "\n";
        }
        return resultado;
    }
}
class Trabajador {
    private String nombreApellido;
    private String direccion;
    private String dni;
    private Jefe jefeAsignado;
    public Trabajador(String nombreApellido, String direccion, String dni) {
        this.nombreApellido = nombreApellido;
        this.direccion = direccion;
        this.dni = dni;
        this.jefeAsignado = null;
    }
    public String getNombreApellido() {
        return nombreApellido;
    }
    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public Jefe getJefeAsignado() {
        return jefeAsignado;
    }
    public void setJefeAsignado(Jefe jefeAsignado) {
        this.jefeAsignado = jefeAsignado;
    }
    @Override
    public String toString() {
        return "Trabajador{nombreApellido=" + nombreApellido +
               ", direccion=" + direccion +
               ", dni=" + dni +
               ", jefeAsignado=" + jefeAsignado + "}";
    }
}
class PorHoras extends Trabajador {
    private double horasTrabajadas;
    private double precioPorHora;
    private double precioHoraExtra;
    public PorHoras(String nombreApellido, String direccion, String dni,
                    double horasTrabajadas, double precioPorHora, double precioHoraExtra) {
        super(nombreApellido, direccion, dni);
        this.horasTrabajadas = horasTrabajadas;
        this.precioPorHora = precioPorHora;
        this.precioHoraExtra = precioHoraExtra;
    }
    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    public double getPrecioPorHora() {
        return precioPorHora;
    }
    public void setPrecioPorHora(double precioPorHora) {
        this.precioPorHora = precioPorHora;
    }
    public double getPrecioHoraExtra() {
        return precioHoraExtra;
    }
    public void setPrecioHoraExtra(double precioHoraExtra) {
        this.precioHoraExtra = precioHoraExtra;
    }
    public double calcularNomina() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * precioPorHora;
        } else {
            return (40 * precioPorHora) + ((horasTrabajadas - 40) * precioHoraExtra);
        }
    }

    public String toString() {
        return "PorHoras{horasTrabajadas=" + horasTrabajadas +
               ", precioPorHora=" + precioPorHora +
               ", precioHoraExtra=" + precioHoraExtra +
               ", Sueldo Total=$" + calcularNomina() + "} " + super.toString();
    }
}
 class Jefe extends Trabajador {

    private double sueldoFijo;

    public Jefe(String nombreApellido, String direccion, String dni, double sueldoFijo) {
        super(nombreApellido, direccion, dni);
        this.sueldoFijo = sueldoFijo;
        setJefeAsignado(null);
    }
    public double getSueldoFijo() {
        return sueldoFijo;
    }
    public void setSueldoFijo(double sueldoFijo) {
        this.sueldoFijo = sueldoFijo;
    }
    public double calcularNomina() {
        return sueldoFijo;
    }
    @Override
    public String toString() {
        return "Jefe{sueldoFijo=" + sueldoFijo + "}" + super.toString();
    }
}
class FijoMensual extends Trabajador {
    private double salarioMensual;
    public FijoMensual(String nombreApellido, String direccion, String dni, double salarioMensual) {
        super(nombreApellido, direccion, dni);
        this.salarioMensual = salarioMensual;
    }
    public double getSalarioMensual() {
        return salarioMensual;
    }
    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }
    public double calcularNomina() {
        return salarioMensual;
    }
    public String toString() {
        return "FijoMensual{salarioMensual=" + salarioMensual +
               ", Sueldo Total=$" + calcularNomina() + "} " + super.toString();
    }
}
class Comisionista extends Trabajador {
    private double porcentajeComision;
    private double ventasRealizadas;
    public Comisionista(String nombreApellido, String direccion, String dni,
                        double porcentajeComision, double ventasRealizadas) {
        super(nombreApellido, direccion, dni);
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = ventasRealizadas;
    }
    public double getPorcentajeComision() {
        return porcentajeComision;
    }
    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }
    public double getVentasRealizadas() {
        return ventasRealizadas;
    }
    public void setVentasRealizadas(double ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }
    public double calcularNomina() {
        return ventasRealizadas * (porcentajeComision / 100);
    }
    @Override
    public String toString() {
        return "Comisionistas{porcentajeComision=" + porcentajeComision +
               ", ventasRealizadas=" + ventasRealizadas +
               ", Sueldo Total=$" + calcularNomina() + "} " + super.toString();
    }
}
public class Problema_4_EjecutorNomina {
    public static void main(String[] args) {
        NominaControlador controlador = new NominaControlador();

        Jefe jefe1 = new Jefe("Ing. Mario Alberto", "Av. De los Paltas", "11098237423", 2500.0);

        Comisionista t1 = new Comisionista("Mateo Alvarez", "Loja Centro", "11059384394", 10.0, 5000.0);
        t1.setJefeAsignado(jefe1);

        FijoMensual t2 = new FijoMensual("Laura Sanchez", "Av. Universidad", "11034567891", 800.0);
        t2.setJefeAsignado(jefe1);

        PorHoras t3 = new PorHoras("Pedro Ramirez", "Calle Sucre", "11076543210", 50.0, 5.0, 8.0);
        t3.setJefeAsignado(jefe1);

        controlador.agregarTrabajador(jefe1);
        controlador.agregarTrabajador(t1);
        controlador.agregarTrabajador(t2);
        controlador.agregarTrabajador(t3);

        System.out.println(controlador.obtenerNominas());
    }
}
/**
 * run:
Jefe{sueldoFijo=2500.0}Trabajador{nombreApellido=Ing. Mario Alberto, direccion=Av. De los Paltas, dni=11098237423, jefeAsignado=null}
Comisionistas{porcentajeComision=10.0, ventasRealizadas=5000.0, Sueldo Total=$500.0} Trabajador{nombreApellido=Mateo Alvarez, direccion=Loja Centro, dni=11059384394, jefeAsignado=Jefe{sueldoFijo=2500.0}Trabajador{nombreApellido=Ing. Mario Alberto, direccion=Av. De los Paltas, dni=11098237423, jefeAsignado=null}}
FijoMensual{salarioMensual=800.0, Sueldo Total=$800.0} Trabajador{nombreApellido=Laura Sanchez, direccion=Av. Universidad, dni=11034567891, jefeAsignado=Jefe{sueldoFijo=2500.0}Trabajador{nombreApellido=Ing. Mario Alberto, direccion=Av. De los Paltas, dni=11098237423, jefeAsignado=null}}
PorHoras{horasTrabajadas=50.0, precioPorHora=5.0, precioHoraExtra=8.0, Sueldo Total=$280.0} Trabajador{nombreApellido=Pedro Ramirez, direccion=Calle Sucre, dni=11076543210, jefeAsignado=Jefe{sueldoFijo=2500.0}Trabajador{nombreApellido=Ing. Mario Alberto, direccion=Av. De los Paltas, dni=11098237423, jefeAsignado=null}}

BUILD SUCCESSFUL (total time: 0 seconds)

 */