
import java.util.ArrayList;
/**
 * Problema 5 - Venta de entradas al teatro
Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:
Se desea gestionar la venta de entradas para un espectáculo en un teatro. 
El patio de butacas del teatro se divide en varias zonas, cada una identificada 
por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:
NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200                     25$             17.5$
PalcoB          40                      70$             40$
Central         400                     20$             14$
Lateral         100                     15.5$           10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que desea 
y presentar al vendedor el documento que justifique que tiene algún tipo de descuento 
(estudiante, abonado o pensionista). El vendedor sacará la entrada del tipo apropiado y 
de la zona indicada, en el momento de la compra se asignará a la entrada un identificador 
(un número entero) que permitirá la identificación de la entrada en todas las operaciones 
que posteriormente se desee realizar con ella.
Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.
Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:
Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.
 *Problema 5:
 * @author Mateo Alvarez
 * version1.0
 */
class TeatroControlador {
    private ArrayList<Zona> zonas;
    private ArrayList<Entrada> entradas;
    private int contadorId;
    public TeatroControlador() {
        this.zonas = new ArrayList<Zona>();
        this.entradas = new ArrayList<Entrada>();
        this.contadorId = 1;
    }
    public void agregarZona(Zona z) {
        zonas.add(z);
    }
    public Zona buscarZona(String nombre) {
        for (Zona z : zonas) {
            if (z.getNombre().equalsIgnoreCase(nombre)) {
                return z;
            }
        }
        return null;
    }
    public Entrada buscarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public String venderEntrada(String nombreZona, String nombreComprador, String tipo) {
        Zona zona = buscarZona(nombreZona);
        if (zona == null) {
            return "Error: no existe ninguna zona con el nombre '" + nombreZona + "'.";
        }
        if (zona.estaCompleta()) {
            return "Error: la zona '" + nombreZona + "' esta completa, no hay localidades disponibles.";
        }
        Entrada entrada;
        if (tipo.equalsIgnoreCase("normal")) {
            entrada = new EntradaNormal(contadorId, nombreComprador, zona);
        } else if (tipo.equalsIgnoreCase("reducida")) {
            entrada = new EntradaReducida(contadorId, nombreComprador, zona);
        } else if (tipo.equalsIgnoreCase("abonado")) {
            entrada = new EntradaAbonado(contadorId, nombreComprador, zona);
        } else {
            return "Error: tipo de entrada invalido. Use: normal, reducida o abonado";
        }
        entradas.add(entrada);
        zona.ocuparLocalidad();
        contadorId++;
        return "Entrada generada ID: " + entrada.getId() +
               " | Precio: $" + entrada.calcularPrecio();
    }
    public String consultarEntrada(int id) {
        Entrada entrada = buscarEntrada(id);

        if (entrada == null) {
            return "Error: no existe ninguna entrada con el ID " + id + ".";
        }
        return "Entrada{id=" + entrada.getId() +
               ", comprador=" + entrada.getNombreComprador() +
               ", zona=" + entrada.getZona().getNombre() +
               ", precio=$" + entrada.calcularPrecio() + "}";
    }
    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
    public ArrayList<Zona> getZonas() {
        return zonas;
    }
}
class Zona{
    private String nombre;
    private int totalLocalidades;
    private int localidadesOcupadas;
    private double precioNormal;
    private double precioAbonado;
    public Zona(String nombre, int totalLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.totalLocalidades = totalLocalidades;
        this.localidadesOcupadas = 0;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTotalLocalidades() {
        return totalLocalidades;
    }
    public void setTotalLocalidades(int totalLocalidades) {
        this.totalLocalidades = totalLocalidades;
    }
    public int getLocalidadesOcupadas() {
        return localidadesOcupadas;
    }
    public double getPrecioNormal() {
        return precioNormal;
    }
    public void setPrecioNormal(double precioNormal) {
        this.precioNormal = precioNormal;
    }
    public double getPrecioAbonado() {
        return precioAbonado;
    }
    public void setPrecioAbonado(double precioAbonado) {
        this.precioAbonado = precioAbonado;
    }
    public boolean estaCompleta() {
        return localidadesOcupadas >= totalLocalidades;
    }
    public void ocuparLocalidad() {
        localidadesOcupadas++;
    }
    @Override
    public String toString() {
        return "Zona{nombre=" + nombre +
               ", localidades=" + totalLocalidades +
               ", ocupadas=" + localidadesOcupadas +
               ", precioNormal=$" + precioNormal +
               ", precioAbonado=$" + precioAbonado + "}";
    }
}
 abstract class Entrada {

