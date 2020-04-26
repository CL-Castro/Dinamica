import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PronosticoStock {
    public static void main(String[] args) throws IOException {
        String pathToCsv = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\Google.csv";
        String row=null;
        ArrayList<String[]> datos = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            datos.add(data);
            System.out.println(data[0]);
        }
        csvReader.close();
        double [][] dy = diferencial(datos);
        System.out.println("waso");
        double [][] yt = desintegrar(datos);
        // yt = gamma * ytmenos
        double[][] gamma = findB(yt,dy);
        System.out.println("RESULTADOS a ->");
        ShowMatrix(gamma);
        // Yule Walker
        double[][] matrixA = {{gamma[0][0]-1,gamma[2][0],0},{gamma[2][0]+gamma[0][0],-1,0},{gamma[0][0],gamma[1][0],1}};
        double[][] matrixARes = {{-gamma[0][0]},{-gamma[1][0]},{-gamma[2][0]}};
        double[][] gammaDeVeras = findB(matrixA,matrixARes);
        System.out.println("RESULTADO GAMMAS MODELO AR3");
        ShowMatrix(gammaDeVeras);
        System.out.println("Y");
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<String> fechas = new ArrayList<>();
        for (String[] val:datos
             ) {
            y.add(Double.parseDouble(val[1]));
            fechas.add(val[0]);
        }
        double[] yp = {dy[dy.length-1][0],dy[dy.length-2][0],dy[dy.length-3][0]};
        double ultimo;
        for(int i = 0;i<1000;i++){
            double ypron = gammaDeVeras[0][0] * yp[0] + gammaDeVeras[1][0] * yp[1] + gammaDeVeras[2][0] * yp[2];
            yp[2] = yp[1];
            yp[1] = yp[0];
            yp[0] = ypron;
            ultimo = y.get(y.size()-1);
            y.add(ultimo+ypron);
            fechas.add("Pronosticado"+(i+1));
        }
        double[] axisY = new double[y.size()];
        String[] axisX = new String[fechas.size()];
        for (int i=0;i<y.size();i++){
            System.out.println(y.get(i));
            axisY[i] = y.get(i);
            axisX[i] = fechas.get(i);
        }
        GenerarGrafica(axisY,axisX,"AR3");
    }

    public static void GenerarGrafica(double numeros[],String numero[],String titulo){
        JFreeChart Grafica;
        DefaultCategoryDataset Datos = new DefaultCategoryDataset();
        for (int i=0;i<numeros.length;i++){
            Datos.addValue(numeros[i],"Numero",String.valueOf(numero[i]));
        }

        Grafica = ChartFactory.createLineChart(titulo,"ind","Numeros",Datos, PlotOrientation.VERTICAL,true,true,false);
        ChartPanel Panel = new ChartPanel(Grafica);
        JFrame Ventana = new JFrame("Graficas");
        Ventana.getContentPane().add(Panel);
        Ventana.pack();
        Ventana.setVisible(true);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static double[][] diferencial(ArrayList<String[]> stockValue)
    {
        double [][] yt = new double[stockValue.size()-4][1];
        int index = 4;
        for (int i = 0;i<stockValue.size()-4;i++)
        {
            yt[i][0] = Double.parseDouble(stockValue.get(index)[1]) - Double.parseDouble(stockValue.get(index-1)[1]);
            index++;
        }
        return yt;
    }
    public static double[][] desintegrar(ArrayList<String[]> stockValue)
    {
        double [][] yt = new double[stockValue.size()-4][3];
        int index = 4;
        for (int i = 0;i<stockValue.size()-4;i++)
        {
            yt[i][0] = Double.parseDouble(stockValue.get(index-1)[1]) - Double.parseDouble(stockValue.get(index-2)[1]);
            yt[i][1] = Double.parseDouble(stockValue.get(index-2)[1]) - Double.parseDouble(stockValue.get(index-3)[1]);
            yt[i][2] = Double.parseDouble(stockValue.get(index-3)[1]) - Double.parseDouble(stockValue.get(index-4)[1]);
            index++;
        }
        return yt;
    }
    public static double[][] findB(double[][] x, double[][]y){
        return multiply(multiply(invert(multiply(trans(x),x)),trans(x)),y);
    }

    public static void ShowMatrix(double [][] matrizX){
        for (int i =0;i<matrizX.length;i++){
            for (int j =0;j<matrizX[0].length;j++){
                System.out.print(matrizX[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        if (a[0].length == b.length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
        return c;
    }

    private static double[][] identidad(int n){
        double[][] matrizIdem = new double[n][n];
        for (int i =0;i<n;i++){
            for (int j =0;j<n;j++){
                if(i==j)matrizIdem[i][j] = 1;
                else matrizIdem[i][j] = 0;
            }
        }
        return matrizIdem;
    }

    private static double[][] trans(double matriz [][]){
        double[][] matrizT = new double[matriz[0].length][matriz.length];
        for (int x=0; x < matriz.length; x++) {
            for (int y=0; y < matriz[x].length; y++) {
                matrizT[y][x] = matriz[x][y];
            }
        }
        return matrizT;
    }

    public static double[][] invert(double a[][])         {

        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;
        gaussian(a, index);
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];
        for (int i=0; i<n; ++i)             {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)                 {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }

                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public static void gaussian(double a[][], int index[])  {

        int n = index.length;
        double c[] = new double[n];

        for (int i=0; i<n; ++i)
            index[i] = i;

        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j=0; j<n-1; ++j) {
            double pi1 = 0;
            for (int i=j; i<n; ++i)  {

                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }


            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) {
                double pj = a[index[i]][j]/a[index[j]][j];

                a[index[i]][j] = pj;

                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    private static double sumatoriaCuadrados(double[] a, double b)
    {
        double sum = 0;

        for(int i = 0; i<a.length; i++)
        {
            sum += Math.pow(a[i]-b,2);
        }

        return sum;
    }

    private static double sumatoriaCuadrado(double[] a)
    {
        double sum = 0;
        for(int i = 0; i<a.length; i++)
        {
            sum += Math.pow(a[i],2);
        }
        return sum;
    }
}
