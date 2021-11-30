import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovesTest {
    private Board gameboard;
    Moves moves;
    Piece[][] board;
    @BeforeEach
    void setup(){
        gameboard = new Board();
        board = init_board();
        moves =new Moves(board, Color.Red);

    }
    @Test
    void check_move() {
        assertTrue(moves.check_move(2, 1, 3, 0));
        assertFalse(moves.check_move(1, 2, 2, 1));
        assertTrue(moves.check_move(2, 7, 3, 6));
        assertFalse(moves.check_move(2, 1, 1, 0));
    }

    @Test
    void getAllPossibleMoves() {
        List<Point> movesList = new ArrayList<Point>();
        Point point1 = new Point (3,0);
        Point point2 = new Point(3, 2);
        movesList.add(point1);
        movesList.add(point2);
        List<Point> res = moves.getAllPossibleMoves(2, 1);
        assertEquals(movesList, res) ;
        movesList.remove(point1);
        movesList.remove(point2);
        List<Point> res2 = moves.getAllPossibleMoves(1, 0);
        assertEquals(movesList, res2);


    }

    @Test
    void getKnockOutPosition() {
        List<Point> expected =new ArrayList<Point>();
        assertEquals(expected, moves.getKnockOutPosition());
    }

    @Test
    void test_multiple_jump(){
        Board gameboard = new Board();

        // Setup Pieces to allow for multiple jump
        gameboard.make_move("[b6]X[b4]", Color.Red);
        gameboard.make_move("[e7]X[e4]", Color.Red);

        // Assert that multiple jump is possible. Needed to update knockout positions
        assertTrue(gameboard.check_validity("[a3]X[e7]", Color.White));

        // Make multiple-jump, assert that "pieces which were jumped over" are eliminated
        gameboard.make_move("[a3]X[e7]", Color.White);
        assertEquals(10, gameboard.count_pieces( Color.Red));
        assertEquals(12, gameboard.count_pieces(Color.White));
    }

    @Test
    void test_DiagonalPositions(){
        /*
         * Would be better to directly call DiagonalPositions and see if it returns correct positions
         * But DiagonalPositions is private, so this is needed
         */
        Board gameboard = new Board();

        // Assert that move is currently illegal because piece is pawn, cannot move backwards
        gameboard.make_move("[b6]X[a5]", Color.Red);
        assertFalse(gameboard.check_validity("[a5]X[b6]", Color.Red));
        // Make the piece king
        gameboard.make_move("[a5]X[b1]", Color.Red);
        gameboard.make_move("[b1]X[a5]", Color.Red);

        // This move was illegal for piece before
        assertTrue(gameboard.check_validity("[a5]X[b6]", Color.Red));
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
