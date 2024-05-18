package Vista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaLogin extends JFrame {
    private JPanel panelPrincipal;
    private JPanel pTop;
    private JPanel pBody;
    private JPanel pFooter;
    private JPanel pDatos;
    private JTextField tfNombre;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton salirButton;
    private JLabel labelImagen;


    public VentanaLogin()
    {
        setContentPane(panelPrincipal);
        setSize(900,400);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }



    public void iniciarComponentes()
    {
        meterImagen();
    }

    public void meterImagen()
    {
        try {
            // Cargar la imagen utilizando ClassLoader
            BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream("src/Img/iLogin.jpeg"));
            if (originalImage == null) {
                throw new IOException("No se pudo cargar la imagen desde el recurso especificado.");
            }

            // Crear una imagen redimensionada
            Image resizedImage = originalImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH);

            // Establecer la imagen redimensionada como ícono de un JLabel ya existente
            labelImagen.setIcon(new ImageIcon(resizedImage));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
