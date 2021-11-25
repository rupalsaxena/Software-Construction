import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;
public class Leaderboard {
    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        InputStream inputStream =  yaml.getClass().getClassLoader().getResourceAsStream("test.yaml");
        Map<String, Object> obj = yaml.load(inputStream);
        System.out.println(obj);
    }
}
//write to yaml file
//find person with max wins
//read yaml and parse yaml to add new values to a winner if winner exists or add new entry