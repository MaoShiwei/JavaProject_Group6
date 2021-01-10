package gitsimulator;

import java.io.File;
import java.util.Scanner;

public class Interaction {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        //unitTest.createUser();
        //System.out.println("git-hash:获取文件或文件夹的哈希值；git-commit:成功提交commit并生成哈希值；git-value:获取文件或文件夹内容；git-log:输出当前的全部提交记录；git-branch:生成并切换分支;git-reset：回滚到指定ID");
        System.out.println("请输入当前项目所在工作路径：");
        String path = input.next();//读取上述指令作用的文件或文件夹
        while(path != null) {
            System.out.println("请输入您的操作：");
            String command = input.next();//读取用户的指令
            if(!command.equals("git-quit")) {
                //根据用户输入的指令进入不同的if分支完成操作
                if (command.equals("git-hash")) {
                    unitTest.testSingleKey(path);//git-hash输出文件或文件夹的哈希值
                } else if (command.equals("git-commit")) {
                    String commitkey = unitTest.testCommit(path);//git-commit输出用户成功提交commit和commitID的提示
                } else if (command.equals("git-value")) {
                    unitTest.testGitObject(new File(path));//git-value输出文件的内容或文件夹的目录
                } else if (command.equals("git-branch")) {
                    unitTest.testBranch();
                }else if(command.equals("git-log")){
                    //System.out.println(commitID);
                    unitTest.testshowCommit();
                } else if (command.equals("git-reset")) {
                    System.out.println("请输入您要回滚的ID：");
                    String ID = input.next();//用户输入要回滚的commitID，完成回滚操作
                    unitTest.testReset(ID, path);//根据commit的hash值，进行reset操作
                    System.out.println("已成功回滚");
                } else {
                    System.out.println("请输入正确的指令");//防止用户输入错误指令，提醒用户
                    System.out.println("您可以尝试输入git-hash,git-commit,git-value,git-branch,git-log,git-reset,git-quit等操作命令");
                }
            }else if(command.equals("git-quit")){
                System.out.println("您将退出程序");
                break;
            }
        }
        input.close();

    }
}
