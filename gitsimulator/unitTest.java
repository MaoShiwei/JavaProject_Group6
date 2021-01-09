package gitsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class unitTest{
    //���ɵ����ļ��л��ļ���hashֵ���Ժ���
    public static void testSingleKey(String filepath){
        File file = new File(filepath);
        try{
            if(file.isDirectory()){
                Tree tree = new Tree(file);
                tree.write();
                System.out.println("�ļ��е�hashֵΪ��" + tree.GetKey());
            }
            else if(file.isFile()){
                Blob blob = new Blob(file);
                blob.write(filepath);
                System.out.println("�ļ���hashֵΪ��" + blob.GetKey());
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

    //����Commit���Ժ���
    public static String testCommit(String filepath) throws Exception {
        String author = "java";
        String committer = "java";
        String comment = "success!";
        String parent = "null";
        Commit commit = new Commit(filepath, parent, author, committer, comment);
        commit.ComputeCommit();
        commit.write();
        System.out.println("�ɹ��ύcommit: " + commit.GetKey());
        System.out.println(commit.ShowCommitList());
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
        HEAD head = new HEAD();//���ɳ�ʼ��HEAD�ļ�
        Branch master = new Branch("master","");//���ɳ�ʼ��master����֧�����ݳ�ʼ��Ϊ��
        Scanner input = new Scanner(System.in);
        System.out.println("�������ļ����ļ��е�·������");
        String path = input.next();
        testAllKey(path);//������·������KeyValue�洢��������commit֮ǰ�Ƚ���KeyValue�洢
        String commitkey = testCommit(path);//�����ݸ�·�����ɵ�һ��commit
        master.updateBranch(commitkey);//��������֧master������
        testAllKey("D:\\Java\\homework1105\\test\\fold1");//���Ե����ļ���hashֵ����
        commitkey = testCommit("D:\\Java\\homework1105\\test\\fold1");//�������ļ���Ϊcommit���ύ��ʽ�ύ
        master.updateBranch(commitkey);//��������֧master������
        Branch branch = new Branch("branch", master.getCommitId());//����һ���µķ�֧
        head.updateHEAD("branch");//�л�������֧������HEADָ���µķ�֧
        testAllKey("D:\\Java\\homework1105");//����һ���µĹ���·��
        commitkey = testCommit("D:\\Java\\homework1105");//����Ϊcommit�ύ
        branch.updateBranch(commitkey);//���·�֧������
        testReset("50d8b8bd807b3b4ce37a95e793583effd54120", "D:\\Java\\reset");//����commit��hashֵ������reset����
        String commitID = master.getCommitId();
        testshowCommit(commitID);
    }
}