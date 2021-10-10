public class ChristmasSong{
    public static void main(String[] args){
        ChristmasSong day_info = new ChristmasSong();
        int para = 1;
        String line_n = "A partridge in a pear tree.\n";
        while (para <= 12){
            String[] day_line = day_info.get_day_and_lines(para);
            String line_1 = "On the " + day_line[0] + " day of Christmas\nmy true love sent to me";
            if (para != 1){
                line_n = day_line[1] + "\n" + line_n;
            }
            System.out.println(line_1 + "\n" + line_n);
            para = para + 1;
        }
    }

    public String[] get_day_and_lines(int para){
        String[] day_line = new String[2];
        if (para == 1){
            day_line[0] = "first";
            day_line[1] = "";
        } else if (para == 2) {
            day_line[0] = "second";
            day_line[1] = "Two turtle doves, and";
        } else if (para == 3) {
            day_line[0] = "third";
            day_line[1] = "Three French hens,";
        } else if (para == 4) {
            day_line[0] = "fourth";
            day_line[1] = "Four colly birds,";
        } else if (para == 5) {
            day_line[0] = "fifth";
            day_line[1] = "Five gold rings,";
        } else if (para == 6) {
            day_line[0] = "sixth";
            day_line[1] = "Six geese a laying,";
        } else if (para == 7) {
            day_line[0] = "seventh";
            day_line[1] = "Seven Swans a Swimming,";
        } else if (para == 8) {
            day_line[0] = "eighth";
            day_line[1] = "Eight maids a milking,";
        } else if (para == 9) {
            day_line[0] = "ninth";
            day_line[1] = "Nine drummers drumming,";
        } else if (para == 10) {
            day_line[0] = "tenth";
            day_line[1] = "Ten pipers piping,";
        } else if (para == 11) {
            day_line[0] = "eleventh";
            day_line[1] = "Eleven ladies dancing,";
        } else {
            day_line[0] = "twelth";
            day_line[1] = "Twelve lords a leaping,";
        }
        return day_line;
    }
}