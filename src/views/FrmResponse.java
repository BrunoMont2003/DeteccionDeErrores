package views;

import algorithms.SumaDeVerificacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmResponse extends JFrame {
    private static FrmResponse instance;
    private JTextArea txaMensaje;
    private JPanel mainPanel;
    private JLabel lblTitulo;
    private JButton btnNuevo;
    private JButton btnSalir;

    public static FrmResponse getInstance(String mensajeOriginal, String mensajeModificado, int algoritmo) {
        if (instance == null) {
            instance = new FrmResponse(mensajeOriginal, mensajeModificado, algoritmo);
        }
        return instance;
    }

    public void Close(){
        this.dispose();
    }

    public FrmResponse(String mensajeOriginal, String mensajeModificado, int algoritmo) {
        txaMensaje.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setContentPane(mainPanel);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        boolean mensajeRecibidoExitosamente = true;

        // logic
        switch (algoritmo) {
            case 1:
                //
                break;
            case 2:
                //redundancia longitudinal
                break;
            case 3:
                //suma de verificacion
                SumaDeVerificacion metodo = new SumaDeVerificacion();
                metodo.setnBits(mensajeOriginal);
                String checksum = metodo.checksum(mensajeOriginal);
                String mensajeEnviado = metodo.messageToSend(mensajeModificado, checksum);
                mensajeRecibidoExitosamente = metodo.receivedMessage(mensajeEnviado);
                break;
            case 4:
                //redundancia cíclica
        }
        if (mensajeRecibidoExitosamente) {
            lblTitulo.setText("¡Mensaje recibido con éxito!");
            lblTitulo.setForeground(new java.awt.Color(121, 255, 121));
            txaMensaje.setText(mensajeModificado);

        } else {
            lblTitulo.setText("¡El mensaje fue adulterado!");
            lblTitulo.setForeground(new java.awt.Color(255, 86, 68));
            txaMensaje.setText("El mensaje pudo haber sido modificado y no llegó a su destino correctamente");
        }
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Close();
                FrmHome f = new FrmHome();
                f.setVisible(true);
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Close();
            }
        });
    }
}
