/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author DELL
 */
public class Grafica extends JDialog{
    private Formulas controller;
    
    public Grafica(JFrame parent, Formulas form){
        super(parent, ModalityType.APPLICATION_MODAL);
        super.setSize(800, 600);
        super.setTitle("Grafica");
        super.setLocationRelativeTo(parent);
        super.setLayout(new BorderLayout());
        
        this.controller = form;
        //String str = nombres.get(nombres.size() - 1);
        JFreeChart chart = ChartFactory.createXYLineChart("Regresion Multiple", "", "", 
            crearDataSet(), PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel pnlChart = new ChartPanel(chart);
        pnlChart.setBackground(Color.WHITE);
        XYPlot plot = chart.getXYPlot();
        chart.getPlot().setBackgroundPaint(Color.WHITE);
      
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0 , Color.GREEN);
        renderer.setSeriesPaint(1 , Color.RED);
        renderer.setSeriesStroke(0 , new BasicStroke(2.0f));
        renderer.setSeriesStroke(1 , new BasicStroke(2.0f));
        
        plot.setRenderer(renderer); 
        super.add(pnlChart);
    }
    
    private XYSeriesCollection crearDataSet(){
        XYSeries y_real = new XYSeries("Y");
        Double[] yreal = controller.getMatrizDependiente();
        for (int i = 0; i < yreal.length; i++) {
            y_real.add(i + 1, yreal[i]);
        }

        XYSeries y_est = new XYSeries("Y estimada");
        ArrayList<Double> yest = controller.getValoresYestimados();
        for (int i = 0; i < yest.size(); i++) {
            y_est.add(i + 1, yest.get(i));
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(y_real);
        dataset.addSeries(y_est);
        
        return dataset;
    }
        
}
