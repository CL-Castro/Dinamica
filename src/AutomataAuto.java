import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AutomataAuto extends JPanel {
    int W = 11;
    int H = 11;
    int recwidth = 50;
    int recheight = 50;

    Brick[][] bricks = new Brick[H][W];

    public AutomataAuto(int[][] maswaso) {
        super();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if(maswaso[y][x] == 1){
                    bricks[y][x] = new Brick(new Color((int) (0), (int) (0), (int) (255)), new Rectangle(x * recwidth, y * recheight, recwidth,
                            recheight));repaint();}
                else if(maswaso[y][x] == 2){
                    bricks[y][x] = new Brick(new Color((int) (255), (int) (0), (int) (0)), new Rectangle(x * recwidth, y * recheight, recwidth,
                            recheight));repaint();}
                else if(maswaso[y][x] == 3){
                    bricks[y][x] = new Brick(new Color((int) (255), (int) (255), (int) (255)), new Rectangle(x * recwidth, y * recheight, recwidth,
                            recheight));repaint();}
                else if(maswaso[y][x] == 10){
                    bricks[y][x] = new Brick(new Color((int) (0), (int) (255), (int) (0)), new Rectangle(x * recwidth, y * recheight, recwidth,
                            recheight));repaint();}
                else if(maswaso[y][x] == 0){
                    bricks[y][x] = new Brick(new Color((int) (0), (int) (0), (int) (0)), new Rectangle(x * recwidth, y * recheight, recwidth,
                            recheight)); repaint();}
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                bricks[y][x].draw(g);
            }
        }
    }
    public static void main(String [] args) throws InterruptedException {
        int [][] plano = generarMatriz();
        boolean flag = false;
        ArrayList<int[][]> pasos= new ArrayList<>();
        ShowMatrix(plano);
        JFrame frame = new JFrame("try me");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(!flag)
        {
            for (int i = 1; i < plano.length-1; i++) {
                for (int j = 0; j < plano[0].length-1; j++) {
                    pasos.add(plano);
                    AutomataLaberinto panel = new AutomataLaberinto(pasos.get(pasos.size()-1));
                    frame.setContentPane(panel);
                    frame.setVisible(true);
                    Thread.sleep (200);
                    if(plano[i][j] == 1)
                    {
                        int rand = (int)(Math.random()*2+1);
                        boolean move = false;
                        System.out.println(rand);
                        switch (rand)
                        {
                            case 1:
                                System.out.println("Derecha");
                                move=false;
                                if(plano[i][j + 1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j+1] = 1;
                                    move = !move;
                                }else if(plano[i+1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i+1][j] = 1;
                                    move = !move;
                                }else if(plano[i-1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i-1][j] = 1;
                                    move = !move;
                                }
                                if(plano[i][j + 1] == 10)
                                    flag = true;
                                if(!move && !flag)
                                {
                                    System.out.println("NO PUEDE MOVERSE");
                                    refrescar(plano,i,j);
                                    ShowMatrix(plano);
                                }
                                break;
                            case 2:
                                System.out.println("Izquierda");
                                move=false;
                                if(plano[i][j + 1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j+1] = 1;
                                    move = !move;
                                }else if(plano[i-1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i-1][j] = 1;
                                    move = !move;
                                }else if(plano[i+1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i+1][j] = 1;
                                    move = !move;
                                }
                                if(plano[i][j + 1] == 10){
                                    flag = true;plano[i][j] = 0;
                                    plano[i][j+1] = 1;}
                                if(!move && !flag)
                                {
                                    System.out.println("NO PUEDE MOVERSE");
                                    refrescar(plano,i,j);
                                    ShowMatrix(plano);
                                }
                                break;
                        }
                        ShowMatrix(plano);
                    }
                }
            }
        }
        System.out.println("Llego a la meta");
    }

    private static int[][] generarMatriz()
    {
        int[][] test = new int[5][11];
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                if(i == 0 || i == 4)
                {
                    test[i][j] = 3;
                }else if(j==10){
                    test[i][j] = 10;
                }else{
                    test[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            int obsI = (int) (Math.random() * 3 + 1);
            int obsJ = (int) (Math.random() * 9 + 1);
            test[obsI][obsJ] = 2;
        }
        test[(int) (Math.random() * 3 + 1)][0] = 1;
        return test;
    }

    private static void refrescar(int[][] test, int PlayerI, int PlayerJ)
    {
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                if(i == 0 || i == 4)
                {
                    test[i][j] = 3;
                }else if(j==10){
                    test[i][j] = 10;
                }else{
                    test[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            int obsI = (int) (Math.random() * 3 + 1);
            int obsJ = (int) (Math.random() * 9 + 1);
            test[obsI][obsJ] = 2;
        }
        test[PlayerI][PlayerJ] = 1;
    }

    private static void ShowMatrix(int [][] matrizX){
        for (int[] x : matrizX) {
            for (int j = 0; j < matrizX[0].length; j++) {
                System.out.print(x[j] + " ");
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------");
    }
}
