import java.awt.Point;
import java.util.*;

public class Moves {

    private static int[][] board;
    private static String player;
    private static List<Point> knock_out_positions =new ArrayList<Point>();
    private static List<Point> possible_knock_out_positions =new ArrayList<Point>();
    private static int row;
    private static int column;
    private static ArrayList<ArrayList<Point>> checkList = new ArrayList<ArrayList<Point>>();
    
    
    public Moves(int [][] input_board, String input_player){
        board = input_board;
        player = input_player;
        knock_out_positions.clear();
        possible_knock_out_positions.clear();
        checkList.clear();

    }

    public static boolean check_move(int current_row, int current_col, int future_row, int future_col){

        row = current_row;
        column = current_col;
        Point future_point = new Point(future_row, future_col);
        List<Point> listSimpleMoves = SimpleMove(current_row, current_col);
        ArrayList<ArrayList<Point>> listSingleJump = SingleJump(current_row, current_col);
        
        if (listSingleJump.size() ==0){
            if(listSimpleMoves.contains(future_point)){
                return true;
            }
            else return false;
        }
        else{
            for(int i=0; i < listSingleJump.size(); i++){
                ArrayList<Point> knockOutPosition = listSingleJump.get(i);
                if (knockOutPosition.contains(future_point)){
                    UpdateKnockOut(knockOutPosition);
                    return true;
                }

            }
            return false;
        }


    }


    public static List<Point> getAllPossibleMoves(int current_row, int current_column){
        row = current_row;
        column = current_column;
        List<Point> allPossibleMoves = new ArrayList<Point>();
        List<Point> simpleMoves = SimpleMove(current_row, current_column);
        ArrayList<ArrayList<Point>> singleJumpExtended = SingleJump(current_row, current_column);
        List<Point> singleJump = new ArrayList<Point>();
        for(int i=0; i<singleJumpExtended.size(); i++){
            List<Point> listPoint = singleJumpExtended.get(i);
            Point point = listPoint.get(listPoint.size()-1);
            singleJump.add(point);
        }
        //ArrayList<ArrayList<Point>> multipleJumpExtended = MultipleJump(current_row, current_column);
        // List<Point> multipleJump = new ArrayList<Point>();
        // for(int i=0; i<multipleJumpExtended.size(); i++){
        //     List<Point> listPoint = multipleJumpExtended.get(i);
        //      Point point = listPoint.get(listPoint.size()-1);
        //     multipleJump.add(point);
        // }
        //if(singleJump.size() == 0 && multipleJump.size() == 0){
        //     allPossibleMoves.add(simpleMoves);
        // }
        if(singleJump.size() == 0){
            allPossibleMoves.addAll(simpleMoves);
        }
        
        allPossibleMoves.addAll(singleJump);
        //allPossibleMoves.add(multipleJump);
        return allPossibleMoves;


    }

    private static List<Point> SimpleMove(int current_row, int current_column){
        List<Point> simpleMoves = new ArrayList<Point>();
        List<Point> diagonalPositions = DiagonalPositions(current_row, current_column);
        for (int i = 0; i < diagonalPositions.size(); i++){
            int row = (int) diagonalPositions.get(i).getX();
            int column = (int) diagonalPositions.get(i).getY();
            if(board[row][column] == 0){
                simpleMoves.add(new Point(row, column));
            }
        }

        return simpleMoves;
    }
    private static ArrayList<ArrayList<Point>> SingleJump(int current_row, int current_column){
        ArrayList<ArrayList<Point>> singleJump = new ArrayList<ArrayList<Point>>();
        
        List<Point> nonEmptyDiagonals = NotEmptyDiagonal(current_row, current_column);
        for(int i=0; i<nonEmptyDiagonals.size(); i++){
            ArrayList<Point> singleJumpWay = new ArrayList<Point>();
            row = (int) nonEmptyDiagonals.get(i).getX();
            column = (int) nonEmptyDiagonals.get(i).getY();
            int knock_out_board_value = board[row][column];
            String[] mapped_knock_out_board_value = utils.map_board_values(knock_out_board_value);
            if(player.equals(mapped_knock_out_board_value[0])){
                continue;
            }
            else{
                if(current_row > row && current_column < column){
                    if(board[row-1][column+1] == 0){
                        singleJumpWay.add(new Point(row, column));
                        singleJumpWay.add(new Point(row-1, column+1));
                        singleJump.add(singleJumpWay);
                }
            }
                else if(current_row > row && current_column > column){
                    if(board[row-1][column-1] == 0){
                        singleJumpWay.add(new Point(row, column));
                        singleJumpWay.add(new Point(row-1, column-1));
                        singleJump.add(singleJumpWay);
                    }
                }
                else if(current_row < row && current_column < column){
                    if(board[row+1][column+1]== 0){
                        singleJumpWay.add(new Point(row, column));
                        singleJumpWay.add(new Point(row+1, column+1));
                        singleJump.add(singleJumpWay);
                    }
                }
                else if(current_row < row && current_column > column){
                    if(board[row+1][column-1]== 0){
                        singleJumpWay.add(new Point(row, column));
                        singleJumpWay.add(new Point(row+1, column-1));
                        singleJump.add(singleJumpWay);
                    }
                }
            }
        }
        
        return singleJump;
    }

    private static void UpdateKnockOut (ArrayList<Point> moves){
        for(int i=0; i < moves.size(); i+=2){
            row = (int) moves.get(i).getX();
            column = (int) moves.get(i).getY();
            knock_out_positions.add(new Point(row, column));
        }
    }




    private static List<Point> NotEmptyDiagonal(int current_row, int current_column){
        List<Point> nonEmptyDiagonals = new ArrayList<Point>();
        List<Point> diagonalPositions = DiagonalPositions(current_row, current_column);
        for (int i = 0; i < diagonalPositions.size(); i++){
            int row = (int) diagonalPositions.get(i).getX();
            int column = (int) diagonalPositions.get(i).getY();
            if(board[row][column] != 0){
                nonEmptyDiagonals.add(new Point(row, column));
            }
        }
        return nonEmptyDiagonals;
    }


    private static List<Point> DiagonalPositions(int current_row, int current_column){
        List<Point> diagonalPositions = new ArrayList<Point>();
        int x;
        int y;
        Point position;
    
        int board_value = board[row][column];
        


        if (board_value % 2 == 1) {
            x = current_row - 1;
            y = current_column - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
            x = current_row - 1;
            y = current_column + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
            x = current_row + 1;
            y = current_column - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
            x = current_row + 1;
            y = current_column + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
        } else if (board_value == 2) {
            x = current_row + 1;
            y = current_column - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
            y = current_column + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
        } else if (board_value == 4) {
            x = current_row - 1;
            y = current_column - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
            y = current_column + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                diagonalPositions.add(position);
            }
        }

        return diagonalPositions;
    }

    public static List<Point> getKnockOutPosition(){
        return knock_out_positions;
    }
}
