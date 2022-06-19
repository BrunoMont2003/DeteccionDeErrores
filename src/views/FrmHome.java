package views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class FrmHome extends JFrame {
    private static FrmHome instance;

    public static FrmHome getInstance() {
        if (instance == null) {
            instance = new FrmHome();
        }
        return instance;
    }
    public void Close(){
        this.dispose();
    }

    private JRadioButton rbVerificacionParidad;
    private JRadioButton rbVerificacionRedundanciaLongitudinal;
    private JRadioButton rbSumaDeVerificacion;
    private JRadioButton rbVerificacionRedundanciaCiclica;
    private JPanel mainPanel;
    private JTextArea txaMensaje;
    private JButton btnEnviar;


    public FrmHome() {
        txaMensaje.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setContentPane(mainPanel);
        setTitle("TELEINFORMÁTICA");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int algoritmo = 0;
                String mensaje = txaMensaje.getText();
                if (rbVerificacionParidad.isSelected()) {
                    algoritmo = 1;
                } else if (rbVerificacionRedundanciaLongitudinal.isSelected()) {
                    algoritmo = 2;
                } else if (rbSumaDeVerificacion.isSelected()) {
                    algoritmo = 3;
                } else if (rbVerificacionRedundanciaCiclica.isSelected()) {
                    algoritmo = 4;
                }
                if (algoritmo == 0) {
                    JOptionPane.showMessageDialog(null, "Ningún algoritmo seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (mensaje.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Ningún mensaje escrito", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else if (mensaje.length() > 1000){
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una cadena menor a 1000 caracteres", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
                else if (algoritmo != 0 && mensaje.length() != 0) {
                    FrmModify frmModify = new FrmModify(mensaje, algoritmo);
                    frmModify.setVisible(true);
                    Close();
                }
            }
        });
    }
}
