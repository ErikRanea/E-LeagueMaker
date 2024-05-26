package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaUsuario extends JFrame{
    private JPanel pPrincipal;
    private JPanel pCabecera;
    private JPanel pUltima;
    private JCheckBox ultimaJornadaCheckBox;
    private JComboBox combobClasifiU;
    private JTextArea taUsuario;
    private JButton bAceptar;
    private JButton bExportar;
    private JButton bSalir;
    private JPanel pOperaciones;
    private JPanel pBotones;

    public VentanaUsuario(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponets();
        setResizable(false);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
        iniciarComponentes();


    }

    public void addBAceptar(ActionListener listener){
        bAceptar.addActionListener(listener);
    }

    public void addBSalirAl(ActionListener al)
    {
        bSalir.addActionListener(al);
    }
    public void addBExportar(ActionListener al){bExportar.addActionListener(al);}

    public void addCombobCompetiAAl(ActionListener al)
    {
        combobClasifiU.addActionListener(al);
    }
    public JComboBox<String> getCombobClasifiU() {
        return combobClasifiU;
    }

    public JTextArea getTaUsuario() {
        return taUsuario;
    }

    public void setTaUsuario(JTextArea tfJugadores) {
        this.taUsuario = tfJugadores;
    }

    public void muestra(String m)
    {
        JOptionPane.showMessageDialog(null,m);
    }
    public void initComponets(){
        //Quitar los bordes de botones
        quitarBordesBoton(bAceptar);
        quitarBordesBoton(bSalir);


        Font fuente = new Font("Serif", Font.PLAIN, 18);
        taUsuario.setFont(fuente);


    }
    private void quitarBordesBoton(JButton pBotones) {
        pBotones.setBorderPainted(false);
        pBotones.setContentAreaFilled(true);
        pBotones.setFocusPainted(false);
        pBotones.setOpaque(true);
    }


    /**
     * Esto cambia el icono a la ventana de usuario
     */
    public void iniciarComponentes()
    {
        ponerIconoPrograma();
    }

    public void ponerIconoPrograma()
    {
        ImageIcon icono = new ImageIcon("./src/Img/Logo_mas_cerca.jpg");
        super.setIconImage(icono.getImage());
    }




}
