import java.io.*;
import java.util.ArrayList;

public class CSVComparer {
    public static void main(String[] args) throws IOException {
        String pathToCsv = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\G3.csv";
        String pathToCHI = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\practicaautos.csv";
        //String pathToTarget = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\GRUPO2ORIGINALWASO.csv";
        String pathToTarget = "C:\\Castro\\ING. Sistemas\\7MO SEMESTRE\\Modelado\\¡Conéctate! - Ministerio de Educación (Grupo 1)(1).csv";
        String row=null;
        String row2=null;
        BufferedReader csvListaOficial = new BufferedReader(new FileReader(pathToCsv));
        BufferedReader csvChi = new BufferedReader(new FileReader(pathToCHI));
        BufferedReader csvTarget = new BufferedReader(new FileReader(pathToTarget));
        BufferedWriter Duplicados = new BufferedWriter(new FileWriter("frecuencias"));
        int i= 0;
        int j= 0;
        int filas1 = 0;
        int filas2 = 0;
        boolean flag = false;
        ArrayList<String> d1 = new ArrayList<>();
        ArrayList<String> d2 = new ArrayList<>();
        ArrayList<String> autos = new ArrayList<>();
        ArrayList<String> dup = new ArrayList<>();
        while((row = csvListaOficial.readLine()) != null)
        {
            d1.add(row);
        }
        while((row = csvTarget.readLine()) != null)
        {
            d2.add(row);
        }
        while((row = csvChi.readLine()) != null)
        {
            autos.add(row);
        }
        /*for (String c1:d1
             ) {
            flag = false;
            for (String c2:d2
                 )
            {
                String []data = c2.split(",");
                String []comp = c1.split(",");
                System.out.println(data[2] +"   "+comp[2]);
                if(comp[3].equals(data[3]))
                {
                    flag=true;
                }
            }
            if(!flag)
            {
                i++;
                Duplicados.write(c1+"\n");
            }
        }*/
        for (String c1:d1
        ) {
            for (String c2:d1
            )
            {
                String []data = c2.split(",");
                String []comp = c1.split(",");
                System.out.println(data[2] +"   "+comp[2]);
                if(comp[3].equals(data[3]))
                {
                    i++;
                }
            }
        }
        System.out.println("Numero de registros duplicados: " + i + " ehh "+ d2.size() + " ahhhh "+ d1.size());
        csvListaOficial.close();
        csvTarget.close();
        //Duplicados.close();

    }
}
