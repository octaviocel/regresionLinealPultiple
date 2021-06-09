/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author DELL
 */
public class DialogResultados extends JDialog{
    
    private Formulas formulitas;
    private JTable tbl;
    private JLabel lblTi;
    
    private TableModel model;
    
    private JPanel pnlBotones;
    private JButton btnCoefi;
    private JButton btnSalir;
    private JButton btnGrafi;
    private JButton btnPronostico;
    
    private DialogCoeficientes dlgCoef;
    
    private Grafica grf;
    
    private DialogPronostico pronos;
    
    public DialogResultados(JFrame parent, Formulas formula){
        super(parent,true);
        //parent.setVisible(false);
        super.setSize(800, 500);
        super.setLocationRelativeTo(parent);
        super.setLayout(new BorderLayout());
        this.formulitas=formula;
        
        lblTi = new JLabel("Regresion Lineal Multiple");
        model = new TableModel(formula);
        tbl = new JTable(model);
        
        pnlBotones = new JPanel(new FlowLayout());
        dlgCoef = new DialogCoeficientes(parent, formula);
        btnCoefi = new JButton("Coeficientes");
        btnCoefi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlgCoef.setVisible(true);
            }
        });
        grf = new Grafica(parent, formula);
        btnGrafi = new JButton("Grafica");
        btnGrafi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grf.setVisible(true);
            }
        });
        pronos = new DialogPronostico(parent, formula);
        btnPronostico = new JButton("Hacer pronostico");
        btnPronostico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pronos.setVisible(true);
            }
        });
        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogResultados.this.setVisible(false);
            }
        });
        
        pnlBotones.add(btnCoefi);
        pnlBotones.add(btnPronostico);
        pnlBotones.add(btnSalir);
        pnlBotones.add(btnGrafi);
        
        super.add(lblTi, BorderLayout.NORTH);
        super.add(new JScrollPane(tbl), BorderLayout.CENTER);
        super.add(pnlBotones, BorderLayout.SOUTH);
    }
}
