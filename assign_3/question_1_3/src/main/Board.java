import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board implements Observer{
    /*
     * Provides 2D-Array board of Pieces
     */

    private static Piece[][] board = new Piece[8][8];
    private int CountRedPieces;
    private int CountWhitePieces;

    public Board() {
        // initialize and display the initial board
        CountRedPieces = 0;
        CountWhitePieces = 0;
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
                    CountRedPieces++;
                } else if (y%2 == 0 && x%2 == 1) {
                    board[y][x] = new Piece(Color.Red, Rank.Pawn, x, y, this);
                    CountRedPieces++;
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
                    CountWhitePieces++;
                } else if (y%2 == 0 && x%2 == 1){
                    board[y][x] = new Piece(Color.White, Rank.Pawn, x, y, this);
                    CountWhitePieces++;
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
        Piece start_piece = board[start_point.y][start_point.x];
        Piece future_piece = board[future_point.y][future_point.x];

        // Remove Piece on future_point, move Piece from start_point to future_point
        future_piece.eliminate_piece();
        start_piece.move_piece(future_point);


        for (int i = 0; i < knock_out_positions.size(); i++) {
            int row = (int) knock_out_positions.get(i).getX();
            int col = (int) knock_out_positions.get(i).getY();
            board[row][col].eliminate_piece();
        }

        display_board();
    }

    public void update(String event_type, Point old_pos, Point new_pos, Color new_pos_color){
        /*
         * Updates Board to reflect changes in a pieces position
         */
        if(event_type.equals("Removed")){
            if(new_pos_color == Color.White){
                CountWhitePieces--;
            } else if(new_pos_color == Color.Red) {
                CountRedPieces--;
            }
        }
        else if(event_type.equals("Moved")) {
            board[new_pos.y][new_pos.x] = null;
            board[new_pos.y][new_pos.x] = board[old_pos.y][old_pos.x];
            board[old_pos.y][old_pos.x] = new Piece(Color.Empty, Rank.Empty, old_pos.x, old_pos.y, this);
        }
    }
    public int count_pieces(Color color) {
        /*
        count and return pieces of given color
        */
        if (color == Color.White){
            return CountWhitePieces;
        } else {
            return CountRedPieces;
        }
    }

    public boolean check_input_validity(String input, Color color) {
        /*
        input: input received from the player and player
        return: if input is valid or not by checking input format and moves
        */

        // Check if Input is valid format, e.g. [a3]X[b6]
        if (input.length() != 9) {return false;}
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

        return current_piece.is_valid_move(future_piece, positions, color, board);
    }

    public static String getAllPossibleMoves (String input, Color player_color){
        List<Point> allMoves = new ArrayList<Point>();
        List<String> allMovesString = new ArrayList<String>();
        String[] col_rows = utils.get_current_future_positions(input);
        int current_mapped_row = utils.map_rows(Integer.valueOf(col_rows[1]));
        int current_mapped_col = utils.map_columns(col_rows[0]);

        /* Not needed, already done in Piece.is_valid_move(). Check with Elena
        Boolean is_valid_position = Validation.is_valid_position(current_mapped_row, current_mapped_col, player, board);

        if(is_valid_position){
            allMoves = Moves.getAllPossibleMoves(current_mapped_row, current_mapped_col);
        }
        */
        Moves moves = new Moves(board, player_color);
        allMoves = moves.getAllPossibleMoves(current_mapped_row, current_mapped_col);
        for (int i= 0; i<allMoves.size(); i++){
            int row = (int) allMoves.get(i).getX();
            int column = (int) allMoves.get(i).getY();
            int board_row = utils.reverse_map_rows(row);
            String board_column = utils.reverse_map_columns(column);
            String board_value = board_column + board_row;
            allMovesString.add(board_value);
        }
        return allMovesString.toString();
    }

    public boolean check_all_possible_moves(Color player_color){
        Moves moves = new Moves(board, player_color);

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Piece boardPiece = board[i][j];
                if(boardPiece.pCol.name() == player_color.name()){
                    Point boardValue = boardPiece.pos;
                    int row = (int) boardValue.getX();
                    int column = (int) boardValue.getY();
                    List<Point> possibleMove = moves.getAllPossibleMoves(row, column);
                    if(possibleMove.size() != 0){
                        return true;
                    }
                    
                };

            }
        }
        
    
        
        return false;

    }
}