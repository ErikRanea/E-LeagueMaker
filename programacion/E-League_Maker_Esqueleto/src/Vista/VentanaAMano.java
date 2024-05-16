package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaAMano extends JFrame {

    private JPanel panelPrincipal;
    private JPanel pLogo;
    private JPanel pMenu;
    private JPanel pBotones;
    private JPanel pVisualizar;

    public VentanaAMano() {
        initComponets();
        setContentPane(panelPrincipal);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void initComponets() {
        crearPaneles();
        llenarPanelIcono();
    }

    public void crearPaneles() {
        panelPrincipal = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        pLogo = new JPanel();
        pLogo.setBackground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(pLogo, gbc);

        pMenu = new JPanel();
        pMenu.setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.85;
        gbc.weighty = 0.15;
        panelPrincipal.add(pMenu, gbc);

        pBotones = new JPanel();
        pBotones.setBackground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.15;
        gbc.weighty = 0.85;
        panelPrincipal.add(pBotones, gbc);

        pVisualizar = new JPanel();
        pVisualizar.setBackground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.85;
        gbc.weighty = 0.85;
        panelPrincipal.add(pVisualizar, gbc);
    }

    public void llenarPanelIcono() {
        JButton bIcono = new JButton();
        setImagenLogOutBoton(bIcono, "/Img/100_circulo.png");
        pLogo.setLayout(new BorderLayout()); // Establecer un BorderLayout para que el botón se expanda
        pLogo.add(bIcono, BorderLayout.CENTER);
    }

    public void setImagenLogOutBoton(JButton boton, String ruta) {
        ImageIcon imagen = new ImageIcon(getClass().getResource(ruta));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boton.setIcon(icono);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaAMano::new);
    }
}

