import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static double x1[]= {10,5,10,20,15,10,6,25,30,10,10,20,20,20,30,20,10};
    private static double y[] = {72,63,65,68,78,64,56,59,60,85,67,61,65,79,61,52,65};
    private static double x2[] = {3,1.5,1,2,4,2,0,1,2,1,1,1,0.5,2,0.5,1,1};
    private static double x3[] = {1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1};
    private static double matrizX[][] = {{10,3,1},{5,1.5,1},{10,1,1},{20,2,1},{15,4,1},{10,2,1},{6,0,0},{25,1,1},{30,2,1},{10,1,1},{10,1,1},{20,1,1},{20,0.5,1},{20,2,1},{30,0.5,1},{20,1,0},{10,1,1}};
    static double ye []= new double[17];
    static double e []= new double[17];
    public static void main (String [] args)
    {
        double s = 0;
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
        if(F>Ftabla)
            System.out.println("Se rechaza la hipotesis H0");
        else
            System.out.println("Se acepta la hipotesis H0");
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
