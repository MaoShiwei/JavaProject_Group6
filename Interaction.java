package gitsimulator;

import java.io.File;
import java.util.Scanner;

public class Interaction {
    public static void main(String[] args) throws Exception{
        HEAD head = new HEAD();//生成初始化HEAD文件
        Branch master = new Branch("master","");//生成初始化master主分支，内容初始化为空
        Scanner input = new Scanner(System.in);
        //System.out.println("git hash:获取文件或文件夹的哈希值；git commit:成功提交commit并生成哈希值；git value:获取文件或文件夹内容；git branch;git reset");
        System.out.println("请输入一个文件或文件夹的路径：");
        String path = input.next();//读取上述指令作用的文件或文件夹
        while(path != null) {
            System.out.println("请输入您的操作：");
            String command = input.next();//读取用户的指令
            if(!command.equals("git-quit")) {
                //根据用户输入的指令进入不同的if分支完成操作
                if (command.equals("git-hash")) {
                    unitTest.testSingleKey(path);//git-hash输出文件或文件夹的哈希值
                } else if (command.equals("git-commit")) {
                    unitTest.testCommit(path);//git-commit输出用户成功提交commit和commitID的提示
                } else if (command.equals("git-value")) {
                    unitTest.testGitObject(new File(path));//git-value输出文件的内容或文件夹的目录
                } else if (command.equals("git-branch")) {
                    System.out.println("请输入新建分支的名字：");
                    String BranchName = input.next();//通过用户输入一个新分支名字，建立并切换到新的分支
                    Branch branch = new Branch(BranchName, master.getCommitId());//生成一个新的分支
                    head.updateHEAD(BranchName);//切换工作分支，更新HEAD指向新的分支
                    System.out.println("已成功新建并切换到分支："+BranchName);
                }else if(command.equals("git-log")){
                    String commitID = master.getCommitId();
                    unitTest.testshowCommit(commitID);

                } else if (command.equals("git-reset")) {
                    System.out.println("请输入您要回滚的ID：");
                    String ID = input.next();//用户输入要回滚的commitID，完成回滚操作
                    unitTest.testReset(ID, "D:\\Java\\reset");//根据commit的hash值，进行reset操作
                    System.out.println("已成功回滚");
                } else {
                    System.out.println("请输入正确的指令");//防止用户输入错误指令，提醒用户
                    System.out.println("您可以尝试输入git-hash,git-commit,git-value,git-branch,git-log,git-reset等操作命令");
                }
            }else if(command.equals("git-quit")){
                System.out.println("您将退出程序");
                break;
            }
        }
        input.close();

    }
}
