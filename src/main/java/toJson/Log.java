package toJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Log {
    public static void main(String[] args) {
        String str = "RAge";
        File f = new File("data.text");

        //write file
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(str);
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
