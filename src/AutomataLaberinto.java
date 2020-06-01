import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AutomataLaberinto extends JPanel {
    public static void main(String [] args) throws InterruptedException, IOException {
        int [][] maze1 = {  {3,3,3,3,3,3,3,3,3,3,3,3},
                            {3,1,2,2,0,0,0,2,0,0,0,3},
                            {3,0,2,2,0,2,2,2,0,2,0,3},
                            {3,0,0,0,0,2,2,2,0,2,0,3},
                            {3,0,2,2,0,0,0,2,0,2,0,3},
                            {3,0,2,2,2,2,0,2,0,2,0,3},
                            {3,0,2,0,0,0,0,2,0,2,0,3},
                            {3,2,0,2,0,2,0,2,0,2,0,3},
                            {3,2,0,2,0,2,0,2,0,2,0,3},
                            {3,2,0,0,0,0,2,2,0,2,0,3},
                            {3,2,2,2,2,0,0,0,0,2,10,3},
                            {3,3,3,3,3,3,3,3,3,3,3,3}};
        int [][] maze2 = {  {3,3,3,3,3,3,3,3,3,3,3,3},
                             {3,1,0,0,0,0,2,0,0,0,2,3},
                             {3,2,2,2,2,0,2,0,2,0,2,3},
                             {3,0,0,0,0,0,2,0,2,0,0,3},
                             {3,0,2,2,0,2,2,0,2,0,2,3},
                             {3,0,0,2,0,2,0,0,2,0,2,3},
                             {3,2,0,2,0,2,0,2,0,0,0,3},
                             {3,0,0,2,0,2,0,2,0,2,2,3},
                             {3,0,2,2,2,2,0,2,0,2,2,3},
                             {3,0,0,0,0,0,0,2,0,2,2,3},
                             {3,2,2,2,2,2,2,2,0,0,10,3},
                             {3,3,3,3,3,3,3,3,3,3,3,3}};
        ArrayList<int[][]> este = resolver(maze1);
    }
    int W = 11;
    int H = 5;
    int recwidth = 50;
    int recheight = 50;

    Brick[][] bricks = new Brick[H][W];

    public AutomataLaberinto(int[][] maswaso) {
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


    Color createRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }
    private static ArrayList<int[][]> resolver(int[][] plano) throws InterruptedException, IOException {
        boolean flag = false;
        boolean horizontal = false, vertical = false;
        ArrayList<int[][]> pasos = new ArrayList<>();
        ShowMatrix(plano);
        int index = 0;
        JFrame frame = new JFrame("try me");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(!flag)
        {
            index++;
            pasos.add(plano);
            AutomataLaberinto panel = new AutomataLaberinto(pasos.get(pasos.size()-1));
            frame.setContentPane(panel);
            frame.setVisible(true);
            Thread.sleep (1000);
            for (int i = 1; i < plano.length-1; i++) {
                for (int j = 1; j < plano[0].length-1; j++) {
                    if(plano[i][j] == 1)
                    {
                        int rand = (int)(Math.random()*4+1);
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
                                }else if(plano[i][j-1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j-1] = 1;
                                    move = !move;
                                }
                                if(plano[i][j + 1] == 10 || plano[i][j-1] == 10 || plano[i+1][j] == 10 || plano[i-1][j] == 10){
                                    flag = true;plano[i][j] = 0;
                                    plano[plano.length-2][plano.length-2] = 1;}
                                break;
                            case 2:
                                System.out.println("Izquierda");
                                move=false;
                                if(plano[i][j - 1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j-1] = 1;
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
                                else if(plano[i][j+1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j+1] = 1;
                                    move = !move;
                                }
                                if(plano[i][j + 1] == 10 || plano[i][j-1] == 10 || plano[i+1][j] == 10 || plano[i-1][j] == 10){
                                    flag = true;plano[i][j] = 0;
                                    plano[plano.length-2][plano.length-2] = 1;}
                                break;
                            case 3:
                                System.out.println("Arriba");
                                move=false;
                                if(plano[i+1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i+1][j] = 1;
                                    move = !move;
                                }else if(plano[i-1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i-1][j] = 1;
                                    move = !move;
                                }else if(plano[i][j + 1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j+1] = 1;
                                    move = !move;
                                }else if(plano[i][j-1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j-1] = 1;
                                    move = !move;
                                }
                                if(plano[i][j + 1] == 10 || plano[i][j-1] == 10 || plano[i+1][j] == 10 || plano[i-1][j] == 10){
                                    flag = true;plano[i][j] = 0;
                                    plano[plano.length-2][plano.length-2] = 1;}
                                break;
                            case 4:
                                System.out.println("Abajo");
                                move=false;
                                if(plano[i-1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i-1][j] = 1;
                                    move = !move;
                                }else if(plano[i+1][j] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i+1][j] = 1;
                                    move = !move;
                                }else if(plano[i][j + 1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j+1] = 1;
                                    move = !move;
                                }else if(plano[i][j-1] == 0)
                                {
                                    plano[i][j] = 0;
                                    plano[i][j-1] = 1;
                                    move = !move;
                                }
                                if(plano[i][j + 1] == 10 || plano[i][j-1] == 10 || plano[i+1][j] == 10 || plano[i-1][j] == 10){
                                    flag = true;plano[i][j] = 0;
                                    plano[plano.length-2][plano.length-2] = 1;}
                                break;
                        }
                        ShowMatrix(plano);
                    }
                }
            }
        }
        ShowMatrix(plano);
        System.out.println("Llego a la meta en "+index+" intentos");
        return pasos;
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
class Brick {

    Color col;
    Rectangle rec;

    public Brick(Color col, Rectangle rec) {
        this.col = col;
        this.rec = rec;
    }

    public void draw(Graphics g) {
        g.setColor(col);
        g.fillRect(rec.x, rec.y, rec.width, rec.height);
    }
}
