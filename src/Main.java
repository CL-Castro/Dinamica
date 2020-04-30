import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //private static double x1[]= {10,5,10,20,15,10,6,25,30,10,10,20,20,20,30,20,10};
    //private static double y[] = {72,63,65,68,78,64,56,59,60,85,67,61,65,79,61,52,65};
    //private static double x2[] = {3,1.5,1,2,4,2,0,1,2,1,1,1,0.5,2,0.5,1,1};
    //private static double x3[] = {1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1};
    //private static double matrizX[][] = {{10,3,1},{5,1.5,1},{10,1,1},{20,2,1},{15,4,1},{10,2,1},{6,0,0},{25,1,1},{30,2,1},{10,1,1},{10,1,1},{20,1,1},{20,0.5,1},{20,2,1},{30,0.5,1},{20,1,0},{10,1,1}};
    //static double ye []= new double[17];
    //static double e []= new double[17];
    //static double[] beta = {56.6,-0.31,2.91,11.39};
    //private static double[][] x = {{20},{27},{22},{24},{28},{33},{40},{42},{48},{52},{50},{47},{58},{67},{77},{73},{72},{80},{83},{80}};
    //private static double[][] y = {{7},{-5},{2},{4},{5},{7},{2},{6},{4},{-2},{-3},{11},{9},{10},{-4},{-1},{8},{3},{-3},{10}};
    public static void main (String [] args)
    {
        /*double s = 0;
        double promedio;
        double r1,r2;
        for(int i = 0;i < 17; i++)
        {
            ye[i] = 56.6-(0.31* x1[i]) + (2.91*x2[i]) + (11.39*x3[i]);
            s += y[i];
            e[i] = y[i] - ye[i];
            //System.out.println(ye[i]+" "+e[i]);
        }
        promedio = s/17;

        r1 = 1 - (sumatoriaCuadrado(e)/sumatoriaCuadrados(y,promedio));
        r2 = (sumatoriaCuadrados(ye,promedio))/ sumatoriaCuadrados(y,promedio);
        double SRC = sumatoriaCuadrado(e);
        double SEC = sumatoriaCuadrados(ye,promedio);
        double STC = sumatoriaCuadrados(y,promedio);
        Scanner read = new Scanner(System.in);
        int k =3;
        int m = 17;
        System.out.println("SRC: "+SRC);
        System.out.println("SEC: "+SEC);
        double F = (SEC/(k-1))/(SRC/(m-k));
        System.out.println("Variable de Fisher : "+F);
        double Ftabla = 3.74;
        //if(F>Ftabla)
            //System.out.println("Se rechaza la hipotesis H0");
        //else
            //System.out.println("Se acepta la hipotesis H0");
        System.out.println("--------------------------");
        double[][] matrizWasa = {{1,20},{2,27},{3,22},{4,24},{5,28},{6,33},{7,40},{8,42},{9,48},{10,52},{11,50},{12,47},{13,58},{14,67},{15,77},{16,73},{17,72},{18,80},{19,83},{20,80},{21,90}};
        double [][]C = invert(multiply(trans(matrizWasa),matrizWasa));
        double [] Var = Varianza(SRC/m-k , C);
        boolean [] est = verify(beta, Var, C, 2.145);
        /*for(int i=0;i<est.length;i++){
            if(est[i])
                System.out.println("La variable Beta"+(i+1)+" es explicativa");
            else
                System.out.println("La variable Beta"+(i+1)+" NO es explicativa");
        }*/
        // MODELO AR(3) COMPLETO
        /*double [] y = {20,27,22,24,28,33,40,42,48,52,50,47,58,67,77,73,72,80,83,80,90};
        // Delta ytest = gamma est y t-1
        double [][] yt = {{4},{5},{7},{2},{6},{4},{-2},{-3},{11},{9},{10},{-4},{-1},{8},{3},{-3},{10}};
        double [][] ytmenos = {{2,-5,7},{4,2,-5},{5,4,2},{7,5,4},{2,7,5},{6,2,7},{4,6,2},{-2,4,6},{-3,-2,4},{11,-3,-2},{9,11,-3},{10,9,11},{-4,10,9},{-1,-4,10},{8,-1,-4},{3,8,-1},{-3,3,8}};

        // yt = gamma * ytmenos
        double[][] gamma = findB(ytmenos,yt);
        ShowMatrix(ytmenos);
        ShowMatrix(yt);
        System.out.println("RESULTADOS a ->");
        ShowMatrix(gamma);
        // Yule Walker
        double[][] matrixA = {{gamma[0][0]-1,gamma[2][0],0},{gamma[2][0]+gamma[0][0],-1,0},{gamma[0][0],gamma[1][0],1}};
        double[][] matrixARes = {{-gamma[0][0]},{-gamma[1][0]},{-gamma[2][0]}};
        double[][] gammaDeVeras = findB(matrixA,matrixARes);
        ShowMatrix(matrixA);
        ShowMatrix(matrixARes);
        System.out.println("RESULTADO GAMMAS MODELO AR3");
        ShowMatrix(gammaDeVeras);
        System.out.println("Y");
        for(int i = 0;i<y.length;i++){
            System.out.println(y[i]);
        }
        double[] yp = {10,-3,3};
        double ultimo = y[y.length-1];
        for(int i = 0;i<100;i++){
            double ypron = gammaDeVeras[0][0] * yp[0] + gammaDeVeras[1][0] * yp[1] + gammaDeVeras[2][0] * yp[2];
            yp[2] = yp[1];
            yp[1] = yp[0];
            yp[0] = ypron;
            System.out.println("y"+(22+i)+" = "+ (ultimo+ypron));
            ultimo = (ultimo+ypron);
        }*/
        //FINAL MODELO AR(3), TODO: INTEGRAR LA SERIE
        //boolean [] est = verify(gamma, Var, C, 2.145);
        /*for(int i=0;i<est.length;i++){
            if(est[i])
                System.out.println("La variable Beta"+(i+1)+" es explicativa");
            else
                System.out.println("La variable Beta"+(i+1)+" NO es explicativa");
        }*/
        System.out.println( " Y = [y1 y2 y3]");
        System.out.println( " X = [x1 x2 x3 x4 x5]");
        double[][] x = {{10,3.06,1.34,8.48,28},{15,3.19,1.44,9.16,35},{20,3.3,1.54,9.9,37},{25,3.4,1.71,11.02,36},{34,3.48,1.89,11.64,29}
        ,{27,3.6,1.99,12.73,47},{28,3.68,2.22,13.88,50},{40,3.72,2.43,14.5,35},{37,3.92,2.43,15.47,33},{36,4.15,2.31,16.61,40},
                {32,4.35,2.39,17.4,38},{30,4.37,2.63,18.83,37},{36,4.59,2.69,20.62,56},{41,5.23,3.35,23.76,88},{45,6.04,2.81,26.52,62},
                {48,6.36,3.38,27.45,51},{49,7.04,3.14,30.28,29},{53,7.81,3.14,25.4,22},{57,8.09,6.19,28.84,38},
                {66,9.24,6.69,34.36,41}};
        double[][] y2={{359.27,102.96,578.49},{415.76,114.38,650.86},{435.11,118.23,684.87},{440.17,120.45,680.47},{410.66,116.25,642.19},{530.33,140.27,787.41},{557.15,143.84,818.06},{472.8,128.2,712.16},{471.76,126.65,722.23},{538.3,141.05,811.44},{547.76,143.71,816.36},{539,142.37,807.78},{677.6,173.13,983.53}
                ,{943.85,223.21,1292.99},{893.42,198.64,1179.64},{871,191.89,1134.78},{793.93,181.27,1053.16},{850.36,180.56,1085.91},{967.42,208.24,1246.99},{1102.61,235.43,1401.94}};
        System.out.println("Matriz X");
        ShowMatrix(x);
        System.out.println("Matriz Y");
        ShowMatrix(y2);
        double [][] pi = multiply(invert(multiply(trans(x),x)),multiply(trans(x),y2));
        System.out.println("Matriz Pi estimado");
        ShowMatrix(pi);
        System.out.println("Matriz Y estimado");
        double [][] yest = multiply(x,pi);
        double [][] ymod = new double[y2.length][1];
        double [][] xmod = new double[y2.length][6];
        for(int i=0;i<y2.length;i++){
            ymod[i][0] =  y2[i][0];
            xmod[i][0] = 1;
            xmod[i][1] = x[i][0];
            xmod[i][2] = x[i][2];
            xmod[i][3] = x[i][3];
            xmod[i][4] = yest[i][1];
            xmod[i][5] = yest[i][2];
        }
        double[][] mody1 = findB(xmod,ymod);
        System.out.println("Primer MODELO");
        ShowMatrix(mody1);
        double [][] ymod2 = new double[y2.length][1];
        double [][] xmod2 = new double[y2.length][3];
        for(int i=0;i<y2.length;i++){
            ymod2[i][0] =  y2[i][1];
            xmod2[i][0] = 1;
            xmod2[i][1] = x[i][4];
            xmod2[i][2] = yest[i][0];
        }
        double[][] mody2 = findB(xmod2,ymod2);
        System.out.println("Segundo MODELO");
        ShowMatrix(mody2);
        double [][] ymod3 = new double[y2.length][1];
        double [][] xmod3 = new double[y2.length][5];
        for(int i=0;i<y2.length;i++){
            ymod3[i][0] =  y2[i][2];
            xmod3[i][0] = 1;
            xmod3[i][1] = x[i][4];
            xmod3[i][2] = x[i][1];
            xmod3[i][3] = yest[i][1];
            xmod3[i][4] = yest[i][0];
        }
        double[][] mody3 = findB(xmod3,ymod3);
        System.out.println("Tercer Modelo");
        ShowMatrix(mody3);
        double [][] ymod4 = new double[y2.length][1];
        double [][] xmod4 = new double[y2.length][2];
        for(int i=0;i<y2.length;i++){
            ymod4[i][0] =  x[i][2];
            xmod4[i][0] = 1;
            xmod4[i][1] = x[i][1];
        }
        double[][] mody4 = findB(xmod4,ymod4);
        System.out.println("Modelo X3");
        ShowMatrix(mody4);
        double [][] ymod5 = new double[y2.length][1];
        double [][] xmod5 = new double[y2.length][2];
        for(int i=0;i<y2.length;i++){
            ymod5[i][0] =  x[i][4];
            xmod5[i][0] = 1;
            xmod5[i][1] = x[i][3];
        }
        double[][] mody5 = findB(xmod5,ymod5);
        System.out.println("Modelo X5");
        ShowMatrix(mody5);
        double [][] ymod6 = new double[y2.length][1];
        double [][] xmod6 = new double[y2.length][3];
        for(int i=0;i<y2.length;i++){
            ymod6[i][0] = x[i][3];
            xmod6[i][0] = 1;
            xmod6[i][1] = x[i][0];
            xmod6[i][2] = x[i][2];
        }
        double[][] mody6 = findB(xmod6,ymod6);
        System.out.println("Modelo X4");
        ShowMatrix(mody6);
    }

    public static double[][] findB(double[][] x, double[][]y){
        return multiply(multiply(invert(multiply(trans(x),x)),trans(x)),y);
    }
    /*public static boolean[] verify(double[] beta, double[] S, double[][] C, double TEst){
        boolean[] estimacion = new boolean[beta.length];
        double t;
        for (int i =0;i<matrizX.length;i++){
            for (int j =0;j<matrizX[0].length;j++){
                if (i == j) {
                    t = beta[i]/(S[i]*Math.sqrt(C[i][j]));
                    if(Math.abs(t) > TEst)
                        estimacion[i] = false;
                    else
                        estimacion[i] = true;
                }
            }
        }
        return estimacion;
    }*/
    /*public static double[] Varianza(double k,double[][] matrix){
        double [] result = new double[matrix.length];
        for (int i =0;i<matrizX.length;i++){
            for (int j =0;j<matrizX[0].length;j++){
                if(i==j){
                    result[i] = matrix[i][i] * k;
                }
            }
        }
        return result;
    }*/
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
