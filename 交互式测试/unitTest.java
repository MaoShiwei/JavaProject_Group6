package gitsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class unitTest {
    //生成hash值测试函数
    public static void testSingleKey(String filepath){
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
    //生成Commit测试函数
    public static void testCommit(String filepath) throws Exception {
        String author = "java";
        String committer = "java";
        String comment = "success!";
        String parent = "null";
        Commit commit = new Commit(filepath, parent, author, committer, comment);
        commit.ComputeCommit();
        commit.write();
        System.out.println("成功提交commit: " + commit.GetKey());
        System.out.println(commit.ShowCommitList());
    }



    //生成Tree测试
    public static void testTree(File file){
        try{
            if(file.isDirectory()){
                System.out.println("Tree:"+new Tree(file).GetKey());
            }
            else{
                System.out.println("Blob:"+new Blob(file).GetKey());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //生成value测试
    public static void testKeyValueStorage(File file) throws Exception{
        try{
            if(file.isFile()) {
                KeyValueStorage keyValueStorage = new KeyValueStorage();
                //获取文件key
                String key = keyValueStorage.GetKey(new FileInputStream(file));
                //将文件重新写入 用于下面GetValue 获取到文件
                keyValueStorage.WriteToFile(keyValueStorage.GetValue(key), "D:\\Java\\managebase\\content.txt");
                //通过key获取文件
                String s = keyValueStorage.GetValue(key);
                System.out.println("文件的内容value是：" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件或文件夹的路径名：");
        String path = input.next();
        testSingleKey(path);
        //testCommit(path);
        //testCommit("D:\\Java\\homework1105");
        testTree(new File(path));
        testKeyValueStorage(new File(path));
        HEAD head = new HEAD();
        System.out.println(head.getHEAD());
    }
}