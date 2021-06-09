/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class TEdit extends JPanel {

    private JLabel lblcaption;
    private JTextField edit;

    public TEdit(String caption) {

        this.lblcaption = new JLabel(caption);
        this.edit = new JTextField(15);

        super.add(lblcaption);
        super.add(edit);
    }

    public void clear() {
        edit.setText("");
    }

    public Double getValue() throws DoubleException {
        try {
            if (edit.getText().isEmpty()) {
                throw new DoubleException("Campo Vacio");
            } else {
                return Double.parseDouble(edit.getText());
            }
        } catch (NumberFormatException e) {
            throw new DoubleException("Ponga un numero Valido");
        }
    }

    public void setCalificacion(Double cal) {
        edit.setText(Double.toString(cal));
    }
}