    private int id;
    private String nombreComprador;
    private Zona zona;
    public Entrada(int id, String nombreComprador, Zona zona) {
        this.id = id;
        this.nombreComprador = nombreComprador;
        this.zona = zona;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreComprador() {
        return nombreComprador;
    }
    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }
    public Zona getZona() {
        return zona;
    }
    public void setZona(Zona zona) {
        this.zona = zona;
    }
    public abstract double calcularPrecio();
    @Override
    public abstract String toString();
}

class EntradaNormal extends Entrada{
      public EntradaNormal(int id, String nombreComprador, Zona zona) {
        super(id, nombreComprador, zona);
    }
    public double calcularPrecio() {
        return getZona().getPrecioNormal();
    }
      @Override
    public String toString() {
        return "EntradaNormal{id=" + getId() +
               ", comprador=" + getNombreComprador() +
               ", zona=" + getZona().getNombre() +
               ", precio=$" + calcularPrecio() + "}";
    }
}
class EntradaReducida extends Entrada{
    private static final double DESCUENTO = 0.15;

    public EntradaReducida(int id, String nombreComprador, Zona zona) {
        super(id, nombreComprador, zona);
    }
    public double calcularPrecio() {
        return getZona().getPrecioNormal() * (1 - DESCUENTO);
    }
    public String toString() {
        return "EntradaReducida{id=" + getId() +
               ", comprador=" + getNombreComprador() +
               ", zona=" + getZona().getNombre() +
               ", precio=$" + calcularPrecio() + "}";
    }
}
class EntradaAbonado  extends Entrada{
      public EntradaAbonado(int id, String nombreComprador, Zona zona) {
        super(id, nombreComprador, zona);
    }
    public double calcularPrecio() {
        return getZona().getPrecioAbonado();
    }
    public String toString() {
        return "EntradaAbonado{id=" + getId() +
               ", comprador=" + getNombreComprador() +
               ", zona=" + getZona().getNombre() +
               ", precio=$" + calcularPrecio() + "}";
    }
}
public class Problema_5_EjecutorTeatro {
    public static void main(String[] args) {
        TeatroControlador controlador = new TeatroControlador();
        controlador.agregarZona(new Zona("Principal", 200, 25.0,  17.5));
        controlador.agregarZona(new Zona("PalcoB",    40,  70.0,  40.0));
        controlador.agregarZona(new Zona("Central",   400, 20.0,  14.0));
        controlador.agregarZona(new Zona("Lateral",   100, 15.5,  10.0));

        System.out.println(controlador.venderEntrada("Principal", "Ana Lopez",    "normal"));
        System.out.println(controlador.venderEntrada("PalcoB",    "Mateo Alvarez", "abonado"));
        System.out.println(controlador.venderEntrada("Central",   "Maria Garcia", "reducida"));
        System.out.println(controlador.venderEntrada("Lateral",   "Mario Torres",  "normal"));
        System.out.println(controlador.venderEntrada("Lateral",   "Gabriela Ruiz",   "abonado"));

        System.out.println(controlador.venderEntrada("VIP", "Pedro Ramirez", "normal"));

        System.out.println(controlador.consultarEntrada(1));
        System.out.println(controlador.consultarEntrada(3));

        System.out.println(controlador.consultarEntrada(99));
    }
}
/**
 * run:
Entrada generada ID: 1 | Precio: $25.0
Entrada generada ID: 2 | Precio: $40.0
Entrada generada ID: 3 | Precio: $17.0
Entrada generada ID: 4 | Precio: $15.5
Entrada generada ID: 5 | Precio: $10.0
Error: no existe ninguna zona con el nombre 'VIP'.
Entrada{id=1, comprador=Ana Lopez, zona=Principal, precio=$25.0}
Entrada{id=3, comprador=Maria Garcia, zona=Central, precio=$17.0}
Error: no existe ninguna entrada con el ID 99.
BUILD SUCCESSFUL (total time: 0 seconds)

 */