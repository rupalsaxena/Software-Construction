import java.util.Scanner;

public class utils {
    /*
    Class responsibility: To provide some utility functions to support the code.
     */

    static Scanner scanner = new Scanner(System.in);

    public static String[] get_current_future_positions(String input) {
        // return {"current col", "current row", "future col", "future row"}
        String[] col_rows = new String[4];
        for (int i = 0; i < input.length(); i++) {
            String character = Character.toString(input.charAt(i));
            if (i == 1 || i == 2) {
                col_rows[i-1] = character;
            }
            else if (i == 6) {
                col_rows[2] = character;
            }
            else if (i == 7) {
                col_rows[3] = character;
            }
        }
        return col_rows;
    }

    public static String input_move(String player) {
        System.out.println("Input your move player " + player + ":");
        String input = scanner.nextLine();
        return input;
    }
}
