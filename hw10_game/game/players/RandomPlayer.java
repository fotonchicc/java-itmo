package game.players;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        final int m = position.getM();
        final int n = position.getN();

        int r = random.nextInt(m);
        int c = random.nextInt(n);
        final Move move = new Move(r, c, cell);
        if (position.isValid(move)) {
            return move;
        }
        return null;
    }
}
