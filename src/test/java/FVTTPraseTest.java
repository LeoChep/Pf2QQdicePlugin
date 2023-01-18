import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example3.fvttDbParse.FvttDbParser;

import java.io.File;
import java.io.IOException;

public class FVTTPraseTest {
    public static  void  main(String[] args)  {
        File file=new File("D:\\workpalce\\tld\\miraiTest\\debug-sandbox\\data\\vampire-mastermind.json");
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode=objectMapper.readValue(file, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new FvttDbParser().parse(jsonNode));
    }


}
