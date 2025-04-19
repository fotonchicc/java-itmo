package game.players;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        final int m = position.getM();
        final int n = position.getN();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
