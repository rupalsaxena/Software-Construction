import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board implements Observer{
    /*
     * Provides 2D-Array board of Pieces
     */

    private static Piece[][] board = new Piece[8][8];

    public Board() {
        // initialize and display the initial board

        init_board();
        display_board();
    }

    private void init_board() {
        /*
        This method is initializing the board. Board is a 2d array storing Piece Objects
        */

        //For the Red player
        for (int y = 0; y <=2; y++) {
            for (int x = 0; x <= 7; x++) {
                if (y%2 == 1 && x%2 == 0){
                    board[y][x] = new Piece(Color.Red, Rank.Pawn, x, y, this);
                } else if (y%2 == 0 && x%2 == 1) {
                    board[y][x] = new Piece(Color.Red, Rank.Pawn, x, y, this);
                } else {
                    board[y][x] = new Piece(Color.Empty, Rank.Empty, x, y, this);
                }
            }
        }
        //For the empty rows in between the players
        for (int y = 3; y <5; y++) {
            for (int x = 0; x <= 7; x++) {
                board[y][x] = new Piece(Color.Empty, Rank.Empty, x, y, this);
            }
        }
        //For the White player
        for (int y = 5; y <=7; y++) {
            for (int x = 0; x <= 7; x++) {
                if (y%2 == 1 && x%2 == 0){
                    board[y][x] = new Piece(Color.White, Rank.Pawn, x, y, this);
                } else if (y%2 == 0 && x%2 == 1){
                    board[y][x] = new Piece(Color.White, Rank.Pawn, x, y, this);
                } else {
                    board[y][x] = new Piece(Color.Empty, Rank.Empty, x, y, this);
                }
            }
        }
    }

    private void display_board() {
        /*
        This method takes 2d array board value convert it to visually understandable form and display it.
         */

        System.out.print("      a     b     c     d     e     f     g     h\n");
        System.out.print("  +-------------------------------------------------+\n");
        for (int i = 0; i < 8; i++) {
            int mapped_row = utils.reverse_map_rows(i);
            System.out.print(mapped_row +" |");
            for (int j = 0; j < 8; j ++) {
                String value = board[i][j].encoded_value();
                System.out.print(" [" + value + "]" );
            }
            System.out.print(" | " + mapped_row +"\n");
        }
        System.out.print("  +-------------------------------------------------+\n");
        System.out.print("      a     b     c     d     e     f     g     h\n");
    }

    public void make_move(String input, Color color) {
        /*
         * Takes validated input from player and moves pieces
         */
        List<Point> knock_out_positions = Moves.getKnockOutPosition();
        String[] col_rows = utils.get_current_future_positions(input);

        //Parse input, save start and future position in points and corresponding Pieces
        Point start_point = new Point(utils.map_columns(col_rows[0]), utils.map_rows(Integer.parseInt(col_rows[1])));
        Point future_point = new Point(utils.map_columns(col_rows[2]), utils.map_rows(Integer.parseInt(col_rows[3])));
        Piece start_pos = board[start_point.y][start_point.x];
        Piece future_pos = board[future_point.y][future_point.x];

        // move piece to future_point and replace start_point with empty piece
        start_pos.move_piece(future_point);

        //Not functional rn, wait for reimplementation of Moves
        for (int i = 0; i < knock_out_positions.size(); i++) {
            int row = (int) knock_out_positions.get(i).getX();
            int col = (int) knock_out_positions.get(i).getY();
            board[row][col] = new Piece(Color.Empty, Rank.Empty, row, col, this);
        }

        display_board();
    }

    public void update(String event_type, Point old_pos, Point new_pos, Piece updated_piece){
        /*
         * Updates Board to reflect changes in a pieces position
         */
        board[new_pos.y][new_pos.x] = null;
        board[new_pos.y][new_pos.x] = board[old_pos.y][old_pos.x];
        board[old_pos.y][old_pos.x] = new Piece(Color.Empty, Rank.Empty, old_pos.x, old_pos.y,this);
    }
    public int count_pieces(Color color) {
        /*
        count and return pieces of given color
        */
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if(piece.get_color() == color){
                   count++;
                }
            }
        }
        return count;
    }

    public boolean check_input_validity(String input, Color color) {
        /*
        input: input received from the player and player
        return: if input is valid or not by checking input format and moves
        */
        // Check if Input is valid format, e.g. [a3]X[b6]
        if (input.length() != 9) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\[[a-h][1-8]\\]X\\[[a-h][1-8]\\]");
        Matcher matcher = pattern.matcher(input);
        boolean input_validity = matcher.find();
        if(!input_validity){return false;}

        // Check if move from input is a valid move
        String[] positions = utils.get_current_future_positions(input);
        int current_mapped_row = utils.map_rows(Integer.parseInt(positions[1]));
        int future_mapped_row = utils.map_rows(Integer.parseInt(positions[3]));

        int current_mapped_col = utils.map_columns(positions[0]);
        int future_mapped_col = utils.map_columns(positions[2]);

        Piece current_piece = board[current_mapped_row][current_mapped_col];
        Piece future_piece = board[future_mapped_row][future_mapped_col];

        return current_piece.is_valid_move(future_piece, positions, color);
    }
}