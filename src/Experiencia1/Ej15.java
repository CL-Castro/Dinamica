package Experiencia1;

import java.util.ArrayList;
import java.util.Scanner;

public class Ej15 {
    private static ArrayList<Integer> estado_gente = new ArrayList<>();
    private static ArrayList <Double> prob_gente = new ArrayList<>();
    static double periodo=5;
    public static void main (String[] args)
    {

        Scanner in = new Scanner(System.in);
        System.out.println("Poblacion:");
        int poblacion= in.nextInt();
        System.out.println("Casos:");
        int casos=in.nextInt();
        System.out.println("Periodo: ");
        periodo = in.nextDouble ();
        int tiempo=0;
        inicial(poblacion,casos);
        while(!estado_gente_2())
        {
            cambio(tiempo);
            tiempo++;
            imprimir_estado(tiempo);
        }
        System.out.println("tardan en ser inmunes "+tiempo+" dias");

    }


    private static void imprimir_estado(int tiempo) {
        System.out.println("En el tiempo "+tiempo);
        for(int i=0; i< estado_gente.size(); i++)
        {
            String estado =" persona numero "+(i+1)+" :";
            if(estado_gente.get(i)==0)
                estado+= " suseptible";
            if(estado_gente.get(i)==1)
                estado+= " enfermo";
            if(estado_gente.get(i)==2)
                estado+= " inmune";
            System.out.println(estado);
        }
    }
    private static boolean estado_gente_2() {
        for(Integer i : estado_gente)
        {
            if(i!=2)
                return false;
        }
        return true;
    }
    private static void cambio(int tiempo) {
        int N= estado_gente.size();
        for(int i=0 ; i<N ;i++)
        {
            double u = Math.random();
            if(tiempo>prob_gente.get(i) && estado_gente.get(i)==1) // inmune
            {
                estado_gente.remove(i);
                prob_gente.remove(i);
                estado_gente.add(i, 2);
                prob_gente.add(i,0.0);
            }
            if(u<prob_gente.get(i) && estado_gente.get(i)==0) // contagio
            {
                estado_gente.remove(i);
                prob_gente.remove(i);
                estado_gente.add(i, 1);
                prob_gente.add(i,5.0+tiempo); // periodo
            }
        }
    }
    private static void inicial(int N, int casos) {
        int k=0;
        for(int i=0 ; i<N ;i++)
        {
            if(k<casos)
            {
                estado_gente.add(1);
                prob_gente.add(periodo);
                k++;
            }
            else
            {
                estado_gente.add(0);
                prob_gente.add(Math.random());
            }
        }
    }


}
