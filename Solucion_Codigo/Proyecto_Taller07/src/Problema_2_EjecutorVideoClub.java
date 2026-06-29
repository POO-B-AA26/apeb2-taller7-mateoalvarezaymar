
import java.util.Arrays;

/**
 * Problema 2 - Alquiler de peliculas
Un videoclub dispone de una serie de películas que pueden estar en DVD 
* (con capacidad en Gb.) o en VHS (una sola cinta por película y con cierto 
* tipo de cinta magnetica). De las películas interesa guardar el título, el 
* autor, el año de edición y el idioma (o los idiomas, en caso de DVD). 
* El precio de alquiler de las películas varía en función del tipo de película.
* Las DVD siempre son 10% mas caras que las de VHS.
 *Problema2:
 * @author Mateo Alvarez
 * version 1.0
 */
class Pelicula {
    private String titulo;
    private String autor;
    private int anio;
 
    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }
 
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
 
    @Override
    public String toString() {
        return "Pelicula{titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + "}";
    }
}
 
class Soporte {
    private Pelicula pelicula;
    private int cantidad;
    private double precio;
    protected double costoAlquiler;
 
    public Soporte(Pelicula pelicula, int cantidad, double precio) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.precio = precio;
    }
 
    public Pelicula getPelicula() { return pelicula; }
    public void setPelicula(Pelicula pelicula) { this.pelicula = pelicula; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public double getCostoAlquiler() { return costoAlquiler; }
 
    public double calcularCostoAlquiler() {
        this.costoAlquiler = this.cantidad * this.precio;
        return this.costoAlquiler;
    }
 
    @Override
    public String toString() {
        return "Soporte{pelicula=" + pelicula + ", cantidad=" + cantidad +
               ", precio=" + precio + ", costoAlquiler=" + costoAlquiler + "}";
    }
}
 
class Dvd extends Soporte {
    private double porcentajeRecargo;
    private String[] idiomas;
 
    public Dvd(double porcentajeRecargo, String[] idiomas, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.porcentajeRecargo = porcentajeRecargo;
        this.idiomas = idiomas;
    }
 
    public double getPorcentajeRecargo() { return porcentajeRecargo; }
    public void setPorcentajeRecargo(double porcentajeRecargo) { this.porcentajeRecargo = porcentajeRecargo; }
    public String[] getIdiomas() { return idiomas; }
    public void setIdiomas(String[] idiomas) { this.idiomas = idiomas; }
 
    @Override
    public double calcularCostoAlquiler() {
        this.costoAlquiler = super.calcularCostoAlquiler() + (this.costoAlquiler * (this.porcentajeRecargo / 100));
        return this.costoAlquiler;
    }
 
    @Override
    public String toString() {
        return "Dvd{porcentajeRecargo=" + porcentajeRecargo + ", Idiomas=" + Arrays.toString(idiomas) + "}" + super.toString();
    }
}
 
class Vhs extends Soporte {
    private String idioma;
 
    public Vhs(String idioma, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idioma = idioma;
    }
 
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
 
    @Override
    public String toString() {
        return "Vhs{Idioma=" + idioma + "}" + super.toString();
    }
}
 
public class Problema_2_EjecutorVideoClub {
    public static void main(String[] args) {
        String[] idiomas = {"ESP", "ENG"};
        Pelicula peli = new Pelicula("El mundial", "Mateo", 2026);
 
        Dvd dvd1 = new Dvd(10, idiomas, peli, 2, 10);
        dvd1.calcularCostoAlquiler();
        System.out.println(dvd1);
 
        Vhs vhs1 = new Vhs(idiomas[0], peli, 2, 10);
        vhs1.calcularCostoAlquiler();
        System.out.println(vhs1);
    }
}
/**
 * run:
Dvd{porcentajeRecargo=10.0, Idiomas=[ESP, ENG]}Soporte{pelicula=Pelicula{titulo=El mundial, autor=Mateo, anio=2026}, cantidad=2, precio=10.0, costoAlquiler=22.0}
Vhs{Idioma=ESP}Soporte{pelicula=Pelicula{titulo=El mundial, autor=Mateo, anio=2026}, cantidad=2, precio=10.0, costoAlquiler=20.0}
BUILD SUCCESSFUL (total time: 0 seconds)

 */
