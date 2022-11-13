package tdlapi.helpers;

import tdlapi.domain.Board;

public class TestCaseContext {

    private static Board board;

    public static void init(){
        board=null;
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        TestCaseContext.board = board;
    }


}
