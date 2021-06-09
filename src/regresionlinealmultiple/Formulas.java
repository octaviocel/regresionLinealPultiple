/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.TDistribution;

/**
 *
 * @author DELL
 */
public class Formulas {

    private ArrayList<Double> array;

    private Double[][] arrayXTranspuesto;

    private Double[][] matrizXNormal;

    private Double[][] matrixXTX;//transpuesta por matriz x

    private Double[][] matrizInversa;

    private Double[] matrizDependiente;

    private Double[] matrizXTY;

    private Double[] matrizBResultado;

    private ArrayList<Double> valoresYestimados;
    private ArrayList<Double> valoresYmenosEstimacion;
    private ArrayList<Double> SSE;
    private ArrayList<Double> SSR;
    private ArrayList<Double> SST;

    private Double coefDeterminacion;
    private Double coefCorrelacion;
    private Double coefDeteAjustado;
    private Double errorEstimacion;

    private Double varianzaSE;
    private ArrayList<Double> errorTipico;
    private ArrayList<Double> estadisticoT;
    
    //private ConexionExcel cncDistribucion;
    private Double distribucion;
    
    private ArrayList<Double> limiteI;
    private ArrayList<Double> limiteS;
    
    private Integer var;
    private Integer d;

    public Formulas(ArrayList<Double> array, int variablesX, int datos) throws Exception {
        this.array = array;
        this.var = variablesX;
        this.d=datos;
        this.arrayXTranspuesto = new Double[variablesX + 1][datos];
        this.matrizXNormal = new Double[datos][variablesX + 1];
        System.out.println("Tranpuesta");
        agregarMatrixT(variablesX + 1, datos);
        System.out.println("\n");
        System.out.println("Matriz X");
        matrixXNormal();
        this.matrixXTX = mutiplicaMatriz(arrayXTranspuesto, matrizXNormal);
        System.out.println("\n");
        System.out.println("Matriz Xt*X");
        imprimeXTX();
        System.out.println("\n");
        System.out.println("Matriz Inversa");
        this.matrizInversa = matrixInversa(matrixXTX);
        imprimeInversa();
        System.out.println("\n");
        System.out.println("Matriz Dependiente");
        crearDependiente(variablesX + 1);
        System.out.println("\n");
        System.out.println("Matriz XT*Y");
        this.matrizXTY = matrizMultiplicadaPorVector(arrayXTranspuesto, matrizDependiente);
        imprimeXTY();
        System.out.println("\n");
        System.out.println("Matriz B0,B1,B2,Bn");
        this.matrizBResultado = matrizMultiplicadaPorVector(matrizInversa, matrizXTY);
        imprimeB();
        System.out.println("\n");
        System.out.println("Y estimada");
        valoresEstimados();
        System.out.println("\n");
        System.out.println("Y - Yestimada");
        valorYmenosEstimado();
        System.out.println("\n");
        System.out.println("SSE");
        crearSSE();
        System.out.println("\n");
        System.out.println("SSR");
        crearSSR();
        System.out.println("\n");
        System.out.println("SST");
        crearSST();
        System.out.println("\n");
        System.out.println("Coeficientes");
        coeficientes(variablesX, datos);
        System.out.println("\n");
        System.out.println("Error tipico");
        errorTipicoVarianzasEstimadoras();
        System.out.println("\n");
        System.out.println("Estadistico T");
        estadisticoT();
        //this.cncDistribucion = new ConexionExcel(0.05, (datos-variablesX-1));
        System.out.println("\n");
        System.out.println("Tstudent");
        this.distribucion=crearDistribusionInversa(.95, variablesX, datos);
        System.out.println(distribucion);
        crearLimiteI();
        crearLimiteS();
    }

    public Integer getVar() {
        return var;
    }

    public Integer getDA() {
        return d;
    }
    
    public Double[][] getMatrizXNormal() {
        return matrizXNormal;
    }

    public Double[] getMatrizDependiente() {
        return matrizDependiente;
    }

    public Double[] getMatrizBResultado() {
        return matrizBResultado;
    }

    public ArrayList<Double> getValoresYestimados() {
        return valoresYestimados;
    }

    public ArrayList<Double> getValoresYmenosEstimacion() {
        return valoresYmenosEstimacion;
    }

    public ArrayList<Double> getSSE() {
        return SSE;
    }

    public ArrayList<Double> getSSR() {
        return SSR;
    }

    public ArrayList<Double> getSST() {
        return SST;
    }

    public Double getCoefDeterminacion() {
        return coefDeterminacion;
    }

    public Double getCoefCorrelacion() {
        return coefCorrelacion;
    }

    public Double getCoefDeteAjustado() {
        return coefDeteAjustado;
    }

    public Double getErrorEstimacion() {
        return errorEstimacion;
    }

    public ArrayList<Double> getErrorTipico() {
        return errorTipico;
    }

