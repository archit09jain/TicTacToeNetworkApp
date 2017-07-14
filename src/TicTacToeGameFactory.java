/**
 * Created by archit.j on 14/07/17.
 */
public class TicTacToeGameFactory {

    private static TicTacToeGameFactory factory = new TicTacToeGameFactory();

    private TicTacToeGameFactory() {}

    public static TicTacToeGameFactory getFactoryInstance() {
        return factory;
    }

    public TicTacToeInterface getGameObject(String type,Player players[]) {

        if(type.equals("StandardTicTacToe"))
                return new TicTacToeBoard(players);
        else
            return null;
    }

}
