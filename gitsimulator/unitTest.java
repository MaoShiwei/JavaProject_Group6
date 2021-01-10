package gitsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class unitTest{
    public static HEAD head;//生成初始化HEAD文件
    public static Branch nowbranch;

    static {
        try {
            nowbranch = new Branch("master","");//生成初始化master主分支，内容初始化为空
            head = new HEAD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成单个文件夹或文件的hash值测试函数
    public static void testSingleKey(String filepath){
        File file = new File(filepath);
        try{
            if(file.isDirectory()){
                Tree tree = new Tree(file);
                tree.write();
            }
            else if(file.isFile()){
                Blob blob = new Blob(file);
                blob.write(filepath);
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
                testAllKey(filepath + File.separator + fs[i].getName());//对文件夹进行递归遍历
            }
        }
    }

    //生成Commit测试函数
    public static String testCommit(String filepath) throws Exception {
        testAllKey(filepath);
        Log user = new Log();
        String author = user.ReadUserID();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入comment：");
        String comment = input.next();
        String parent = "null";
        Commit commit = new Commit(filepath, parent, author, author, comment);
        commit.ComputeCommit();
        System.out.println("commit: " + commit.GetKey());
        System.out.println(commit.ShowCommitList());
        nowbranch.updateBranch(commit.GetKey());
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
    public static void testBranch() throws Exception {
        System.out.println("请输入新建分支的名字：");
        Scanner input = new Scanner(System.in);
        String BranchName = input.next();//通过用户输入一个新分支名字，建立并切换到新的分支
        Branch branch = new Branch(BranchName, nowbranch.getCommitId());//生成一个新的分支
        System.out.println(branch);
        //branch.checkoutBranch(BranchName,path);
        head.updateHEAD(BranchName);//切换工作分支，更新HEAD指向新的分支
        System.out.println("已成功创建并切换到分支： " + BranchName);
        nowbranch = branch;
    }

    public static void testshowCommit() throws Exception {
        Log log = new Log();
        String commitID = nowbranch.getCommitId();
        log.showCommitParent(commitID);
        System.out.println(log.getCommitlist());
    }

    public static void createUser() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userID = input.next();
        System.out.println("请输入用户名邮箱：");
        String userEmail = input.next();
        Log user = new Log();
        user.WriteUserID(userID,userEmail);
    }

}