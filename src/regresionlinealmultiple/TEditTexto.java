/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class TEditTexto extends JPanel {

    private JLabel lblcaption;
    private JTextField edit;

    public TEditTexto(String caption) {
        //super.setLayout(new GridLayout(1,2));
        this.lblcaption = new JLabel(caption);
        this.edit = new JTextField(15);

        super.add(lblcaption);
        super.add(edit);
    }

    public void clear() {
        edit.setText("");
    }

    public String getValue() throws ExcepcionCampoVacio {
        if (edit.getText().isEmpty()) {
            throw new ExcepcionCampoVacio("Por favor no dejes sin nombre a tu variable");
        } else {
            return edit.getText();
        }

    }

    public void setTexto(String texto) {
        edit.setText(texto);
    }
}
