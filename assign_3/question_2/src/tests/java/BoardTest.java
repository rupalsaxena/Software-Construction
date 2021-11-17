import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }

    @Test
    void make_move() {
    }

    @Test
    void count_pieces() {
        assertEquals(12, board.count_pieces(Color.Red));
        assertEquals(12, board.count_pieces(Color.White));

        board.make_move("[b6]X[c5]", Color.Red);
        assertEquals(12, board.count_pieces(Color.Red));
        assertEquals(12, board.count_pieces(Color.White));

        board.make_move("[a3]X[b4]", Color.White);
        assertEquals(12, board.count_pieces(Color.Red));
        assertEquals(12, board.count_pieces(Color.White));

        String input = "[c5]X[a3]";
        if(board.check_validity(input, Color.Red)) {
            board.make_move(input, Color.Red);
            assertEquals(12, board.count_pieces(Color.Red));
            assertEquals(11, board.count_pieces(Color.White));
        } else assertEquals(1,2);
    }

    @Test
    void check_input_validity() {
        assertTrue(board.check_validity("[b6]X[c5]", Color.Red));
        assertFalse(board.check_validity("[j2]X[b9]", Color.Red));
        assertFalse(board.check_validity("[1a]X[2b]", Color.Red));
        assertFalse(board.check_validity("[aa]X[aa]", Color.Red));
        assertFalse(board.check_validity("[c11]X[b5]", Color.Red));
        assertFalse(board.check_validity("wewiwezriuwrz", Color.Red));
    }

    @Test
    void getAllPossibleMoves() {
        // Used in hint
        String res = Board.getAllPossibleMoves("[b6]X[c5]", Color.Red);
        assertEquals("[a5, c5]", res);
    }

    @Test
    void check_all_possible_moves() {
        assertTrue(board.check_all_possible_moves(Color.Red));
    }
}