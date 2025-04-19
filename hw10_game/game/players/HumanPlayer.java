package game.players;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        out.println("Position:");
        out.println(position);
        out.println(cell + "'s move");
        out.println("Enter row and column:");
        String firstNum = in.next();
        String secondNum = in.next();
        try {
            int x = Integer.parseInt(firstNum) - 1;
            int y = Integer.parseInt(secondNum) - 1;
            final Move move = new Move(x, y, cell);
            if (position.isValid(move)) {
                return move;
            }
            System.out.println("Move " + move + " is invalid, try again");
        } catch (NumberFormatException e) {
            System.out.println("Wrong input for row and position");
        }
        return null;
    }
}
