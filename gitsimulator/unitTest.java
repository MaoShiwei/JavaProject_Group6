package gitsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class unitTest{
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
    public static String testCommit(String filepath) throws Exception {
        String author = "java";
        String committer = "java";
        String comment = "success!";
        String parent = "null";
        Commit commit = new Commit(filepath, parent, author, committer, comment);
        commit.ComputeCommit();
        commit.write();
        System.out.println("成功提交commit: " + commit.GetKey());
        System.out.println(commit.ShowCommitList());
        return commit.GetKey();//返回commit的hash值
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

    public static void testshowCommit(String commitID) throws Exception {
        Log log = new Log();
        log.showCommitParent(commitID);
        System.out.println(log.getCommitlist());
    }

    public static void main(String args[]) throws Exception {
        HEAD head = new HEAD();//生成初始化HEAD文件
        Branch master = new Branch("master","");//生成初始化master主分支，内容初始化为空
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件或文件夹的路径名：");
        String path = input.next();
        testAllKey(path);//对输入路径进行KeyValue存储，必须在commit之前先进行KeyValue存储
        String commitkey = testCommit(path);//并根据该路径生成第一次commit
        master.updateBranch(commitkey);//更新主分支master的内容
        testAllKey("D:\\Java\\homework1105\\test\\fold1");//测试单个文件的hash值生成
        commitkey = testCommit("D:\\Java\\homework1105\\test\\fold1");//将单个文件作为commit的提交形式提交
        master.updateBranch(commitkey);//更新主分支master的内容
        Branch branch = new Branch("branch", master.getCommitId());//生成一个新的分支
        head.updateHEAD("branch");//切换工作分支，更新HEAD指向新的分支
        testAllKey("D:\\Java\\homework1105");//测试一个新的工作路径
        commitkey = testCommit("D:\\Java\\homework1105");//并作为commit提交
        branch.updateBranch(commitkey);//更新分支的内容
        testReset("50d8b8bd807b3b4ce37a95e793583effd54120", "D:\\Java\\reset");//根据commit的hash值，进行reset操作
        String commitID = master.getCommitId();
        testshowCommit(commitID);
    }
}