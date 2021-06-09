/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class TableCoeficientes extends AbstractTableModel {

    private Formulas formulita;

    public TableCoeficientes(Formulas form) {
        formulita = form;
    }

    @Override
    public int getRowCount() {
        return formulita.getMatrizBResultado().length;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "B" + rowIndex;
            case 1:
                return formulita.getMatrizBResultado()[rowIndex];
            case 2:
                return formulita.getErrorTipico().get(rowIndex);
            case 3:
                return formulita.getEstadisticoT().get(rowIndex);
            case 4:
                return formulita.getLimiteI().get(rowIndex);
            case 5:
                return formulita.getLimiteS().get(rowIndex);
            default:
                throw new AssertionError();

        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "Error Tipico";
            case 3:
                return "Estadistico";
            case 4:
                return "Limite Inferior";
            case 5:
                return "Limite Superior";

            default:
                throw new AssertionError();

        }
    }
}
