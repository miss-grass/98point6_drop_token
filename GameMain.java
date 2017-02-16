// Zhu Li
// 2/15/17
//
// Class GameMain is the driver program for the game management task.
// It reads comments from users, prints outputs to the users until the game is called over.

import java.util.Scanner;

public class GameMain {
   private static final int DIMENSION = 4;  // dimension of the board
   
   public static void main(String[] args) {
      // give information
      System.out.println("Welcome to 98point6 Drop Token!");
      rule();
      instruction();
      
      // create new GameManage
      GameManage game = new GameManage();
      
      // set basic parameters
      Scanner console = new Scanner(System.in);
      String command = console.nextLine();
      int player = 0; // 0 indicates player 1, and 1 indicates player 2
      
      // loop over stdln taking commands, stop when "EXIT"
      while (!command.equalsIgnoreCase("EXIT")) {
         Scanner line = new Scanner(command);
         String word = line.next();
         if (word.equalsIgnoreCase("PUT")) {
            int col = line.nextInt();
            if (col < 1 || col > DIMENSION) {  // deal with invalid input
               System.out.println("Invalid column number! (1 <= <COLUMN> <= 4)");
            } else {
               String output = game.put(col, player+1);
               System.out.println(output);
               if (!output.equals("ERROR")) {
                  player = 1 - player;
               }
            }
         } else if (word.equalsIgnoreCase("BOARD")) {
            int[] board = game.board();
            //System.out.println(board.length);
            for (int i = 0; i <= board.length - DIMENSION; i += DIMENSION) {
               System.out.print("|");
               for (int j = 0; j < DIMENSION; j++) {
                  System.out.print(" " + board[(j + i)]);
               }
               System.out.println();
            }
            System.out.println("+--------");
            System.out.println("  1 2 3 4");
         } else if (word.equalsIgnoreCase("GET")) {
            int[] list = game.get();
            for (int i = 0; i < game.size(); i++) {
               System.out.println(list[i]);
            }
         } else if (word.equalsIgnoreCase("HELP")) {
            instruction();
         } else {
            // gives instructions if gets invalid input
            System.out.println("Invalid Input!");
            instruction();
         }
         // continue reading input
         command = console.nextLine();
      }
   }
   
   // prints instructions of valid commands
   public static void instruction() {
      System.out.println("Actions (Ignore Cases):");
      System.out.println("    PUT <COLUMN> : drop a token along column <COLUMN> (1 <= <COLUMN> <= 4)");
      System.out.println("    GET : show a list of columns that have been successfully put to");
      System.out.println("    BOARD : show current board state");
      System.out.println("    EXIT : ends the game");
      System.out.println("    HELP : prints out this menu");
      System.out.println();
   }
   
   public static void rule() {
      System.out.println("Game Rule:");
      System.out.println("    User: 2 Players. Board: 4x4. ");
      System.out.println("    A token is dropped along a column (labeled 1-4) to the lowest unoccupied row of the board. ");
      System.out.println("    A player wins when they have 4 tokens next to each other either along a row, in a column, or on a diagonal. ");
      System.out.println("    If the board is filled, and nobody has won then the game is a draw.");
      System.out.println();
   }
}

