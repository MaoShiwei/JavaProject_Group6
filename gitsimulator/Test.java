package gitsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Scanner;

public class Test {
    //生成单个文件夹或文件的hash值测试函数
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
    //生成整个文件夹project的hash值测试函数
    public static void testAllKey(String filepath){
        testSingleKey(filepath);
        File dir = new File(filepath);
        File[] fs = dir.listFiles();
        int a = fs.length;
        String array = "";
        for(int i = 0; i < a; i++) {
            if(fs[i].isFile()) {
                try{
                    Blob blob = new Blob(filepath + File.separator + fs[i].getName());
                    blob.write(filepath + File.separator + fs[i].getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(fs[i].isDirectory()) {
                try{
                    Tree tree = new Tree(filepath + File.separator + fs[i].getName());
                    tree.write();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                testAllKey(filepath + File.separator + fs[i].getName());
            }
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
    //生成reset测试函数
    public static void testReset(String commitkey, String resetpath) throws Exception {
        Reset reset = new Reset();
        reset.Resetcommit(commitkey, resetpath);
    }

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件或文件夹的路径名：");
        String path = input.next();
        testAllKey(path);
        testCommit("D:\\Java\\test.docx");
        testCommit(path);
        HEAD head = new HEAD();
        System.out.println(head.getHEAD());
        //testReset("f191141f83d69e2ca5287bd5881635903fc8e6a7", "D:\\Java\\reset");
    }
}
