package game;

import game.players.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter players separated by a space (\"h\" - human, \"r\" - random, \"s\" - sequential):");
        ArrayList<Player> players = new ArrayList<>();
        while (players.size() < 2) {
            String nextPlayer = input.next();
            switch (nextPlayer) {
                case "h" -> players.add(new HumanPlayer());
                case "r" -> players.add(new RandomPlayer());
                case "s" -> players.add(new SequentialPlayer());
                default -> {
                    System.out.println("Wrong input, enter next player again (\"h\" - human, \"r\" - random, \"s\" - sequential):");
                }
            }
        }
        final Game game = new Game(false, players.get(0), players.get(1));
        play(input, game);
    }

    private static void play(Scanner input, Game game) {
        int result;
        do {
            System.out.println("Enter m, n, k separated by a space (enter \"exit\" if you want to end the game):");
            try {
                String stringM = input.next();
                if (stringM.equals("exit")) {
                    break;
                }
                String stringN = input.next();
                String stringK = input.next();
                int m = Integer.parseInt(stringM);
                int n = Integer.parseInt(stringN);
                int k = Integer.parseInt(stringK);
                System.out.println("Enter the desired shape of the board (\"s\" - for a square, \"d\" - for a diamond):");
                String shape = input.next();
                result = game.play(new MnkBoard(m, n, k, shape));
                System.out.println("Game result: " + result);
            } catch (NumberFormatException e) {
                System.out.println("The numbers must be integers and separated by a space");
            }
        } while (true);
        input.close();
    }
}
