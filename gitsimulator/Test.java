package gitsimulator;

import java.io.File;
import java.util.Scanner;

public class Test {
    //生成hash值测试函数
    public static void testKey(String filepath){
        File file = new File(filepath);
        try{
            if(file.isDirectory()){
                Tree tree = new Tree(file);
                tree.write();
                System.out.println("文件夹的hash值为：" + tree.GetKey());
            }
            else if(file.isFile()){
                Blob blob = new Blob(file);
                blob.write(filepath);
                System.out.println("文件的hash值为：" + blob.GetKey());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件或文件夹的路径名：");
        String path = input.next();
        testKey(path);
    }
}
