import java.awt.Point;
import java.util.*;

public class Moves {

    private static Piece[][] board;
    private static Color player_color;
    private static List<Point> knock_out_positions =new ArrayList<Point>();
    private static int row;
    private static int column;
    private static ArrayList<ArrayList<Point>> checkList = new ArrayList<ArrayList<Point>>();
    private static ArrayList<ArrayList<Point>> modifiedMultipleJump = new ArrayList<ArrayList<Point>>();

    public Moves(Piece[][] input_board, Color input_player){
        board = input_board;
        player_color = input_player;
        knock_out_positions.clear();
        checkList.clear();
        modifiedMultipleJump.clear();
    }

    public static boolean check_move(int current_row, int current_col, int future_row, int future_col){

        row = current_row;
        column = current_col;
        Point future_point = new Point(future_row, future_col);
        List<Point> listSimpleMoves = SimpleMove(current_row, current_col);
        ArrayList<ArrayList<Point>> listSingleJump = SingleJump(current_row, current_col);
        ArrayList<ArrayList<Point>> listMultipleJump = MultipleJump(current_row, current_col);
        ArrayList<ArrayList<Point>> modifiedListMultipleJump = ModifyMultipleJump(listMultipleJump);


        if (listSingleJump.size() ==0 && modifiedListMultipleJump.size() == 0){
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
            for(int k=0; k < modifiedListMultipleJump.size(); k++){
                ArrayList<Point> knockOutPosition = modifiedListMultipleJump.get(k);
                if(knockOutPosition.contains(future_point)){
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
        ArrayList<ArrayList<Point>> multipleJumpExtended = MultipleJump(current_row, current_column);
        ArrayList<ArrayList<Point>> multipleJumpModified = ModifyMultipleJump(multipleJumpExtended);
        List<Point> multipleJump = new ArrayList<Point>();
        for(int i=0; i<multipleJumpModified.size(); i++){
            List<Point> listPoint = multipleJumpModified.get(i);
            Point point = listPoint.get(listPoint.size()-1);
            multipleJump.add(point);
        }
        if(singleJump.size() == 0 && multipleJump.size() == 0){
            allPossibleMoves.addAll(simpleMoves);
        }


        allPossibleMoves.addAll(singleJump);
        allPossibleMoves.addAll(multipleJump);
        return allPossibleMoves;


    }

    private static List<Point> SimpleMove(int current_row, int current_column){
        List<Point> simpleMoves = new ArrayList<Point>();
        List<Point> diagonalPositions = DiagonalPositions(current_row, current_column);
        for (int i = 0; i < diagonalPositions.size(); i++){
            int row = (int) diagonalPositions.get(i).getX();
            int column = (int) diagonalPositions.get(i).getY();
            if(board[row][column].is_empty()){
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
            int nonEmptyRow = (int) nonEmptyDiagonals.get(i).getX();
            int nonEmptyColumn = (int) nonEmptyDiagonals.get(i).getY();
            Piece knock_out_board_value = board[nonEmptyRow][nonEmptyColumn];
            if(knock_out_board_value.piece_equals(player_color, Rank.Pawn) || knock_out_board_value.piece_equals(player_color, Rank.King)){
                continue;
            }
            else{
                if(nonEmptyRow > 0 && nonEmptyRow < 7 && nonEmptyColumn > 0 && nonEmptyColumn < 7){
                    if(current_row > nonEmptyRow && current_column < nonEmptyColumn){
                        if(board[nonEmptyRow-1][nonEmptyColumn+1].is_empty()){
                            singleJumpWay.add(new Point(current_row, current_column));
                            singleJumpWay.add(new Point(nonEmptyRow, nonEmptyColumn));
                            singleJumpWay.add(new Point(nonEmptyRow-1, nonEmptyColumn+1));
                            singleJump.add(singleJumpWay);
                        }
                    }
                    else if(current_row > nonEmptyRow && current_column > nonEmptyColumn){
                        if(board[nonEmptyRow-1][nonEmptyColumn-1].is_empty()){
                            singleJumpWay.add(new Point(current_row, current_column));
                            singleJumpWay.add(new Point(nonEmptyRow, nonEmptyColumn));
                            singleJumpWay.add(new Point(nonEmptyRow-1, nonEmptyColumn-1));
                            singleJump.add(singleJumpWay);
                        }
                    }
                    else if(current_row < nonEmptyRow && current_column < nonEmptyColumn){
                        if(board[nonEmptyRow+1][nonEmptyColumn+1].is_empty()){
                            singleJumpWay.add(new Point(current_row, current_column));
                            singleJumpWay.add(new Point(nonEmptyRow, nonEmptyColumn));
                            singleJumpWay.add(new Point(nonEmptyRow+1, nonEmptyColumn+1));
                            singleJump.add(singleJumpWay);
                        }
                    }
                    else if(current_row < nonEmptyRow && current_column > nonEmptyColumn){
                        if(board[nonEmptyRow+1][nonEmptyColumn-1].is_empty()){
                            singleJumpWay.add(new Point(current_row, current_column));
                            singleJumpWay.add(new Point(nonEmptyRow, nonEmptyColumn));
                            singleJumpWay.add(new Point(nonEmptyRow+1, nonEmptyColumn-1));
                            singleJump.add(singleJumpWay);
                        }
                    }
                }
            }
        }

        return singleJump;
    }

    private static ArrayList<ArrayList<Point>> MultipleJump (int current_row, int current_column){
        ArrayList<ArrayList<Point>> singleJump = SingleJump(current_row, current_column);
        for(int i=0; i<singleJump.size(); i++){
            ArrayList<Point> possibleMultiple = singleJump.get(i);
            checkList.add(possibleMultiple);
            int possibleRow = (int) possibleMultiple.get(2).getX();
            int possibleColumn = (int) possibleMultiple.get(2).getY();
            MultipleJump (possibleRow, possibleColumn);

        }
        ArrayList<ArrayList<Point>> multipleJump = checkList;
        return multipleJump;
    }

    private static ArrayList<ArrayList<Point>> ModifyMultipleJump(ArrayList<ArrayList<Point>> multipleJump){
        if(multipleJump.size() == 0){
            return modifiedMultipleJump;
        }
        ArrayList<Point> startJump = multipleJump.get(0);
        for (int i = 1; i < multipleJump.size(); i++){
            ArrayList<Point> nextJump = multipleJump.get(i);
            if(startJump.get(startJump.size()-1).equals(nextJump.get(0))){
                nextJump.remove(0);
                ArrayList<Point> newJump = startJump;
                newJump.addAll(nextJump);
                ArrayList<ArrayList<Point>> newModifiedMultipleJump = multipleJump;
                newModifiedMultipleJump.remove(0);
                modifiedMultipleJump.add(newJump);
                ModifyMultipleJump(newModifiedMultipleJump);

            }

        }
        return modifiedMultipleJump;
    }

    private static void UpdateKnockOut (ArrayList<Point> moves){
        for(int i=1; i < moves.size(); i+=2){
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
            if(!board[row][column].is_empty()){
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

        Piece board_value = board[row][column];

        if (board_value.piece_equals(Color.Red, Rank.King) || board_value.piece_equals(Color.White, Rank.King)) { // Previous: (board_value % 2 == 1)
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
        } else if (board_value.piece_equals(Color.Red, Rank.Pawn)) { // Previous: (board_value == 2)
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
        } else if (board_value.piece_equals(Color.White, Rank.Pawn)) { // Previous: (board_value == 4)
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