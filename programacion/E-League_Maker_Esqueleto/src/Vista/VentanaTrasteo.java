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
    private JPanel pCabecera;
    private JPanel pIzqCabeVacio;
    private JPanel pLogOut;
    private JPanel pLogo;
    private JButton bVerResultado;
    private JButton bVerClasifi;
    private JButton bIntroResult;
    private JPanel pDatos;


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
        URL iLogOut = new URL("https://github.com/ErikRanea/E-LeagueMaker/blob/develop/programacion/" +
                "E-League_Maker_Esqueleto/src/Img/logOutBlanco.png?raw=true");

        System.out.println("\n"+"URL de la imagen: " + iLogOut);

        BufferedImage imagenOriginal = ImageIO.read(iLogOut);

        BufferedImage bufferedImage = Scalr.resize(imagenOriginal,25);

        ImageIcon iconoEscalado = new ImageIcon(bufferedImage);

        bLogOut.setIcon(iconoEscalado);
        bLogOut.setSize(new Dimension(50,50));
    }

}
