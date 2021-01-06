package gitsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Scanner;

public class Test {
    //生成单个文件夹或文件的hash值测试函数
    public static void testSingleKey(String filepath){
        File file = new File(filepath);//根据路径生成file文件
        try{
            if(file.isDirectory()){//文件夹类型
                Tree tree = new Tree(file);//调用Tree生成hash值
                tree.write();//将KeyValue写入objects存储文件夹
                System.out.println("文件夹的hash值为：" + tree.GetKey());
            }
            else if(file.isFile()){
                Blob blob = new Blob(file);//调用Blob生成hash值
                blob.write(filepath);//将KeyValue写入objects存储文件夹
                System.out.println("文件的hash值为：" + blob.GetKey());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //生成整个文件夹project的hash值测试函数,目前只能测试文件夹，测试单个文件可能会报错
    public static void testAllKey(String filepath){
        testSingleKey(filepath);//需要首先对文件夹路径进行hash值计算
        File dir = new File(filepath);
        File[] fs = dir.listFiles();
        int a = fs.length;
        for(int i = 0; i < a; i++) {
            if(fs[i].isFile()) {//读取到文件类型
                try{
                    Blob blob = new Blob(filepath + File.separator + fs[i].getName());
                    blob.write(filepath + File.separator + fs[i].getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(fs[i].isDirectory()) {//读取到文件夹类型
                try{
                    Tree tree = new Tree(filepath + File.separator + fs[i].getName());
                    tree.write();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                testAllKey(filepath + File.separator + fs[i].getName());//递归调用测试文件夹函数
            }
        }
    }
    //生成Commit测试函数
    public static String testCommit(String filepath) throws Exception {
        String author = "java";//commit作者，可交互
        String committer = "java";//commit提交者，可交互
        String comment = "success!";//commit评论，可交互
        String parent = "null";//初始设定第一次commit，前一个commit不存在
        Commit commit = new Commit(filepath, parent, author, committer, comment);//生成commit
        commit.ComputeCommit();//计算commit的hash值
        commit.write();//将KeyValue写入objects存储文件夹
        System.out.println("成功提交commit: " + commit.GetKey());//提示成功提交commit并显示commit的hash值
        System.out.println(commit.ShowCommitList());//显示当前commit列表
        return commit.GetKey();//返回commit的hash值
    }
    //生成reset测试函数
    public static void testReset(String commitkey, String resetpath) throws Exception {
        Reset reset = new Reset();//生成reset
        reset.Resetcommit(commitkey, resetpath);//根据需要reset的commit值以及文件最终需要恢复到的工作路径进行reset
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
        testSingleKey("D:\\Java\\test.docx");//测试单个文件的hash值生成
        commitkey = testCommit("D:\\Java\\test.docx");//将单个文件作为commit的提交形式提交
        master.updateBranch(commitkey);//更新主分支master的内容
        Branch branch = new Branch("branch", master.getCommitId());//生成一个新的分支
        head.updateHEAD("branch");//切换工作分支，更新HEAD指向新的分支
        testAllKey("D:\\Java\\homework1105");//测试一个新的工作路径
        commitkey = testCommit("D:\\Java\\homework1105");//并作为commit提交
        branch.updateBranch(commitkey);//更新分支的内容
        testReset("7a2035435f15bcf795194defb13f6b18e339a1", "D:\\Java\\reset");//根据commit的hash值，进行reset操作
    }
}
//测试补充说明：KeyValue的产生本质是为了提交commit，在测试commit的时候，不能忘记生成KeyValue，否则会造成无法reset的问题，目前reset还不能恢复单个文件提交的commit
