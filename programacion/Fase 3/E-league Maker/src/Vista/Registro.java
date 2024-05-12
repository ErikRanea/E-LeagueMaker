package Vista;

import javax.swing.*;
import java.awt.event.*;

public class Registro extends JDialog {
    private JPanel contentPane;
    private JButton bAceptar;
    private JTextField tfUsuario;
    private JPasswordField pfContra;

    /**
     * Constructor del diálogo de registro
     * @author Oskar
     * @version 1.0 10/05/2024
     */
    public Registro() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bAceptar);
    }

    /**
     * Getters y setters de los atributos de de registro
     * @author Oskar
     * @version 1.0 10/05/2024
     */
    public JButton getbAceptar() {
        return bAceptar;
    }

    public void setbAceptar(JButton bAceptar) {
        this.bAceptar = bAceptar;
    }

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

    /**
     * Listeners de los atributos de registro
     * @author Oskar
     * @version 1.0 10/05/2024
     */
    public void bAceptarAL(ActionListener al)
    {
        bAceptar.addActionListener(al);
    }

    public void tfUsuarioFL(FocusListener fl)
    {
        tfUsuario.addFocusListener(fl);
    }
}
