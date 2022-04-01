
/**
 * Interface that has to be implemented by components that want to listen for
 * Win event.
 *
 * @author Leonardo Vona 545042
 */
public interface WinListener {

    /**
     *
     * @param winner Winner of the game. Possible values: null (if tie), O, X.
     * @param type Identifies how the victory occurred. Possible values: 0 ->
     * tie, 1 -> row, 2 -> column, 3 -> diagonal, 4 -> anti-diagonal.
     * @param xPosition Row of the cell that caused the end of the match.
     * Possible values: 0, 1, 2.
     * @param yPosition Column of the cell that caused the end of the match.
     * Possible values: 0, 1, 2.
     */
    void winOccurred(String winner, int type, int xPosition, int yPosition);
}
