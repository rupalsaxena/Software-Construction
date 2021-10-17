import java.awt.Point;
import java.util.*;

public class Moves {
    // algorithms to check and priortise Simple move, Single jump, Multiple jump moves stay here

    public static List<Point> possible_positions = new ArrayList<Point>();
    public static List<Point> knock_out_positions = new ArrayList<Point>();

    public static boolean check_move(int current_row, int current_col, int future_row, int future_col) {
        List<Point> empty_positions = new ArrayList<Point>();
        Point future_point = new Point(future_row, future_col);
        knock_out_positions.clear();

        // check for simple move

        // get possible positions of adjacent diagonals where simple move can be possible
        possible_positions = Moves.get_possible_simple_move_positions(current_row, current_col);

        // for each possible diagonal positions check if the position is empty
        for (int i = 0; i < possible_positions.size(); i++){
            int row = (int) possible_positions.get(i).getX();
            int col = (int) possible_positions.get(i).getY();
            if (check_if_position_empty(row, col)) {
                empty_positions.add(new Point(row, col));
            }
        }

        // if all possible diagonal positions are empty then single move
        if (possible_positions.size() == empty_positions.size()) {
            if (possible_positions.contains(future_point)) {
                return true;
            }
            else return false;
        }
        // if one of the possible diagonal position not empty then check for single jump move
        else {
            // check for single jump move
            List<Point> possible_simple_jump_positions = Moves.get_possible_single_jump_moves(current_row, current_col, empty_positions, future_point);
            // if possible single jump moves is empty then go for simple move
            if (possible_simple_jump_positions.isEmpty()) {
                if (empty_positions.contains(future_point)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            // else check if future point is in possible simple jump moves
            else if (possible_simple_jump_positions.contains(future_point)) {
                return true;
            }
        }
        return false;
    }

    public static List get_possible_simple_move_positions(int current_row, int current_col) {
        // return all the possible simple move positions irrespective of whether its empty or not.
        int x;
        int y;
        Point position;
        List<Point> possible_positions = new ArrayList<Point>();

        int board_value = Board.board[current_row][current_col];

        if (board_value % 2 == 1) {
            x = current_row - 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
            x = current_row - 1;
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
            x = current_row + 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
            x = current_row + 1;
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
        } else if (board_value == 2) {
            x = current_row + 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
        } else if (board_value == 4) {
            x = current_row - 1;
            y = current_col - 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
            y = current_col + 1;
            if ((0 <= x && x <= 7) && (0 <= y && y <= 7)) {
                position = new Point(x, y);
                possible_positions.add(position);
            }
        }

        return possible_positions;
    }

    public static List get_possible_single_jump_moves(int current_row, int current_col, List empty_positions, Point future_point) {
        List<Point> possible_simple_jump_positions = new ArrayList<Point>();
        List<Point> possible_knock_out_positions = new ArrayList<Point>();
        String player = Game.player;

        possible_knock_out_positions.addAll(possible_positions);
        possible_knock_out_positions.removeAll(empty_positions);

        for (int i = 0; i < possible_knock_out_positions.size(); i++) {
            int possible_knock_out_row = (int) possible_knock_out_positions.get(i).getX();
            int possible_knock_out_col = (int) possible_knock_out_positions.get(i).getY();

            int knock_out_board_value = Board.board[possible_knock_out_row][possible_knock_out_col];
            String[] mapped_knock_out_board_value = Mapper.map_board_values(knock_out_board_value);
            if (player.equals(mapped_knock_out_board_value[0])) {
                continue;
            }

            int delta_row = possible_knock_out_row - current_row;
            int delta_col = possible_knock_out_col - current_col;
            int test_row = possible_knock_out_row + delta_row;
            int test_col = possible_knock_out_col + delta_col;
            if ((0 <= test_row && test_row <= 7) && (0 <= test_col && test_col <= 7)) {
                int test_board_value = Board.board[test_row][test_col];
                if (test_board_value == 0) {
                    possible_simple_jump_positions.add(new Point(test_row, test_col));
                    if (possible_simple_jump_positions.contains(future_point)) {
                        knock_out_positions.add(new Point(possible_knock_out_row, possible_knock_out_col));
                    }
                }
            }
        }
        return possible_simple_jump_positions;
    }

    public static boolean check_if_position_empty(int row, int col) {
        int board_value = Board.board[row][col];
        if (board_value == 0) return true;
        return false;
    }
}
// todo: rename simple jump variable