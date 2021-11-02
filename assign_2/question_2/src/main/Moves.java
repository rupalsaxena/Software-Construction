import java.awt.Point;
import java.util.*;

public class Moves {
    /*
    Class responsibility is to check and priortise allowed moves in a particular chance of player.
     */

    private static List<Point> possible_diagonal_positions = new ArrayList<Point>();
    private static List<Point> knock_out_positions = new ArrayList<Point>();
    private static List<Point> possible_single_jump_positions = new ArrayList<Point>();
    private static List<Point> possible_knock_out_positions = new ArrayList<Point>();
    private static List<Point> empty_positions = new ArrayList<Point>();
    private static int[][] board;
    private static String player;

    public Moves(int[][] input_board, String input_player) {
        board = input_board;
        player = input_player;

        empty_positions.clear();
        knock_out_positions.clear();
        possible_diagonal_positions.clear();
        possible_knock_out_positions.clear();
        possible_single_jump_positions.clear();
    }

    public static boolean check_move(int current_row, int current_col, int future_row, int future_col) {
        /*
        check if the future move is a valid move. If so is it knocking out some pieces.
         */

        Point future_point = new Point(future_row, future_col);

        // check for simple move

        // update possible positions of adjacent diagonals where simple move can be possible
        update_possible_simple_move_positions(current_row, current_col);

        // for each possible diagonal positions check if the position is empty
        for (int i = 0; i < possible_diagonal_positions.size(); i++){
            int row = (int) possible_diagonal_positions.get(i).getX();
            int col = (int) possible_diagonal_positions.get(i).getY();
            if (check_if_position_empty(row, col)) {
                empty_positions.add(new Point(row, col));
            }
        }

        // if all possible diagonal positions are empty then single move
        if (possible_diagonal_positions.size() == empty_positions.size()) {
            if (possible_diagonal_positions.contains(future_point)) {
                return true;
            }
            else return false;
        }
        // if one or more of the possible next diagonal position not empty then check for single jump move
        else {
            // update for single jump move
            update_possible_single_jump_moves(current_row, current_col, future_point);
            // if possible single jump moves is empty then go for simple move
            if (possible_single_jump_positions.isEmpty()) {
                if (empty_positions.contains(future_point)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            //check if from the points in possible_single_jump_positions diagonals are empty
            //if empty, check if future_point is in possible_jump_moves
            //else check if possible_single_jump_moves from not empty diagonals is possible, if true add this point to multiple_jump_move
            //repeat until no more rows and columns possible
            //check if future point is in multiple_jump_positions
            //if not check if future point in single jump move
            

            // else check if future point is in possible simple jump moves
            else if (possible_single_jump_positions.contains(future_point)) {
                return true;
            }
        }
        return false;
    }

    private static void update_possible_simple_move_positions(int current_row, int current_col) {
        /*
        return all the possible diagonal move positions irrespective of whether its empty or not.
         */

        int x;
        int y;
        Point position;

        int board_value = board[current_row][current_col];

        if (board_value % 2 == 1) {
            x = current_row - 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
            x = current_row - 1;
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
            x = current_row + 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
            x = current_row + 1;
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
        } else if (board_value == 2) {
            x = current_row + 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
        } else if (board_value == 4) {
            x = current_row - 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_diagonal_positions.add(position);
            }
        }

    }

    private static void update_possible_single_jump_moves(int current_row, int current_col, Point future_point) {
        /*
        return: possible single jump moves
         */

        possible_knock_out_positions.addAll(possible_diagonal_positions);
        possible_knock_out_positions.removeAll(empty_positions);

        for (int i = 0; i < possible_knock_out_positions.size(); i++) {
            int possible_knock_out_row = (int) possible_knock_out_positions.get(i).getX();
            int possible_knock_out_col = (int) possible_knock_out_positions.get(i).getY();

            int knock_out_board_value = board[possible_knock_out_row][possible_knock_out_col];
            String[] mapped_knock_out_board_value = utils.map_board_values(knock_out_board_value);
            if (player.equals(mapped_knock_out_board_value[0])) {
                // piece belong to the same player. Continue checking for next possible knock out position.
                continue;
            }

            int delta_row = possible_knock_out_row - current_row;
            int delta_col = possible_knock_out_col - current_col;
            int test_row = possible_knock_out_row + delta_row;
            int test_col = possible_knock_out_col + delta_col;
            if ((0 <= test_row && test_row <= 7) && (0 <= test_col && test_col <= 7)) {
                int test_board_value = board[test_row][test_col];
                if (test_board_value == 0) {
                    possible_single_jump_positions.add(new Point(test_row, test_col));
                    if (possible_single_jump_positions.contains(future_point)) {
                        knock_out_positions.add(new Point(possible_knock_out_row, possible_knock_out_col));
                        break;
                    }
                }
            }
        }
    }

    private static boolean check_if_position_empty(int row, int col) {
        /*
        checks if input positions are empty on board
         */

        int board_value = board[row][col];
        if (board_value == 0) return true;

        return false;
    }

    public static List<Point> getKnockOutPosition() {
        return knock_out_positions;
    }

}