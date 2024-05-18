/**
 * Esta clase se encargar de crear un JFrame para el Administrador una vez cerrada la etapa de inscripcion
 * @author Erik
 * @since 15/05/2024
 */
package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import org.imgscalr.Scalr;


public class VentanaCompeticion extends JFrame{
    private JPanel pPrincipal;
    private JPanel pHead;
    private JPanel pMenu;
    private JPanel pBody;
    private JPanel pBotones;
    private JPanel pVisualizar;
    private JComboBox cbCompeticiones;
    private JComboBox cbJornadas;
    private JButton bLogOut;
    private JButton bInsertarResult;
    private JButton bVerClasificaciones;
    private JButton bVerTodosLosResultados;


    public VentanaCompeticion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        iniciarComponentes();



    }


     public void iniciarComponentes()
    {
        pBotones.setVisible(false);
    }


    /**
     * En este apartado creamos los listener para cada uno de los botones
     * @param listener
     */
    public void addBLogOutAL(ActionListener listener){bLogOut.addActionListener(listener);}
    public void addBInsertarResultAL(ActionListener listener){bInsertarResult.addActionListener(listener);}
    public void addBVerClasifiAL(ActionListener listener){bVerClasificaciones.addActionListener(listener);}
    public void addVerResultadosAL(ActionListener listener){bVerTodosLosResultados.addActionListener(listener);}


    /**
     * Las siguientes funciones interactuan directamente con acciones de la ventana
     */

    public void verPanelBotonesLateralIzq()
    {
        if(pBotones.isVisible())
        {
            pBotones.setVisible(false);
        }
        else
        {
            pBotones.setVisible(true);
        }
    }


    public JComboBox getCbCompeticiones() {
        return cbCompeticiones;
    }

    public void setCbCompeticiones(JComboBox cbCompeticiones) {
        this.cbCompeticiones = cbCompeticiones;
    }

    public JComboBox getCbJornadas() {
        return cbJornadas;
    }

    public void setCbJornadas(JComboBox cbJornadas) {
        this.cbJornadas = cbJornadas;
    }
}
