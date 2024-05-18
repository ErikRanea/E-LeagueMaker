/**
 * Clase creada para almenacenar un JFrame encargado de el inicio de sesión de los usuarios.
 * @author Erik
 * @since 18/05/2024
 */
package Vista;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class VentanaLogin extends JFrame {
    private JPanel panelPrincipal;
    private JPanel pTop;
    private JPanel pBody;
    private JPanel pFooter;
    private JPanel pDatos;
    private JTextField tfUsuario;
    private JPasswordField tfPassword;
    private JButton bLogin;
    private JButton bSalir;
    private JLabel labelImagen;
    private JLabel lError;


    public VentanaLogin()
    {
        setContentPane(panelPrincipal);
        setSize(900,400);
        setLocationRelativeTo(null);
        iniciarComponentes();
        setResizable(false);

    }



    public void iniciarComponentes()
    {
        meterImagen();
    }

    public void meterImagen()
    {
        try {
            URL iLogOut = new URL("https://github.com/ErikRanea/E-LeagueMaker/blob/develop/programacion/" +
                    "E-League_Maker_Esqueleto/src/Img/iLogin.jpg?raw=true");

            System.out.println("\n" + "URL de la imagen: " + iLogOut);

            BufferedImage imagenOriginal = ImageIO.read(iLogOut);

            BufferedImage bufferedImage = Scalr.resize(imagenOriginal, 700,10);

            ImageIcon iconoEscalado = new ImageIcon(bufferedImage);

            labelImagen.setIcon(iconoEscalado);
            //labelImagen.setSize(new Dimension(600, 500));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JTextField getTfUsuario() {
        return tfUsuario;
    }

    public void setTfUsuario(JTextField tfUsuario) {
        this.tfUsuario = tfUsuario;
    }

    public JPasswordField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(JPasswordField tfPassword) {
        this.tfPassword = tfPassword;
    }


    public void addBLogInAL(ActionListener listener){bLogin.addActionListener(listener);}
    public void addBSalirAL(ActionListener listener){bSalir.addActionListener(listener);}



    //Metodos propios

    public void mensajeErrorUsuario(String mensaje)
    {
        lError.setText(mensaje);
        lError.setVisible(true);
    }
    public void quitarMensajeError()
    {
        lError.setVisible(false);
    }

}
