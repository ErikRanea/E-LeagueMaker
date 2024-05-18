package Vista;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class VentanaRegistro extends JDialog {
    private JPanel contentPane;
    private JButton bAceptar;
    private JTextField tfUsuario;
    private JPasswordField pfContra;
    private JLabel lIcono;
    private JButton button1;

    /**
     * Constructor del diálogo de registro
     *
     * @author Oskar
     * @version 2.0
     * @since 16/05/2024
     */
    public VentanaRegistro() throws IOException {
        setContentPane(contentPane);
        setSize(500, 600);
        setModal(true);
        getRootPane().setDefaultButton(bAceptar);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    /**
     * Getters y setters de los atributos de de registro
     *
     * @author Oskar
     * @version 1.0
     * @since 10/05/2024
     */


    public JTextField getTfUsuario() {
        return tfUsuario;
    }

    public void setTfUsuario(JTextField tfUsuario) {
        this.tfUsuario = tfUsuario;
    }

    public JPasswordField getPfContra() {
        return pfContra;
    }

    public void setPfContra(JPasswordField pfContra) {
        this.pfContra = pfContra;
    }


    public void bAceptarAL(ActionListener al) {
        bAceptar.addActionListener(al);
    }

    public void tfUsuarioFL(FocusListener fl) {
        tfUsuario.addFocusListener(fl);
    }


    public void iniciarComponentes() throws IOException {
        addIconoBotoLogOut();
    }


    public void addIconoBotoLogOut() throws IOException {
        URL iLogOut = new URL("https://github.com/ErikRanea/E-LeagueMaker/blob/main/programacion/" +
                "E-League_Maker_Esqueleto/src/Img/100_circulo.png?raw=true");

        System.out.println("\n" + "URL de la imagen: " + iLogOut);

        BufferedImage imagenOriginal = ImageIO.read(iLogOut);

        BufferedImage bufferedImage = Scalr.resize(imagenOriginal, 40);

        ImageIcon iconoEscalado = new ImageIcon(bufferedImage);

        lIcono.setIcon(iconoEscalado);
        lIcono.setSize(new Dimension(50, 50));

    }
}