    public ArrayList<Double> getEstadisticoT() {
        return estadisticoT;
    }

    public Double getDistribucion() {
        return distribucion;
    }

    public ArrayList<Double> getLimiteI() {
        return limiteI;
    }

    public ArrayList<Double> getLimiteS() {
        return limiteS;
    }
    
    
    private void crearLimiteI(){
        limiteI = new ArrayList<>();
        for (int i = 0; i < matrizBResultado.length; i++) {
            limiteI.add(matrizBResultado[i]-distribucion*errorTipico.get(i));
        }
    }
    
    private void crearLimiteS(){
        limiteS = new ArrayList<>();
        for (int i = 0; i < matrizBResultado.length; i++) {
            limiteS.add(matrizBResultado[i]+distribucion*errorTipico.get(i));
        }
    }
    
    public Double crearDistribusionInversa(Double confianza,int variablesX, int datos){
        TDistribution t = new TDistribution(datos - (variablesX + 1));
        //Double confianza = doub.doubleValue() / 100;
        Double student = t.inverseCumulativeProbability(1 - ((1 - confianza) / 2));
        return student;
    }

    private void estadisticoT() {
        this.estadisticoT = new ArrayList<>();
        for (int i = 0; i < matrizBResultado.length; i++) {
            Double valor = matrizBResultado[i] / errorTipico.get(i);
            System.out.println(valor);
            estadisticoT.add(valor);
        }
    }

    private void coeficientes(int variableX, int datos) {
        Double sumaSST = 0.0, sumaSSR = 0.0, sumaSSE = 0.0;
        for (int i = 0; i < SSR.size(); i++) {
            sumaSSR += SSR.get(i);
        }
        for (int i = 0; i < SST.size(); i++) {
            sumaSST += SST.get(i);
        }
        for (int i = 0; i < SSE.size(); i++) {
            sumaSSE += SSE.get(i);
        }
        System.out.println(this.coefDeterminacion = sumaSSR / sumaSST);
        System.out.println(this.coefCorrelacion = Math.sqrt(coefDeterminacion));
        System.out.println(this.coefDeteAjustado = coefCorrelacion - (variableX * (1 - coefCorrelacion) / (datos - variableX - 1)));
        System.out.println(this.errorEstimacion = Math.sqrt((sumaSSE) / (datos - variableX - 1)));
    }

    private void errorTipicoVarianzasEstimadoras() {
        this.varianzaSE = Math.pow(errorEstimacion, 2);
        this.errorTipico = new ArrayList<>();
        for (int i = 0; i < matrizInversa.length; i++) {
            for (int j = 0; j < matrizInversa[i].length; j++) {
                if (i == j) {
                    Double valor = matrizInversa[i][j] * varianzaSE;
                    valor = Math.sqrt(valor);
                    System.out.println(valor);
                    errorTipico.add(valor);
                }
            }
        }
    }

    private void valoresEstimados() {
        this.valoresYestimados = new ArrayList<>();
        for (int i = 0; i < matrizXNormal.length; i++) {
            Double suma = 0.0;
            for (int j = 0; j < matrizXNormal[0].length; j++) {
                suma += (matrizXNormal[i][j] * matrizBResultado[j]);
            }
            System.out.println(suma);
            valoresYestimados.add(suma);
        }
    }

    private void valorYmenosEstimado() {
        valoresYmenosEstimacion = new ArrayList<>();

        for (int i = 0; i < matrizDependiente.length; i++) {
            Double valor = matrizDependiente[i] - valoresYestimados.get(i);
            valoresYmenosEstimacion.add(valor);
            System.out.println(valor);
        }
    }

    private void crearSSE() {
        SSE = new ArrayList<>();
        for (int i = 0; i < valoresYmenosEstimacion.size(); i++) {
            Double valor = Math.pow(valoresYmenosEstimacion.get(i), 2);
            SSE.add(valor);
            System.out.println(valor);
        }
    }

    private void crearSSR() {
        SSR = new ArrayList<>();
        Double promedio = 0.0;
        for (int i = 0; i < matrizDependiente.length; i++) {
            promedio += matrizDependiente[i];
        }
        promedio /= matrizDependiente.length;
        for (int i = 0; i < valoresYestimados.size(); i++) {
            Double valor = Math.pow((valoresYestimados.get(i) - promedio), 2);
            System.out.println(valor);
            SSR.add(valor);
        }
    }

    private void crearSST() {
        SST = new ArrayList<>();
        Double promedio = 0.0;
        for (int i = 0; i < matrizDependiente.length; i++) {
            promedio += matrizDependiente[i];
        }
        promedio /= matrizDependiente.length;
        for (int i = 0; i < matrizDependiente.length; i++) {
            Double valor = Math.pow((matrizDependiente[i] - promedio), 2);
            System.out.println(valor);
            SST.add(valor);
        }
    }

