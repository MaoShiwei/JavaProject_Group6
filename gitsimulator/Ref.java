package gitsimulator;

import java.io.*;

public class Ref {

    public void Write(String path, String value) throws Exception{
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file.createNewFile();
        Writer out = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(value);
        bw.close();
    }

    public String Readfile(String path) {
        File file = new File(path);
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine())!=null){
                result = result + s + "\n";
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
