package gitsimulator;

import java.io.File;
import java.util.Scanner;

public class Interaction {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        //System.out.println("git hash:获取文件或文件夹的哈希值；git commit:成功提交commit并生成哈希值；git value:获取文件或文件夹内容；git branch;git reset");
        System.out.println("请输入一个文件或文件夹的路径：");
        String path = input.next();//读取上述指令作用的文件或文件夹
        System.out.println("请输入您的操作：");
        String command = input.next();//读取用户的指令


        if (command.equals("githash")) {
            unitTest.testSingleKey(path);
        } else if (command.equals("gitcommit")) {
            unitTest.testCommit(path);
        } else if (command.equals("gitvalue")) {
            unitTest.testGitObject(new File(path));
        } else if (command.equals("gitbranch")) {
            System.out.println("请输入新建分支的名字：");
            String BranchName = input.next();
            //unitTest.testBranch(BranchName, new Branch(BranchName).getCommitId()); commitID的获取不太对
        }else if(command.equals("gitcheckout")){
            System.out.println("请输入要切换的分支名：");
            String checkoutName = input.next();
            //new Branch(checkoutName); 切换分支这里有bug
        } else if (command.equals("gitreset")) {
            System.out.println("请输入您要回滚的ID：");
            String ID = input.next();
            unitTest.testReset(ID, "D:\\Java\\reset");//这里也有bug
        } else {
            System.out.println("请输入正确的指令");
        }

    }
}
