import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBinomial {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        //Binomial();
        //Hypergeometrico();
        //Bernoulli();
        //Poisson();
        //BinomialNegativa();
        Geometrica();
    }

    public static JFreeChart createHistogram(double[][] doubleMatrix){

        // Generate a one dimensional array of the size w*h of the double matrix
        ArrayList<Double> dataArrayList = new ArrayList<Double>();

        for (int i=0; i<doubleMatrix.length; i++) {
            for (int j = 0; j < doubleMatrix[i].length; j++) {
                double value =  doubleMatrix[i][j];
                if( Double.isNaN(value))
                    continue;
                else
                    dataArrayList.add(value);
                System.out.println(value);
            }
        }

        double[] data = new double[dataArrayList.size()];

        for(int p = 0; p < dataArrayList.size();p++)
            data[p] = dataArrayList.get(p);


        // int number = data.length;
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        dataset.addSeries("Hist",data,200); // Number of bins is 50
        String plotTitle = "";
        String xAxis = "Frequency";
        String yAxis = "Mass Error (Da)";
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        boolean show = false;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xAxis, yAxis,
                dataset, orientation, show, toolTips, urls);

        chart.setBackgroundPaint(Color.white);

        return chart;
    }

    public static void Geometrica(){
        System.out.println("Ingrese p");
        double p = in.nextDouble();
        System.out.println("Media: "+(1/p)+"\nVarianza: "+((1-p)/Math.pow(p,2)));
        double[] F = new double[1000];
        double[] numeros = new double[1000];
        double suma = 0;
        int index = 0;
        for (int i = 0;i<F.length;i++)
        {
            F[i]=Math.random();
            suma = 0;
            index = 1;
            while(suma<F[i]){
                suma += p * Math.pow(1-p,index-1);
                index++;
            }
            numeros[i] = index;
            System.out.println(numeros[i]);
        }

    }
    public static void Poisson()
    {
        System.out.println("Ingrese lamda: ");
        double landa = in.nextDouble();
        double[] F = new double[1000];
        double[] numeros = new double[1000];

        for (int i = 0;i<F.length;i++)
        {
            F[i] = Math.random();
            double sum = 0;
            int index = 0;
            double intervalo = F[i];
            while(sum<intervalo){
                sum += Math.exp(-landa) * Math.pow(landa, index)/factorial(index);
                //System.out.println(sum);
                index+=1;
            }
            numeros[i] = index;
            System.out.println(numeros[i]);
        }
    }

    public static void Bernoulli()
    {
        System.out.println("Ingrese p: ");
        double p = in.nextDouble();
        double[] F = new double[1000];
        double[] numeros = new double[1000];
        for (int i = 0;i<F.length;i++)
        {
            F[i] = Math.random();
            if(F[i]<=p)
            {
                numeros[i] = 1;
            }
            else
            {
                numeros[i] = 0;
            }
            System.out.println(numeros[i]);
        }
    }

    public static void BinomialNegativa()
    {
        System.out.println("Ingrese k");
        double k = in.nextDouble();
        System.out.println("Ingrese p");
        double p = in.nextDouble();
        double[] F = new double[1000];
        double[] numeros = new double[1000];
        System.out.println("Media: "+(k*(1-p)/p)+" \nVarianza: "+(k*(1-p)/Math.pow(p,2)));
        for (int i = 0;i<F.length;i++)
        {
            //Intervalos 0-0.09 es x = 0, 0.1 - 0.19 es x = 1
            F[i] = Math.random();
            double sum = 0;
            int index = 0;
            double intervalo = F[i];
            while(sum < intervalo){
                sum += combinacion(k+index-1,index) * Math.pow(p,k) * Math.pow(1-p,index);
                /*if(prob[index][0] == 0)
                {
                    prob[index][0] = combinacion(k+index-1,index) * Math.pow(p,k) * Math.pow(1-p,index);
                    prob[index][1] = sum;
                }*/
                index+=1;
            }
            numeros[i] = index;
        }
        for (double numero : numeros) {
            System.out.println(numero);
        }
    }

    public static void Hypergeometrico()
    {
        System.out.println("Ingrese N");
        double Npob = in.nextDouble();
        System.out.println("Ingrese n");
        double n = in.nextDouble();
        System.out.println("Ingrese p");
        double p = in.nextDouble();
        double[] F = new double[1000];
        double[] numeros = new double[1000];
        double[][] prob = new double[10][2];
        for(int i=0;i<prob.length;i++)
        {
            prob[i][0] = 0;
            prob[i][1] = 0;
        }
        for (int i = 0;i<F.length;i++)
        {
            //Intervalos 0-0.09 es x = 0, 0.1 - 0.19 es x = 1
            F[i] = Math.random();
            double sum = 0;
            int index = 0;
            double intervalo = F[i] *10;
            while(index<intervalo){
                sum += combinacion(Npob*p,index) * combinacion(Npob*(1-p),n-index) / combinacion(Npob,n);
                if(prob[index][0] == 0)
                {
                    prob[index][0] = combinacion(Npob*p,index) * combinacion(Npob*p,n-index) / combinacion(Npob,n);
                    prob[index][1] = sum;
                }
                index+=1;
            }
            numeros[i] = index;
        }
        ShowMatrix(prob);
        for(int i=0;i<numeros.length;i++)
        {
            System.out.println(numeros[i]);
        }
    }

    public static void Binomial()
    {
        System.out.println("Ingrese n");
        double n = in.nextDouble();
        System.out.println("Ingrese p");
        double p = in.nextDouble();
        double[] F = new double[1000];
        double[] numeros = new double[1000];
        double[][] prob = new double[10][2];
        for(int i=0;i<prob.length;i++)
        {
            prob[i][0] = 0;
            prob[i][1] = 0;
        }
        for (int i = 0;i<F.length;i++)
        {
            //Intervalos 0-0.09 es x = 0, 0.1 - 0.19 es x = 1
            F[i] = Math.random();
            double sum = 0;
            int index = 0;
            double intervalo = F[i] *10;
            while(index<intervalo){
                sum += combinacion(n,index) * Math.pow(p,index) * Math.pow(1-p,n-index);
                if(prob[index][0] == 0)
                {
                    prob[index][0] = combinacion(n,index) * Math.pow(p,index) * Math.pow(1-p,n-index);
                    prob[index][1] = sum;
                }
                index+=1;
            }
            numeros[i] = index;
        }
        ShowMatrix(prob);
        for(int i=0;i<numeros.length;i++)
        {
            System.out.println(numeros[i]);
        }
    }

    public static double combinacion(double n, double x)
    {
        return factorial(n)/(factorial(x)*factorial(n-x));
    }

    public static double factorial (double numero) {
        if (numero==0)
            return 1;
        else
            return numero * factorial(numero-1);
    }

    public static void ShowMatrix(double [][] matrizX){
        for (int i =0;i<matrizX.length;i++){
            for (int j =0;j<matrizX[0].length;j++){
                System.out.print(matrizX[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
