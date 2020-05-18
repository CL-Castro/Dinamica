import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChiCuadrado {
    public static void main(String[] args) throws IOException {
        String pathToCHI = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\practicaautos.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToCHI));
        ArrayList<double[]> Autos = new ArrayList<>();
        String row;
        int n = 100;
        for (int i = 0; i < n; i++) {
            Autos.add(new double[] {i,0,0});
        }
        int index = 0;
        while((row = bufferedReader.readLine()) != null)
        {
            index++;
            for (double[] auto:Autos
                 ) {
                if(Integer.parseInt(row) == auto[0])
                {
                    auto[1]++;
                }
            }
        }
        for (int i = 0; i < Autos.size(); i++) {
            if(Autos.get(i)[1] == 0)
                Autos.remove(i);
        }
        double lamda = 0;
        for (double[] auto:Autos
        ) {
            lamda+= auto[0] * auto[1];
        }
        lamda = lamda/index;
        for (double[] auto:Autos
        ) {
            auto[2] = Math.pow(lamda,auto[0]) * Math.exp(-lamda) / factorial(auto[0]);
            System.out.println("Clase: "+auto[0]+" Frecuencia: "+auto[1] + " Esperado: "+(auto[2]*index));
        }
        System.out.println("Total: "+Autos.size());
        double chi = 0;
        for (int i = 0; i < Autos.size()-1; i++) {
            chi += Math.pow(Autos.get(i)[1]-(Autos.get(i)[2]*index),2) / (Autos.get(i)[2]*index);
        }
        System.out.println("Estadistico chi: "+chi);
        if(chi>113.15)
            System.out.println("Se acepta H1: Los datos NO siguen una distribucion Poisson");
        else
            System.out.println("Se acepta H0: Los datos siguen una distribucion Poisson");
        bufferedReader.close();
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
}
