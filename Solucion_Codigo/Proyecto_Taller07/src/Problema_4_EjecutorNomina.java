
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
class Trabajador{
    public String nombreApellido;
    public String direccion;
    public String dni;
    public Jefe jefeAsignado;
    public Trabajador(String nombreApellido, String direccion, String dni, Jefe jefeAsignado) {
        this.nombreApellido = nombreApellido;
        this.direccion = direccion;
        this.dni = dni;
        this.jefeAsignado = jefeAsignado;
    }
    public Trabajador(String nombreApellido, String direccion, String dni) {
        this.nombreApellido = nombreApellido;
        this.direccion = direccion;
        this.dni = dni;
        this.jefeAsignado = null;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "nombreApellido=" + nombreApellido + ", direccion=" + direccion + ", dni=" + dni + ", jefeAsignado=" + jefeAsignado + '}';
    }
}
class Jefe extends Trabajador{
    public double sueldoFijo;
    public Jefe(double sueldoFijo, String nombreApellido, String direccion, String dni) {
        super(nombreApellido, direccion, dni);
        this.sueldoFijo = sueldoFijo;
    }
    @Override
    public String toString() {
        return "Jefe{" + "sueldoFijo=" + sueldoFijo + '}' + super.toString();
    }
}
class FijoMensual extends Trabajador{
    public double salarioMensual;

    public FijoMensual(double salarioMensual, String nombreApellido, String direccion, String dni, Jefe jefeAsignado) {
        super(nombreApellido, direccion, dni, jefeAsignado);
        this.salarioMensual = salarioMensual;
    }
    @Override
    public String toString() {
        return "FijoMensual{" + "salarioMensual=" + salarioMensual + '}' + super.toString();
    } 
}
class Comisionistas extends Trabajador{
    public double porcentajeComision;
    public double ventasRealizadas;

    public Comisionistas(double porcentajeComision, double ventasRealizadas, String nombreApellido, String direccion, String dni, Jefe jefeAsignado) {
        super(nombreApellido, direccion, dni, jefeAsignado);
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = 0.0;
    }
    public void fijarVentas(double ventas){
        this.ventasRealizadas = ventas;
    }
    public double calcularSueldo(){
        return (this.porcentajeComision / 100) * this.ventasRealizadas;
    }

    @Override
    public String toString() {
        return "Comisionistas{" + "porcentajeComision=" + porcentajeComision + ", ventasRealizadas=" + ventasRealizadas + ", Sueldo Total=$" + calcularSueldo() + "} " + super.toString();
    }
}
class PorHoras extends Trabajador{
    public double precioHoraNormal;
    public double precioHoraExtra;
    public int horasTrabajadas;
    public PorHoras(double precioHoraNormal, double precioHoraExtra, String nombreApellido, String direccion, String dni, Jefe jefeAsignado) {
        super(nombreApellido, direccion, dni, jefeAsignado);
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;
        this.horasTrabajadas = 0;
    }
    public void fijarHoras(int horas){
        this.horasTrabajadas = horas;
    }
    public double calcularSueldo(){
        if(horasTrabajadas <= 40){
            return horasTrabajadas * precioHoraNormal;
        }else{
            int horasExtras = horasTrabajadas - 40;
            return (40 * precioHoraNormal) + (horasExtras * precioHoraExtra);
        }
    }
    @Override
    public String toString() {
        return "PorHoras{" + "precioHoraNormal=" + precioHoraNormal + ", precioHoraExtra=" + precioHoraExtra + ", horasTrabajadas=" + horasTrabajadas + ", Sueldo Total=$" + calcularSueldo() + "} " + super.toString();
    }
}
public class Problema_4_EjecutorNomina {
    public static void main(String[] args) {
         Jefe jefeSeccion = new Jefe(2500.0, "Ing. Mario Alberto", "Av. De los Paltas", "11098237423");
         Comisionistas empComision = new Comisionistas(10.0, 0.0, "Mateo Alvarez", "Loja Centro", "11059384394", jefeSeccion);
         empComision.fijarVentas(5000.0);
         System.out.println(jefeSeccion);
         System.out.println(empComision);
    }
}
/**
 * run:
 * Jefe{sueldoFijo=2500.0}Trabajador{nombreApellido=Ing. Mario Alberto, direccion=Av. De los Paltas, dni=11098237423, jefeAsignado=null}
 * Comisionistas{porcentajeComision=10.0, ventasRealizadas=5000.0, Sueldo Total=$500.0} Trabajador{nombreApellido=Mateo Alvarez, direccion=Loja Centro, dni=11059384394, jefeAsignado=Jefe{sueldoFijo=2500.0}Trabajador{nombreApellido=Ing. Mario Alberto, direccion=Av. De los Paltas, dni=11098237423, jefeAsignado=null}}
 * BUILD SUCCESSFUL (total time: 0 seconds)
 */