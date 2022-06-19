package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModify extends JFrame {
    private static FrmModify instance;

    public static  FrmModify getInstance(String mensaje, int algoritmo) {
        if (instance == null){
            instance = new FrmModify(mensaje, algoritmo);
        }
        return instance;
    }

    private JTextArea txaMensajeModificado;
    private JButton btnModificar;
    private JPanel mainPanel;


    public void Close(){
        this.dispose();
    }


    public FrmModify(String mensaje, int algoritmo) {
        txaMensajeModificado.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setContentPane(mainPanel);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        txaMensajeModificado.setText(mensaje);
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensajeModificado =txaMensajeModificado.getText();
                FrmResponse frmResponse = new FrmResponse(mensaje, mensajeModificado, algoritmo);
                frmResponse.setVisible(true);
                Close();
            }
        });
    }

}
