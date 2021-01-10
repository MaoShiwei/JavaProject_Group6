package gitsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class unitTest{
    public static HEAD head;//���ɳ�ʼ��HEAD�ļ�
    public static Branch nowbranch;

    static {
        try {
            nowbranch = new Branch("master","");//���ɳ�ʼ��master����֧�����ݳ�ʼ��Ϊ��
            head = new HEAD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //���ɵ����ļ��л��ļ���hashֵ���Ժ���
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
    //���������ļ���project��hashֵ���Ժ���
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
                testAllKey(filepath + File.separator + fs[i].getName());//���ļ��н��еݹ����
            }
        }
    }

    //����Commit���Ժ���
    public static String testCommit(String filepath) throws Exception {
        testAllKey(filepath);
        Log user = new Log();
        String author = user.ReadUserID();
        Scanner input = new Scanner(System.in);
        System.out.println("������comment��");
        String comment = input.next();
        String parent = "null";
        Commit commit = new Commit(filepath, parent, author, author, comment);
        commit.ComputeCommit();
        System.out.println("commit: " + commit.GetKey());
        System.out.println(commit.ShowCommitList());
        nowbranch.updateBranch(commit.GetKey());
        return commit.GetKey();//����commit��hashֵ
    }

    //����Tree����
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

    //����gitObject����
    public static void testGitObject(File file) throws Exception{
        try{
            if(file.isFile()) {
                GitObject gitObject = new GitObject();
                //��ȡ�ļ�key
                String key = gitObject.GetKey(new FileInputStream(file));
                //���ļ�����д�� ��������GetValue ��ȡ���ļ�
                gitObject.WriteToFile(gitObject.GetValue(key), "D:\\Java\\managebase\\kvstest.txt");
                //ͨ��key��ȡ�ļ�
                String s = gitObject.GetValue(key);
                System.out.println("�ļ��������ǣ�" + s);
                System.out.println("�ļ���value�ǣ�"+new Blob(file).GetType()+" "+new Blob(file).Getvrtype()+" "+new Blob(file).GetKey());
            }
            else if(file.isDirectory()){
                System.out.println("�ļ��е������ǣ�" +new Tree(file).GetValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //����reset���Ժ���
    public static void testReset(String commitkey, String resetpath) throws Exception {
        Reset reset = new Reset();
        reset.Resetcommit(commitkey, resetpath);
    }

    //���ɷ�֧branch���Ժ���
    public static void testBranch() throws Exception {
        System.out.println("�������½���֧�����֣�");
        Scanner input = new Scanner(System.in);
        String BranchName = input.next();//ͨ���û�����һ���·�֧���֣��������л����µķ�֧
        Branch branch = new Branch(BranchName, nowbranch.getCommitId());//����һ���µķ�֧
        System.out.println(branch);
        //branch.checkoutBranch(BranchName,path);
        head.updateHEAD(BranchName);//�л�������֧������HEADָ���µķ�֧
        System.out.println("�ѳɹ��������л�����֧�� " + BranchName);
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
        System.out.println("�������û�����");
        String userID = input.next();
        System.out.println("�������û������䣺");
        String userEmail = input.next();
        Log user = new Log();
        user.WriteUserID(userID,userEmail);
    }

}