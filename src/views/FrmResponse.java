package views;

import algorithms.SumaDeVerificacion;
import algorithms.VerificacionDeParidad;
import algorithms.VerificacionDeRedundanciaCiclica;
import algorithms.VerificacionRedundanciaLongitudinal;

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
                VerificacionDeParidad m = new VerificacionDeParidad();
                String c1=m.sended(mensajeOriginal);
                String c2=m.messageToSend(mensajeModificado);
                mensajeRecibidoExitosamente=m.receivedMessage(mensajeOriginal,mensajeModificado);

                break;
            case 2:
                VerificacionRedundanciaLongitudinal ml = new VerificacionRedundanciaLongitudinal();
                String l1=ml.sended(mensajeOriginal);
                String l2=ml.messageToSend(mensajeModificado);
                mensajeRecibidoExitosamente=ml.receivedMessage(mensajeOriginal,mensajeModificado);
                break;
            case 3:
                //suma de verificacion
                SumaDeVerificacion metodo = new SumaDeVerificacion();
                metodo.setBits(mensajeOriginal);
                String checksum = metodo.checksum(mensajeOriginal);
                String mensajeEnviado = metodo.messageToSend(mensajeModificado, checksum);
                mensajeRecibidoExitosamente = metodo.receivedMessage(mensajeEnviado);
                break;
            case 4:
                //redundancia c??clica
                VerificacionDeRedundanciaCiclica crc = new VerificacionDeRedundanciaCiclica();
                int residuo1 = crc.residuoEnviado(mensajeOriginal);
                int residuo2 = crc.residuoRecibido(mensajeModificado);
                mensajeRecibidoExitosamente = crc.receivedMessage(residuo1, residuo2);
        }
        if (mensajeRecibidoExitosamente) {
            lblTitulo.setText("??Mensaje recibido con ??xito!");
            lblTitulo.setForeground(new java.awt.Color(121, 255, 121));
            txaMensaje.setText(mensajeModificado);

        } else {
            lblTitulo.setText("??El mensaje fue adulterado!");
            lblTitulo.setForeground(new java.awt.Color(255, 86, 68));
            txaMensaje.setText("El mensaje pudo haber sido modificado y no lleg?? a su destino correctamente");
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
