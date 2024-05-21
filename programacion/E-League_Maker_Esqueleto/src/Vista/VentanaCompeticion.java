/**
 * Esta clase se encargar de crear un JFrame para el Administrador una vez cerrada la etapa de inscripcion
 * @author Erik
 * @since 15/05/2024
 */
package Vista;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private JButton bBuscar;
    private JButton bInsertar;
    private JButton bCalendario;
    private JButton generarCalendarioButton;


    public VentanaCompeticion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        setResizable(false); //Este parametro no permite redimensionar la ventana
        iniciarComponentes();



    }


     public void iniciarComponentes()
    {
        pBotones.setVisible(false);
        ponerIconoPrograma();
      //  meterImagenCalendario();
    }


    public void meterImagenCalendario()
    {
        try {

            BufferedImage imagenOriginal = ImageIO.read(new File("./src/Img/logoCalendario.png"));

            BufferedImage bufferedImage = Scalr.resize(imagenOriginal, 100,100);

            ImageIcon iconoEscalado = new ImageIcon(bufferedImage);

            bCalendario.setIcon(iconoEscalado);
            //labelImagen.setSize(new Dimension(600, 500));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Este metodo se encarga de ponerle la imagen de marca a la aplicación
     */
    public void ponerIconoPrograma()
    {
        ImageIcon icono = new ImageIcon("./src/Img/Logo_mas_cerca.jpg");
        super.setIconImage(icono.getImage());
    }
    /**
     * En este apartado creamos los listener para cada uno de los botones
     * @param listener
     */
    public void addBLogOutAL(ActionListener listener){bLogOut.addActionListener(listener);}
    public void addBInsertarResultAL(ActionListener listener){bInsertarResult.addActionListener(listener);}
    public void addBVerClasifiAL(ActionListener listener){bVerClasificaciones.addActionListener(listener);}
    public void addVerResultadosAL(ActionListener listener){bVerTodosLosResultados.addActionListener(listener);}
    public void addBInsertarAL(ActionListener listener){bInsertar.addActionListener(listener);}
    public void addBBuscarAL(ActionListener listener){bBuscar.addActionListener(listener);}
    public void addBGenerarCalendarioAL(ActionListener listener){bCalendario.addActionListener(listener);}


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

    public void mostrarMensaje(String mensaje){JOptionPane.showMessageDialog(this,mensaje);}

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

    public JPanel getpVisualizar() {
        return pVisualizar;
    }

    public void setpVisualizar(JPanel pVisualizar) {
        this.pVisualizar = pVisualizar;
    }
}
