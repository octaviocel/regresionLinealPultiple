/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author DELL
 */
public class DialogoObtener extends JDialog {

    private PanelDatos pnl;
    private JLabel lbl;

    private JButton btnAceptar;
    private JButton btnCancelar;

    private CancelarActionListener listener;

    private Formulas form;

    private DialogResultados dlgRes;

    public DialogoObtener(JFrame parent, int variable, int datos) {
        super(parent, true);
        super.setSize(650, 450);
        super.setLocationRelativeTo(parent);
        super.setLayout(new BorderLayout());

        pnl = new PanelDatos(variable, datos);

        lbl = new JLabel("Introduce los Datos ");
        lbl.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel temp = new JPanel(new FlowLayout());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Double> a = pnl.datos();
                try {
                    form = new Formulas(a, variable, datos);
                    DialogoObtener.this.setVisible(false);
                    dlgRes = new DialogResultados(parent, form);
                    dlgRes.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            DialogoObtener.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.cancelar();
            }
        });
        temp.add(btnAceptar);
        temp.add(btnCancelar);

        super.add(lbl, BorderLayout.NORTH);
        super.add(new JScrollPane(pnl), BorderLayout.CENTER);
        super.add(temp, BorderLayout.SOUTH);
    }

    public void setListener(CancelarActionListener listener) {
        this.listener = listener;
    }
}
