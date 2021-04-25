import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kolmogorovSmirnovNormal extends ApplicationFrame {
        static List<Double> fteo;
        static List<Double>fprac;
        static double prom = 0.6; /////////////////////////
        static double var = 1; /////////////////////////

        public kolmogorovSmirnovNormal( String applicationTitle , String chartTitle ) throws IOException {
            super(applicationTitle);
            fteo=new ArrayList<Double>();
            fprac=new ArrayList<Double>();
            JFreeChart lineChart = ChartFactory.createLineChart(
                    chartTitle,
                    "Tiempo","",
                    createDataset(),
                    PlotOrientation.VERTICAL,
                    true,true,false);

            ChartPanel chartPanel = new ChartPanel( lineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 900, 900 ) );
            setContentPane( chartPanel );
        }
        private DefaultCategoryDataset createDataset( ) throws IOException {
            List<Double> data=new ArrayList<Double>();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
            // DATOS
            String pathToCHI = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\datos.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToCHI));
            //*
            String row;
            double suma = 0;
            int index = 0;
            while((row = bufferedReader.readLine()) != null)
            {
                data.add(Double.parseDouble(row));
                System.out.println(row);
                suma+=Double.parseDouble(row);
                index++;
            }
            bufferedReader.close();
            prom = suma/index;
            var = varianza(data);
            System.out.println("Media : "+prom+" Desviacion tipica: "+var);
            if(ks(data)) System.out.println("Siguen Distribucion Normal");
            else System.out.println("No siguen Distribucion Normal");
            for(int i=1;i<fteo.size();i++)
            {
                dataset.addValue(fteo.get(i-1)  , "teo" , String.valueOf(i));
            }
            for(int i=1;i<fprac.size();i++)
            {
                dataset.addValue(fprac.get(i-1)  , "prac" , String.valueOf(i));
            }
            return dataset;
        }
        public static double intnormal(double a,double b,double prom,double s) {
            double acum=0,delta=0.01;
            for(double i=a;i<=b;i=i+delta) {
                double normal=(i-prom)/s;
                double altura=(1/(Math.sqrt(2*Math.PI)*s))*(Math.exp(0.5*normal*normal));
                double area=delta*altura;
                acum+=area;
            }
            return acum;
        }

        public static int getFrecuencia(List<Double> data,double li,double ls)
        {
            int cont=0;
            for(int i=0;i<data.size();i++)
            {
                if(data.get(i)>= li && data.get(i)<ls)
                {
                    cont++;
                }
                if(data.get(i)>=ls)
                    break;
            }
            return cont;
        }


        public static boolean ks(List<Double>data)
        {
            Collections.sort(data);
            int n=data.size();
            double nclases=Math.floor(Math.sqrt(n));
            System.out.println("Clases: "+nclases);
            double rango=data.get(n-1)-data.get(0);
            double ancho=rango/nclases;
            double li=data.get(0),ls=li+ancho;
            double acumf=0;
            double mini=data.get(0);
            double maxks=-1000;
            int index = 0;
            while(li<=data.get(n-1))
            {
                double f=getFrecuencia(data,li,ls);
                System.out.print("Clase  ("+li+ " : "+ls+") ");
                double hi=f/n;
                acumf+=hi;
                double tf=intnormal(li,ls,prom,var);
                double d=Math.abs(tf-acumf);
                System.out.println("\t Frecuencia: "+f +" \tAcumulada: "+acumf+" \tFTeorica: "+tf+" \tProb: "+ hi +" \tDMax: " + d);
                fprac.add(acumf);
                fteo.add(f);
                index++;
                maxks=Math.max(maxks,d);
                li=ls;
                ls+=ancho;
            }
            System.out.println("DMaximo "+maxks);
            System.out.println("DTabla "+1.65);
            if(maxks>1.65)
                return false;
            return true;
        }
        public static double maximo(double a,double b) {
            if(a>b) return a;
            return b;
        }
        public static void main(String[] args) throws IOException {
            kolmogorovSmirnovNormal chart = new kolmogorovSmirnovNormal(
                    "Kolmogorov Smirnov" ,
                    "Normal Teorico vs Practico");
            chart.pack( );
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible( true );
        }

    private double varianza( List<Double> data) {
        double sum=0, ret=0;
        int n =data.size();
        double prom = promedio(data);
        for(int i=0 ; i<data.size(); i++)
        {
            double tem=data.get(i)-prom;
            sum+=Math.pow(tem, 2);
        }

        ret=Math.sqrt((sum)/n-1);
        return ret;
    }

    private  double promedio( List<Double> data) {
        double sum=0;
        int n = data.size();
        for(int i=0 ; i<data.size(); i++)
        {
            sum+=data.get(i);
        }
        sum=sum/n;
        return sum;
    }

}
