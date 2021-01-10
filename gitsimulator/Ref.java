package gitsimulator;

import java.io.*;

public class Ref {//HEAD,Branch,Reset的父类

    public void Write(String path, String value) throws Exception{//写入操作,将value写入指定path中
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

    public void WriteToFile(String savepath, String targetpath) throws Exception{//文件与文件之间的读取写入操作，将targetpath文件的内容写入savepath文件中
        File file = new File(savepath);
        if (!file.getParentFile().exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file.createNewFile();
        FileInputStream input = new FileInputStream(targetpath);
        FileOutputStream output = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        do {
            numRead = input.read(buffer);
            if(numRead > 0){
                output.write(buffer,0,numRead);
            }
        }while(numRead!=-1);
        input.close();
        output.close();
    }

    public static String Readfile(String path) {//读取指定path下的文件并返回文件内容
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

    public void initiate(String path) {//初始化项目管理路径
        File file = new File(path);
        boolean IsOK = file.exists();
        if (IsOK){
            file.delete();
        }
    }
}
