/**
 * Clase encarga de controlar la ventana de seleccion de modo del administrador.
 * @author Erik
 * @sincer 20/05/2024
 */
package Controlador.ControladoresVista;

import Vista.VentanaInscripcion;
import Vista.VentanaLogin;
import Vista.VentanaSeleccionAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVentanaSeleccion {

    private ControladorVista cv;

    private VentanaSeleccionAdmin vSeleccionAdmin;
    private VentanaInscripcion vInscripcion;

    public ControladorVentanaSeleccion(ControladorVista cv)
    {
        this.cv = cv;
    }


    public void crearMostrar()
    {
        vSeleccionAdmin = new VentanaSeleccionAdmin();

        vSeleccionAdmin.addBCompetiAL(new BModoCompeti());
        vSeleccionAdmin.addBInscripcionAL(new BModoInscripcion());
        vSeleccionAdmin.setVisible(true);
    }

    /**
     * Interfaz encargada de abrir la ventana de competición
     */
    public class BModoCompeti implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            vSeleccionAdmin.dispose();
            cv.mostrarVentanaCompeti();
        }
    }

    /**
     * Interfaz encargada de abrir la ventana del modo Inscripcion
     */
    /* todo Poner la ventana de inscripcion*/
    public class BModoInscripcion implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            vSeleccionAdmin.dispose();
            cv.mostrarVentanaInscripcion();

        }
    }
}
