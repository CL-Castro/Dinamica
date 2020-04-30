import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.sun.javafx.fxml.expression.Expression.add;

public class PronosticoStock extends JFrame{
    static int colPron = 7;
    static int colx = 4;
    static int numpron = 10;
    static String pais = "Bolivia";
    public static void main(String[] args) throws IOException {
        String pathToCsv = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\Corona.csv";
        Ar5(pathToCsv);
        Ar3(pathToCsv);
    }

    public static void Ar3(String pathToCsv) throws IOException
    {
        String row=null;
        ArrayList<String[]> datos = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            //datos.add(data);
            if(data[1].compareToIgnoreCase(pais)==0)
            {
                datos.add(data);
            }
        }
        System.out.println(" NUMERO DE DATOS : " + datos.size());
        csvReader.close();
        double [][] dy = diferencial3(datos);
        System.out.println("waso");
        double [][] yt = desintegrar3(datos);
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
            y.add(Double.parseDouble(val[colPron]));
            fechas.add(val[colx]);
        }
        double[] yp = {dy[dy.length-1][0],dy[dy.length-2][0],dy[dy.length-3][0]};
        double ultimo;
        double [] ywaso = new double[numpron];
        String [] xwaso = new String[numpron];
        for(int i = 0;i<numpron;i++){
            double ypron = gammaDeVeras[0][0] * yp[0] + gammaDeVeras[1][0] * yp[1] + gammaDeVeras[2][0] * yp[2];
            yp[2] = yp[1];
            yp[1] = yp[0];
            yp[0] = ypron;
            ultimo = y.get(y.size()-1);
            y.add(ultimo+ypron);
            fechas.add("Pronosticado"+(i+1));
            ywaso[i] = ultimo+ypron;
            xwaso[i] = String.valueOf(i+1);
        }
        double[] axisY = new double[y.size()-numpron];
        String[] axisX = new String[fechas.size()-numpron];
        for (int i=0;i<y.size()-numpron;i++){
            System.out.println(y.get(i));
            axisY[i] = y.get(i);
            axisX[i] = fechas.get(i);
        }
        new PronosticoStock(axisY,axisX,ywaso,xwaso,"AR3").setVisible(true);
    }
    public static void Ar5(String pathToCsv) throws IOException {
        String row=null;
        ArrayList<String[]> datos = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            //datos.add(data);
            if(data[1].compareToIgnoreCase(pais)==0)
            {
                datos.add(data);
                //System.out.println(data[1]+" ->"+data[5]);
            }
        }
        csvReader.close();
        double [][] dy = diferencial5(datos);
        double [][] yt = desintegrar5(datos);
        ShowMatrix(dy);
        ShowMatrix(yt);
        // yt = gamma * ytmenos
        double[][] gamma = findB(yt,dy);
        System.out.println("RESULTADOS a ->");
        for (String[] val:datos
             ) {
            System.out.println(val[1]+" Contagios: "+val[5]+" Muertes: "+val[6]+" Recuperados: "+val[7]);
        }
        // Yule Walker
        double[][] matrixA = {{gamma[1][0]-1,gamma[2][0],gamma[3][0],gamma[4][0],0},{gamma[0][0]+gamma[2][0],gamma[3][0]-1,gamma[4][0],0,0}
                ,{gamma[1][0]+gamma[3][0],gamma[0][0]+gamma[4][0],-1,0,0},{gamma[2][0]+gamma[4][0],gamma[1][0],gamma[0][0],-1,0},{gamma[3][0],gamma[2][0],gamma[1][0],gamma[0][0],-1}};
        double[][] matrixARes = {{-gamma[0][0]},{-gamma[1][0]},{-gamma[2][0]},{-gamma[3][0]},{-gamma[4][0]}};
        double[][] gammaDeVeras = findB(matrixA,matrixARes);
        System.out.println("RESULTADO GAMMAS MODELO AR5");
        ShowMatrix(gammaDeVeras);
        System.out.println("Y");
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<String> fechas = new ArrayList<>();
        for (String[] val:datos
        ) {
            y.add(Double.parseDouble(val[colPron]));
            fechas.add(val[colx]);
        }
        double[] yp = {dy[dy.length-1][0],dy[dy.length-2][0],dy[dy.length-3][0],dy[dy.length-4][0],dy[dy.length-5][0]};
        double ultimo;
        double [] ywaso = new double[numpron];
        String [] xwaso = new String[numpron];
        for(int i = 0;i<numpron;i++){
            double ypron = gammaDeVeras[0][0] * yp[0] + gammaDeVeras[1][0] * yp[1] + gammaDeVeras[2][0] * yp[2]
                    + gammaDeVeras[3][0] * yp[3] + gammaDeVeras[4][0] * yp[4];
            yp[4] = yp[3];
            yp[3] = yp[2];
            yp[2] = yp[1];
            yp[1] = yp[0];
            yp[0] = ypron;
            ultimo = y.get(y.size()-1);
            y.add(ultimo+ypron);
            System.out.println("Pron = "+ypron+" Resultado = " + (ultimo+ypron));
            fechas.add("Pronosticado"+(i+1));
            ywaso[i] = ultimo+ypron;
            xwaso[i] = String.valueOf(i+1);
        }
        double[] axisY = new double[y.size()-numpron];
        String[] axisX = new String[fechas.size()-numpron];
        for (int i=0;i<y.size()-numpron;i++){
            axisY[i] = y.get(i);
            axisX[i] = fechas.get(i);
            System.out.println(axisY[i] + axisX[i]);
        }
        new PronosticoStock(axisY,axisX,ywaso,xwaso,"AR5").setVisible(true);
    }

    public PronosticoStock(double numeros[],String numero[],double yp[],String xp[],String titulo) {   // the constructor will contain the panel of a certain size and the close operations
        super("XY Line Chart Example with JFreechart"); // calls the super class constructor

        JPanel chartPanel = createChartPanel(numeros,numero,yp,xp,titulo);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(double numeros[],String numero[],double yp[],String xp[],String titulo) { // this method will create the chart panel containin the graph
        String chartTitle = titulo;
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = createDataset(numeros,numero,yp,xp,titulo);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        customizeChart(chart);

        // saves the chart as an image files
        File imageFile = new File("XYLineChart.png");
        int width = 640;
        int height = 480;

        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return new ChartPanel(chart);
    }
    private XYDataset createDataset(double numeros[],String numero[],double yp[],String xp[],String titulo) {    // this method creates the data as time seris
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Valor real");
        XYSeries series2 = new XYSeries("Pronosticado");

        int index = 0;
        for (int i=0;i<numeros.length;i++){
            series1.add(i,numeros[i]);
            System.out.println(numeros[i]);
            index=i;
        }
        series2.add(index,numeros[index]);
        for (int i=0;i<yp.length;i++){
            series2.add(index+Double.parseDouble(xp[i]),yp[i]);
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private void customizeChart(JFreeChart chart) {   // here we make some customization
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);

        // sets thickness for series (using strokes)
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));

        // sets paint color for plot outlines
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(1.0f));

        // sets renderer for lines
        plot.setRenderer(renderer);

        // sets plot background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }

    public static double[][] diferencial3(ArrayList<String[]> stockValue)
    {
        double [][] yt = new double[stockValue.size()-4][1];
        int index = 4;
        for (int i = 0;i<stockValue.size()-4;i++)
        {
            yt[i][0] = Double.parseDouble(stockValue.get(index)[colPron]) - Double.parseDouble(stockValue.get(index-1)[colPron]);
            index++;
        }
        return yt;
    }
    public static double[][] desintegrar3(ArrayList<String[]> stockValue)
    {
        double [][] yt = new double[stockValue.size()-4][3];
        int index = 4;
        for (int i = 0;i<stockValue.size()-4;i++)
        {
            yt[i][0] = Double.parseDouble(stockValue.get(index-1)[colPron]) - Double.parseDouble(stockValue.get(index-2)[colPron]);
            yt[i][1] = Double.parseDouble(stockValue.get(index-2)[colPron]) - Double.parseDouble(stockValue.get(index-3)[colPron]);
            yt[i][2] = Double.parseDouble(stockValue.get(index-3)[colPron]) - Double.parseDouble(stockValue.get(index-4)[colPron]);
            index++;
        }
        return yt;
    }
    public static double[][] diferencial5(ArrayList<String[]> stockValue)
    {
        double [][] yt = new double[stockValue.size()-6][1];
        int index = 6;
        for (int i = 0;i<stockValue.size()-6;i++)
        {
            yt[i][0] = Double.parseDouble(stockValue.get(index)[colPron]) - Double.parseDouble(stockValue.get(index-1)[colPron]);
            index++;
        }
        return yt;
    }
    public static double[][] desintegrar5(ArrayList<String[]> stockValue)
    {
        double [][] yt = new double[stockValue.size()-6][5];
        int index = 6;
        for (int i = 0;i<stockValue.size()-6;i++)
        {
            yt[i][0] = Double.parseDouble(stockValue.get(index-1)[colPron]) - Double.parseDouble(stockValue.get(index-2)[colPron]);
            yt[i][1] = Double.parseDouble(stockValue.get(index-2)[colPron]) - Double.parseDouble(stockValue.get(index-3)[colPron]);
            yt[i][2] = Double.parseDouble(stockValue.get(index-3)[colPron]) - Double.parseDouble(stockValue.get(index-4)[colPron]);
            yt[i][3] = Double.parseDouble(stockValue.get(index-4)[colPron]) - Double.parseDouble(stockValue.get(index-5)[colPron]);
            yt[i][4] = Double.parseDouble(stockValue.get(index-5)[colPron]) - Double.parseDouble(stockValue.get(index-6)[colPron]);
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
