package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCarga extends JDialog {

    private JProgressBar progressBar;

    public VentanaCarga(Frame parent) {
        super(parent, "Barra de carga", true);
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Indeterminate for unknown duration

        ponerIconoPrograma();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(progressBar, BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(parent);
    }

    public void iniciarBarra() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    public void detenerBarra() {
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose();
        });
    }

    public void ponerIconoPrograma() {
        ImageIcon icono = new ImageIcon("./src/Img/Logo_mas_cerca.jpg");
        super.setIconImage(icono.getImage());
    }
}
