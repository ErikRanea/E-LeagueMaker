/**
 * Esta clase heredada de excepciones la he creado para que pueda gestionar el error del login de manera personalizada
 * y aislada.
 * @author Erik
 * @sincer 18/05/2024
 */
package Excepciones;

public class ExcepcionErrorUsuario extends Exception{

    private String mensaje;
    public ExcepcionErrorUsuario(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public ExcepcionErrorUsuario(){}

    public String getMensaje(){return mensaje;}
}
