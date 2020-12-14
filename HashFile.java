import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;

public class HashFile {
    public static byte[] SHA1Checksum(InputStream is) throws Exception {
        byte[] buffer = new byte[1024];// 用于计算hash值的文件缓冲区
        MessageDigest complete = MessageDigest.getInstance("SHA-1");// 使用SHA1哈希/摘要算法
        int numRead = 0;
        do {
            numRead = is.read(buffer);// 读出numRead字节到buffer中
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);// 根据buffer[0:numRead]的内容更新hash值
            }
        } while (numRead != -1);// 文件已读完，退出循环
        is.close();// 关闭输入流
        return complete.digest();// 返回SHA1哈希值
    }

    public static String readfile(String path) {
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

    public static void writefile(String array,String path) throws IOException {
        String filepath = "";
        String filecontext = "";
        filepath = path;
        filecontext = array;
        File file = new File(filepath);
        file.createNewFile();
        Writer out = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(filecontext);
        bw.close();
    }

    public static void writecontent(String array) throws IOException {
        String content = "";
        String path = "D:\\Java\\managebase\\content.txt";
        File file = new File(path);
        file.createNewFile();
        content = readfile(path);
        content = content + array;
        Writer out = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(content);
        bw.close();
    }

    public static void dfs(String path) throws IOException {
        File dir = new File(path);
        File[] fs = dir.listFiles();
        int a = fs.length;
        String array = "";
        for(int i = 0; i < a; i++) {
            if(fs[i].isFile()) {
                try{
                    FileInputStream is = new FileInputStream(fs[i]);
                    byte[] sha1 = SHA1Checksum(is);
                    String result = "";
                    for (int j = 0; j < sha1.length; j++) {
                        result += Integer.toString(sha1[j]&0xFF, 16);
                    }
                    String context = "";
                    String savepath = "D:\\Java\\managebase\\files";
                    context = readfile(path + File.separator + fs[i].getName());
                    savepath = savepath + File.separator + result +".txt";
                    writefile(context,savepath);
                    System.out.println("The Hash of "+ fs[i].getName() + " is " + result);
                    array = array + "blob " + result + " " + fs[i].getName()  + "\n";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(fs[i].isDirectory()) {
                dfs(path + File.separator + fs[i].getName());
                try{
                    byte[] directory = array.getBytes();
                    MessageDigest complete = MessageDigest.getInstance("SHA-1");
                    complete.update(directory);
                    byte[] sha1 = complete.digest();
                    String result = "";
                    for (int j = 0; j < sha1.length; j++) {
                        result += Integer.toString(sha1[j]&0xFF, 16);
                    }
                    System.out.println("The Hash of "+ fs[i].getName() + " is " + result);
                    array = array + "tree " + result + " " + fs[i].getName() + "\n";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        writecontent(array);
    }

    public static void main(String[] args) {
        try {
            String path = "D:\\Java\\homework1105";  //文件存储路径
            String contentpath = "D:\\Java\\managebase\\content.txt";  //文件目录存储路径
            File file = new File(contentpath);
            boolean isOK = file.exists();
            if(isOK){
                file.delete();
            }
            dfs(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
