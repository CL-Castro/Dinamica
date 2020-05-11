package Experiencia1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EjerciciosParte1 extends JFrame{
    public static void main(String[] args)
    {
        //EJERCICIO 1
        /*Ej1(100);
        Ej1(1000);
        Ej1(10000);
        */
        //EJERCICIO 2
        /*Ej2(100);
        Ej2(1000);
        Ej2(10000);
        */
        //Ejercicio 3
        //Ej3(50000);
        //Ejercicio 4
        //Ejercicio 5
        //Ej5();

        //Ejercicio 6
//        Cada año una tienda de computadoras vende un promedio de 1 000 cajas de disquetes.
//        La demanda anual se distribuye normalmente con desviación estándar de 40.8 cajas.
//        La tienda pide disquetes a un distribuidor regional. Cada pedido se efectúa en 2
//        semanas. El costo por cada pedido es de $50 y el costo de almacenar una caja de
//        disquetes en existencia es de $10 por año. El costo por unidad de escasez, debido a
//        que se pierde la clientela y se incurre en el costo de hacer un pedido especial, se supone
//        que es de $20. Si el nivel de servicio es del 95%, determinar la cantidad optima a ordenar y punto de reorden:
        //Ej6();
        //Ejercicio 7
        //Ej7();
        //Ejercicio 8
        //Ej8();
        //TODO: Ejercicio 9 Imposible
        //Ej9();
        //Ejercicio 10
        //Ej10();

        //Ejercicio 11
        //Ej11();
        //Ejercicio 12
        //Ej12();
        //Ejercicio 13
        //Ej13();
        //Ejercicio 14
        //Ej14();

        //Ejercicio
        //Ej15(100,1,0.6,10);
    }

    private static void Ej1(int n)
    {
        ArrayList<Double> N = new ArrayList<Double>();
        double seed;
        double minimo=99999;
        for(int j=0;j<n;j++)
        {
            for(int i=1;i<=n;i++)
            {
                minimo = 99999;
                double sum = 0;
                int contador = 1;
                while(sum<1)
                {
                    seed = Math.random();
                    sum+=seed;
                    contador++;
                }
                minimo = Math.min(minimo,contador);
            }
            N.add(minimo);
        }
        double s=0;
        for (int i=0;i<n;i++)
            s+=N.get(i);
        System.out.println("Esperanza para "+n+" Valores de N: "+ s/n);
    }

    private static void Ej2(int n)
    {
        ArrayList<Double> N = new ArrayList<Double>();
        double seed;
        double minimo=0;
        for(int j=0;j<=n;j++)
        {
            for(int i=1;i<n;i++)
            {
                minimo = 0;
                double prod = 1;
                int contador = 1;
                while(prod>=0.04978706836)
                {
                    seed = Math.random();
                    prod*=seed;
                    contador++;
                }
                minimo = Math.max(minimo,contador);
            }
            N.add(minimo);
        }
        double s=0;
        for (int i=0;i<n;i++)
            s+=N.get(i);
        System.out.println("Esperanza para "+n+" Valores de N: "+ s/n);
    }

    private static void Ej3(int n)
    {
        for (int i = 0;i<n;i++)
        {
            int A;
            double seed;
            seed=Math.random()*(6+1)-1;
            seed=Math.floor(seed);
            seed=(1+seed);
            A=(int)seed;
            System.out.print("Numero de dado:"+A+" ");
            if(i%10==0)
            {
                System.out.println("");
            }
        }
    }
    private static void Ej5()
    {
        ArrayList<Double> X = new ArrayList<>();
        ArrayList<Double> Y = new ArrayList<>();
        double []x =new double[10000];
        double []y =new double[10000];
        for(int i=0;i<10000;i++)
        {
            double rand2 = (Math.random() * 2) + 0;
            double rand1 = Math.random();
            //System.out.println("Aleatorios RAND2: "+rand2+" RAND1: "+rand1);
            double u1 = 0 + rand2 * ((2*Math.PI)-0);
            double u2 = 0.5 * Math.log(rand1);
            //System.out.println("Aleatorios U1: "+u1+" U2: "+u2);
            double r = -1 * Math.log(rand1);
            double tetta = 2 * Math.PI * u2;
            //System.out.println("Aleatorios R: "+r+" T: "+tetta);
            x[i] = Math.sqrt(2*r) * Math.cos(tetta);
            y[i] = Math.sqrt(2*r) * Math.sin(tetta);
            X.add(Math.sqrt(2*r) * Math.cos(tetta));
            Y.add(Math.sqrt(2*r) * Math.sin(tetta));
            System.out.println("Aleatorio X: "+x[i]+" Y: "+y[i]);
        }
        Collections.sort(X);
        Collections.sort(Y);
        for (int i=0;i<x.length;i++)
        {
            x[i] = X.get(i);
            y[i] = Y.get(i);
        }
    }

    private static void Ej6()
    {
        System.out.println("sqrt(2*k*A/h) * sqrt((p+h)/p) y una distribucion normal al 95% de confianza");
        double A = 1000;
        double h = 10;
        double alfa = 0.95;
        double om = 40.8;
        double k = 50;
        double p = 20;
        double q = Math.sqrt(2*k*A/h) * Math.sqrt((p+h)/p);
        double distNormal = 1.65;
        double reorden = qnorm(distNormal,A,om);
        System.out.println("Pedir Q = "+q+" cajas cuando el inventario descienda a R: "+reorden+" cajas.");
    }

    private static double qnorm(double alfa, double prom, double sigma)
    {
        double numDiscreto;
        double sumatoria;
        double n = 100;
        double index;
        double deltax = 6 * sigma / n;
        double dx = deltax;
        sumatoria = 0;
        index = 1;
        while(sumatoria < alfa)
        {
            dx = index * deltax;
            sumatoria += (1/Math.sqrt(2*Math.PI*Math.pow(sigma,2)))*
                    (Math.exp(-1*Math.pow((dx - prom)/sigma,2)))*dx;
            index += 1.0;
        }
        //System.out.println(F[i] + " < "+sumatoria);
        numDiscreto = dx;
        return numDiscreto;
    }

    private static void Ej7()
    {
        double [] F = {0.15,0.15,0.15,0.25,0.25,0.05};
        int n = 1000;
        double[] probAcum = new double[6];
        for(int i=0;i<6;i++)
        {
            for(int j=i;j>=0;j--)
            {
                probAcum[i] += F[j];
            }
        }
        System.out.println("INCISO A");
        ArrayList<Integer> numeros = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            double z = Math.random();
            if (z<probAcum[0])
                numeros.add(1);
            else if (z<probAcum[1])
                numeros.add(2);
            else if (z<probAcum[2])
                numeros.add(3);
            else if (z<probAcum[3])
                numeros.add(4);
            else if (z<probAcum[4])
                numeros.add(5);
            else if (z<probAcum[5])
                numeros.add(6);
        }
        //ShowList(numeros);
        int a = 0,b = 0,c = 0,d = 0,e = 0, f =0;
        double suma = 0;
        System.out.println("Cantidad "+ numeros.size());
        for (int numero : numeros) {
            suma+=numero;
            if (numero == 1)
                a++;
            else if (numero == 2)
                b++;
            else if (numero == 3)
                c++;
            else if (numero == 4)
                d++;
            else if (numero == 5)
                e++;
            else if (numero == 6)
                f++;
        }
        System.out.println("Frecuencia tipo 1: "+a+"\nFrecuencia tipo 2: "+b+"\nFrecuencia tipo 3: "+c+"\nFrecuencia tipo 4: "+d+"\nFrecuencia tipo 5: "+e+"\nFrecuencia tipo 6: "+f);
        double lamda = suma/numeros.size();
        System.out.println("Media lamda: "+lamda);
        ArrayList<Integer> poisson = new ArrayList<>();
        double z;
        double x;
        double sum;
        int k;
        for(int i=1;i<n;i++)
        {
            z = Math.random();
            x=0;
            k=0;
            while(z>x)
            {
                sum=(Math.pow(lamda,k))*Math.exp(lamda*-1);
                x+=sum/factorial(k);
                k++;
            }
            poisson.add(k);
        }
        ShowList(poisson);
    }

    private static double factorial (double numero) {
        if (numero==0)
            return 1;
        else
            return numero * factorial(numero-1);
    }
    private static void ShowMatrix(double [][] matrizX){
        for (int i =0;i<matrizX.length;i++){
            for (int j =0;j<matrizX[0].length;j++){
                System.out.print(matrizX[i][j]+" ");
            }
            System.out.println("");
        }
    }

    private static void ShowList(ArrayList<Integer> list)
    {
        int d=0;
        for (Integer i:
             list) {
            System.out.print(i+" ");
            d++;
            if(d%10==0)
                System.out.println("");
        }
    }

    private static void Ej8()
    {
        //Periodo de 7 dias = 10080 min
        int n = 10080;
        ArrayList<double[]> trabajo = new ArrayList<>();
        int max = 0;
        boolean dia = true;
        //Tiempos hasta fallas sigue distribucion uniforme en [33 = 1980 min, 43 = 2580min]
        double falla = uniforme(1980,2580)+0;
        double reparacion;
        if(dia)
            reparacion = uniforme(180,420)+0;
        else
            reparacion = uniforme(660,1020)+0;
        double llegada = uniforme(4,6) +0;
        for(double i=0; i<=n;i+=1)
        {
            //Pasadas 12 horas se cambia el estado del dia
            if(i%720 == 0)
                dia= !dia;
            //Si el tiempo supera el minuto de la primera llegada, se genera otra llegada
            if(llegada <= i)
            {
                //Trabajo contiene el estado (En cola o procesamiento), el tiempo de procesamiento generado
                //y el tiempo de procesamiento acumulado
                trabajo.add(new double[]{0, procesamiento(), atencion(trabajo, i)});
                llegada = uniforme(4,6)+i;
            }
            //Se verifica si el tiempo de la primera falla generada ya sucedio.
            if(falla > i)
            {
                if(trabajo.size()>0)
                {
                    //Si el tiempo de procesamiento acumulado es menor que el tiempo actual
                    //y el trabajo se encuentra en cola pasamos a procesar
                    if(trabajo.get(0)[2]<=i && trabajo.get(0)[0]==0)
                    {
                        trabajo.get(0)[0]=1.0;
                    }
                    //Si el trabajo acumulado con el proceso actual son menores que el minuto actual
                    // y el estado es de procesamiento se remueve el ultimo trabajo que ya fue procesado
                    if(trabajo.get(0)[2]+trabajo.get(0)[1]<=i &&
                            trabajo.get(0)[0]==1)
                    {
                        trabajo.remove(0);
                    }
                }
                //Generamos la siguiente reparacion
                if(dia)
                    reparacion = uniforme(180,420)+i;
                else
                    reparacion = uniforme(660,1020)+i;

                int sum_cola=0;
                if(trabajo.size()>0)
                {
                    for(double[] trab: trabajo)
                    {
                        //Si el estado es en cola, se suman trabajos
                        if(trab[0]==0)
                            sum_cola++;
                    }
                    if(sum_cola>0)
                        System.out.println("En el tiempo "+i+" existen " +sum_cola+" trabajos en cola");
                }
                //Recuperamos el numero maximo de trabajos en cola registrado
                max=Math.max(max, sum_cola);
                String []estados = {"cola","proceso"};
                //if(trabajo.size()>0)
                //{
                    //for(double[] trab: trabajo)
                        //System.out.println("Estado: en "+estados[(int) trab[0]]+ " Tiempo de proceso "+trab[1]+ " Momento de atencion "+trab[2]);
                //}
            }
            else //El sistema fallo y debe ser reparado
            {
                if(reparacion-i<=0)
                {
                    System.out.println("Inicio una reparacion que duro "+(i-falla)+"minutos \n Se retoma el servicio en el minuto "+i);
                    falla =uniforme(1980,2580)+i;
                    if(dia)
                        reparacion = uniforme(180,420)+i;
                    else
                        reparacion = uniforme(660,1020)+i;
                }
            }
        }
        System.out.println("El maximo de colas es de " + max);
    }

    private static double atencion(ArrayList<double[]> trabajo, double i) {
        if(trabajo.size()>0) {
            double[] ultimo = trabajo.get(trabajo.size() - 1);
            return ultimo[1]+ultimo[2];
        }
        else
            return i;
    }
    private static double procesamiento() {
        double [] acumulada = {0.4,0.7,0.9,1};
        double [] ret = {1.0,2.0,5.0,10.0};
        double x = Math.random();
        for(int i =0; i<acumulada.length ; i++)
        {
            if(x<acumulada[i])
                return ret[i];
        }
        return ret[0];
    }


    private static double  uniforme (float a,  float b)
    {
        double u= Math.random();
        double x=a+(b-a)*u;
        return x;
    }

    private static void Ej10()
    {
        ArrayList<Double> TipoA = new ArrayList<>();
        ArrayList<Double> TipoB = new ArrayList<>();
        double[] VelB = {100,124,50,123,80,75,55,60,70};
        double mediaB;
        double sumb=0;
        double suma=0;
        for (double x:VelB)
        {
            sumb += x;
        }
        mediaB = sumb/ VelB.length;
        double lamda = 1/mediaB;
        for(int i = 0;i<1000;i++)
        {
            TipoA.add(45 + Math.random() * (121-45));
            TipoB.add(- mediaB * Math.log(Math.random()));
            System.out.println(TipoA.get(i)+" "+TipoB.get(i));
        }
        sumb = 0;
        for (double x:TipoB)
        {
            sumb += x;
        }
        for (double x:TipoA)
        {
            suma += x;
        }
        System.out.println("Media A: "+(suma/TipoA.size())+" Media B: "+(sumb/TipoB.size()));
    }

    private static void Ej11()
    {
        int[] tipo = {1,2,3,4};
        double p[] = {0.15,0.45,0.30,0.10};
        double[] F = new double[1000];
        int[] numeros = new int[1000];
        double[][] prob = new double[4][2];
        for (int i = 0;i<F.length;i++)
        {
            double sum = 0;
            int index = 0;
            F[i] = Math.random();
            while(sum<F[i])
            {
                sum+=p[index];
                index++;
            }
            numeros[i] = tipo[index-1];
        }
        int a = 0,b = 0,c = 0,d = 0;
        for (double numero : numeros) {
            System.out.println(numero);
            if (numero == 1)
                a++;
            else if (numero == 2)
                b++;
            else if (numero == 3)
                c++;
            else if (numero == 4)
                d++;
        }
        System.out.println("Frecuencia tipo 1: "+a+"\nFrecuencia tipo 2: "+b+"\nFrecuencia tipo 3: "+c+"\nFrecuencia tipo 4: "+d);
    }
    public static double combinacion(double n, double x)
    {
        return factorial(n)/(factorial(x)*factorial(n-x));
    }

    private static void Ej12()
    {
        //Vector con 3 valores {Media, Varianza, Desviacion Tipica)
        double [] ValoresA = nodeA();
        double [] ValoresB = nodeExp(100);
        double [] ValoresC = nodeC();
        double [] ValoresD = nodeD();
        double [] ValoresE = nodeExp(150);
        String [][] rutas = {{"A>B>C>C>A","0"},{"A>C>A>C>A","0"},{"A>B>D>C>C>A","0"}
                ,{"A>B>D>B>B>A","0"},{"A>B>D>B>C>A","0"},{"A>C>D>B>C>A","0"}
                ,{"A>C>D>C>C>A","0"},{"A>C>D>B>B>A","0"},{"A>C>A>B>C>A","0"},{"A>C>A>B>B>A","0"}};
        double min = Double.MAX_VALUE;
        for (String[] nodos:rutas
             ) {
            String [] nodito = nodos[0].split(">");
            double sum = 0;
            for(int i=0;i<nodito.length;i++)
            {
                if(nodito[i].equals("A"))
                    sum+=ValoresA[0];
                else if(nodito[i].equals("B"))
                    sum+=ValoresB[0];
                else if(nodito[i].equals("C"))
                    sum+=ValoresC[0];
                else if(nodito[i].equals("D"))
                    sum+=ValoresD[0];
                else if(nodito[i].equals("E"))
                    sum+=ValoresE[0];
            }
            min = Math.min(min,sum);
            nodos[1] = String.valueOf(sum);
        }
        for(int i=0;i<rutas.length;i++)
        {
            System.out.println("Costo: "+rutas[i][0]+" = "+ rutas[i][1]);
        }
        System.out.println("El menor costo es: "+min);
    }

    private static void Ej13()
    {
        //Vector con 3 valores {Media, Varianza, Desviacion Tipica)
        double [] ValoresA = nodeA();
        double [] ValoresB = nodeExp(100);
        double [] ValoresC = nodeC();
        double [] ValoresD = nodeD();
        double [] ValoresE = nodeExp(150);
        String [][] rutasA = {{"A>B2>A>B>A","0"},{"A>B2>A>B>D3>A","0"},{"A>B2>C>D3>A","0"}};
        String [][] rutasB = {{"A>B2>D2>C","0"},{"A>C>E>A>C","0"},{"A>A>B3>A>C","0"}};
        String [][] rutasC = {{"B>B2>C>A3>D>B2","0"},{"B>B2>A3>D>B2","0"},{"B>B2>C>D>B2","0"}};
        double min = Double.MAX_VALUE;
        System.out.println("INCISO A");
        for (String[] nodos:rutasA
        ) {
            String [] nodito = nodos[0].split(">");
            double sum = 0;
            for(int i=0;i<nodito.length;i++)
            {
                if(nodito[i].equals("A"))
                    sum+=ValoresA[0];
                else if(nodito[i].equals("B"))
                    sum+=ValoresB[0];
                else if(nodito[i].equals("C"))
                    sum+=ValoresC[0];
                else if(nodito[i].equals("D"))
                    sum+=ValoresD[0];
                else if(nodito[i].equals("E"))
                    sum+=ValoresE[0];
                else if(nodito[i].equals("B2"))
                    sum+=Math.pow(ValoresB[0],2);
                else if(nodito[i].equals("D3"))
                    sum+=Math.pow(ValoresD[0],3);
            }
            min = Math.min(min,sum);
            nodos[1] = String.valueOf(sum);
        }
        for(int i=0;i<rutasA.length;i++)
        {
            System.out.println("Costo: "+rutasA[i][0]+" = "+ rutasA[i][1]);
        }
        System.out.println("El menor costo es para el inciso A: "+min);
        min = Double.MAX_VALUE;
        System.out.println("INCISO B");
        for (String[] nodos:rutasB
        ) {
            String [] nodito = nodos[0].split(">");
            double sum = 0;
            for(int i=0;i<nodito.length;i++)
            {
                if(nodito[i].equals("A"))
                    sum+=ValoresA[0];
                else if(nodito[i].equals("B"))
                    sum+=ValoresB[0];
                else if(nodito[i].equals("C"))
                    sum+=ValoresC[0];
                else if(nodito[i].equals("D"))
                    sum+=ValoresD[0];
                else if(nodito[i].equals("E"))
                    sum+=ValoresE[0];
                else if(nodito[i].equals("B2"))
                    sum+=Math.pow(ValoresB[0],2);
                else if(nodito[i].equals("B3"))
                    sum+=Math.pow(ValoresB[0],3);
                else if(nodito[i].equals("D2"))
                    sum+=Math.pow(ValoresD[0],2);
            }
            min = Math.min(min,sum);
            nodos[1] = String.valueOf(sum);
        }
        for(int i=0;i<rutasB.length;i++)
        {
            System.out.println("Costo: "+rutasB[i][0]+" = "+ rutasB[i][1]);
        }
        System.out.println("El menor costo es del inciso B: "+min);
        min = Double.MAX_VALUE;
        System.out.println("INCISO C");
        for (String[] nodos:rutasC
        ) {
            String [] nodito = nodos[0].split(">");
            double sum = 0;
            for(int i=0;i<nodito.length;i++)
            {
                if(nodito[i].equals("A"))
                    sum+=ValoresA[0];
                else if(nodito[i].equals("B"))
                    sum+=ValoresB[0];
                else if(nodito[i].equals("C"))
                    sum+=ValoresC[0];
                else if(nodito[i].equals("D"))
                    sum+=ValoresD[0];
                else if(nodito[i].equals("E"))
                    sum+=ValoresE[0];
                else if(nodito[i].equals("B2"))
                    sum+=Math.pow(ValoresB[0],2);
                else if(nodito[i].equals("A3"))
                    sum+=Math.pow(ValoresA[0],3);
            }
            min = Math.min(min,sum);
            nodos[1] = String.valueOf(sum);
        }
        for(int i=0;i<rutasC.length;i++)
        {
            System.out.println("Costo: "+rutasC[i][0]+" = "+ rutasC[i][1]);
        }
        System.out.println("El menor costo es del inciso C: "+min);
    }

    private static double[] nodeA()
    {
        double[] prob = {10f/85f,17f/85f,14f/85f,35f/85f,9f/85f};
        double[][] acum = {{0,100},{0,200},{0,300},{0,400},{0,500}};
        double[] res = new double[3];
        double[] numeros = new double[5000];
        for(int i =0;i<5;i++)
        {
            for(int j=0;j<=i;j++)
            {
                acum[i][0] += prob[j];
            }
        }
        for(int i =0;i<5000;i++)
        {
            double seed = Math.random();
            double sum = 0;
            int index = 0;
            while(sum<seed)
            {
                sum = acum[index][0];
                index++;
            }
            numeros[i] = index*100;
        }
        int a = 0,b = 0,c = 0,d = 0, e = 0;
        double s=0;
        for (double numero : numeros) {
            s+=numero;
            if (numero == 100)
                a++;
            else if (numero == 200)
                b++;
            else if (numero == 300)
                c++;
            else if (numero == 400)
                d++;
            else if (numero == 500)
                e++;
        }
        //System.out.println("Frecuencia tipo 1: "+a+"\nFrecuencia tipo 2: "+b+"\nFrecuencia tipo 3: "+c+"\nFrecuencia tipo 4: "+d+"\nFrecuencia tipo 5: "+e);
        double sumatoria = a*100+b*200+c*300+d*400+e*500;
        double sumatoriacuadrada = a*100*100+b*200*200+c*300*300+d*400*400+e*500*500;
        res[0] = sumatoria/numeros.length;
        res[1] = (sumatoriacuadrada/numeros.length) - Math.pow(res[0],2);
        res[2] = Math.sqrt(res[1]);
        return res;
    }

    private static double[] nodeExp(double promedio)
    {
        double [] res = new double[3];
        res[0] = promedio;
        double lamda = 1/res[0];
        double [] numeros = new double[5000];
        for(int i=0;i<5000;i++)
        {
            double seed = Math.random();
            numeros[i] = -1 * Math.log(seed)/lamda;
        }
        res[1] = 1/Math.pow(lamda,2);
        res[2] = Math.sqrt(res[1]);
        return res;
    }

    private static double[] nodeC()
    {
        double [] res = new double[3];
        double [] numeros = new double[5000];
        for(int i=0;i<5000;i++)
        {
            double seed= Math.random();
            numeros[i] = 52+(435-52)*seed;
        }
        res[0] = (435f+52f)/2f;
        res[1] = Math.pow(435f+52f,2)/12f;
        res[2] = Math.sqrt(res[1]);
        return res;
    }

    private static double[] nodeD()
    {
        double [] res = new double[3];
        double [] numeros = new double[5000];
        double [] var = new double[5000];
        double s = 0;
        for(int i=0;i<5000;i++)
        {
            double seed= Math.random();
            numeros[i] = (Math.pow(seed,0.185) - Math.pow(1-seed,0.185))/0.1975;
            s+=numeros[i];
        }
        res[0] = s/numeros.length;
        double sumVar= 0;
        for(int i=0;i<5000;i++)
        {
            var[i] = res[0]-numeros[i];
            sumVar+=Math.pow(var[i],2);
        }
        res[1] = sumVar/numeros.length;
        res[2] = Math.sqrt(res[1]);
        return res;
    }

    private static void Ej14()
    {
        int tiempo = 100;
        int n = 5, s = 3;
        //Cada maquina tiene un tiempo de funcionamiento y de repacion
        //Tras terminar su tiempo de funcionamiento entra en repacion
        ArrayList<double[]> maqFun = new ArrayList<>();
        ArrayList<double[]> maqRep = new ArrayList<>();
        ArrayList<double[]> EnEspera = new ArrayList<>();
        ArrayList<Double> Tfalla = new ArrayList<>();
        for(int y = 0;y<tiempo;y++)
        {
            maqFun.clear();
            maqRep.clear();
            EnEspera.clear();
            for(int j=0;j<n;j++)
            {
                double seed = Math.random();
                maqFun.add(new double[] {1 - Math.exp(-seed),1 - Math.exp(-3*seed)});
                System.out.println("Maquina Funcional, Hasta Falla : "+maqFun.get(j)[0]+" Reparacion: "+maqFun.get(j)[1]);
            }
            for(int j=0;j<s;j++)
            {
                double seed = Math.random();
                maqRep.add(new double[] {1 - Math.exp(-seed),1 - Math.exp(-3*seed)});
                System.out.println("Maquina de repuesto, Hasta Falla : "+maqRep.get(j)[0]+" Reparacion: "+maqRep.get(j)[1]);
            }
            for(double i=0;i<tiempo;i+=0.1)
            {
                boolean exploto = false;
                System.out.println("Funcionales: "+maqFun.size()+" Repuesto: "+maqRep.size()+" En Espera "+EnEspera.size()+" TIEMPO: "+(i*10));
                for(int j=0;j<maqFun.size();j++)
                {
                    double[] maquina = maqFun.get(j);
                    if(i>=maquina[0] && maqFun.size()>=5)
                    {
                        EnEspera.add(maquina);
                        maqFun.remove(j);
                        if(maqRep.size()>0)
                        {
                            maqFun.add(maqRep.get(0));
                            maqRep.remove(0);
                        }
                        else
                        {
                            System.out.println("Funcionales: "+maqFun.size()+" Repuesto: "+maqRep.size()+" En Espera "+EnEspera.size()+" TIEMPO: "+(i*10));
                            System.out.println("El sistema fallo en el tiempo: "+ (i*10));
                            Tfalla.add(i*10);
                            exploto = true;
                            break;
                        }
                    }
                }
                if(EnEspera.size()>0)
                {
                    EnEspera.get(0)[1]-=0.1;
                    if(EnEspera.get(0)[1]<=0.1)
                    {
                        double seed = Math.random();
                        if(maqFun.size()<5)
                            maqFun.add(new double[]{(1 - Math.exp(-seed))+i,(1 - Math.exp(-3*seed))+i});
                        else
                            maqRep.add(new double[]{(1 - Math.exp(-seed))+i,(1 - Math.exp(-3*seed))+i});
                        EnEspera.remove(0);
                    }
                }
                if(exploto)
                    break;
            }
        }
        double sum = 0;
        for (double i:Tfalla
             ) {
            sum+=i;
        }
        System.out.println("E[T] = "+sum/Tfalla.size());
    }

    private static void Ej15(int pob, int casos, double p, int periodo)
    {
        ArrayList<int[]> poblacion = new ArrayList<>();
        //Cada individuo puede ser Susceptible (0),
        //Infectado(1) o Inmune(2), Ademas se tiene el periodo
        //que tarda en ser inmune
        for(int i=0;i<pob;i++)
        {
            poblacion.add(new int[]{0,periodo});
        }
        for(int i=0;i<casos;i++)
        {
            int caso =(int) (Math.random()*pob)+1;
            poblacion.get(caso)[0] = 1;
        }
        boolean inmunidad = false;
        int tiempo = 1;
        ArrayList<Integer> Listenf = new ArrayList<>(), Listsus = new ArrayList<>(), Listinm = new ArrayList<>();
        while(!inmunidad)
        {
            int enf = 0, sus = 0, inm = 0;
            for (int[] gente:poblacion
            ) {
                if(gente[0] == 0) {
                    sus++;
                }
                else if(gente[0] == 1) {
                    enf++;
                }
                else if(gente[0] == 2) {
                    inm++;
                }
                int caso =(int) (Math.random()*pob-1)+1;
                if(gente[0]==1)
                {
                    if(gente[1]<=0)
                    {
                        gente[0] = 2;
                    }else {
                        gente[1]--;
                    }
                }
                if(poblacion.get(caso)[0]==1 && gente[0]!=2)
                {
                    double F = Math.random();
                    if(F<=p)
                    {
                        gente[0] = 1;
                    }
                }
            }
            if(inm == pob || enf == 0)
                inmunidad = true;
            System.out.println("En el periodo: "+ tiempo+" se tiene Enfermos: "+ enf+" Susceptibles: "+sus+" Inmunes: "+inm);
            tiempo++;
            Listsus.add(sus);
            Listenf.add(enf);
            Listinm.add(inm);
        }
        System.out.println("La poblacion tomo "+tiempo+" periodos en conseguir inmunidad total");
        double [] enfermos = new double[Listenf.size()];
        for (int i = 0;i<Listenf.size();i++)
        {
            enfermos[i] = Listenf.get(i);
        }
        GenerarGrafica(enfermos,"Enfermos");
        double [] susceptibles = new double[Listsus.size()];
        for (int i = 0;i<Listsus.size();i++)
        {
            susceptibles[i] = Listsus.get(i);
        }
        GenerarGrafica(susceptibles,"Susceptibles");
        double [] inmunes = new double[Listinm.size()];
        for (int i = 0;i<Listinm.size();i++)
        {
            inmunes[i] = Listinm.get(i);
        }
        GenerarGrafica(inmunes,"Inmunes");
    }
    public static void GenerarGrafica(double numeros[],String titulo){
        EjerciciosParte1 example = new EjerciciosParte1(numeros,titulo);
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
    }

    public EjerciciosParte1(double[] numeros, String title) {
        super(title);
        // Create dataset
        XYDataset dataset = createDataset(numeros,title);

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Personas",
                "Tiempo",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(double[] numeros, String title) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("Evolucion "+title);
        for(int i=1;i<=numeros.length;i++)
        {
            series.add(i, numeros[i-1]);
        }
        //Add series to dataset
        dataset.addSeries(series);

        return dataset;
    }
}
