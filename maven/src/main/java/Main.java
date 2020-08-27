import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Daulet Zholtayev
 * @since 27.08.2020 - 19:44
 */
public class Main {

    public static void main(String[] args) {

        InputStream stream = Main.class.getClassLoader()
                .getResourceAsStream("db.properties");

        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String psw = null;
        if(properties.containsKey("db.password")) {
            psw = properties.getProperty("db.password");
        }
        System.out.println(psw);

//        List<String> list = new ArrayList();
//        list.stream().map(data-> {
//            data += "ent";
//            return data;
//        }).collect(Collectors.toList());

    }
}
