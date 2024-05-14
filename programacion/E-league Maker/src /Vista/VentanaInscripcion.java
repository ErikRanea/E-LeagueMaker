package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInscripcion extends JFrame{
    private JPanel pPrincipal;
    private JPanel pCabecera;
    private JPanel pAcciones;
    private JPanel pBotones;
    private JPanel pDatos;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel pvisualizar;

    public VentanaInscripcion(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //initComponets();
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
    }
    public void initComponets() {
        // Obtener el tamaño de la pantalla
        System.out.println("Redimensionando la ventana");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        pCabecera.setPreferredSize(new Dimension((int) (screenWidth * 1), (int) (screenHeight * 15)));

        // Agregar icono a p1 usando un JLabel

        // Panel 2: 15% alto, 85% ancho

        pAcciones.setPreferredSize(new Dimension((int) (screenWidth * 1), (int) (screenHeight * 0.05)));

        pvisualizar.setPreferredSize(new Dimension((int) (screenWidth * 1), (int) (screenHeight * 0.8)));

        // Panel 3: 85% alto, 15% ancho
    }

}
