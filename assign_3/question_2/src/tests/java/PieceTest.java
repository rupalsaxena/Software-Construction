import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private Board gameboard;
    private Piece dummy_piece;
    private Piece red_pawn;
    private Piece white_pawn;
    private Piece white_king;

    @BeforeEach
    void setUp() {
        gameboard = new Board();
        dummy_piece = new Piece(Color.Empty, Rank.Empty, 0 ,0, gameboard);
        red_pawn = new Piece(Color.Red, Rank.Pawn, 1 ,2, gameboard);
        white_pawn = new Piece(Color.White, Rank.Pawn, 0 ,5, gameboard);
        white_king = new Piece(Color.White, Rank.King, 0 ,0, gameboard);
    }

    @Test
    void move_piece_red() {
        // Move pawn to last line and check if it became king
        assertFalse(red_pawn.piece_equals(Color.Red, Rank.King));
        Point future_point = new Point(3, 7);
        red_pawn.move_piece(future_point);
        assertTrue(red_pawn.piece_equals(Color.Red, Rank.King));
        assertEquals(future_point, red_pawn.pos);
    }

    @Test
    void move_piece_white() {
        // Move pawn to last line and check if it became king
        assertFalse(white_pawn.piece_equals(Color.White, Rank.King));
        Point future_point = new Point(1, 0);
        white_pawn.move_piece(future_point);
        assertTrue(white_pawn.piece_equals(Color.White, Rank.King));
    }

    @Test
    void eliminate_piece() {
        assertTrue(red_pawn.piece_equals(Color.Red, Rank.Pawn));
        red_pawn.eliminate_piece();
        assertTrue(red_pawn.piece_equals(Color.Empty, Rank.Empty));
    }

    @Test
    void encoded_value() {
        assertEquals("   ", dummy_piece.encoded_value());
        assertEquals("R_P", red_pawn.encoded_value());
        assertEquals("W_K", white_king.encoded_value());
    }

    @Test
    void is_empty() {
        dummy_piece = new Piece(Color.Empty, Rank.Empty, 0 ,0, gameboard);
        assertTrue(dummy_piece.is_empty());
    }

    @Test
    void piece_equals() {
        assertTrue(red_pawn.piece_equals(Color.Red, Rank.Pawn));
        assertFalse(red_pawn.piece_equals(Color.Empty, Rank.Pawn));
    }

    @Test
    void is_valid_move_case_1() {
        // positions = {"current col", "current row", "future col", "future row"}
//        "Red Pawn cannot move in opposite direction! \n"
        Point future_point = new Point(1, 4);
        red_pawn.move_piece(future_point);
        String[] positions = utils.get_current_future_positions("[b4]X[c5]");
        Piece[][] board = init_board();
        assertFalse(red_pawn.is_valid_move(red_pawn, positions, Color.Red, board));

    }

    @Test
    void is_valid_move_case_2() {
//        "White Pawn cannot move in opposite direction! \n"
        Point future_point = new Point(0, 3);
        red_pawn.move_piece(future_point);
        String[] positions = utils.get_current_future_positions("[a5]X[b4]");
        Piece[][] board = init_board();
        assertFalse(white_pawn.is_valid_move(white_pawn, positions, Color.White, board));
    }

    @Test
    void is_valid_move_case_3() {
//        "Your move is from White checkers! "
        String[] positions = utils.get_current_future_positions("[a8]X[b7]");
        Piece[][] board = init_board();
        assertFalse(red_pawn.is_valid_move(red_pawn, positions, Color.Red, board));
    }

    @Test
    void is_valid_move_case_4() {
//        "Your move is to White checkers! "
        String[] positions = utils.get_current_future_positions("[b6]X[b5]");
        Piece[][] board = init_board();
        assertFalse(red_pawn.is_valid_move(red_pawn, positions, Color.Red, board));
    }

    @Test
    void is_valid_move_case_5() {
//        "There is no piece on your current move! "
        Piece[][] board = init_board();
        Piece piece = board[4][3];
        String[] positions = utils.get_current_future_positions("[d4]X[c5]");
        assertFalse(piece.is_valid_move(piece, positions, Color.White, board));
    }

    @Test
    void is_valid_move_case_6() {
//        "Future move already has your piece! "
        Piece[][] board = init_board();
        Piece piece = board[0][1];
        String[] positions = utils.get_current_future_positions("[a7]X[b6]");
        assertFalse(piece.is_valid_move(piece, positions, Color.Red, board));
    }

    @Test
    void is_valid_move_case_7() {
//        "This is not your piece! "
        String[] positions = utils.get_current_future_positions("[b6]X[c5]");
        Piece[][] board = init_board();
        assertFalse(red_pawn.is_valid_move(red_pawn, positions, Color.White, board));
    }

    @Test
    void is_valid_move_final_return() {
//        "This is not your piece! "
        String[] positions = utils.get_current_future_positions("[b6]X[c5]");
        Piece[][] board = init_board();
        Piece cur_piece = board[2][1];
        Piece future_piece = board[3][2];
        assertTrue(cur_piece.is_valid_move(future_piece, positions, Color.Red, board));
    }

    @Test
    void registerObserver() {
    }

    @Test
    void removeObserver() {
    }

    @Test
    void notifyObservers() {

    }

    private Piece[][] init_board() {
        /*
        Used to give board array to functions which need one
        */

        Piece[][] board = new Piece[8][8];
        //For the Red player
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 7; x++) {
                if (y % 2 == 1 && x % 2 == 0) {
                    board[y][x] = new Piece(Color.Red, Rank.Pawn, x, y, gameboard);
                } else if (y % 2 == 0 && x % 2 == 1) {
                    board[y][x] = new Piece(Color.Red, Rank.Pawn, x, y, gameboard);
                } else {
                    board[y][x] = new Piece(Color.Empty, Rank.Empty, x, y, gameboard);
                }
            }
        }
        //For the empty rows in between the players
        for (int y = 3; y < 5; y++) {
            for (int x = 0; x <= 7; x++) {
                board[y][x] = new Piece(Color.Empty, Rank.Empty, x, y, gameboard);
            }
        }
        //For the White player
        for (int y = 5; y <= 7; y++) {
            for (int x = 0; x <= 7; x++) {
                if (y % 2 == 1 && x % 2 == 0) {
                    board[y][x] = new Piece(Color.White, Rank.Pawn, x, y, gameboard);
                } else if (y % 2 == 0 && x % 2 == 1) {
                    board[y][x] = new Piece(Color.White, Rank.Pawn, x, y, gameboard);
                } else {
                    board[y][x] = new Piece(Color.Empty, Rank.Empty, x, y, gameboard);
                }
            }
        }
        return board;
    }
}