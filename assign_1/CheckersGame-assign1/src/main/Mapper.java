public class Mapper {
    public static int map_rows(String row) throws Exception {
        int mapped_row;
        if (row == "8") mapped_row = 0;
        else if (row == "7") mapped_row = 1;
        else if (row == "6") mapped_row = 2;
        else if (row == "5") mapped_row = 3;
        else if (row == "4") mapped_row = 4;
        else if (row == "3") mapped_row = 5;
        else if (row == "2") mapped_row = 6;
        else if (row == "1") mapped_row = 7;
        else {throw new Exception("Invalid input");} // use this else later to validate rows
        return mapped_row;
    }

    public static int map_columns(String column) throws Exception {
        int mapped_column;
        if (column == "a") mapped_column = 0;
        else if (column == "b") mapped_column = 1;
        else if (column == "c") mapped_column = 2;
        else if (column == "d") mapped_column = 3;
        else if (column == "e") mapped_column = 4;
        else if (column == "f") mapped_column = 5;
        else if (column == "g") mapped_column = 6;
        else if (column == "h") mapped_column = 7;
        else {throw new Exception("Invalid input");} // use this else later to validate columns
        return mapped_column;
    }

    public static String[] map_board_values(int board_value) throws Exception{
        String[] map_board_values = new String[2];
        if (board_value == -1) {
            map_board_values[0] = "white";
            map_board_values[1] = "empty";
        }
        else if (board_value == 0) {
            map_board_values[0] = "black";
            map_board_values[1] = "empty";
        }
        else if (board_value == 2) {
            map_board_values[0] = "red";
            map_board_values[1] = "pawn";
        }
        else if (board_value == 4) {
            map_board_values[0] = "white";
            map_board_values[1] = "pawn";
        }
        else if (board_value == 1) {
            map_board_values[0] = "red";
            map_board_values[1] = "king";
        }
        else if (board_value == 3) {
            map_board_values[0] = "white";
            map_board_values[1] = "king";
        }
        else {throw new Exception("Invalid input");}
        return map_board_values;
    }
}
