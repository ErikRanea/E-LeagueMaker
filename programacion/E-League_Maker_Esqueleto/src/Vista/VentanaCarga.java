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
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                int delay = milisegundos / 100;
                for (int i = 0; i <= 100; i++) {
                    publish(i);
                    Thread.sleep(delay);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (Integer value : chunks) {
                    progressBar.setValue(value);
                }
            }

            @Override
            protected void done() {
                setVisible(false);
                dispose();
            }
        };

        worker.execute();
        setVisible(true);
    }

    public void ponerIconoPrograma() {
        ImageIcon icono = new ImageIcon("./src/Img/Logo_mas_cerca.jpg");
        super.setIconImage(icono.getImage());
    }

    public void iniciarCarga() {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(0);
            setVisible(true);
        });
    }

    public void detenerCarga() {
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose();
        });
    }
}
