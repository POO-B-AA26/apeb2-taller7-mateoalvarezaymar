
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

class Pelicula{
    public String titulo, autor;
    public int anio;
    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }
    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + '}';
    }
}
class Soporte{
    public Pelicula pelicula;
    public int cantidad;
    public double precio, costoAlquiler;
    public Soporte(Pelicula pelicula, int cantidad, double precio) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public double calcularCostoAlquiler() {
        this.costoAlquiler = (this.cantidad * this.precio);
        return this.costoAlquiler;
    }
    @Override
    public String toString() {
        return "Soporte{" + "pelicula=" + pelicula + ", cantidad=" + cantidad + ", precio=" + precio + ", costoAlquiler=" + costoAlquiler + '}';
    }
}
class Dvd extends Soporte{
    public double porcentajeRecargo;
    public String Idiomas[];
    public Dvd(double porcentajeRecargo, String[] Idiomas, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.porcentajeRecargo = porcentajeRecargo;
        this.Idiomas = Idiomas;
    }
    public double calcularCostoAlquiler(){
        this.costoAlquiler = super.calcularCostoAlquiler() + (this.costoAlquiler * (this.porcentajeRecargo / 100)) ;
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Dvd{" + "porcentajeRecargo=" + porcentajeRecargo + ", Idiomas=" + Arrays.toString(Idiomas) + '}' + super.toString();
    }
    
}
class Vhs extends Soporte{
    public String Idioma;
    public Vhs(String Idioma, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.Idioma = Idioma;
    }
    @Override
    public String toString() {
        return "Vhs{" + "Idioma=" + Idioma + '}' + super.toString();
    }
    
}
public class Problema_2_EjecutorVideoClub {
    public static void main(String[] args) {
        String Idiomas[] = {"ESP" ,"ENG"};
        Pelicula peli = new Pelicula("El mundial", "Mateo", 2026);
        Dvd dvd1 = new Dvd(10, Idiomas, peli, 2, 10);
        dvd1.calcularCostoAlquiler();
        System.out.println(dvd1);
        Vhs vhs1 = new Vhs(Idiomas[0], peli, 2, 10);
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
