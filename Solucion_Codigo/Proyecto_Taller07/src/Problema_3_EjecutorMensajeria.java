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
class Contacto{
    public String numeroMovil;
    public String nombre;

    public Contacto(String numeroMovil, String nombre) {
        this.numeroMovil = numeroMovil;
        this.nombre = nombre;
    }
    public Contacto(String numeroMovil) {
        this.numeroMovil = numeroMovil;
        this.nombre = "Mateo";
    }
    @Override
    public String toString() {
        return "Contacto{" + "numeroMovil=" + numeroMovil + ", nombre=" + nombre + '}';
    }
}
class Mensaje{
    public Contacto remitente;
    public Contacto destinatario;

    public Mensaje(Contacto remitente, Contacto destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
    public String enviarMensaje(){
        return "Enviando mensaje desde " + remitente.numeroMovil + " hacia " + destinatario.numeroMovil; 
    }
    public String visualizarMensaje(){
        return this.toString();
    }
    @Override
    public String toString() {
        return "Mensaje{" + "remitente=" + remitente + ", destinatario=" + destinatario + '}';
    }
}   
class Sms extends Mensaje{
    public String texto;
    public Sms(String texto, Contacto remitente, Contacto destinatario) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Sms{" + "texto=" + texto + '}' + super.toString();
    }
}
class Mms extends Mensaje{
    public String nombreFicheroImagen;
    public Mms(String nombreFicheroImagen, Contacto remitente, Contacto destinatario) {
        super(remitente, destinatario);
        this.nombreFicheroImagen = nombreFicheroImagen;
    }
    @Override
    public String toString() {
        return "Mms{" + "nombreFicheroImagen=" + nombreFicheroImagen + '}' + super.toString();
    } 
}    
public class Problema_3_EjecutorMensajeria {
    public static void main(String[] args) {
        Contacto c1 = new Contacto("0978742341", "Mateo");
        Contacto c2 = new Contacto("0945760021", "Juan");
        
        Sms mensajeTexto = new Sms("Hola buenas tardes ya se entrego su pedido" , c1, c2);
        System.out.println(mensajeTexto.enviarMensaje());
        System.out.println(mensajeTexto.visualizarMensaje());
        System.out.println();
        
        Mms mensajeImagen = new Mms("Taller_05_Corregido.png", c1,c2);
        System.out.println(mensajeImagen.enviarMensaje());
        System.out.println(mensajeImagen.visualizarMensaje());
    }   
}
/**
 * run:
 * Enviando mensaje desde 0978742341 hacia 0945760021 Sms{texto=Hola buenas tardes ya se entrego su pedido}Mensaje{remitente=Contacto{numeroMovil=0978742341, nombre=Mateo}, destinatario=Contacto{numeroMovil=0945760021, nombre=Juan}}
 * Enviando mensaje desde 0978742341 hacia 0945760021 Mms{nombreFicheroImagen=Taller_05_Corregido.png}Mensaje{remitente=Contacto{numeroMovil=0978742341, nombre=Mateo}, destinatario=Contacto{numeroMovil=0945760021, nombre=Juan}}
   BUILD SUCCESSFUL (total time: 0 seconds)
 */