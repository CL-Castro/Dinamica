import java.util.Scanner;
import java.lang.Math;
public class NumerosAleatorios {
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        double F;
        /*double [] numContinuos = new double [100];
        System.out.println("A");
        double a = in.nextDouble();
        System.out.println("B");
        double b = in.nextDouble();

        for(int i=0;i<100;i++){
            F = (Math.random() * 1) + 0;
            numContinuos[i] = a + F * (b-a);
            System.out.println(numContinuos[i]);
        }
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
        }*/
        double [] numDiscreto = new double [100];
        System.out.println("Sigma");
        double sigma = in.nextDouble();
        System.out.println("Promedio");
        double prom = in.nextDouble();
        double sumatoria;
        double n = 1000;
        double index;
        double deltax = 6 * sigma / n;
        for(int i=0;i<numDiscreto.length;i++){
            F = Math.random();
            sumatoria = 0;
            index = 1;
            while(sumatoria < F){
                sumatoria += (1/Math.sqrt(2*Math.PI*Math.pow(sigma,2)))*
                        (Math.pow(Math.E,-1*Math.pow((index*deltax - prom)/sigma,2)))*deltax;
                index += 1.0;
                //System.out.println(F + " > "+sumatoria);
            }
            numDiscreto[i] = sumatoria;
            System.out.println(numDiscreto[i]);
        }

    }
}
