/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class MainFrame extends JFrame {

    private JButton btnIniciar;
    private JButton btnSalir;
    private NumeroVariableDependientes dlgX;
    
    private JLabel imagen;
    private JLabel lblText;
    
    private JPanel pnlN;
    private JPanel pnlC;
    private JPanel pnlS;
    
    public MainFrame() {
        super("Regresion Lineal");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(420, 520);
        super.setLayout(new FlowLayout());
        super.setLocationRelativeTo(null);
        
        lblText = new JLabel("Regresion Lineal Multiple Calculator");
        lblText.setFont(new Font("Arial", Font.BOLD, 18));
        pnlC = new JPanel(new BorderLayout());
        imagen = new JLabel();
        imagen.setSize(400, 400);
        ImageIcon profe = new ImageIcon("regresi.png");
        Icon i = new ImageIcon(profe.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT));
        imagen.setIcon(i);
        pnlC.add(imagen, BorderLayout.CENTER);
        
        dlgX = new NumeroVariableDependientes(this);
        
        pnlS = new JPanel(new FlowLayout());
        btnIniciar = new JButton("Iniciar");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlgX.setVisible(true);
            }
        });
        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pnlS.add(btnIniciar);
        pnlS.add(btnSalir);
        
        super.add(lblText, BorderLayout.NORTH);
        super.add(pnlC, BorderLayout.CENTER);
        super.add(pnlS, BorderLayout.SOUTH);
        super.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainFrame();
    }

}
