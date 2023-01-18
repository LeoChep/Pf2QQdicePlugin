import org.example3.StringInterpreter;

public class ParseTest {
    public static  void  main(String[] args){
        StringInterpreter instance = StringInterpreter.INSTANCE;
        boolean testResult=instance.parse("巨蜂","","巨蜂");
        System.out.println(testResult);
    }

}
