package Vista;



import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class VentanaTrasteo extends JFrame{
    private JPanel pPrincipal;
    private JButton bLogOut;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JButton button1;
    private JButton button2;
    private JButton button3;


    public VentanaTrasteo() throws IOException
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    public void iniciarComponentes() throws IOException
    {
        addIconoBotoLogOut();
    }


    public void addIconoBotoLogOut() throws IOException {
        URL iLogOut = new URL("");

        BufferedImage imagenOriginal = ImageIO.read(new File("/Img/logOutBlanco.png"));

        BufferedImage bufferedImage = Scalr.resize(imagenOriginal,55);

        ImageIcon iconoEscalado = new ImageIcon(bufferedImage);

        bLogOut.setIcon(iconoEscalado);
    }

}