    private void crearDependiente(int v) {
        this.matrizDependiente = new Double[array.size() / v];
        int apunto = (v - 1) * (array.size() / v);
        int f = 0;
        for (int i = apunto; i < array.size(); i++) {
            matrizDependiente[f] = array.get(i);
            System.out.println("[" + matrizDependiente[f] + "]");
            f++;
        }
    }

    private void agregarMatrixT(int x, int y) {
        int o = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0) {
                    arrayXTranspuesto[i][j] = 1.0;
                } else {
                    arrayXTranspuesto[i][j] = array.get(o);
                    o++;
                }
            }
        }
        imprimirTranpuesta();
    }

    private void matrixXNormal() {
        for (int x = 0; x < arrayXTranspuesto.length; x++) {
            for (int y = 0; y < arrayXTranspuesto[x].length; y++) {
                matrizXNormal[y][x] = arrayXTranspuesto[x][y];
            }
        }
        imprimirNormal();
    }

    private void imprimirNormal() {
        for (int i = 0; i < matrizXNormal.length; i++) {
            for (int j = 0; j < matrizXNormal[i].length; j++) {
                System.out.print("[" + matrizXNormal[i][j] + "]");
            }
            System.out.println();
        }
    }

    private void imprimirTranpuesta() {
        for (int i = 0; i < arrayXTranspuesto.length; i++) {
            for (int j = 0; j < arrayXTranspuesto[i].length; j++) {
                System.out.print("[" + arrayXTranspuesto[i][j] + "]");
            }
            System.out.println();
        }
    }

    private Double obtenerCelda(Double[][] m1, Double[][] m2, int fila, int col) {
        Double celda = 0.0;
        for (int i = 0; i < m2.length; i++) {
            celda += m1[fila][i] * m2[i][col];
        }
        return celda;
    }

    private Double[][] mutiplicaMatriz(Double[][] a, Double[][] b) {
        Double[][] matriz = new Double[a.length][b[0].length];

        for (int fila = 0; fila < matriz.length; fila++) {
            for (int col = 0; col < matriz[fila].length; col++) {
                matriz[fila][col] = obtenerCelda(a, b, fila, col);
            }
        }
        return matriz;
    }

    private void imprimeXTX() {
        for (int i = 0; i < matrixXTX.length; i++) {
            for (int j = 0; j < matrixXTX[i].length; j++) {
                System.out.print("[" + matrixXTX[i][j] + "]");
            }
            System.out.println();
        }
    }

    private Double[][] matrixInversa(Double[][] matrix) throws Exception {
        if (determinante(matrix) != 0) {
            Double[][] inversa = new Double[matrix.length][matrix.length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    inversa[i][j] = Math.pow(-1, i + j) * determinante(menor(matrix, i, j));
                }
            }

            double det = 1.0 / determinante(matrix);
            for (int i = 0; i < inversa.length; i++) {
                for (int j = 0; j <= i; j++) {
                    double temp = inversa[i][j];
                    inversa[i][j] = inversa[j][i] * det;
                    inversa[j][i] = temp * det;
                }
            }

            return inversa;
        } else {
            throw new Exception("Matriz singular");
        }
    }

    private Double determinante(Double[][] matriz) {
        if (matriz.length == 2) {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }

        Double det = 0.0;
        for (int i = 0; i < matriz[0].length; i++) {
            det += Math.pow(-1, i) * matriz[0][i] * determinante(menor(matriz, 0, i));
        }

        return det;
    }

    private Double[][] menor(Double[][] matrix, int fila, int column) {
        Double[][] min = new Double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; i != fila && j < matrix[i].length; j++) {
                if (j != column) {
                    min[i < fila ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
                }
            }
        }

        return min;
    }

    private void imprimeInversa() {
        for (int i = 0; i < matrizInversa.length; i++) {
            for (int j = 0; j < matrizInversa[i].length; j++) {
                System.out.print("[" + matrizInversa[i][j] + "]");
            }
            System.out.println();
        }
    }

    private Double[] matrizMultiplicadaPorVector(Double[][] matriz, Double[] vector) {
        int rows = matriz.length;
        int columns = matriz[0].length;

        Double[] result = new Double[rows];

        for (int row = 0; row < rows; row++) {
            double sum = 0;
            for (int column = 0; column < columns; column++) {
                sum += matriz[row][column] * vector[column];
            }
            result[row] = sum;
        }
        return result;
    }

    public void imprimeXTY() {
        for (int i = 0; i < matrizXTY.length; i++) {
            System.out.println("[" + matrizXTY[i] + "]");
        }
    }

    public void imprimeB() {
        for (int i = 0; i < matrizBResultado.length; i++) {
            System.out.println("[" + matrizBResultado[i] + "]");
        }
    }
}
