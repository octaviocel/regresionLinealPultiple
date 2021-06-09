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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.commons.math3.distribution.TDistribution;

/**
 *
 * @author DELL
 */
public class DialogPronostico extends JDialog {

    private final String[] COMBO = {"5%", "10%", "15%", "20%"};
    private TEdit edt1;
    private TEdit edt2;
    private TEdit edt3;
    private JLabel lblSignificacia;
    private JComboBox combo;

    private Formulas formulita;

    private JLabel Y;
    private JLabel confianza;
    private JLabel tStudent;
    private JLabel limiteI;
    private JLabel limiteS;

    private JButton btnAceptar;

    public DialogPronostico(JFrame parent, Formulas form) {
        super(parent, true);
        super.setSize(750, 400);
        super.setTitle("Pronostico");
        super.setLocationRelativeTo(parent);
        super.setLayout(new BorderLayout());
        this.formulita = form;
        crear();
        instalar();
        JPanel temp = new JPanel(new FlowLayout());
        btnAceptar = new JButton("Calcular");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    calcular();
                } catch (DoubleException ex) {
                    JOptionPane.showMessageDialog(
                            DialogPronostico.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        temp.add(btnAceptar);
        super.add(temp, BorderLayout.SOUTH);
    }

    private void instalar() {
        JPanel pnl = new JPanel(new GridLayout(3, 2));
        Y = new JLabel("Y: ");
        confianza = new JLabel("Porcentaje Confianza:     %");
        tStudent = new JLabel("Tstudent: ");
        limiteI = new JLabel("Limite Inferior: ");
        limiteS = new JLabel("Limite Superior: ");
        pnl.add(Y);
        pnl.add(confianza);
        pnl.add(limiteI);
        pnl.add(limiteS);
        pnl.add(tStudent);
        super.add(pnl, BorderLayout.CENTER);
    }

    private void crear() {
        JPanel pnl = new JPanel();
        JPanel pnlcombo = new JPanel(new BorderLayout());
        lblSignificacia = new JLabel("Significacia: ");
        combo = new JComboBox(COMBO);
        pnlcombo.add(lblSignificacia, BorderLayout.WEST);
        pnlcombo.add(combo, BorderLayout.EAST);
        if (formulita.getMatrizXNormal()[0].length == 3) {
            pnl.setLayout(new GridLayout(1, 3));
            edt1 = new TEdit("Variable X1");
            edt2 = new TEdit("Variable X2");
            pnl.add(edt1);
            pnl.add(edt2);
            pnl.add(pnlcombo);
        } else {
            pnl.setLayout(new GridLayout(1, 4));
            edt1 = new TEdit("Variable X1");
            edt2 = new TEdit("Variable X2");
            edt3 = new TEdit("Variable X3");
            pnl.add(edt1);
            pnl.add(edt2);
            pnl.add(edt3);
            pnl.add(pnlcombo);
        }
        super.add(pnl, BorderLayout.NORTH);
    }

    private void calcular() throws DoubleException {
        Double y = formulita.getMatrizBResultado()[0] + edt1.getValue() * formulita.getMatrizBResultado()[1] + edt2.getValue() * formulita.getMatrizBResultado()[2];
        Double signi = significacia();
        Double tstudent = ditribusion(signi);
        Double limteI = y - (tstudent * formulita.getErrorEstimacion());
        Double limteS = y + (tstudent * formulita.getErrorEstimacion());
        actualizar(y, tstudent, limteI, limteS, signi);
    }

    private void actualizar(Double y, Double student, Double i, Double s, Double signi) {
        Y.setText("Y:  " + y);
        confianza.setText("Porcentaje Confianza: " + (100 - (signi * 100)) + " %");
        tStudent.setText("Tstudent: " + student);
        limiteI.setText("Limite Inferior: " + i);
        limiteS.setText("Limite Superior: " + s);
    }

    private Double ditribusion(Double confianza) {
        TDistribution t = new TDistribution(formulita.getDA() - (formulita.getVar()+1));
        //Double confianza = doub.doubleValue() / 100;
        Double student = t.inverseCumulativeProbability(1 - ((1 - (1-confianza)) / 2));
        return student;
    }

    private Double significacia() {
        switch (combo.getSelectedIndex()) {
            case 0:
                return 0.05;
            case 1:
                return 0.10;
            case 2:
                return 0.15;
            case 3:
                return 0.20;
            default:
                return 0.05;
        }
    }
}
