package gitsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class unitTest {
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

    //生成gitObject测试
    public static void testGitObject(File file) throws Exception{
        try{
            if(file.isFile()) {
                GitObject gitObject = new GitObject();
                //获取文件key
                String key = gitObject.GetKey(new FileInputStream(file));
                //将文件重新写入 用于下面GetValue 获取到文件
                gitObject.WriteToFile(gitObject.GetValue(key), "D:\\Java\\managebase\\kvstest.txt");
                //通过key获取文件
                String s = gitObject.GetValue(key);
                System.out.println("文件的内容是：" + s);
                System.out.println("文件的value是："+new Blob(file).GetType()+" "+new Blob(file).Getvrtype()+" "+new Blob(file).GetKey());
            }
            else if(file.isDirectory()){
                System.out.println("文件夹的内容是：" +new Tree(file).GetValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成reset测试函数
    public static void testReset(String commitkey, String resetpath) throws Exception {
        Reset reset = new Reset();
        reset.Resetcommit(commitkey, resetpath);
    }

    //生成分支branch测试函数
    public static void testBranch(String branchname,String commitkey) throws Exception {
        Branch branch = new Branch(branchname,commitkey);
        System.out.println(branch.getBranchName());
        System.out.println(branch.getCommitId());
    }


    public static void main(String args[]) throws Exception {
        HEAD head = new HEAD();
        Branch master = new Branch("master","");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件或文件夹的路径名：");
        String path = input.next();
        testAllKey(path);
        //testSingleKey(path);
        testCommit(path);
        testCommit("D:\\Java\\test.docx");
        testTree(new File(path));
        testGitObject(new File(path));

        testBranch("branch","f191141f83d69e2ca5287bd5881635903fc8e6a7");
        //testBranch("branch", Branch.getBranchName());

        //testBranch("branch",new Branch("branch").checkoutBranch("branch"));
        //testReset("f191141f83d69e2ca5287bd5881635903fc8e6a7", "D:\\Java\\reset");
    }
}