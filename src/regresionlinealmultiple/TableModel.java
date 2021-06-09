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
public class TableModel extends AbstractTableModel {

    private Formulas formulita;

    public TableModel(Formulas form) {
        formulita = form;
    }

    @Override
    public int getRowCount() {
        return formulita.getMatrizDependiente().length;
    }

    @Override
    public int getColumnCount() {
        if (formulita.getMatrizXNormal()[0].length == 3) {
            return 8;
        } else {
            return 9;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (formulita.getMatrizXNormal()[0].length == 3) {
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return formulita.getMatrizXNormal()[rowIndex][1];
                case 2:
                    return formulita.getMatrizXNormal()[rowIndex][2];
                case 3:
                    return formulita.getMatrizDependiente()[rowIndex];
                case 4:
                    return formulita.getValoresYestimados().get(rowIndex);
                case 5:
                    return formulita.getValoresYmenosEstimacion().get(rowIndex);
                case 6:
                    return formulita.getSSE().get(rowIndex);
                case 7:
                    return formulita.getSSR().get(rowIndex);
                case 8:
                    return formulita.getSST().get(rowIndex);
                default:
                    throw new AssertionError();

            }
        } else {
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return formulita.getMatrizXNormal()[rowIndex][1];
                case 2:
                    return formulita.getMatrizXNormal()[rowIndex][2];
                case 3:
                    return formulita.getMatrizXNormal()[rowIndex][3];
                case 4:
                    return formulita.getMatrizDependiente()[rowIndex];
                case 5:
                    return formulita.getValoresYestimados().get(rowIndex);
                case 6:
                    return formulita.getValoresYmenosEstimacion().get(rowIndex);
                case 7:
                    return formulita.getSSE().get(rowIndex);
                case 8:
                    return formulita.getSSR().get(rowIndex);
                case 9:
                    return formulita.getSST().get(rowIndex);
                default:
                    throw new AssertionError();
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        if (formulita.getMatrizXNormal()[0].length == 3) {
            switch (column) {
                case 0:
                    return "No.";
                case 1:
                    return "Variable X1";
                case 2:
                    return "Variable X2";
                case 3:
                    return "Y";
                case 4:
                    return "Y estimada";
                case 5:
                    return "Y - estimada";
                case 6:
                    return "SSE";
                case 7:
                    return "SSR";
                case 8:
                    return "SST";
                default:
                    throw new AssertionError();

            }
        } else {
            switch (column) {
                case 0:
                    return "No.";
                case 1:
                    return "Variable X1";
                case 2:
                    return "Variable X2";
                case 3:
                    return "Variable X3";
                case 4:
                    return "Y";
                case 5:
                    return "Y estimada";
                case 6:
                    return "Y - estimada";
                case 7:
                    return "SSE";
                case 8:
                    return "SSR";
                case 9:
                    return "SST";
                default:
                    throw new AssertionError();

            }
        }

    }
}
