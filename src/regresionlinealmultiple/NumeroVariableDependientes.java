/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class NumeroVariableDependientes extends JDialog {

    private final Integer[] COMBOBOX = {2, 3};

    private JComboBox combo;
    private JLabel lblNom;

    private JPanel pnlSuperior;

    private DialogoObtener dlg;

    private final Integer[] COMBO = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private JComboBox combo2;
    private JLabel lblPide;
    private JPanel pnlCombo;

    private JButton btn;

    public NumeroVariableDependientes(JFrame parent) {
        super(parent, true);
        super.setTitle("Variables X");
        super.setSize(350, 150);
        super.setLocationRelativeTo(parent);
        super.setLayout(new GridLayout(3, 1));

        pnlSuperior = new JPanel(new FlowLayout());
        lblNom = new JLabel("Elige el numero de Variables Independientes");
        combo = new JComboBox(COMBOBOX);
        combo2 = new JComboBox(COMBO);
        lblPide = new JLabel("Dame el numero de datos aleatorios de muestra");
        pnlCombo = new JPanel(new FlowLayout());
        pnlCombo.add(lblPide);
        pnlCombo.add(combo2);

        btn = new JButton("Empezar");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlg = new DialogoObtener(parent, combo.getSelectedIndex() + 2, combo2.getSelectedIndex() + 3);
                dlg.setListener(new CancelarActionListener() {
                    @Override
                    public void cancelar() {
                        dlg.setVisible(false);
                        NumeroVariableDependientes.this.setVisible(true);
                    }
                });
                NumeroVariableDependientes.this.setVisible(false);
                dlg.setVisible(true);
            }
        });

        pnlSuperior.add(lblNom);
        pnlSuperior.add(combo);

        super.add(pnlSuperior);
        super.add(pnlCombo);
        super.add(btn);
    }
}
