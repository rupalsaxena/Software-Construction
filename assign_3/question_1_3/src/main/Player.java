//enum PlayerColor{Red, White}

public class Player{
    String player_name;
    Color player_color;

    public void set_player_name(String name){
        this.player_name = name;
    }

    public void set_player_color(Color color){
        this.player_color = color;
    }

    public static String get_player_name(Player p){
        return p.player_name;
    }

    public static Color get_player_color(Player p){
        return p.player_color;
    }
}