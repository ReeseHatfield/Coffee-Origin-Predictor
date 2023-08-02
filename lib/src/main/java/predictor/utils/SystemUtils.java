package predictor.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SystemUtils {
    public static void suppressBLASandNETLIBWarnings(){
        try {
            System.setErr(new PrintStream(new FileOutputStream("error.log")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
