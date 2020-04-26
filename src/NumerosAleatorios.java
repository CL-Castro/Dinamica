import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Scanner;
import java.lang.Math;
public class NumerosAleatorios {
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        double[] F = new double[100];
        /*double [] numContinuos = new double [100];
        System.out.println("A");
        double a = in.nextDouble();
        System.out.println("B");
        double b = in.nextDouble();

        for(int i=0;i<100;i++){
            F[i] = (Math.random() * 1) + 0;
            numContinuos[i] = a + F[i] * (b-a);
            System.out.println(numContinuos[i]);
        }
        GenerarGrafica(numContinuos,F,"Numeros Continuos");

        System.out.println("Landa");
        double landa = in.nextDouble();
        double [] numExp = new double [100];
        for(int i=0;i<100;i++){
            F = (Math.random() * 1/landa) + 0;
            numExp[i] = landa * Math.log(F);
            System.out.println(numExp[i]);
        }
        double [] numWeib = new double [100];
        System.out.println("Alfa");
        double alfa = in.nextDouble();
        System.out.println("Beta");
        double beta = in.nextDouble();
        for(int i=0;i<100;i++){
            F = Math.random();
            numWeib[i] = beta * Math.pow(Math.log(F),1/alfa);
            System.out.println(numWeib[i]);
        }*//*
        double [] numDiscreto = new double [100];
        System.out.println("Sigma");
        double sigma = in.nextDouble();
        System.out.println("Promedio");
        double prom = in.nextDouble();
        double sumatoria;
        double n = 1000;
        double index;
        double deltax = 6 * sigma / n;
        for(int i=0;i<numDiscreto.length;i++)
        {
            F = Math.random();
            sumatoria = 0;
            index = 1;
            while(sumatoria < F)
            {
                sumatoria += (1/Math.sqrt(2*Math.PI*Math.pow(sigma,2)))*
                        (Math.pow(Math.E,-1*Math.pow((index*deltax - prom)/sigma,2)))*deltax;
                index += 1.0;
                //System.out.println(F + " > "+sumatoria);
            }
            numDiscreto[i] = sumatoria;
            System.out.println(numDiscreto[i]);
        }*/
        double [] numLogN = new double [100];
        System.out.println("Varianza");
        double sigma = in.nextDouble();
        System.out.println("Media");
        double u = in.nextDouble();
        double sumatoria;
        double n = 1000;
        double index;
        double deltax = 6 * sigma / n;
        double dx = deltax;
        for(int i=0;i<numLogN.length;i++)
        {
            F[i] = Math.random();
            sumatoria = 0;
            index = 1;
            while(sumatoria < F[i])
            {
                dx = index * deltax;
                sumatoria += dx*Math.exp(-(Math.pow(Math.log(dx)-u,2))/(2*sigma*sigma))/(sigma*dx*Math.sqrt(2*Math.PI));
                index += 1.0;
                //System.out.println("Integral = "+sumatoria+" // Semilla = "+F+" // Delta X = "+dx);
            }
            numLogN[i] = dx;
            System.out.println(numLogN[i]);
        }
        /*double [] numBeta = new double [100];
        System.out.println("Alfa");
        double alfa = in.nextDouble();
        System.out.println("Beta");
        double beta = in.nextDouble();
        double sumatoria;
        double n = 1000;
        double index;
        double deltax = 0.001;
        double dx = deltax;
        for(int i=0;i<numBeta.length;i++)
        {
            F[i] = Math.random();
            sumatoria = 0;
            index = 1;
            while(sumatoria < F[i])
            {
                dx = index * deltax;
                sumatoria += (gamma(alfa+beta)/(gamma(alfa)+gamma(beta)))*Math.pow(dx,alfa-1)*Math.pow(1-dx,beta-1)*deltax;
                index += 1.0;
                //System.out.println("Integral = "+sumatoria+" // Semilla = "+F+" // Delta X = "+dx);
            }
            numBeta[i] = dx;
            System.out.println(numBeta[i]);
        }*/
        GenerarGrafica(numLogN,F,"Funcion LogNormal");
    }
    static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }
    static double gamma(double x) { return Math.exp(logGamma(x)); }
    public static void GenerarGrafica(double numeros[],double numero[],String titulo){
        JFreeChart Grafica;
        DefaultCategoryDataset Datos = new DefaultCategoryDataset();
        for (int i=0;i<100;i++){
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
}
