package othello;

import java.util.Scanner;

public class Othello {
    private int numPlayers;

    private char black ='B';
    private char white ='W';

    public static void main(String[] args) {
    Othello o = new Othello();
    o.startGame();
    }

    private void startGame() {
        Scanner sc = new Scanner(System.in);
        Player player1 = takePlayerInput(++numPlayers);
        Player player2 = takePlayerInput(++numPlayers);
        Board board = new Board();
        board.print();
        int n = board.getBoardSize() * board.getBoardSize();
        boolean playerTurn = true;
        while (n > 0) {
            System.out.println("Player 1- " + player1.getName() + "'s turn");
            System.out.println("Enter x: ");
            int x = sc.nextInt();
            System.out.println("Enter y: ");
            int y = sc.nextInt();
            boolean answer = false;
            while(!answer) {
                if (playerTurn) {
                    answer = board.move(black, x, y);
                } else {
                    answer = board.move(white, x, y);
                }
                if (!answer) {
                    System.out.println("Invalid move!! Please try again!!");
                } else {
                    board.print();
                    playerTurn = !playerTurn;
                    n--;
                }
            }


        }
    }

    private Player takePlayerInput(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of player"+num);
        String name = sc.nextLine();
        Player p =new Player(name);
        return p;
    }
}
