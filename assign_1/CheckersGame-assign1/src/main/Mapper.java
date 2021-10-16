public class Mapper {
    public static int map_rows(int row) {
        int mapped_row;
        if (row == 8) mapped_row = 0;
        else if (row == 7) mapped_row = 1;
        else if (row == 6) mapped_row = 2;
        else if (row == 5) mapped_row = 3;
        else if (row == 4) mapped_row = 4;
        else if (row == 3) mapped_row = 5;
        else if (row == 2) mapped_row = 6;
        else mapped_row = 7;
        //else {throw new Exception("Invalid input");} // use this else later to validate rows
        return mapped_row;
    }

    public static int reverse_map_rows(int row) {
        int reverse_mapped_row;
        if (row == 0) reverse_mapped_row = 8;
        else if (row == 1) reverse_mapped_row = 7;
        else if (row == 2) reverse_mapped_row = 6;
        else if (row == 3) reverse_mapped_row = 5;
        else if (row == 4) reverse_mapped_row = 4;
        else if (row == 5) reverse_mapped_row = 3;
        else if (row == 6) reverse_mapped_row = 2;
        else reverse_mapped_row = 1;
        //else {throw new Exception("Invalid input");} // use this else later to validate rows
        return reverse_mapped_row;
    }

    public static int map_columns(char column) {
        int mapped_column;
        if (column == 'a') mapped_column = 0;
        else if (column == 'b') mapped_column = 1;
        else if (column == 'c') mapped_column = 2;
        else if (column == 'd') mapped_column = 3;
        else if (column == 'e') mapped_column = 4;
        else if (column == 'f') mapped_column = 5;
        else if (column == 'g') mapped_column = 6;
        else mapped_column = 7;
        //else {throw new Exception("Invalid input");} // use this else later to validate columns
        return mapped_column;
    }

    public static String reverse_map_columns(int column) {
        String reverse_mapped_column;
        if (column == 0) reverse_mapped_column = "a";
        else if (column == 1) reverse_mapped_column = "b";
        else if (column == 2) reverse_mapped_column = "c";
        else if (column == 3) reverse_mapped_column = "d";
        else if (column == 4) reverse_mapped_column = "e";
        else if (column == 5) reverse_mapped_column = "f";
        else if (column == 6) reverse_mapped_column = "g";
        else reverse_mapped_column = "h";
        //else {throw new Exception("Invalid input");} // use this else later to validate columns
        return reverse_mapped_column;
    }

    public static String[] map_board_values(int board_value) {
        String[] map_board_values = new String[3];
        if (board_value == -1) {
            map_board_values[0] = "white";
            map_board_values[1] = "empty";
            map_board_values[2] = "   ";
        }
        else if (board_value == 0) {
            map_board_values[0] = "black";
            map_board_values[1] = "empty";
            map_board_values[2] = "   ";
        }
        else if (board_value == 2) {
            map_board_values[0] = "red";
            map_board_values[1] = "pawn";
            map_board_values[2] = "R_P";
        }
        else if (board_value == 4) {
            map_board_values[0] = "white";
            map_board_values[1] = "pawn";
            map_board_values[2] = "W_P";
        }
        else if (board_value == 1) {
            map_board_values[0] = "red";
            map_board_values[1] = "king";
            map_board_values[2] = "R_K";
        }
        else if (board_value == 3) {
            map_board_values[0] = "white";
            map_board_values[1] = "king";
            map_board_values[2] = "W_K";
        }
        //else {throw new Exception("Invalid input");}
        return map_board_values;
    }
}
