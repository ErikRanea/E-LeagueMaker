/**
 * Esta clase se encargar de crear un JFrame para el Administrador una vez cerrada la etapa de inscripcion
 * @author Erik
 * @since 15/05/2024
 */
package Vista;

import javax.swing.*;

public class VentanaCompeticion extends JFrame{
    private JPanel pPrincipal;
    private JPanel pCabecera;
    private JPanel pAcciones;
    private JPanel pvisualizar;
    private JPanel pBotones;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel pDatos;


    public VentanaCompeticion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(pPrincipal);
        setLocationRelativeTo(null);
    }



}
