
import java.util.ArrayList;

/**
 *Problema 3 - Sistema de envío de mensajes a móviles
Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de
* mensajes que se pueden enviar entre móviles, mensajes de texto (SMS) y 
* mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto 
* contienen un mensaje en caracteres que se desea enviar de un móvil a otro. 
* Por otro lado, los mensajes que contienen imágenes almacenan información 
* sobre la imagen a enviar, la cual se representará por el nombre del fichero
* que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá 
* asociado un remitente de dicho mensaje y un destinatario. Ambos estarán definidos 
* obligatoriamente por un número de móvil, y opcionalmente se podrá guardar 
* información sobre su nombre. Además, los métodos enviarMensaje y 
* visualizarMensaje deben estar definidos.
 * Problema 3:
 * @author Mateo Alvarez
 * version 1.0
 */
 class MensajeControlador {
    private ArrayList<Mensaje> mensajes;
    public MensajeControlador() {
        this.mensajes = new ArrayList<Mensaje>();
    }
    public void enviar(Mensaje m) {
        mensajes.add(m);
        m.enviarMensaje();
    }
    public void visualizar(Mensaje m) {
        m.visualizarMensaje();
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public String obtenerTodos() {
        String resultado = "";
        for (Mensaje m : mensajes) {
            resultado += m.toString() + "\n";
        }
        return resultado;
    }
}
 abstract class Mensaje {
    private Movil remitente;
    private Movil destinatario;
    public Mensaje(Movil remitente, Movil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
    public Movil getRemitente() {
        return remitente;
    }
    public void setRemitente(Movil remitente) {
        this.remitente = remitente;
    }

    public Movil getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Movil destinatario) {
        this.destinatario = destinatario;
    }
    public abstract void enviarMensaje();
    public abstract void visualizarMensaje();
    public abstract String toString();
}
class MMS extends Mensaje {
    private String nombreFichero;
    public MMS(Movil remitente, Movil destinatario, String nombreFichero) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }
    public String getNombreFichero() {
        return nombreFichero;
    }
    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }
    @Override
    public void enviarMensaje() {
        System.out.println("[MMS enviado] De: " + getRemitente().toString() +
                           " Para: " + getDestinatario().toString());
    }
    @Override
    public void visualizarMensaje() {
        System.out.println("[MMS] Imagen: " + nombreFichero);
    }

    @Override
    public String toString() {
        return "MMS | De: " + getRemitente().toString() +
               " | Para: " + getDestinatario().toString() +
               " | Fichero: " + nombreFichero;
    }
}
 class Movil {
    private String numero;
    private String nombre;
    public Movil(String numero) {
        this.numero = numero;
        this.nombre = "";
    }
    public Movil(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        if (nombre.equals("")) {
            return numero;
        }
        return nombre + " (" + numero + ")";
    }
}
class SMS extends Mensaje {
    private String texto;
    public SMS(Movil remitente, Movil destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public void enviarMensaje() {
        System.out.println("[SMS enviado] De: " + getRemitente().toString() +
                           " Para: " + getDestinatario().toString());
    }
    public void visualizarMensaje() {
        System.out.println("[SMS] Mensaje: " + texto);
    }
    @Override
    public String toString() {
        return "SMS | De: " + getRemitente().toString() +
               " | Para: " + getDestinatario().toString() +
               " | Texto: " + texto;
    }
}
public class Problema_3_Mensajeria {

    public static void main(String[] args) {
        Movil m1 = new Movil("0991234567", "Ana");
        Movil m2 = new Movil("0987654321", "Carlos");
        Movil m3 = new Movil("0976543210");
        Movil m4 = new Movil("0965432109", "Maria");

        MensajeControlador controlador = new MensajeControlador();

        SMS sms1 = new SMS(m1, m2, "Hola Carlos, como estas?");
        SMS sms2 = new SMS(m2, m4, "Maria, nos vemos a las 5.");
        MMS mms1 = new MMS(m1, m4, "foto_reunion.jpg");
        MMS mms2 = new MMS(m3, m2, "captura_pantalla.png");

        controlador.enviar(sms1);
        controlador.enviar(sms2);
        controlador.enviar(mms1);
        controlador.enviar(mms2);

        System.out.println(controlador.obtenerTodos());
    }
}
/**
 * run:
[SMS enviado] De: Ana (0991234567) Para: Carlos (0987654321)
[SMS enviado] De: Carlos (0987654321) Para: Maria (0965432109)
[MMS enviado] De: Ana (0991234567) Para: Maria (0965432109)
[MMS enviado] De: 0976543210 Para: Carlos (0987654321)
SMS | De: Ana (0991234567) | Para: Carlos (0987654321) | Texto: Hola Carlos, como estas?
SMS | De: Carlos (0987654321) | Para: Maria (0965432109) | Texto: Maria, nos vemos a las 5.
MMS | De: Ana (0991234567) | Para: Maria (0965432109) | Fichero: foto_reunion.jpg
MMS | De: 0976543210 | Para: Carlos (0987654321) | Fichero: captura_pantalla.png
BUILD SUCCESSFUL (total time: 0 seconds)
 */
