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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author DELL
 */
public class DialogCoeficientes extends JDialog{
    
    private JTable tbl;
    private TableCoeficientes model;
    private JPanel pnlNort;
    
    private JPanel pnlCentro;
    
    private JLabel lblDeterminacion;
    private JLabel lblCorre;
    private JLabel lblDeteAjus;
    private JLabel lblEstimacion;
    
    private JPanel pnlSouth;
    private JButton btnAcep;
    
    public DialogCoeficientes(JFrame parent, Formulas form){
        super(parent,true);
        //parent.setVisible(false);
        super.setSize(500, 300);
        super.setLocationRelativeTo(parent);
        super.setLayout(new BorderLayout());
        
        model = new TableCoeficientes(form);
        tbl = new JTable(model);
        pnlNort = new JPanel(new BorderLayout());
        pnlNort.add(new JScrollPane(tbl), BorderLayout.CENTER);
        pnlNort.setBounds(300, 300, 300, 300);
        
        pnlCentro = new JPanel(new GridLayout(4, 1));
        lblDeterminacion = new JLabel("Coeficiente de Determinacion:  "+form.getCoefDeterminacion());
        lblCorre = new JLabel("Coeficiente de Correlacion:  "+form.getCoefCorrelacion());
        lblDeteAjus = new JLabel("Coeficiente de Det. Ajustado:  "+form.getCoefDeteAjustado());
        lblEstimacion = new JLabel("Error de Estimacion: "+form.getErrorEstimacion());
        pnlCentro.add(lblDeterminacion);
        pnlCentro.add(lblCorre);
        pnlCentro.add(lblDeteAjus);
        pnlCentro.add(lblEstimacion);
        
        pnlSouth = new JPanel(new FlowLayout());
        btnAcep = new JButton("Aceptar");
        btnAcep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCoeficientes.this.setVisible(false);
            }
        });
        pnlSouth.add(btnAcep);
        
        super.add(pnlNort, BorderLayout.NORTH);
        super.add(pnlCentro, BorderLayout.CENTER);
        super.add(pnlSouth, BorderLayout.SOUTH);
        DialogCoeficientes.this.pack();
    }
}
