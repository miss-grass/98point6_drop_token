// Zhu Li
// 2/15/17
//
// Class GameManage can be used to manage a game of 98oint drop tokens. The 2 players drop tokens one by
// one to the indicated column on the game board, and each token is dropped to the lowest unoccupied row
// of the board. It keeps track of the state of the game, and provides information for managing the game.

public class GameManage {
   private static final int CAPACITY = 16;  // capacity of tokens
   private static final int DIMENSION = 4;  // dimension of the board
   private static final int diaLeft = 5;  // dimension number for diagonal from top left to bottom right
   private static final int diaRight = 3;  // dimension number for diagonal from top right to bottom left
   
   private int[] board;  // store tokens on the board
   private int[] successCol;  // store successful columns successfully put in in order
   private int[] fill;  // store number of tokens in each column
   private int size;
   private boolean flag;  // true if one player wins, false otherwise
   
   public GameManage() {
      board = new int[CAPACITY];
      successCol = new int[CAPACITY];
      fill = new int[DIMENSION];
      flag = false;
   }
   
   // pre: takes an integer of column number, and an integer of player number
   // post: updates the game state, returns a String for output
   public String put(int col, int player) {
      if (flag || fill[col - 1] == DIMENSION) { // one player already wins or the column is full
         return "ERROR";
      } else {
         // store token in board
         int index = col - 1 + CAPACITY - DIMENSION - fill[col - 1] * DIMENSION;
         board[index] = player;
         
         // update all fields
         successCol[size] = col;
         size++;
         fill[col - 1]++;
         
         // check the game state
         if (checkWin(index, col, player)) {
            flag = true;
            return "WIN";
         }else {
            if (size == CAPACITY) {
               return "DRAW";
            } else {
               return "OK";
            }
         }
      }
   }
   
   
   // pre: takes an integer of the index in board array, an integer of column number
   //      token is put, an integer of player number
   // post: returns true if the player wins, false if not
   private boolean checkWin(int index, int col, int player) {
      boolean result = true;
      // check diagonals
      if (index % diaLeft == 0) {
         for(int i = 0; i < CAPACITY; i += diaLeft) {
            if (board[i] != player) {
               result = false;
               break;
            }
         }
         if (result) {
            return result;
         }
      }
      if (index % diaRight == 0) {
         for(int i = diaRight; i <= CAPACITY - DIMENSION; i += diaRight) {
            if (board[i] != player) {
               result = false;
               break;
            }
         }
         if (result) {
            return result;
         }
      }
      
      // check column
      result = true;
      for (int i = col - 1; i < CAPACITY; i += DIMENSION) {
         if (board[i] != player) {
            result = false;
            break;
         }
      }
      if (result) {
         return result;
      } else {
         result = true;
      }
      
      // check row
      int start = index % DIMENSION * DIMENSION;
      for (int i = start; i < start + DIMENSION; i++) {
         if (board[i] != player) {
            result = false;
            break;
         }
      }
      return result;
   }
   
   // post: return an array of successful columns successfully put in in order,
   //       index not accessed contains 0
   public int[] get() {
      return successCol;
   }
   
   // post: return an array storing tokens on the board,
   //       in the order of reading rows, left to right in each row
   public int[] board() {
      return board;
   }
   
   // post: return total number of tokens successfully put in
   public int size() {
      return size;
   }
}
