/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PanelDatos extends JPanel {

    private ArrayList<TEdit> a;

    public PanelDatos(int variable, int datos) {
        //super(parent,true);
        //super.setSize(400, 200);
        //super.setLocationRelativeTo(parent);
        super.setLayout(new GridLayout(1, variable + 1));//CAMBIAR LA ORIENTACION DE SCRROL VERTICAL HORIZONTAL
        a = new ArrayList<>();

        for (int i = 0; i < variable + 1; i++) {
            JPanel temp = new JPanel(new GridLayout(datos + 1, 1));
            if (i != variable) {
                JLabel lbl = new JLabel("Variable X" + (i + 1));
                temp.add(lbl);
            } else {
                JLabel lbl = new JLabel("Variable Y dependiente");
                temp.add(lbl);
            }
            for (int j = 0; j < datos; j++) {
                TEdit te = new TEdit("Muestra " + (j + 1));
                a.add(te);
                temp.add(te);
            }
            super.add(temp);
        }

    }

    public ArrayList<Double> datos() {
        ArrayList<Double> regreso = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            try {
                regreso.add(a.get(i).getValue());
            } catch (DoubleException ex) {
                JOptionPane.showMessageDialog(
                        PanelDatos.this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return regreso;
    }
}
