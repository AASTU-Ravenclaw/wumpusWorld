package wumpusworld;

import java.text.BreakIterator;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Agent {
    private int x = 0;
    private int y = 0;
    private Environment env = new Environment();

    public static void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();
    }

    public void moveUp(){
        env.grid[y][x].agent = false;
        if(this.y > 0)
            this.y -= 1;
        env.grid[y][x].agent = true;
    }
    
    public void moveDown(){
        env.grid[y][x].agent = false;
        if(this.y < 3)
            this.y += 1;
        env.grid[y][x].agent = true;
    }

    public void moveRight(){
        env.grid[y][x].agent = false;
        if(this.x < 3)
            this.x += 1;
        env.grid[y][x].agent = true;
    }

    public void moveLeft(){
        env.grid[y][x].agent = false;
        if(this.x > 0)
            this.x -= 1;
        env.grid[y][x].agent = true;
    }

    public void shoot(String direction){
        switch (direction) {
            case "i":
                if(this.y > 0)
                    if(env.grid[y - 1][x].wumpus){
                        env.grid[y-1][x].wumpus = false;
                        env.removeStench(y-1, x);
                    }
                break;
            case "j":
                if(this.x > 0)
                    if(env.grid[y][x-1].wumpus){
                        env.grid[y][x-1].wumpus = false;
                        env.removeStench(y, x-1);
                    }
                break;
            case "k":
                if(this.y < 3)
                    if(env.grid[y + 1][x].wumpus){
                        env.grid[y+1][x].wumpus = false;
                        env.removeStench(y+1, x);
                    }
                break;
            case "l":
                if(this.x < 3)
                    if(env.grid[y][x+1].wumpus){
                        env.grid[y][x+1].wumpus = false;
                        env.removeStench(y, x+1);
                    }
                break;
            
        }
    }
    public static void main(String[] args) throws InterruptedException{

        Agent agent = new Agent();
        Scanner scan = new Scanner(System.in);
        while(!(agent.env.checkGame(agent.y, agent.x))){
            agent.env.printEnv();

            String inp = scan.nextLine();
            switch(inp){
                case "a":
                    agent.moveLeft();
                    break;
                case "w":
                    agent.moveUp();
                    break;
                case "d":
                    agent.moveRight();
                    break;
                case "s":
                    agent.moveDown();
                    break;
                case "i":
                case "j":
                case "l":
                case "k":
                    agent.shoot(inp);
                    break;
                default:
                    System.out.println("Wrong input");
            }
            //TimeUnit.SECONDS.sleep(1);
            clearScreen();
        }
        scan.close();
        
    }
}
