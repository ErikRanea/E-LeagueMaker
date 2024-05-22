package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCarga extends JDialog {


    private JProgressBar progressBar;

    public VentanaCarga(Frame parent) {
        super(parent, "Barra de carga", true);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(false);

        ponerIconoPrograma();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(progressBar, BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(parent);
    }

    public void iniciarBarra(int milisegundos) {
        Runnable runnable = () -> {
            int delay = milisegundos / 100;
            for (int i = 0; i <= 100; i++) {
                progressBar.setValue(i);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            setVisible(false);
            dispose();
        };

        Thread thread = new Thread(runnable);
        thread.start();
        setVisible(true);
    }

    public void ponerIconoPrograma()
    {
        ImageIcon icono = new ImageIcon("./src/Img/Logo_mas_cerca.jpg");
        super.setIconImage(icono.getImage());
    }

}
