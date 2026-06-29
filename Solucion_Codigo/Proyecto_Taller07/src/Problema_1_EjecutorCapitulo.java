
/**
 *Problema 1 - Jerarquía de clases para el capítulo de libro
Dibuje un diagrama de clases que muestre la estructura de un capítulo de libro;
un capítulo está compuesto por varias secciones, cada una de las cuales comprende 
varios párrafos y figuras. Un párrafo incluye varias sentencias, cada una de las 
cuales contiene varias palabras.
 * Problema 1:
 * @author Mateo Alvarez
 * version1.0
 */

import java.util.ArrayList;
import java.util.List;
class CapituloControlador {
    private Capitulo capitulo;
    public CapituloControlador(Capitulo capitulo) {
        this.capitulo = capitulo;
    }
    public void agregarSeccion(Seccion s) {
        capitulo.agregarSeccion(s);
    }
    public void agregarComponente(Seccion s, ComponenteSeccion c) {
        s.agregarComponente(c);
    }
    public void agregarSentencia(Parrafo p, Sentencia s) {
        p.agregarSentencia(s);
    }
    public void agregarPalabra(Sentencia s, Palabra p) {
        s.agregarPalabra(p);
    }
    public String obtenerInfo() {
        return capitulo.toString();
    }
}
class Capitulo {
    private String numero;
    private String titulo;
    private ArrayList<Seccion> secciones;
    public Capitulo(String numero, String titulo) {
        this.numero = numero;
        this.titulo = titulo;
        this.secciones = new ArrayList<Seccion>();
    }
    public void agregarSeccion(Seccion s) {
        secciones.add(s);
    }
    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }
    public String getNumero() {
        return numero;
    }
    public String getTitulo() {
        return titulo;
    }
    @Override
    public String toString() {
        String resultado = "Capitulo " + numero + ": " + titulo + "\n";
        resultado += "================================\n";
        for (Seccion s : secciones) {
            resultado += s.toString();
        }
        return resultado;
    }
}
abstract class ComponenteSeccion {
    protected String id;
    public ComponenteSeccion(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    @Override
    public abstract String toString();
}
class Palabra {
    private String texto;
    public Palabra(String texto) {
        this.texto = texto;
    }
    public String getTexto() {
        return texto;
    }
    @Override
    public String toString() {
        return texto;
    }
}
class Sentencia {
  private String id;
    private ArrayList<Palabra> palabras;
    public Sentencia(String id) {
        this.id = id;
        this.palabras = new ArrayList<Palabra>();
    }
    public void agregarPalabra(Palabra p) {
        palabras.add(p);
    }
    public ArrayList<Palabra> getPalabras() {
        return palabras;
    }
  @Override
    public String toString() {
        String resultado = "      Sentencia " + id + ": ";
        for (Palabra p : palabras) {
            resultado += p.getTexto() + " ";
        }
        return resultado;
    }
}
class Parrafo extends ComponenteSeccion {
    private ArrayList<Sentencia> sentencias;
    public Parrafo(String id) {
        super(id);
        this.sentencias = new ArrayList<Sentencia>();
    }
    public void agregarSentencia(Sentencia s) {
        sentencias.add(s);
    }
    public ArrayList<Sentencia> getSentencias() {
        return sentencias;
    }
    @Override
    public String toString() {
        String resultado = "    Parrafo " + id + ":\n";
        for (Sentencia s : sentencias) {
            resultado += s.toString() + "\n";
        }
        return resultado;
    }
}
class Figura extends ComponenteSeccion {
    private String descripcion;
    public Figura(String id, String descripcion) {
        super(id);
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    @Override
    public String toString() {
        return "    Figura " + id + ": " + descripcion;
    }
}
class Seccion {
     private String id;
    private String titulo;
    private ArrayList<ComponenteSeccion> componentes;
    public Seccion(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.componentes = new ArrayList<ComponenteSeccion>();
    }
    public void agregarComponente(ComponenteSeccion c) {
        componentes.add(c);
    }
    public ArrayList<ComponenteSeccion> getComponentes() {
        return componentes;
    }
    public String getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
     @Override
    public String toString() {
        String resultado = "  Seccion " + id + " - " + titulo + ":\n";
        for (ComponenteSeccion c : componentes) {
            resultado += c.toString() + "\n";
        }
        return resultado;
    }
}
public class Problema_1_EjecutorCapitulo {
    public static void main(String[] args) {
        Palabra p1 = new Palabra("La");
        Palabra p2 = new Palabra("herencia");
        Palabra p3 = new Palabra("es");
        Palabra p4 = new Palabra("importante");
        Palabra p5 = new Palabra("en");
        Palabra p6 = new Palabra("Java");
        Palabra p7 = new Palabra("permite");
        Palabra p8 = new Palabra("reutilizar");
        Palabra p9 = new Palabra("codigo");

        Sentencia s1 = new Sentencia("S1");
        s1.agregarPalabra(p1); s1.agregarPalabra(p2); s1.agregarPalabra(p3);
        s1.agregarPalabra(p4); s1.agregarPalabra(p5); s1.agregarPalabra(p6);

        Sentencia s2 = new Sentencia("S2");
        s2.agregarPalabra(p1); s2.agregarPalabra(p2); s2.agregarPalabra(p7);
        s2.agregarPalabra(p8); s2.agregarPalabra(p9);

        Parrafo parrafo1 = new Parrafo("P1");
        parrafo1.agregarSentencia(s1);
        parrafo1.agregarSentencia(s2);

        Figura figura1 = new Figura("F1", "Diagrama de clases de herencia");

        Seccion seccion1 = new Seccion("1.1", "Conceptos de Herencia");
        seccion1.agregarComponente(parrafo1);
        seccion1.agregarComponente(figura1);

        Capitulo capitulo = new Capitulo("1", "Programacion Orientada a Objetos");
        CapituloControlador controlador = new CapituloControlador(capitulo);
        controlador.agregarSeccion(seccion1);

        System.out.println(controlador.obtenerInfo());
    }
}
/**
 * run:
    Capitulo 1: Programacion Orientada a Objetos
    ================================
    Seccion 1.1 - Conceptos de Herencia:
    Parrafo P1:
      Sentencia S1: La herencia es importante en Java 
      Sentencia S2: La herencia permite reutilizar codigo 
    Figura F1: Diagrama de clases de herencia
BUILD SUCCESSFUL (total time: 0 seconds)
 */