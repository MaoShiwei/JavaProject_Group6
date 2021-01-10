package gitsimulator;

import java.io.*;

public class Ref {//HEAD,Branch,Reset�ĸ���

    public void Write(String path, String value) throws Exception{//д�����,��valueд��ָ��path��
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

    public void WriteToFile(String savepath, String targetpath) throws Exception{//�ļ����ļ�֮��Ķ�ȡд���������targetpath�ļ�������д��savepath�ļ���
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

    public static String Readfile(String path) {//��ȡָ��path�µ��ļ��������ļ�����
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

    public void initiate(String path) {//��ʼ����Ŀ����·��
        File file = new File(path);
        boolean IsOK = file.exists();
        if (IsOK){
            file.delete();
        }
    }
}
