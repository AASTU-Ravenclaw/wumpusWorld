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
                default:
                    System.out.println("Wrong input");
            }
            //TimeUnit.SECONDS.sleep(1);
            clearScreen();
        }
        scan.close();
        
    }
}
