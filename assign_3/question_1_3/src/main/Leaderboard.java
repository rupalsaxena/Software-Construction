import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class Leaderboard {
    private String score_board_path = "test.yaml";

    private Map<String, Integer> read_scoreboard() {
        Yaml yaml = new Yaml();
        InputStream inputStream = yaml.getClass().getClassLoader().getResourceAsStream(this.score_board_path);
        Map<java.lang.String, java.lang.Integer> obj = yaml.load(inputStream);
        return obj;
        // what if the file does not exist or is empty?
    }
    public int read_player_score(String player_name) {
        Map<String, Integer> scoreboard = this.read_scoreboard();
        Integer player_score = scoreboard.get(player_name);

        if (player_score == null) {
            player_score = 0;
        }
        return player_score;
    }

    public void update_player_score(String player_name) throws IOException {
        //just add 1 to the existing score
        Map<String, Integer> scores = read_scoreboard();
        int player_score = this.read_player_score(player_name);
        player_score+=1;
        //return player_score;
        scores.put(player_name, player_score);
        System.out.println(scores);
        // TODO: write it actually to the file
        Yaml yaml = new Yaml();
        //StringWriter writer = new StringWriter();
        //PrintWriter writer = new PrintWriter(new File(score_board_path));
        FileWriter writer = new FileWriter(score_board_path);
        yaml.dump(scores, writer);
        writer.close();
        //System.out.println();
    }

    public static void main(String[] args) throws Exception {
//        Yaml yaml = new Yaml();
//        InputStream inputStream = yaml.getClass().getClassLoader().getResourceAsStream("test.yaml");
//        Map<String, Object> obj = yaml.load(inputStream);
//        System.out.println(obj);
        Leaderboard lb = new Leaderboard();
        Map<String, Integer> scoreboard = lb.read_scoreboard();
        System.out.println(scoreboard);
        int AI_score = lb.read_player_score("Sam");
        System.out.println(AI_score);
        lb.update_player_score("AI");
        AI_score = lb.read_player_score("Sam");
        System.out.println(AI_score);
        scoreboard = lb.read_scoreboard();
        System.out.println(scoreboard);
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
    }
}
//write to yaml file
//find person with max wins
//read yaml and parse yaml to add new values to a winner if winner exists or add new entry