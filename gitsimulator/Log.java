package gitsimulator;

import java.io.File;

public class Log extends Ref{
    private String commitlist = "";//存储commit内容
    private String branchpath = "D:\\Java\\managebase\\refs\\head";//分支所在路径
    private String objectpath = "D:\\Java\\managebase\\objects";//keyvalue键值对所在路径
    private String logspath = "D:\\Java\\managebase\\logs\\user.txt";//用户名信息所在路径

    public Log(){}

    public void showCommit(String branchname) {//根据分支名显示commit
        String path = branchpath + File.separator + branchname;
        String commithash = Readfile(path);
        showCommitParent(commithash);
    }

    public void showCommitParent(String commithash) {//根据commit的hash值显示commit
        commitlist += "commitID: " + commithash + "\n";
        String path = objectpath + File.separator + commithash;
        String commit = Readfile(path);
        commitlist += commit + "\n";
        String[] commitcontent = commit.split("\n");
        String[] parentID = commitcontent[1].split(" ");
        if (parentID[1].equals("null")){
            commitlist = commitlist;
        } else{
            showCommitParent(parentID[1]);
        }
    }

    public void WriteUserID(String userID, String userEmail) throws Exception {
        String user = userID + " " + userEmail;
        Write (logspath, user);
    }

    public String ReadUserID() { //读取用户ID，邮箱信息
        String userID = Readfile(logspath);
        String[] user = userID.split("\n");
        return user[0];
    }

    public String getCommitlist() {
        return commitlist;
    }
}
