package wumpusworld;

import java.util.Arrays;
import java.util.Random;

class Box{
    public boolean wumpus = false;
    public boolean gold = false;;
    public boolean pit = false;
    public boolean agent = false;

    public boolean stench = false;
    public boolean breeze = false;
    public boolean glitter = false;
}

public class Environment{

    public Box[][] grid = new Box[4][4];
    private Random rand = new Random();

    public void setGrid(){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                grid[i][j] = new Box();
            }
        }
        grid[0][0].agent = true;

        int gold = rand.nextInt(14) + 2;

        grid[gold/4][gold%4].gold = true;

        int wum = rand.nextInt(14) + 2;
        while(wum == gold)
            wum = rand.nextInt(14) + 2;
        
        grid[wum/4][wum%4].wumpus = true;

        Integer[] pits = new Integer[3];

        for(int i = 0; i < 3; i++){
            int temp = rand.nextInt(14) + 2;
            while(temp == gold || temp == wum ||  Arrays.asList(pits).contains(temp))
                temp = rand.nextInt(14) + 2;

            pits[i] = temp;
            grid[temp/4][temp%4].pit = true;
        }
        
        
    }

    public void printEnv(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j< 4; j++)
            {
                if(grid[i][j].agent)
                    System.out.print(" A ");
                else if(grid[i][j].gold)
                    System.out.print("[g]");
                else if(grid[i][j].pit)
                    System.out.print("[p]");
                else if(grid[i][j].wumpus)
                    System.out.print("[w]");
                else
                    System.out.print("[ ]");   
            }
            System.out.println();
        }
    }

    public void stench(int x, int y){
        if(x > 0)
            grid[x-1][y].stench = true;
        if(y > 0)
            grid[x][y-1].stench = true;
        if(x < 3)
            grid[x+1][y].stench = true;
        if(y < 3)
            grid[x][y+1].stench = true;  
    }

    public void removeStench(int x, int y){
        if(x > 0)
            grid[x-1][y].stench = false;
        if(y > 0)
            grid[x][y-1].stench = false;
        if(x < 3)
            grid[x+1][y].stench = false;
        if(y < 3)
            grid[x][y+1].stench = false;  
    }

    public void breeze(int x, int y){
        if(x > 0)
            grid[x-1][y].breeze = true;
        if(y > 0)
            grid[x][y-1].breeze = true;
        if(x < 3)
            grid[x+1][y].breeze = true;
        if(y < 3)
            grid[x][y+1].breeze = true;
    }

    public void glitter(int x, int y){
        if(x > 0)
            grid[x-1][y].glitter = true;
        if(y > 0)
            grid[x][y-1].glitter = true;
        if(x < 3)
            grid[x+1][y].glitter = true;
        if(y < 3)
            grid[x][y+1].glitter = true;
    }

    public boolean  checkGame(int i, int j){
        if(grid[i][j].gold){
            System.out.println("You Win!!!");
            return true;
        }else if(grid[i][j].wumpus){
            System.out.println("The wumpus got ya!!!");
            return true;
        }else if(grid[i][j].pit){
            System.out.println("You fell into the pit");
            return true;
        }
        return false;

    }

    public Environment(){
        this.setGrid();
    }

}