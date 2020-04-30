import java.util.Scanner;

public class DBinomial {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        Binomial();
        //Hypergeometrico();

    }

    public static void BinomialNegativa()
    {

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
